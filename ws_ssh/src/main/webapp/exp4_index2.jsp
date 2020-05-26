<%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/13
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:directive.page import="com.action.exp4.User"/>
<%User user = (User)session.getAttribute("user");
    String dateTime = (String)session.getAttribute("loginTime");
    String sessionId = (String)session.getAttribute("sessionId");
    String loginInfo = (String)session.getAttribute("loginInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form action="CreateExcel" method="get">
Hello,this is an index page.<p>
    用户名：<%=user.getUsername()%><p>
    密码：<%=user.getPassword()%><p>
    登陆时间：<%=dateTime %><p>
    SessionId：<%=sessionId %><p>
    验证码状态：<%=loginInfo %><p>

    <button type="submit">生成用户表格</button>
</form>
</body>
</html>

