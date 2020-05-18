<%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/12
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>input</title>
</head>
<body>
    <h3>从表单向Servlet传递参数</h3>
    <form method="GET" action="paramtest" >
        姓名：<input type="text" name="name"><br>
        年龄：<input type="text" name="age"><br>
        <input type="submit" value="确定" >
        <input type="reset" value="重置" >
    </form>
</body>

