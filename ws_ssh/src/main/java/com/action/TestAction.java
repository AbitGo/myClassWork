package com.action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
    public String test(){
        System.out.println("测试struts2");
        return "test";
    }
}
