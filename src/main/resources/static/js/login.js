function login() {
    //点击登陆按钮
    var uname = $.trim($('#username').val());
    var password = $("#password").val();
    var vcode = $("#vcode").val();
    var rememberMe = $("#rememberMe").is(':checked');
    if (uname == "请输入用户名" || uname == "") {
        //alert("请输入用户名！");
        window.wxc.xcConfirm("请输入用户名!", window.wxc.xcConfirm.typeEnum.warning);
        return;
    } else if (password == "") {
        //alert("请输入密码！")
        window.wxc.xcConfirm("请输入密码!", window.wxc.xcConfirm.typeEnum.warning);
        return;
    } else if (password.length < 6 || password.length > 18) {
        //alert("密码错误，密码长度为6-18位");
        window.wxc.xcConfirm("用户名或密码错误，请重新输入！", window.wxc.xcConfirm.typeEnum.warning);
        return;
    } else {
//            var str = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$/;
//            if(str.test(password)){
        $.ajax({
            type: 'POST',
            url: '/ajaxlogin',
            data: {'password': password, 'uname': uname, "rememberMe": rememberMe, "vcode": vcode},
            complete: function (status) {
                var str = status.responseText;
                //swal({title:"太帅了",text:"登录成功，进入系统！",type:"success"},
                // 	function () {
                // 		location.href = "/userinfo";
                // 	});
                location.href = "/userinfo";
                // location.href = "/index.html";
                // if(status == 200){
                // 	if (result.status != 200) {
                // 		swal("哦豁",result.message,"error");
                // 	} else {
                // 		swal({title:"太帅了",text:"登录成功，进入系统！",type:"success"},
                // 			function () {
                // 				location.href = "/userinfo";
                // 			});
                // 	}
                // }
//                        if(str=="index"){
//                            window.location.href="/index";
//                        }else if(str=="login"){
//                            //alert('用户名或者密码输入有误，请重新输入！');
//                            var txt=  "用户名或密码错误，请重新输入！";
//                            var option = {
//                                title: "错误",
//                                btn: parseInt("0001",2),
//                                icon:"-48px -48px",
//                                onOk: function(){
//                                    window.location.href="/login";
//                                }
//                            }
//                            window.wxc.xcConfirm(txt, "custom", option);
//                            //window.location.href="/login";
//                        }else{
//                            window.location.href="/login";
//                        }
            }
        });
        //}
//            else{
//                //alert("输入密码错误，密码是数字字母组成的字符串");
//                window.wxc.xcConfirm("用户名或密码错误，请重新输入！", window.wxc.xcConfirm.typeEnum.error);
//                return;
//            }
    }
}
function refresh() {
    // $.ajax({
    // 	type: 'POST',
    // 	url: '/ajaxlogin?rnd=' + Math.random(),
    // 	complete: function (status) {
    // 	}
    // }
    var img = document.getElementById("img");
    img.complete = false;
    img.src = "/getGifCode?rnd=" + Math.random();
    // var timer = setInterval(function() {
    // 	if (img.complete) {
    // 		// var sessioninfo = document.getElementById("sessioninfo");
    // 		//alert(document.getElementById("sessioninfocode").text);
    // 		/*<![CDATA[*/
    //
    // 		//var username = /*[[${session._code}]]*/ 'Sebastian';
    //
    // 		/*]]>*/
    // 		alert(username);
    // 		clearInterval(timer)
    // 	}
    // }, 50)
}
// /*刷新机场信息*/
// function refresh() {
// 	var url = "/getGifCode";
// 	$.ajax({
// 		type: "get",
// 		async: true,  //同步请求
// 		url: url,
// 		success: function (dates) {
// 			//alert(dates);
// 			$("#airport").html(dates);//要刷新的div
// 		},
// 	});
//
// }
window.onload = function () {
    refresh();
}

