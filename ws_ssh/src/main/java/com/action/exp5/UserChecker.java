package com.action.exp5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserChecker {
    private DBConn dbConn;
    private Statement statement;
    public int checkUserInfo(String username, String password) throws SQLException {
        dbConn = new DBConn();
        statement = dbConn.getStatement();

        //可以直接写sql语句
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("select * from user where username='" + username + "' Limit 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4.处理数据库的返回结果(使用ResultSet类)
        String passwd = "";
        try {
            rs.next();
            passwd = rs.getString("password");//返回一条记录
        } catch (SQLException e) {
            //账号不存在
            return 0;
        }
        if (passwd.equals(password)) {
            return 1;
        } else {
            return 2;
        }
    }

    public int addAccount(String username, String password) throws SQLException {
        dbConn = new DBConn();
        statement = dbConn.getStatement();
        try {
            statement.execute("insert into user set username ='" + username + "',password='" + password + "'");
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    public int updateAccount(String username,String password) throws SQLException {
        dbConn = new DBConn();
        statement = dbConn.getStatement();
        try {
            statement.executeUpdate("UPDATE user set password ='"+password+"' where username = '"+username+"' ");
        }catch (SQLException e){
            return 0;
        }
        return 1;
    }
}
