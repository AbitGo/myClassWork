<%@ page import="com.action.exp2.Student" %><%--
  Created by IntelliJ IDEA.
  User: Abit
  Date: 2020/5/12
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registerProcess</title>

</head>
<body>

<%
    String name = request.getParameter("name");
    String student_id = request.getParameter("student_id");
    String password = request.getParameter("password");
    String ismale_s = request.getParameter("ismale");
    boolean ismale = false;
    if(ismale_s.equals("1"))
        ismale = true;

    int age = Integer.parseInt(request.getParameter("age"));
    String nativePalce = request.getParameter("nativePalce");
    String info = request.getParameter("info");
    String[] className = request.getParameterValues("classname");

    Student student =new Student();
    student.setName(name);
    student.setAge(age);
    student.setClassName(className);
    student.setInfo(info);
    student.setStudent_id(student_id);
    student.setIsmale(ismale);
    student.setPassword(password);
    student.setNativePalce(nativePalce);
    out.print(student.toString());
%>


</body>
</html>
