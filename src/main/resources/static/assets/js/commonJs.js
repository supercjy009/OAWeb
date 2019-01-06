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

function setDateRangePicker(el) {
    $('#' + el).daterangepicker(
        {
            showDropdowns: true,
            showWeekNumbers: false, //是否显示第几周
            timePicker: false, //是否显示小时和分钟
            timePickerIncrement: 60, //时间的增量，单位为分钟
            timePicker12Hour: false, //是否使用12小时制来显示时间
            ranges: {
                //'最近1小时': [moment().subtract('hours',1), moment()],
                '昨天': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
                '今天': [moment().startOf('day'), moment()],
                '最近7日': [moment().subtract('days', 6), moment()],
                '最近30日': [moment().subtract('days', 29), moment()],
                '本周': [moment().week(moment().week()).startOf('week').add('days', 1), moment().week(moment().week()).endOf('week').add('days', 1)],
                '本月': [moment().month(moment().month()).startOf('month'), moment().month(moment().month()).endOf('month')]
            },
            opens: 'right', //日期选择框的弹出位置
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary blue',
            cancelClass: 'btn-small',
            format: 'YYYY-MM-DD', //控件中from和to 显示的日期格式
            separator: ' ~ ',
            locale: {
                applyLabel: '选取',
                cancelLabel: '清除',
                fromLabel: '起始时间',
                toLabel: '结束时间',
                customRangeLabel: '自定义',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'],
                firstDay: 1
            }
        }, function (start, end, label) {//格式化日期显示框
            $('#' + el + ' span').html(start.format('YYYY-MM-DD') + ' ~ ' + end.format('YYYY-MM-DD'));
        });
    //清空日期
    $('#' + el).on('cancel.daterangepicker', function (ev, picker) {
        $('#' + el).val('');
        $('#' + el + ' span').html('');
    });
}

function setDeliveryDateRangePicker(el) {
    $('#' + el).daterangepicker(
        {
            showDropdowns: true,
            showWeekNumbers: false, //是否显示第几周
            timePicker: false, //是否显示小时和分钟
            timePickerIncrement: 60, //时间的增量，单位为分钟
            timePicker12Hour: false, //是否使用12小时制来显示时间
            ranges: {
                //'最近1小时': [moment().subtract('hours',1), moment()],
                //'昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
                '今天': [moment().startOf('day'), moment()],
                '明天': [moment().add('days', 1).startOf('day'), moment().add('days', 1).endOf('day')],
                '后天': [moment().add('days', 2).startOf('day'), moment().add('days', 2).endOf('day')],
                '未来一周': [moment().add('days', 1).startOf('day'), moment().add('days', 7).endOf('day')]
            },
            opens: 'right', //日期选择框的弹出位置
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary blue',
            cancelClass: 'btn-small',
            format: 'YYYY-MM-DD', //控件中from和to 显示的日期格式
            separator: ' ~ ',
            locale: {
                applyLabel: '选取',
                cancelLabel: '清除',
                fromLabel: '起始时间',
                toLabel: '结束时间',
                customRangeLabel: '自定义',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'],
                firstDay: 1
            }
        }, function (start, end, label) {//格式化日期显示框
            $('#' + el + ' span').html(start.format('YYYY-MM-DD') + ' ~ ' + end.format('YYYY-MM-DD'));
        });
    //清空日期
    $('#' + el).on('cancel.daterangepicker', function (ev, picker) {
        $('#' + el).val('');
        $('#' + el + ' span').html('');
    });
}