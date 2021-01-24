package com.user;

import com.pojo.itempojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserServiceImpl {
    @Autowired
    UserMapper userMapper;
    public Map<String,Object> userLogin(String iLoginName){
        return userMapper.userLogin(iLoginName);
    }

    public int changeUserPwd(Map<String,String> param){
        return userMapper.changeUserPwd(param);
    }



/*    public void inertUserInfo(List<itempojo> mList){
        userMapper.inertUserInfo(mList);
    }*/

}
