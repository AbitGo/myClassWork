package com.pojo;

public class itempojo {
    private String userName;
    private String loginName;
    private String loginPassword;
    private String userEmail;
    private int userRole;

    public itempojo(String userName, String loginName, String loginPassword, String userEmail, int userRole) {
        this.userName = userName;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
}
