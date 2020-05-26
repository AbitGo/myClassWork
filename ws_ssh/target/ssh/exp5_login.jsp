<%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/18
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>exp5_login</title>
</head>
<body>
<h1>用户登陆</h1>
    <form action="exp5Login" method="get" name="login">
        账户
        <input name="username" type="text">
        <br>
        密码
        <input name="password" type="password">
        <br>
        <button name="submit">登陆</button>
    </form>

    <form action="exp5_register.jsp" method="post" name="register">
        <button name = "register" type="submit">注册</button>
    </form>


</body>
</html>
