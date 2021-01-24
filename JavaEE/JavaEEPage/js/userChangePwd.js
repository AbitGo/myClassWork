//用户修改密码POST请求
$(function () {
    $("#userChangePwd").click(function () {
        var LoginName = $("#fname_changePwd").val();
        var oldLoginPassword = $("#password_old").val();
        var newLoginPassword = $("#password_new").val();
        //进行用户登陆的接口调用
        $.ajax
            ({
                async: true,
                type: "POST",
                url: "http://47.103.135.104:8456/user/userChangePwd",
                dataType: "json",
                data: JSON.stringify({
                    "newLoginPassword": newLoginPassword,
                    "oldLoginPassword": oldLoginPassword,
                    "LoginName": LoginName
                }),
                contentType: "application/json",
                success: function (obj) {
                    alert(obj.msg);
                }
            });

    });

});