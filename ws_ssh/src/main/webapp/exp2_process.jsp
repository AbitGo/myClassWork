<%@ page import="com.action.exp2.user" %><%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/12
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>exp2_process.jsp</title>
</head>
<body>

<%
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    user u1 = new user();
    u1.setName(name);
    u1.setPassword(password);
    if(!name.equals("root")){
        out.print("用户名不存在");
    }else{
        if(password.equals("root")){
            out.print("密码正确");
        }else{
            out.print("密码不正确");
        }
    }
%>

</body>
</html>
