package com.Form;

import com.Mail.SendMailService;
import com.alibaba.fastjson.JSONObject;
import com.util.PubicMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FormController {
    @Autowired
    FormServiceImpl formService;
    @Autowired
    SendMailService mailService;

    @RequestMapping(value = "/formtest/sendEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String sendEmail() throws Exception {
        long startTime = System.currentTimeMillis()/1000;
        //进行一个时间戳的取整
        startTime/=3600;
        startTime*=3600;
        long endTime = startTime+3600*5;
        String formCode = "Fom"+startTime+ PubicMethod.getAcademeCode();
        Date formDate = new Date(System.currentTimeMillis());
        Map<String,Object> cMap = new HashMap<>();
        int formNum =1;
        cMap.put("endTime",endTime);
        cMap.put("startTime",startTime);
        cMap.put("formCode",formCode);
        cMap.put("formDate",formDate);
        cMap.put("formNum",formNum);

        //欢迎语
        String welcome = "";
        if(formNum==1){
            welcome="上午好";
        }else{
            welcome="晚上好";
        }

        //formService.addFormStyle(cMap);
        mailService.sendHtmlMail(welcome,startTime,endTime);
        return "ok";
    }

    /***
     * @function 前端通过调用接口进行表单碰撞
     * @tips POST方法
     * @param searchJson 前端传回的JSON数据
     * @return 主要分为 不可填写、未填写、可修改
     * @throws Exception
     */
    @RequestMapping(value = "/form/getForm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String sendEmail(@RequestBody String searchJson) throws Exception {
        //表单提交的时间戳
        long searchTime = System.currentTimeMillis()/1000;
        JSONObject jsonObject = JSONObject.parseObject(searchJson);
        //获得登录名
        String loginName = jsonObject.getString("loginName");
        String formCode = formService.formCrash(searchTime);
        JSONObject result = new JSONObject();
        if(null==formCode){
            //当没找到表单的时候则为不可填写
            result.put("flag","0");
            result.put("msg","当前无表单可填写");
            return result.toString();
        }else{
            //当前表单可以提交的，分别需要查看是否可编辑还是重新创建提交的
            Map<String,Object> cParam = new HashMap<>();
            cParam.put("loginName",loginName);
            cParam.put("formCode",formCode);
            Map<String,Object> cResult = formService.getFormByTimeAndFormCode(cParam);
            if(null==cResult){
                result.put("flag","1");
                result.put("msg","请仔细填写防疫体温表!");
                result.put("formCode",formCode);
            }else{
                result.put("flag","2");
                result.put("msg","重新提交防疫体温表!");
                //登录ID
                result.put("loginName",loginName);
                //唯一表单信息
                result.put("formCode",formCode);
                //参数1
                result.put("formValue1",cResult.get("formValue1"));
                //参数2
                result.put("formValue2",cResult.get("formValue2"));
                //上次提交时间
                result.put("submitTime",cResult.get("submitTime"));
                //表单唯一记录
                result.put("formRecord",cResult.get("formRecord"));
            }
        }
        return result.toString();
    }

//    /***
//     * @function 作为新表单的插入
//     * @tips POST方法
//     * @return 测试方法
//     * @throws Exception
//     */
//    @RequestMapping(value = "/formtest/formAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    private String userLogin() throws Exception {
//        long startTime = System.currentTimeMillis()/1000;
//        //进行一个时间戳的取整
//        startTime/=3600;
//        startTime*=3600;
//        long endTime = startTime+3600*5;
//        String formCode = "Fom"+startTime+ PubicMethod.getAcademeCode();
//        Date formDate = new Date(System.currentTimeMillis());
//        Map<String,Object> cMap = new HashMap<>();
//        cMap.put("endTime",endTime);
//        cMap.put("startTime",startTime);
//        cMap.put("formCode",formCode);
//        cMap.put("formDate",formDate);
//        formService.addFormStyle(cMap);
//        return "ok";
//    }

}
