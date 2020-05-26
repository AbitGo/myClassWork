<%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/11
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gb2312" %>
<%
    String remoteAddr = request.getRemoteAddr();
    String remoteHost = request.getRemoteHost();
    int serverPort = request.getServerPort();
%>
<html>
<body>
你的IP地址为：<%=remoteAddr %><br>
你的主机名为：<%=remoteHost %><br>
服务器的端口号为：<%=serverPort %><br>
</body>
</html>
