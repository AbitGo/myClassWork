package com.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int addUser(Map<String,String> param);
    Map<String,String> loginUser(String LoginName);
    List<Map<String,String>> getAllUser();
    int changeUserPwd(Map<String,String> param);
}
