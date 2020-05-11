<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<
<jsp:useBean id="person" class="com.action.pojo.person"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

    <h1>这是17200135123-韦海涛的JSTL作业</h1>
    <c:set var="name" value="韦海涛"></c:set>
    <c:set var="age" value="22"></c:set>

    <c:out value="接下来输出名字与年龄/n"></c:out>
    <c:out value="名字为:${name}年龄是${age}"></c:out>

    <c:out value="超过18了，可以去网吧上网了"></c:out>
</body>
</html>
