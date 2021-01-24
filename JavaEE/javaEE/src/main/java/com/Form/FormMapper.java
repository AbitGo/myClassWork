package com.Form;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Mapper
public interface FormMapper {
    //插入表单
    public void addFormStyle(Map<String,Object> mForm);

    //使用时间戳进行表单碰撞
    public String formCrash(long mParam);
    //查看用户的表单是否为可编辑或者需要重新提交
    public Map<String,Object> getFormByTimeAndFormCode(Map<String,Object> mParam);
}
