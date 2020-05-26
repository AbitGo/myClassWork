package com.action.exp6;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.ServletContext;

public class Login extends ActionSupport  {
    private String username;
    private String password;
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
        if(username.equals("admin")&&password.equals("admin")){
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
        int onlineUserCount = 0;
        //在此处编写退出代码，将在线人数减1后返回登录界面login.jsp。
        ServletContext servletContext = ServletActionContext.getServletContext();
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

}
