package com.action.exp6;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;

public class LoginDriv extends ActionSupport implements ModelDriven {
    private String username;
    private String password;
    private userlogin myUser = new userlogin();
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String login(){
        if(myUser.getUsername()!=null){
            int onlineUserCount = 0;
            ServletContext servletContext = ServletActionContext.getServletContext();
            synchronized (servletContext) {
                try {
                    onlineUserCount = (Integer) servletContext
                            .getAttribute("onlineUserCount");
                } catch (Exception e) {
                }
                servletContext.setAttribute("onlineUserCount",
                        onlineUserCount + 1);
            }
            return "success";
        }else{
            return "input";
        }

    }
    public String logout() {
        //在此处编写退出代码，将在线人数减1后返回登录界面login.jsp。
        ServletContext servletContext = ServletActionContext.
                getServletContext();
        int onlineUserCount = 0;
        synchronized (servletContext) {
            try {
                onlineUserCount = (Integer) servletContext
                        .getAttribute("onlineUserCount");
            } catch (Exception e) {
            }
            servletContext.setAttribute("onlineUserCount",
                    onlineUserCount - 1);
        }

        return "success";

    }

    @Override
    public Object getModel() {
        return myUser;
    }
    public String execute() throws Exception{
        ActionContext context = ActionContext.getContext();
        context.put("user",myUser);
        return SUCCESS;
    }
    public void validate(){
        if(myUser.getUsername()==null || myUser.getUsername().trim().length()==0){
            this.addFieldError("account", "账号不可以为空");
        }else if(!(myUser.getUsername().equals("admin"))&&!(myUser.getPassword().equals("admin"))){
            this.addFieldError("account", "账号或密码输入错误");
        }
        if(myUser.getPassword()==null || myUser.getPassword().length()==0){
            this.addFieldError("password", "密码不可以为空");
        }

    }

}

