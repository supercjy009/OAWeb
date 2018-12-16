function login() {
    //点击登陆按钮
    var uname = $.trim($('#username').val());
    var password = $("#password").val();
    var vcode = $("#vcode").val();
    var rememberMe = $("#rememberMe").is(':checked');
    var tishi = $("#dangers");
    if (uname == "请输入用户名" || uname == "") {
        //alert("请输入用户名！");
        tishi.text("请输入用户名!");
        // layer.alert("请输入用户名!");
        return;
    } else if (password == "") {
        //alert("请输入密码！")
        tishi.text("请输入密码!");
        return;
    } else if (password.length < 6 || password.length > 18) {
        //alert("密码错误，密码长度为6-18位");
        tishi.text("用户名或密码错误，请重新输入！");
        return;
    } else {
//            var str = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/;
//            if(str.test(password)){
        $.ajax({
            type: 'POST',
            url: ajaxUri + '/ajaxlogin',
            data: {'password': password, 'uname': uname, "rememberMe": rememberMe, "vcode": vcode},
            complete: function (status) {
                var str = status.responseText;
                var resJson = status.responseJSON;
                console.log(resJson.status);
                if (resJson.status == 200) {
                    userRoleId = resJson.roleId;
                    permissionList = resJson.permission;
                    location.href = "/index";
                } else {
                    tishi.text(resJson.message);
                }
            }
        });
    }
}


