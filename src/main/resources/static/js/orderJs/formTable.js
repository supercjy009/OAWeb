/**
 * Created by p51 on 2018/5/30.
 */
var table;
var h1 = [{align: 'center', title: '客户交易登记表', colspan: 22},
    {align: 'center', title: '派单登记表', colspan: 12}];

var header = [ //表头
    {checkbox: true, fixed: true, rowspan: 2}
    , {title: '序号', type: 'numbers', fixed: true, rowspan: 2}
    , {field: 'serviceName', title: '接单客服', width: 100, rowspan: 2}
    , {field: 'getOrderDate', title: '接单时间', width: 110, rowspan: 2}
    , {field: 'customerIm', title: '客户IM', width: 100, rowspan: 2}
    , {field: 'orderNumber', title: '订单编号', width: 100, rowspan: 2}
    , {field: 'deliveryDate', title: '交稿时间', width: 110, rowspan: 2}
    , {field: 'customerMail', title: '客户邮箱', width: 100, rowspan: 2}
    , {field: 'orderContent', title: '订单内容', width: 100, rowspan: 2}
    , {field: 'orderPrice', title: '金额', width: 100, rowspan: 2}
    , {field: 'payState', title: '付款状态', width: 100, rowspan: 2}
    , {field: 'payProgress', title: '付款进度', width: 110, rowspan: 2}
    , {field: 'dueDate', title: '催款日', width: 110, rowspan: 2}
    , {field: 'dueMoney', title: '催款金额', width: 100, rowspan: 2}
    , {field: 'remark', title: '客服备注', width: 100, rowspan: 2}
    , {align: 'center', title: '退款详情', colspan: 6}
    , {field: 'audit', title: '审核', templet: '#auditTpl', rowspan: 2}
    , {field: 'partTimes', title: '兼职QQ', width: 100, rowspan: 2, templet: '<div>{{d.partTimes[0]?d.partTimes[0].partQq:""}}</div>'}
    , {field: 'submitState', title: '交稿状态', width: 100, rowspan: 2}
    , {field: 'partPhone', title: '兼职电话', width: 100, rowspan: 2}
    , {field: 'partAlipay', title: '兼职支付宝', width: 100, rowspan: 2}
    , {field: 'partMoney', title: '稿酬', width: 100, rowspan: 2}
    , {field: 'deduct', title: '应扣', width: 100, rowspan: 2}
    , {field: 'settleDate', title: '结算日', width: 110, rowspan: 2}
    , {field: 'partRemark', title: '客服说明', width: 100, rowspan: 2}
    , {field: 'partAudit', title: '审核', width: 100, rowspan: 2}
    , {field: 'partSettleState', title: '结算状态', width: 100, rowspan: 2}
    , {field: 'financeRemark', title: '财务备注', width: 100, rowspan: 2}
];

var h2 = [{field: 'recommendIm', title: '推荐人IM', width: 100}
    , {field: 'customerAccount', title: '客户账户', width: 100}
    , {field: 'refundWay', title: '退款途径', width: 100}
    , {field: 'refundMoney', title: '退款金额', width: 100}
    , {field: 'refundDate', title: '退款日', width: 110}
    , {field: 'refundRemark', title: '退款说明', width: 100}];


// $.fn.rowspan = function (combined) {
//     return this.each(function () {
//         var that;
//         $('tr', this).each(function (row) {
//             $('td:eq(' + combined + ')', this).filter(':visible').each(function (col) {
//                 if (that != null && $(this).html() == $(that).html()) {
//                     rowspan = $(that).attr("rowSpan");
//                     if (rowspan == undefined) {
//                         $(that).attr("rowSpan", 1);
//                         rowspan = $(that).attr("rowSpan");
//                     }
//                     rowspan = Number(rowspan) + 1;
//                     $(that).attr("rowSpan", rowspan);
//                     $(this).hide();
//                 } else {
//                     that = this;
//                 }
//             });
//         });
//     });
// }

layui.use(['table', 'form'], function () {
    var form = layui.form;

    table = layui.table;
    //第一个实例
    table.render({
        id: 'id',
        elem: '#fromManageTable',
        height: 'full-280',
        // skin: 'row',
        url: ajaxUri + '/webAjax/order/queryAllOrder', //数据接口
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            // layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            limit: 500,
            limits: [500, 100, 50, 20, 10]
        },
        cols: [h1, header, h2],
        done: function (res, curr, count) {
            // $('table.layui-table thead tr th:eq(1)').addClass('layui-hide');
            debugger
            var $title = $(".layui-table-view thead th");
            for (var n = 0; n < $title.length; n++) {
                var $th = $(".layui-table-view thead th:eq(" + n + ")");
                var calssType = (n > 18 && n < 30 || n == 1) ? 'back2' : 'back1';
                $th.addClass(calssType);
            }

            var data = res.data;
            if (!data) {
                return;
            }
            for (var i = 0; i < data.length; i++) {
                var $checktr = $(".layui-table-view tbody tr[data-index='" + i + "']");
                // $checktr.children('td').each(function (j) {  // 遍历 tr 的各个 td
                //     // alert("第" + (i + 1) + "行，第" + (j + 1) + "个td的值：" + $(this).text() + "。");
                //     if (j == 3) {
                //         $(this).attr("rowspan", "2");
                //     }
                // });

                var audit = data[i].audit;
                if (audit === '0') {
                    $checktr.addClass("changeGray");
                }
            }
            //得到数据总量
            console.log(count);
            // $("#fromManageTable").rowspan(0)
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
                    payDate: $('#payDate').val(),
                    getUser: $('#getUser').val(),
                    audit: $('#audit').val(),
                    settle: $('#settle').val()
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


$("#addWorkPay").click(function () {
    //iframe窗
    layer.open({
        type: 2,
        title: '新建任务',
        shadeClose: true,
        shade: 0.8,
        area: ['580px', '60%'],
        content: '/wenanPart/workPayAdd'
    });
});

function submitAudit(flag) {
    var title
    if (flag == 'audit') {
        title = "审核";
    } else {
        title = "结算";
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
            content: '/widget/audit',
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

$("#auditPay").click(function () {
    submitAudit("audit");
});

$("#settlePay").click(function () {
    submitAudit("settle");
});

$("#editWorkPay").click(function () {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        //iframe窗
        layer.open({
            type: 2,
            title: '编辑任务',
            shadeClose: true,
            shade: 0.8,
            area: ['580px', '60%'],
            content: '/wenanPart/workPayAdd',
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

