<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords"
          content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates"/>
    <link href="css/style.css" rel='stylesheet' type='text/css'/>
</head>
<body>
<form action="${pageContext.request.contextPath}/LoginServlet" method="get" >
    <h1>user Login Form</h1>
    <div class="login-form">
        <div class="close"></div>
        <div class="head-info">
            <label class="lbl-1"> </label>
            <label class="lbl-2"> </label>
            <label class="lbl-3"> </label>
        </div>
        <div class="clear"></div>
        <div class="avtar">
            <img src="images/avtar.png"/>
        </div>
        <form>
            <input name = "username" type="text" class="text" onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'Username';}">

            <input name = "password"type="password" class="text" value="password" onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'password';}">
        </form>
        <div class="submit">
            <input type="submit" value="Submit">
        </div>
    </div>
</form>

</body>
</html>