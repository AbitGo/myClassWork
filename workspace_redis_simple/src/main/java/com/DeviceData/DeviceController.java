package com.DeviceData;

import com.IOT.utils.Constant;
import com.IOT.utils.HttpsUtil;
import com.IOT.utils.JsonUtil;
import com.IOT.utils.StreamClosedHttpResponse;
import com.Redis.RedisService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.PubicMethod;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    RedisService redisService;

    //通过唯一硬件标识符添加设备
    @RequestMapping(value = "/Device/addDeviceByIMEI", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String addDeviceByIMEI(@RequestBody String AddJSON) throws Exception {
        JSONObject AddJson = JSONObject.parseObject(AddJSON);
        String DeviceIMEI = AddJson.getString("DeviceIMEI");
        String DeviceName = AddJson.getString("DeviceName");
        String DeviceUser = AddJson.getString("DeviceUser");
        String DeviceCode = "Dev" + PubicMethod.getAcademeCode();

        String DianXinCode = deviceService.getDeviceByDeviceIMEI(DeviceIMEI);

        //System.out.println("DianXinCode1:"+DianXinCode);

        if (DianXinCode != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "添加失败,该设备IMEI已经被注册过");
            jsonObject.put("flag", "0");
            return jsonObject.toString();
        }

        DianXinCode = AddDeviceFromDX(DeviceIMEI);
        //将电信返回的唯一标识符打印出来
        //System.out.println("DianXinCode2:" + DianXinCode);

        //将添加之后的数据，并且生成时间添加至设备中-以免造成空数据
        //首先添加进去时间具有五分钟的时延性，也就是将时间提前至当前时间的 24*3600-5*60
        //86400-300=86100
        Map<String,Object> newTime = new HashMap<>();
        long tempTime = System.currentTimeMillis()/1000-86100;
        newTime.put("updataTime",tempTime);
        newTime.put("lastWakeTime",tempTime);
        redisService.AddHashKeyAndValue("deviceId:"+DianXinCode,newTime);

        if (DianXinCode.equals("null")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "电信平台添加设备失败,请联系管理员");
            jsonObject.put("flag", "0");
            return jsonObject.toString();
        }

        Map<String, Object> param = new HashMap<>();
        param.put("DeviceIMEI", DeviceIMEI);
        param.put("DeviceCode", DeviceCode);
        param.put("DeviceName", DeviceName);
        param.put("DeviceUser", DeviceUser);
        param.put("DianXinCode", DianXinCode);

        JSONObject jsonObject = new JSONObject();

        try {
            int result = deviceService.addDeviceByUserCode(param);
            if (result == 1) {
                jsonObject.put("msg", "添加成功,请查看设备列表");
                jsonObject.put("flag", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //一般发生在数据库错误
            jsonObject.put("msg", "添加失败,程序发生错误");
            jsonObject.put("flag", "0");
        }
        return jsonObject.toString();
    }

    public String AddDeviceFromDX(String DeviceIMEI) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = Constant.accessToken;

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlRegisterDirectConnectedDevice = Constant.REGISTER_DIRECT_CONNECTED_DEVICE;

        //please replace the verifyCode and nodeId and timeout, when you call this interface.
        String verifyCode = DeviceIMEI;
        String nodeId = verifyCode;
        Integer timeout = 0;

        String manufacturerId = "1e1e44510d3141039ddae66bd45c51d5";
        String manufacturerName = "String_lite";
        String deviceType = "DoorLock";
        String model = "JQTX_NBLOCK";
        String protocolType = "CoAP";

        Map<String, Object> paramDeviceInfo = new HashMap<>();
        paramDeviceInfo.put("manufacturerId", manufacturerId);
        paramDeviceInfo.put("manufacturerName", manufacturerName);
        paramDeviceInfo.put("deviceType", deviceType);
        paramDeviceInfo.put("model", model);
        paramDeviceInfo.put("protocolType", protocolType);

        Map<String, Object> paramReg = new HashMap<>();
        paramReg.put("verifyCode", verifyCode.toUpperCase());
        paramReg.put("nodeId", nodeId.toUpperCase());
        paramReg.put("deviceInfo", paramDeviceInfo);
        paramReg.put("timeout", timeout);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseRegisterDirectConnectedDevice = httpsUtil.doPostJsonGetStatusLine(urlRegisterDirectConnectedDevice, header, jsonRequest);

        String responseBody = responseRegisterDirectConnectedDevice.getContent();
        JSONObject jsonObject = JSONObject.parseObject(responseBody);
        String deviceId = (String) jsonObject.get("deviceId");
        //作为返回值
        if (deviceId == null) {
            //该状态值为空,可以直接返回null字符串
            deviceId = "null";
        }
        return deviceId;
    }

    //通过硬件持有者查找设备
    @RequestMapping(value = "/Device/getDeviceByUserCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String getDeviceByUserCode(@RequestBody String getJson) throws Exception {
        JSONObject GetJSON = JSONObject.parseObject(getJson);
        String DeviceUser = GetJSON.getString("DeviceUser");
        int Page = GetJSON.getIntValue("Page");
        int Limit = GetJSON.getIntValue("Limit");

        Map<String, Object> param = new HashMap<>();
        param.put("DeviceUser", DeviceUser);

        PageHelper.startPage(Page, Limit);
        List<Map<String, Object>> results = deviceService.getDeviceByUserCode(param, Page, Limit);
        //设置返回的总记录数
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(results);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Map<String, Object> result : results) {
            JSONObject paramJSON = new JSONObject();
            paramJSON.put("DeviceIMEI", result.get("DeviceIMEI"));
            paramJSON.put("DeviceName", result.get("DeviceName"));
            paramJSON.put("DeviceCode", result.get("DeviceCode"));
            paramJSON.put("DeviceUser", result.get("DeviceUser"));

            //在获取到DianXinCode的同时应该获取该设备对应的数据
            String DianXinCode = (String)result.get("DianXinCode");
            paramJSON.put("DianXinCode", DianXinCode);
            //开始拼接数据
            Map<String,Object> data = redisService.GetHashKeyAndValue("deviceId:" + DianXinCode);
            JSONObject xxx = new JSONObject();
            xxx.put("deviceId", data.get("deviceId"));
            xxx.put("SS_staus", data.get("SS_staus"));
            xxx.put("mc_staus", data.get("mc_staus"));
            xxx.put("shuijing", data.get("shuijing"));
            xxx.put("yangan", data.get("yangan"));
            xxx.put("zhengdong", data.get("zhengdong"));
            xxx.put("voltage", data.get("voltage"));
            xxx.put("dbm", data.get("dbm"));
            xxx.put("temp", data.get("temp"));
            xxx.put("humi", data.get("humi"));
            xxx.put("updataTime", data.get("updataTime"));
            xxx.put("lock_number", data.get("lock_number"));
            xxx.put("lastWakeTime", data.get("lastWakeTime"));
            paramJSON.put("DeviceData", xxx);
            jsonArray.add(paramJSON);
        }
        Long count = pageInfo.getTotal();
        //不满足一页数
        Long page = 0L;
        if (count != 0) {
            page = count / Limit;
            if (count % Limit != 0) {
                page++;
            }
        } else {
            page = 0L;
        }
        jsonObject.put("Msg", jsonArray);
        jsonObject.put("Count", count);
        jsonObject.put("Page", page);
        jsonObject.put("Index", Page);
        jsonObject.put("Limit", Limit);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/Device/getDeviceDataByDainXinCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private String getDeviceDataByDainXinCode(@RequestBody String DeviceTable) throws Exception {
        JSONObject DataJSON = JSONObject.parseObject(DeviceTable);
        String DianXinCode = DataJSON.getString("DianXinCode");
        Map<String,Object> data = redisService.GetHashKeyAndValue("deviceId:" + DianXinCode);

        //开始拼接数据
        JSONObject paramJSON = new JSONObject();
        paramJSON.put("deviceId", data.get("deviceId"));
        paramJSON.put("SS_staus", data.get("SS_staus"));
        paramJSON.put("mc_staus", data.get("mc_staus"));
        paramJSON.put("shuijing", data.get("shuijing"));
        paramJSON.put("yangan", data.get("yangan"));
        paramJSON.put("zhengdong", data.get("zhengdong"));
        paramJSON.put("voltage", data.get("voltage"));
        paramJSON.put("dbm", data.get("dbm"));
        paramJSON.put("temp", data.get("temp"));
        paramJSON.put("humi", data.get("humi"));
        paramJSON.put("updataTime", data.get("updataTime"));
        paramJSON.put("lock_number", data.get("lock_number"));
        //最近唤醒时间即可判断是否已经进行睡眠
        //大于60s睡眠，小于60s则唤醒
        paramJSON.put("lastWakeTime", data.get("lastWakeTime"));

        JSONObject endResults = new JSONObject();
        endResults.put("msg", paramJSON);
        endResults.put("flag","1");
        return paramJSON.toString();
    }

    @RequestMapping(value = "/Device/getDeviceData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private String getDeviceData(@RequestBody String DeviceTable) throws Exception {
        JSONObject DataJSON = JSONObject.parseObject(DeviceTable);
        int DataLen = DataJSON.getInteger("Length");
        JSONArray jsonArray = DataJSON.getJSONArray("DianXinCodeList");
        List<String> DeviceIdList = new ArrayList<>();
        List<Map<String, Object>> DeviceDataList = new ArrayList<>();

        for (int i = 0; i < DataLen; i++) {
            JSONObject param = jsonArray.getJSONObject(i);
            DeviceIdList.add(param.getString("DianXinCode"));

        }
        for (String DeviceId : DeviceIdList) {
            DeviceDataList.add(redisService.GetHashKeyAndValue("deviceId:" + DeviceId));
        }
        //开始拼接数据
        JSONObject result = new JSONObject();

        JSONArray results = new JSONArray();
        for (Map<String, Object> data : DeviceDataList) {
            JSONObject paramJSON = new JSONObject();
            paramJSON.put("deviceId", data.get("deviceId"));
            paramJSON.put("SS_staus", data.get("SS_staus"));
            paramJSON.put("mc_staus", data.get("mc_staus"));
            paramJSON.put("shuijing", data.get("shuijing"));
            paramJSON.put("yangan", data.get("yangan"));
            paramJSON.put("zhengdong", data.get("zhengdong"));
            paramJSON.put("voltage", data.get("voltage"));
            paramJSON.put("dbm", data.get("dbm"));
            paramJSON.put("temp", data.get("temp"));
            paramJSON.put("humi", data.get("humi"));
            paramJSON.put("updataTime", data.get("updataTime"));
            paramJSON.put("lock_number", data.get("lock_number"));
            //最近唤醒时间即可判断是否已经进行睡眠
            //大于60s睡眠，小于60s则唤醒
            paramJSON.put("lastWakeTime", data.get("lastWakeTime"));

            results.add(paramJSON);
        }
        result.put("msg", results);
        return result.toString();
    }

    //该函数是用来删除设备信息的,因为redis中存在着设备的历史数据
    //清空释放内存空间
    @RequestMapping(value = "/Device/DelDeviceData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String DelDeviceData(@RequestBody String getJson) throws Exception {
        JSONObject DataJson = JSONObject.parseObject(getJson);
        String param = DataJson.getString("deviceId");
        //这里的也就是释放内存的
        //deviceId:DianXinCode
        Boolean result = redisService.RemoveHashKeyAndValue("deviceId:" + param);


        //这里需要对result进行判断,是否删除
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", "1");
        jsonObject.put("msg", "已经删除成功");

        return jsonObject.toString();
    }

    //该函数是用来删除设备的
    //14a6162c-5e7c-42fa-9274-9cb6444c30df
    //98765789
    @RequestMapping(value = "/Device/DelDeviceByDeviceCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String DelDeviceByDeviceCode(@RequestBody String dataJson) throws Exception {
        JSONObject DataJson = JSONObject.parseObject(dataJson);
        String DianXinCode = DataJson.getString("DianXinCode");

        String result = DeleteDevice(DianXinCode);
        if (result.equals("ok")) {
            deviceService.deleteDeviceByDianXinCode(DianXinCode);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "0");
            jsonObject.put("msg", "电信平台删除失败,请尝试再次删除或联系管理员,或者已经删除");
            return jsonObject.toString();
        }

        //这里需要对result进行判断,是否删除
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", "1");
        jsonObject.put("msg", "已经删除成功");
        return jsonObject.toString();
    }

    //该函数是用来删除设备的
    //14a6162c-5e7c-42fa-9274-9cb6444c30df
    //98765789
    @RequestMapping(value = "/Device/UpdataDeviceByIMEI", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String UpdataDeviceByIMEI(@RequestBody String dataJson) throws Exception {
        JSONObject DataJson = JSONObject.parseObject(dataJson);
        String DeviceIMEI = DataJson.getString("DeviceIMEI");
        //设备新名字
        String DeviceName = DataJson.getString("DeviceName");
        Map<String, String> param = new HashMap<>();
        param.put("DeviceIMEI", DeviceIMEI);
        param.put("DeviceName", DeviceName);

        JSONObject jsonObject = new JSONObject();
        int result = 0;
        try {
            result = deviceService.updataDeviceByDeviceIMEI(param);
        } catch (Exception e) {
            jsonObject.put("flag", "0");
            jsonObject.put("msg", "该设备已经删除，请刷新设备页面");
        }
        if(result==0){
            jsonObject.put("flag", "0");
            jsonObject.put("msg", "修改设备名称失败，请刷新设备页面");
        }else{
            jsonObject.put("flag", "1");
            jsonObject.put("msg", "修改设备名称成功，请刷新设备页面");
        }
        return jsonObject.toString();
    }

    public String DeleteDevice(String deviceId) throws Exception {

        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        String accessToken = Constant.accessToken;
        String appId = Constant.APPID;

        String urlDeleteDirectConnectedDevice = Constant.DELETE_DIRECT_CONNECTED_DEVICE + "/" + deviceId;

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseDeleteDirectConnectedDevice = httpsUtil.doDeleteWithParasGetStatusLine(urlDeleteDirectConnectedDevice, null, header);

        String result = "null";
        String Response = responseDeleteDirectConnectedDevice.getStatusLine().toString();
        //成功Status Code: 204 No Content
        if (Response.contains("204")) {
            result = "ok";
        }
        return result;
    }


    //执行下发
    @RequestMapping(value = "/Device/OperateLock", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String PerformTheUnlockTask(@RequestBody String GetJSON) throws Exception {
        JSONObject getJson = JSONObject.parseObject(GetJSON);
        Long CreateTime = System.currentTimeMillis() / 1000;
        String serviceId = getJson.getString("serviceId");
        String method = getJson.getString("method");
        String paras = getJson.getString("paras");
        String deviceId = getJson.getString("deviceId");
        //命令下发者
        String DeviceUser = getJson.getString("DeviceUser");

        Map<String, Object> wakeTime = redisService.GetHashKeyAndValue("deviceId:" + deviceId);
        //开始拼接数据

        //最近唤醒时间即可判断是否已经进行睡眠
        //大于60s睡眠，小于60s则唤醒
        Object lastWakeTimeObject = wakeTime.get("lastWakeTime");
        //redis中没该数据
        if (lastWakeTimeObject == null) {
            JSONObject jsonObject = new JSONObject();
            //Command request failed
            jsonObject.put("msg", "该设备处于睡眠状态，请唤醒");
            jsonObject.put("flag", "0");
            return jsonObject.toString();
        }
        long lastWakeTime = (long) lastWakeTimeObject;
        long now = System.currentTimeMillis() / 1000;
        //如果最新唤醒时间小于60s时则不允许开锁
        if (lastWakeTime + 60 < now) {
            JSONObject jsonObject = new JSONObject();
            //Command request failed
            jsonObject.put("msg", "该设备处于睡眠状态，请唤醒");
            jsonObject.put("flag", "0");
            return jsonObject.toString();
        }


        //数据的存储与下发
        Map<String, String> CommandParam = new HashMap<>();
        CommandParam.put("deviceId", deviceId);
        CommandParam.put("serviceId", serviceId);
        CommandParam.put("method", method);
        CommandParam.put("paras", paras);
        String CommandReult = CreateDeviceCommand(CommandParam);

        JSONObject jsonObject = new JSONObject();
        //Command request failed
        if (CommandReult.equals("null")) {
            jsonObject.put("msg", "电信平台未返回下发结果");
            jsonObject.put("flag", "0");
            return jsonObject.toString();
        } else {
            String RecordCode = "Rec"+PubicMethod.getAcademeCode();
            Map<String,Object> param = new HashMap<>();
            param.put("CreateTime",CreateTime);
            param.put("RecordCode",RecordCode);
            param.put("DianXinCode",deviceId);
            param.put("DeviceUser",DeviceUser);
            //缓存数据添加
            deviceService.addDeviceRecord(param);
            jsonObject.put("msg", "执行下发成功,观察设备");
            jsonObject.put("flag", "1");
        }
        return jsonObject.toString();
    }

    public String CreateDeviceCommand(Map<String, String> param) throws Exception {
        //param start
        String deviceId = param.get("deviceId");
        String serviceId = param.get("serviceId");
        String method = param.get("method");
        ObjectNode paras = JsonUtil.convertObject2ObjectNode(param.get("paras"));
        //param start

        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        String accessToken = Constant.accessToken;

        String urlCreateDeviceCommand = Constant.CREATE_DEVICE_CMD;
        String appId = Constant.APPID;
        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

        Integer expireTime = 0;
        Integer maxRetransmit = 3;
        Map<String, Object> paramCommand = new HashMap<>();
        paramCommand.put("serviceId", serviceId);
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);

        Map<String, Object> paramCreateDeviceCommand = new HashMap<>();
        paramCreateDeviceCommand.put("deviceId", deviceId);
        paramCreateDeviceCommand.put("command", paramCommand);
        paramCreateDeviceCommand.put("callbackUrl", callbackUrl);
        paramCreateDeviceCommand.put("expireTime", expireTime);
        paramCreateDeviceCommand.put("maxRetransmit", maxRetransmit);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramCreateDeviceCommand);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse responseCreateDeviceCommand = httpsUtil.doPostJson(urlCreateDeviceCommand, header, jsonRequest);
        String responseBody = httpsUtil.getHttpResponseBody(responseCreateDeviceCommand);
        JSONObject jsonObject = JSONObject.parseObject(responseBody);

        String status = (String) jsonObject.get("status");
        if (status == null) {
            //该状态值为空,可以直接返回null字符串
            status = "null";
        }
        return status;
    }

    //通过硬件持有者查找设备开锁记录
    @RequestMapping(value = "/Device/getDeviceRecordByUserCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String getDeviceRecordByUserCode(@RequestBody String getJson) throws Exception {
        JSONObject GetJSON = JSONObject.parseObject(getJson);
        String DeviceUser = GetJSON.getString("DeviceUser");
        int Page = GetJSON.getIntValue("Page");
        int Limit = GetJSON.getIntValue("Limit");

        PageHelper.startPage(Page, Limit);
        List<Map<String, Object>> results = deviceService.getDeviceRecord(DeviceUser);
        //设置返回的总记录数
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(results);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Map<String, Object> result : results) {
            JSONObject paramJSON = new JSONObject();
            paramJSON.put("CreateTime", result.get("CreateTime"));
            paramJSON.put("DeviceCode", result.get("DeviceCode"));
            paramJSON.put("RecordCode", result.get("RecordCode"));
            paramJSON.put("DeviceUser", result.get("DeviceUser"));
            paramJSON.put("DianXinCode",result.get("DianXinCode"));
            jsonArray.add(paramJSON);
        }
        Long count = pageInfo.getTotal();
        //不满足一页数
        Long page = 0L;
        if (count != 0) {
            page = count / Limit;
            if (count % Limit != 0) {
                page++;
            }
        } else {
            page = 0L;
        }
        jsonObject.put("Msg", jsonArray);
        jsonObject.put("Count", count);
        jsonObject.put("Page", page);
        jsonObject.put("Index", Page);
        jsonObject.put("Limit", Limit);
        return jsonObject.toString();
    }

    //通过硬件持有者查找设备开锁记录
    @RequestMapping(value = "/Device/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public String test(@RequestBody String getJson) throws Exception {
        return "OK";
    }
}
