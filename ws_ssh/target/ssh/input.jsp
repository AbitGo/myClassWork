<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>input</title>
</head>
<body>

<%
    out.print("请输入下列信息");
%>
</br>
<%
    out.print("输入您的名字:");
%>
<form action="receive.jsp" method="get">
<input name="input_name">
</br>
<input name="sex" type="radio" value="男" checked="checked">男
<input name="sex" type="radio" value="女">女
</br>

<%
    out.print("选择您喜欢的歌手:");
%>
<input type="checkbox" name="songer"  value="张歌手" />张歌手
<input type="checkbox" name="songer" value="李歌手"/>李歌手
<input type="checkbox" name="songer"  value="刘歌手" />刘歌手
<input type="checkbox" name="songer" value="王歌手"/>王歌手

</br>

<button type="submit" >提交</button>
</form>
</body>
</html>
