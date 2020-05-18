<%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/12
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/redirectServlet" method="post">
    <table width="298" border="0" align="center"
           cellpading="2" cellspacing="1">
        <tr>
            <td align="right">用户名：</td>
            <td align="left"><input type="text" name="username" size="15"></td>
        </tr>
        <tr>
            <td align="right">密&nbsp;&nbsp;码：</td>
            <td><input type="password" name="password" size="15"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="登录">
                &nbsp; <input type="reset" value="取消"></td>
        </tr>
    </table>
</form>
</body>
</html>

