package com.user;

import com.pojo.itempojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * @function 使用loginName进行账号的登陆验证
     * @param mLoginName 用户的登录名
     * @returnu
     */
//    public Map<String, Object> userLogin(String mLoginName);
    public Map<String, Object> userLogin(String mParam);
    // public void inertUserInfo(List<itempojo> mData);

    public int changeUserPwd(Map<String,String> param);

    /**
     * @function 通过当前用户登陆时间进行表单碰撞、并且获取当前
     * @param  登录时间以及用户ID
     * @returnu 返回当前符合的表单或者为空
     */
    //public String formCarsh(String loginTime);
}
