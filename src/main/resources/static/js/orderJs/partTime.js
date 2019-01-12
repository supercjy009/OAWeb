/**
 * Created by p51 on 2018/5/30.
 */
var table, ins;

var header = [ //表头
    {type: 'checkbox'}
    , {title: '序号', type: 'numbers'}
    , {field: 'startJobDate', title: '入职日期', width: 110}
    , {field: 'partQq', title: 'QQ', width: 110}
    , {field: 'recentOrderDate', title: '最近接单日', width: 110}
    , {field: 'getOrderNumber', title: '接单数量', width: 90}
    , {field: 'problemRate', title: '问题率', width: 80}
    , {field: 'outSettleCount', title: '待结数', width: 80}
    , {field: 'outDeliveryCount', title: '待交数', width: 80}
    , {field: 'totalReward', title: '总稿酬', width: 80}
    , {field: 'major', title: '专业', width: 100}
    , {field: 'englishLevel', title: '英语水平', width: 110}
    , {field: 'acceptableSubject', title: '可承接科目', width: 110}
    , {field: 'education', title: '学历'}
    , {field: 'school', title: '学校', width: 100}
    , {field: 'age', title: '年龄'}
    , {field: 'partPhone', title: '联系电话', width: 110}
    , {field: 'partAlipay', title: '支付宝', width: 110}
    , {field: 'masterHandStr', title: '在熟手群', width: 90}
    // , {field: 'referrer', title: '有无推荐人', templet: '#auditTpl',width: 110}
    , {field: 'referrer', title: '推荐人', width: 90}
];

setDateRangePicker('recentDate');
layui.use(['table', 'form'], function () {
    var form = layui.form;

    table = layui.table;
    //第一个实例
    ins = table.render({
        id: 'id',
        elem: '#partUserTable',
        title: '兼职信息',
        // skin: 'row',
        height: full,
        url: ajaxUri + '/webAjax/partUser/queryAllOrder', //数据接口
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            // layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            limit: 1000,
            limits: [1000,500, 100, 50, 20, 10]
        },
        cols: [header],
        done: function (res, curr, count) {
            setBttonPermission();
            //如果是异步请求数据方式，res即为你接口返回的信息。
            var data = res.data;
            // for (var i = 0; i < data.length; i++) {
            //     var audit = data[i].audit;
            //     if (audit === '0') {
            //         var $checktr = $(".layui-table-view tbody tr[data-index='" + i + "']");
            //         $checktr.addClass("changeGray");
            //     }
            // }
            //得到数据总量
            console.log(count);
        }
    });

    var $ = layui.$, active = {
        reload: function () {
            // console.log("idddd===" + payDate.val());
            //执行重载
            reloadTable();
        }
    };

    $('.layui-form .layui-btn').on('click', function () {
        var type = $(this).data('type');
        // console.log(active[type]);
        active[type] ? active[type].call(this) : '';
    });

});


// layui.use(['laydate'], function () {
//     console.log("ddddddaaate")
//     var laydate = layui.laydate;
//     //日期
//     laydate.render({
//         elem: '#payDate',
//         type: 'month', //只选年月
//         btns: ['clear', 'confirm']
//     });
// });
$("#deleteEntity").click(function () {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        layer.confirm('确认删除这条兼职人员信息吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            $.ajax({
                type: 'POST',
                url: ajaxUri + '/webAjax/partUser/deleteEntity',
                data: {id: data[0].id},
                complete: function (status) {
                    var str = status.responseJSON;
                    console.log(str.code);
                    if (str.code >= 1) {
                        parent.layer.alert('删除成功');
                        reloadTable();
                    } else {
                        parent.layer.alert('删除失败，服务器异常.');
                    }
                }
            });
        });
    } else if (data.length > 1) {
        layer.alert("删除时不能勾选多条数据");
    } else {
        layer.alert("请先勾选一条数据");
    }
});

$("#addEntity").click(function () {
    //iframe窗
    layer.open({
        type: 2,
        title: '新建',
        shadeClose: true,
        shade: 0.8,
        area: ['50%', '60%'],
        content: '/partTime/partUserAdd'
    });
});

$("#editEntity").click(function () {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        //iframe窗
        layer.open({
            type: 2,
            title: '修改',
            shadeClose: true,
            shade: 0.8,
            area: ['50%', '60%'],
            content: '/partTime/partUserAdd',
            success: function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe' + index];
                // 向子页面的全局函数child传参

                iframe.initEdit(data);
            }
        });
    } else if (data.length > 1) {
        layer.alert("修改时不能勾选多条数据");
    } else {
        layer.alert("请先勾选一条数据");
    }
});

$("#exportExcel").click(function () {
    exportExcel(table, ins);
});

function reloadTable() {
    // var workPayReload = $('#workPayReload');
    table.reload('id', {
        page: {
            curr: 1 //重新从第 1 页开始
        }
        , where: {
            flag: $('#keyWord').val(),
            recentDate: $('#recentDate').val()
            , masterHand: $('#masterHand').val()
            , referrer: $('#referrer').val()
        }
    });
}

