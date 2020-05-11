package com.action.jsp_demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //可以通过过滤器的方式进行设置
//        //设置语言编码
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");

        //获得前台输入的账号密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&allowMultiQueries=true";
        String USER = "root";
        String PASSWORD = "123456";
//1.加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.获得数据库链接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = st.executeQuery("select * from user where name='" + username + "' Limit 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4.处理数据库的返回结果(使用ResultSet类)
        String passwd = "";
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }      //这里必须循环遍历
            try {
                passwd = rs.getString("password");//返回一条记录
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (passwd.equals("")) {
            response.getWriter().print("<h1><font color='red'> 不好意思，" + username + "您的的用户名不存在！</font></h1>");
        } else {
            if (passwd.equals(password)) {
                response.getWriter().print("<h1><font color='red'> " + username + "登录成功！</font></h1>");
            } else {
                response.getWriter().print("<h1><font color='red'> " + username + "密码错误！</font></h1>");
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //进行post接口的调用

        doGet(request, response);
    }
}


