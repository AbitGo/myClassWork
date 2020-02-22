package com.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.PubicMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/User/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String addUser(@RequestBody String addJson){
        JSONObject jsonObject = JSONObject.parseObject(addJson);
        String LoginName = jsonObject.getString("LoginName");
        String LoginPassword = jsonObject.getString("LoginPassword");
        String DeviceUser = "Usr" + PubicMethod.getAcademeCode();
        Map<String,String> param = new HashMap<>();
        param.put("LoginName",LoginName);
        param.put("LoginPassword",LoginPassword);
        param.put("DeviceUser",DeviceUser);

        JSONObject resultJson = new JSONObject();

        try {
            int result = userService.addUser(param);
            if(result == 1){
                resultJson.put("flag","1");
                resultJson.put("msg","注册成功");
            }
        }catch (Exception e) {
            resultJson.put("flag","0");
            resultJson.put("msg","该用户已被注册，请勿重复注册");
        }
        return resultJson.toString();
    }

    @RequestMapping(value = "/User/loginUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String loginUser(@RequestBody String addJson){
        JSONObject jsonObject = JSONObject.parseObject(addJson);
        String LoginName = jsonObject.getString("LoginName");
        String LoginPassword = jsonObject.getString("LoginPassword");

        JSONObject resultJson = new JSONObject();
        Map<String,String> result = userService.loginUser(LoginName);
        if(result==null){
            resultJson.put("flag","0");
            resultJson.put("msg","登陆失败，该账户不存在");
        }else {
            String psd = result.get("LoginPassword");
            if(!psd.equals(LoginPassword)){
                resultJson.put("flag","0");
                resultJson.put("msg","登陆失败，密码不正确");
            }else {
                resultJson.put("flag","1");
                resultJson.put("msg","登陆成功");
                resultJson.put("DeviceUser",result.get("DeviceUser"));
            }
        }
        return resultJson.toString();
    }

    @RequestMapping(value = "/User/changeUserPwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String changeUserPwd(@RequestBody String addJson){
        JSONObject jsonObject = JSONObject.parseObject(addJson);
        String DeviceUser = jsonObject.getString("DeviceUser");
        String NewPwd = jsonObject.getString("NewPwd");
        Map<String,String> param= new HashMap<>();
        param.put("DeviceUser",DeviceUser);
        param.put("NewPwd",NewPwd);

        JSONObject resultJson = new JSONObject();
        int result = userService.changeUserPwd(param);
        if(result==0){
            resultJson.put("flag","0");
            resultJson.put("msg","修改失败，该账户不存在");
        }else {
            resultJson.put("flag","1");
            resultJson.put("msg","修改成功，请重新登陆");
        }
        return resultJson.toString();
    }

    @RequestMapping(value = "/User/getAllUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String getAllUser(@RequestBody String addJson){
        JSONObject jsonObject = JSONObject.parseObject(addJson);
        //获取当前是否为root账户
        String DeviceUser = jsonObject.getString("DeviceUser");

        JSONObject resultJson = new JSONObject();
        if(!DeviceUser.equals("root")){
            resultJson.put("flag","0");
            resultJson.put("msg","该账户非管理员");
            return resultJson.toString();
        }
        JSONArray jsonArray = new JSONArray();
        List<Map<String,String>> results = userService.getAllUser();
        for(Map<String,String> result:results){
            JSONObject innerJson = new JSONObject();
            innerJson.put("LoginName",result.get("LoginName"));
            innerJson.put("DeviceUser",result.get("DeviceUser"));
            jsonArray.add(innerJson);
        }
        resultJson.put("flag","1");
        resultJson.put("msg",jsonArray);
        return resultJson.toString();
    }
}
