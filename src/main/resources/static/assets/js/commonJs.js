// ajaxUri = "http://47.99.47.49:8080";
ajaxUri = "http://localhost:8080";
workPayInit = false;
var userRoleId = 0;
permissionList = [];
curUserName = "";
partNow = "";
var full = 'full-220';

function getRandomString(len) {
    len = len || 32;
    var $chars = 'abcdefghijklmnopqrstuvwxyz0123456789'; // any letter uppercase / lowercase, any number
    var maxPos = $chars.length;
    var Random = '';
    for (i = 0; i < len; i++) {
        Random += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return Random;
}

function permissionInit() {
    // 初始化
    $.ajax({
        // url: 'tree.json',
        url: ajaxUri + '/webAjax/roleManage/queryPermissionAll',
        dataType: 'json',
        async: false,
        success: function (data) {
            if (data.code === 1) {
                permissionList = data.permission
                curUserName = data.username;
                $("#username").html(curUserName)
            } else if (data.code === -1005) {
                layer.msg("查询此用户无用户组");
            } else {
                layer.msg("查询此用户用户组失败");
            }
        },
        error: function (xml, errstr, err) {
            layer.alert(errstr + '，获取用户组失败！');
        }
    });
}

//左侧菜单权限设置
function setBttonPermission() {
    $("button[name='btn:pms']").each(function () {
        //console.log('val == ' + $(this).attr("value"))
        var liValue = $(this).attr("value");
        if (permissionList.indexOf(liValue) == -1 && permissionList.indexOf("all") == -1) {
            $(this).remove();
        } else {
            $(this).show();
        }
    });
}