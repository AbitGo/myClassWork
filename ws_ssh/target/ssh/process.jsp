<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %><%--
  Created by IntelliJ IDEA.
  User: 韦海涛
  Date: 2020/3/1
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome</title>
</head>
<body>
<%
    //获得前台输入的账号密码
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String URL="jdbc:mysql://localhost:3306/test?serverTimezone=UTC&allowMultiQueries=true";
    String USER="root";
    String PASSWORD="123456";
    //1.加载驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    //2.获得数据库链接
    Connection conn= DriverManager.getConnection(URL, USER, PASSWORD);
    //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("select * from user where name='"+username+"' Limit 1");
    //4.处理数据库的返回结果(使用ResultSet类)
    String passwd = "";
    while(rs.next()){      //这里必须循环遍历
        passwd = rs.getString("password");//返回一条记录
    }
    if (passwd.equals("")){
        response.getWriter().print("<h1><font color='red'> 不好意思，"+username+"您的的用户名不存在！</font></h1>");
    }else {
        if(passwd.equals(password)){
            response.getWriter().print("<h1><font color='red'> "+username+"登录成功！</font></h1>");
        } else {
            response.getWriter().print("<h1><font color='red'> "+username+"密码错误！</font></h1>");
        }
    }
%>

</body>
</html>
