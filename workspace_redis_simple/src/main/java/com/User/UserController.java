package com.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.util.PubicMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
}
