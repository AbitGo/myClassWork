<%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/24
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=GBK"
         pageEncoding="GBK"%>
<html>

<body>
<p>
    在线人数：
    <s:property value="#application.onlineUserCount"/>
</p>
<p>
    <a href="logout.action">退出</a>
</p>
</body>
</html>

