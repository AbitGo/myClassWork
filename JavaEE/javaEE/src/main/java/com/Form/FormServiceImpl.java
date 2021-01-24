package com.Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FormServiceImpl {
    @Autowired
    FormMapper formMapper;
    public void addFormStyle(Map<String,Object> sMap){
        formMapper.addFormStyle(sMap);
    }
    public String formCrash(long sMap){
        return formMapper.formCrash(sMap);
    }

    public Map<String,Object> getFormByTimeAndFormCode(Map<String,Object> sParam){
        return formMapper.getFormByTimeAndFormCode(sParam);
    }
}
