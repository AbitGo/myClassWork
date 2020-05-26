<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>receive</title>
</head>
<body>

<%
    String name = request.getParameter("input_name");
    out.print("您的姓名:" + name);
%>
<br>

<%
    String sex = request.getParameter("sex");
    out.print("您的性别:" + sex);
%>
<br>

<%
    out.print("您喜欢的歌手是:");
    String[] checkbox = request.getParameterValues("songer");
    for (int i = 0; i < checkbox.length; i++)
        out.print(checkbox[i] + " ");
%>

</body>
</html>
