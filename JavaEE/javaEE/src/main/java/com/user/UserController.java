package com.user;

import com.Redis.RedisService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RedisService redisService;

    /***
     * @function 验证用户登陆信息以及控制页面跳转
     * @tips POST方法
     * @param loginJson 前端传回的JSON数据
     * @return 用户登录验证信息
     * @throws Exception
     */
    @RequestMapping(value = "/user/userLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String userLogin(@RequestBody String loginJson) throws Exception {
        //将JSON数据序列化
        JSONObject jsonObject = JSONObject.parseObject(loginJson);
        //从JSON中获取登录名与登录密码等信息
        String loginName = jsonObject.getString("loginName");
        String loginPassword = jsonObject.getString("loginPassword");
        Map<String, Object> resultUserLogin = userServiceImpl.userLogin(loginName);

        JSONObject returnJson = new JSONObject();
        //没有通过loginName查找到相关用户
        if (null == resultUserLogin) {
            returnJson.put("msg", "未找到该用户，请检查账户是否出入错误。");
            //错误标志
            returnJson.put("flag", "0");
        } else if (loginPassword.equals(resultUserLogin.get("loginPassword"))) {
            //密码正确
            returnJson.put("flag", "1");
            JSONObject innerJson = new JSONObject();
            innerJson.put("userName", resultUserLogin.get("userName"));
            //因为userRole设置的是int值
            innerJson.put("userRole", resultUserLogin.get("userRole").toString());
            returnJson.put("msg", innerJson);
        } else {
            //密码错误
            returnJson.put("msg", "密码输入错误。");
            returnJson.put("flag", "0");
        }
        return returnJson.toString();
    }

    /***
     * @function 实现用户登陆密码的更新
     * @tips POST方法
     * @param changeJson 前端传回的JSON数据
     * @return 是否更新成功
     * @throws Exception
     */
    @RequestMapping(value = "/user/userChangePwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private String userChangePwd(@RequestBody String changeJson) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(changeJson);
        //将新密码、旧密码、登陆名都获取到
        String newLoginPassword = jsonObject.getString("newLoginPassword");
        String oldLoginPassword = jsonObject.getString("oldLoginPassword");
        String cLoginName = jsonObject.getString("LoginName");
        Map<String,String> param = new HashMap<>();
        param.put("newLoginPassword",newLoginPassword);
        param.put("oldLoginPassword",oldLoginPassword);
        param.put("cLoginName",cLoginName);

        JSONObject returnJson =new JSONObject();
        int result = userServiceImpl.changeUserPwd(param);
        if(0 == result){
            returnJson.put("msg","密码更新失败,请查看账号密码是否错误！");
            returnJson.put("flag","0");
        }else {
            returnJson.put("msg","密码更新成功！");
            returnJson.put("flag","1");
        }
        return returnJson.toString();
    }




//    @RequestMapping(value = "/user/userInsert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    private String userLogin() throws Exception {
//
//        String excelFileName = "C:\\Users\\Administrator\\Desktop\\JavaEE大作业\\JavaEE 课程报告所需用户信息附件.xls";
//        List<PoiPojo> param = ExcelReader.readExcel(excelFileName);
//        List<itempojo> itempojos = new ArrayList<>();
//        for(PoiPojo poiPojo:param){
//            String loginName = poiPojo.getID();
//            String UserName = poiPojo.getUserName();
//            String loginPassword = poiPojo.getUserPassword();
//            String userEmail = poiPojo.getID()+"@post.usts.edu.cn";
//            int userRole;
//            if(poiPojo.getTip().equals("学生")){
//                userRole=0;
//            }else{
//                userRole = 1;
//            }
//            itempojos.add(new itempojo(UserName,loginName,loginPassword,userEmail,userRole));
//        }
//        userServiceImpl.inertUserInfo(itempojos);
//        return "1";
//    }

}

