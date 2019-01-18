/**
 * Created by p51 on 2018/5/30.
 */
var table,ins;

var h1 = [
    {checkbox: true, fixed: 'left', rowspan: 2}
    , {title: '序号', type: 'numbers', rowspan: 2}
    , {field: 'partQq', title: '兼职QQ', rowspan: 2, width: 100}
    , {align: 'center', title: '兼职接单登记表', colspan: 9}
    , {align: 'center', title: '财务结算登记表', colspan: 6}];

var header = [ //表头
    {field: 'createTime', title: '接单日期', width: 110}
    , {field: 'sendServiceName', title: '客服', width: 100}
    , {field: 'partPhone', title: '兼职电话', width: 100}
    , {field: 'partAlipay', title: '支付宝', width: 100}
    , {field: 'orderNumber', title: '订单编号', width: 100}
    , {field: 'deliveryDate', title: '规定交稿时间', width: 145}
    , {field: 'partMoneyFinance', title: '稿酬', width: 100}
    , {field: 'submitState', title: '交稿状态', width: 100, templet: '#submitStateTpl'}
    , {field: 'partUserRemark', title: '兼职说明', width: 100}

    , {field: 'partAuditFinance', title: '审核', width: 100, templet: '#partAuditTpl'}
    , {field: 'partSettleStateFinance', title: '结算状态', width: 100, templet: '#settleTpl'}
    , {field: 'settleDate', title: '结算日', width: 110}
    , {field: 'partRemark', title: '主管说明', width: 100}
    , {field: 'partMoneyReal', title: '实发稿酬', width: 100}
    , {field: 'partFinanceRemark', title: '财务备注', width: 100}
];


layui.use(['table', 'form'], function () {
    var form = layui.form;

    table = layui.table;
    //第一个实例
    ins = table.render({
        id: 'id',
        elem: '#partOrderTable',
        title:'兼职接单登记表',
        // skin: 'row',
        height: full,
        url: ajaxUri + '/webAjax/partOrder/queryAllOrder', //数据接口
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            // layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            limit: 1000,
            limits: [1000,500, 100, 50, 20, 10]
        },
        cols: [h1, header],
        done: function (res, curr, count) {
            setBttonPermission();
            //如果是异步请求数据方式，res即为你接口返回的信息。
            var data = res.data;
            if (!data) {
                return;
            }
            for (var i = 0; i < data.length; i++) {
                var $checktr = $(".layui-table-view tbody tr[data-index='" + i + "']");
                //设置待审核单元格背景颜色
                var audit = data[i].partAuditFinance;
                if (audit == null || audit === '0' || audit === '-1') {
                    $($checktr[0]).addClass("changeGray");
                    $($checktr[1]).addClass("changeGray");
                }
            }
            console.log(count);
        }
    });

    var $ = layui.$, active = {
        reload: function () {
            // console.log("idddd===" + payDate.val());
            //执行重载
            table.reload('id', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    partAuditFinance: $('#partAuditFinance').val(),
                    partSettleStateFinance: $('#partSettleStateFinance').val(),
                    moneyState: $('#moneyState').val(),
                    settleDate: $('#settleDate').val(),
                    keyWord: $('#keyWord').val()
                }
            });
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
    if (data.length >= 1) {
        layer.confirm('确认删除吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var ids = [];
            for (var i = 0; i < data.length; i++) {
                ids.push(data[i].id)
            }
            $.ajax({
                type: 'POST',
                url: ajaxUri + '/webAjax/partOrder/deleteEntity',
                traditional: true,
                data: {ids: ids},
                complete: function (status) {
                    var str = status.responseJSON;
                    console.log(str.code);
                    if (str.code === 1) {
                        parent.layer.alert('删除成功');
                        reloadTable();
                    } else {
                        parent.layer.alert('删除失败，服务器异常.');
                    }
                }
            });
        });
    } else {
        layer.alert("请先勾选数据");
    }
});

$("#addEntity").click(function () {
    editEntity(0);
});

function editEntity(flag) {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        //iframe窗
        layer.open({
            type: 2,
            title: flag == 1 ? '修改' : '添加',
            shadeClose: true,
            shade: 0.8,
            area: ['50%', '60%'],
            content: '/partTime/partOrderEdit',
            success: function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe' + index];
                // 向子页面的全局函数child传参

                iframe.initEdit(data);
            }
        });
    } else if (data.length > 1) {
        layer.alert("不能勾选多条数据");
    } else {
        layer.alert("请先勾选一条数据");
    }
}

$("#editEntity").click(function () {
    editEntity(1);
});

$("#auditPay").click(function () {
    submitAudit("audit");
});

$("#settlePay").click(function () {
    submitAudit("settle");
});

//结算日
$("#setSettleDate").click(function () {
    addjiesuan();
});

//实发稿酬
$("#setRealMoney").click(function () {
    setRealMoney();
});

$("#exportExcel").click(function () {
    exportExcel(table, ins);
});

function submitAudit(flag) {
    var title
    if (flag == 'audit') {
        title = "审核";
    } else if (flag == 'settle') {
        title = "结算"
    }
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length >= 1) {
        //iframe窗
        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            shade: 0.8,
            area: ['240px', '270px'],
            content: '/widget/auditPart',
            success: function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe' + index];
                // 向子页面的全局函数child传参
                iframe.initAudit(data, flag);
            }
        });
    } else {
        layer.alert("请至少勾选一条数据");
    }
}

function addjiesuan() {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length >= 1) {
        //iframe窗
        layer.open({
            type: 2,
            title: '添加结算日',
            shadeClose: true,
            shade: 0.8,
            area: ['30%', '50%'],
            content: '/widget/settleDate',
            success: function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe' + index];
                // 向子页面的全局函数child传参
                iframe.initAudit(data, 11);
            }
        });
    } else {
        layer.alert("请至少勾选一条数据");
    }
}

function setRealMoney() {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        //iframe窗
        layer.open({
            type: 2,
            title: '实发稿酬',
            shadeClose: true,
            shade: 0.8,
            area: ['50%', '60%'],
            content: '/partTime/partOrderMoneyEdit',
            success: function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe' + index];
                // 向子页面的全局函数child传参

                iframe.initEdit(data);
            }
        });
    } else if (data.length > 1) {
        layer.alert("不能勾选多条数据");
    } else {
        layer.alert("请先勾选一条数据");
    }
}

function reloadTable() {
    // var workPayReload = $('#workPayReload');
    table.reload('id', {
        page: {
            curr: 1 //重新从第 1 页开始
        }
        // , where: {
        //     key: {
        //         id: workPayReload.val()
        //     }
        // }
    });
}

