<%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/12
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>


<form action="registerProcess.jsp" method="get">
    输入您的姓名:
    <input name="name">
    </br>
    输入您的学号:
    <input name="student_id">
    </br>
    输入您的登陆密码:
    <input name="password" type="password">
    </br>
    请选择性别:
    <input name="ismale" type="radio" value="1" checked="checked">男
    <input name="ismale" type="radio" value="0">女
    </br>
    输入您的年龄:
    <input name="age" >
    </br>
    输入您的籍贯:
    <input name="nativePalce">
    </br>
    输入您的选修:
    <input type="checkbox" name="classname"  value="语文" />语文
    <input type="checkbox" name="classname" value="数学"/>数学
    <input type="checkbox" name="classname"  value="英语" />英语
    <input type="checkbox" name="classname" value="政治"/>政治
    </br>
    输入您的个人简介:
    <input name="info">
    </br>

    <button name="submit" type="submit">sumit</button>

</form>
</body>
</html>
