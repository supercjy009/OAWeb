/**
 * Created by p51 on 2018/5/30.
 */
var table;

var header = [ //表头
    {checkbox: true, fixed: true},
    {title: '序号', type: 'numbers'}
    // , {field: 'id', title: 'ID', width: 0, style: 'display:none;'}
    , {field: 'payDate', title: '支出日期'}
    , {field: 'payUser', title: '支出人'}
    , {field: 'payProject', title: '支出项目'}
    , {field: 'payMoney', title: '支出金额'}
    , {field: 'remark', title: '支出说明'}
    , {field: 'getUser', title: '支付人', templet: '#userTpl'}
    , {field: 'audit', title: '审核', templet: '#auditTpl'}
    , {field: 'settle', title: '结算', templet: '#settleTpl'}
];



layui.use(['table', 'form'], function () {
    var form = layui.form;

    table = layui.table;
    //第一个实例
    table.render({
        id: 'id',
        elem: '#workPayTable',
        skin: 'row',
        // height: 'full-280',
        url: ajaxUri + '/webAjax/workpay/queryAllOrder', //数据接口
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            // layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            limit: 500,
            limits: [500, 100, 50, 20, 10]
        },
        cols: [header],
        done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            // console.log(res);
            //得到当前页码
            // console.log(curr);
            // debugger
            // $('table.layui-table thead tr th:eq(1)').addClass('layui-hide');
            var data = res.data;
            for (var i = 0; i < data.length; i++) {
                var audit = data[i].audit;
                if (audit === '0') {
                    var $checktr = $(".layui-table-view tbody tr[data-index='" + i + "']");
                    $checktr.addClass("changeGray");
                }
            }
            //得到数据总量
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

