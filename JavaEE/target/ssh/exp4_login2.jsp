<%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/13
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String loginInfo = (String) session.getAttribute("loginInfo");
%>
<html>
<head>
    <title>exp4_login2</title>
</head>
<body>
<form action="ServletSession" method="post">
    账号
    <input type="text" name="username"/>
    <br>
    密码
    <input type="password" name="password"/>
    <br>


    <img alt="点击更换验证码"
         src="<%= request.getContextPath() %>/servlet/FunctionServlet"
         id="img" onclick="reloadImg()">
    <br>

    验证码
    <input type="text" name="checkText"/>
    <br>

    <input type="submit" value="提交"/>


</form>
</body>
<script>
    function reloadImg() {
        var time = new Date().getTime();
        document.getElementById("img").src = "<%= request.getContextPath()%>/servlet/FunctionServlet?d=" + time;
    }
</script>
</html>
