package com.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public int addUser(Map<String,String> param){
        return userMapper.addUser(param);
    }

    public Map<String,String> loginUser(String LoginName){
        return this.userMapper.loginUser(LoginName);
    }

    public  List<Map<String,String>> getAllUser(){
        return this.userMapper.getAllUser();
    }
    public int changeUserPwd(Map<String,String> param){
        return this.userMapper.changeUserPwd(param);
    }
}
