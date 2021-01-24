//用户登陆POST请求
$(function () {
    $("#userLogin").click(function () {
        var loginName = $("#fname_login").val();
        var loginPassword = $("#fpassword").val();
        //进行用户登陆的接口调用
        $.ajax
            ({
                async: true,
                type: "POST",
                url: "http://47.103.135.104:8456/user/userLogin",
                dataType: "json",
                data: JSON.stringify({
                    "loginName": loginName,
                    "loginPassword": loginPassword
                }),
                contentType: "application/json",
                success: function (obj) {
                    if (obj.flag == "1") {
                        //将数据存储至localStorage中
                        var　str = window.sessionStorage;
                        str.setItem("loginName",loginName);
                        window.location.href = 'submitInfoPage.html';
                        
                        alert("登陆成功");

                    } else {
                        alert(obj.msg);
                    }
                }
            });
    });

});