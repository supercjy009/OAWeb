/**
 * Created by p51 on 2018/5/30.
 */
var table, form;

var h1 = [
    {align: 'center', title: '客户交易登记表', colspan: 22},
    {align: 'center', title: '派单登记表', colspan: 15}];

var header = [ //表头
    {checkbox: true, rowspan: 2},
    {title: '序号', type: 'numbers', rowspan: 2},
    {field: 'serviceName', title: '接单客服', width: 100, rowspan: 2}
    , {field: 'getOrderDate', title: '接单时间', width: 145, rowspan: 2}
    , {field: 'customerIm', title: '客户IM', width: 100, rowspan: 2}
    , {field: 'orderNumber', title: '订单编号', width: 100, rowspan: 2}
    , {field: 'deliveryDate', title: '交稿时间', width: 145, rowspan: 2}
    , {field: 'customerMail', title: '客户邮箱', width: 100, rowspan: 2}
    , {field: 'orderContent', title: '订单内容', width: 100, rowspan: 2}
    , {field: 'orderPrice', title: '金额', width: 100, rowspan: 2}
    , {field: 'payState', title: '付款状态', width: 100, rowspan: 2, templet: '#payStateTpl'}
    , {field: 'payDate', event: 'viewProgress', style: 'cursor: pointer;', title: '付款进度', width: 110, rowspan: 2}
    , {field: 'dueDate', title: '催款日', width: 110, rowspan: 2}
    , {field: 'dueMoney', title: '催款金额', width: 100, rowspan: 2}
    , {field: 'remark', title: '客服备注', width: 100, rowspan: 2}
    , {align: 'center', title: '退款详情', colspan: 6}
    , {field: 'audit', title: '审核', width: 100, templet: '#auditTpl', rowspan: 2}
    , {checkbox: true, rowspan: 2}
    , {field: 'partQq', title: '兼职QQ', width: 100, rowspan: 2}
    , {field: 'submitState', title: '交稿状态', width: 100, rowspan: 2, templet: '#submitStateTpl'}
    , {field: 'partPhone', title: '兼职电话', width: 100, rowspan: 2}
    , {field: 'partAlipay', title: '兼职支付宝', width: 100, rowspan: 2}
    , {field: 'masterHandStr', title: '在熟手群', width: 90, rowspan: 2}
    , {field: 'partMoney', title: '稿酬', width: 100, rowspan: 2}
    , {field: 'deduct', title: '应扣', width: 100, rowspan: 2}
    , {field: 'settleDate', title: '结算日', width: 110, rowspan: 2}
    , {field: 'partRemark', title: '客服说明', width: 100, rowspan: 2}
    , {field: 'partAudit', title: '审核', width: 100, rowspan: 2, templet: '#partAuditTpl'}
    , {field: 'partSettleState', title: '结算状态', width: 100, rowspan: 2, templet: '#settleTpl'}
    , {field: 'financeRemark', title: '财务备注', width: 100, rowspan: 2}
    , {field: 'sendServiceName', title: '派单客服', width: 100, rowspan: 2}
];

var h2 = [{field: 'recommendIm', title: '推荐人IM', width: 100}
    , {field: 'customerAccount', title: '客户账户', width: 100}
    , {field: 'refundWay', title: '退款途径', width: 100}
    , {field: 'refundMoney', title: '退款金额', width: 100}
    , {field: 'refundDate', title: '退款日', width: 110}
    , {field: 'refundRemark', title: '退款说明', width: 100}];


layui.use(['table', 'form'], function () {
    form = layui.form;

    table = layui.table;
    //第一个实例
    var ins = table.render({
        id: 'id',
        elem: '#fromManageTable',
        title: getTitlePart() + '订单',
        height: full,
        // skin: 'row',
        url: ajaxUri + '/webAjax/order/queryAllOrder?partName=' + partNow, //数据接口
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter'],
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            // layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            limit: 1000,
            limits: [1000, 500, 100, 50, 20, 10]
        },
        cols: [h1, header, h2],
        done: function (res, curr, count) {
            setBttonPermission();
            // $('table.layui-table thead tr th:eq(1)').addClass('layui-hide');
            var $title = $(".layui-table-view thead th");
            for (var n = 0; n < $title.length; n++) {
                var $th = $(".layui-table-view thead th:eq(" + n + ")");
                var calssType = (n > 18 && n < 33 || n == 1) ? 'back2' : 'back1';
                $th.addClass(calssType);
            }

            var data = res.data;
            if (!data) {
                return;
            }
            var indexNum = 0;
            var totalNum = 0;
            var clearIndex = false;
            for (var i = 0; i < data.length; i++) {
                var indexTds = $(".layui-table-view tbody tr[data-index='" + i + "'] td[data-field='1']");
                for (var x = 0; x < indexTds.length; x++) {
                    var indexTd = indexTds[x];
                    var ind = $(indexTd).find('div:eq(0)').text();
                    //设置序号值
                    $(indexTd).find('div:eq(0)').text(parseInt(ind) - totalNum);
                }
                if (clearIndex) {
                    indexNum = 0;
                }
                var $checktr = $(".layui-table-view tbody tr[data-index='" + i + "']");

                if (data[i + 1] && data[i].orderNumber == data[i + 1].orderNumber) {//隐藏重复的订单
                    clearIndex = false;
                    totalNum++;
                    indexNum++;
                } else {
                    clearIndex = true;
                    if (indexNum > 0) {
                        for (ci = 0; ci < 2; ci++) {
                            var checkIndextrs = $(".layui-table-view tbody tr[data-index='" + (i - indexNum) + "']");
                            var $checkIndextr = $(checkIndextrs[ci]);
                            // debugger
                            var left = ci == 0 ? 22 : 3;
                            $checkIndextr.children('td').each(function (j) {  // 遍历 tr 的各个 td
                                if (j < left) {
                                    $(this).attr("rowspan", indexNum + 1);
                                }
                            });
                            //隐藏从i 到 i - indexNum之间的行
                            for (var y = 0; y < indexNum; y++) {
                                var nextIndextrs = $(".layui-table-view tbody tr[data-index='" + (i - y) + "']");
                                var $nextIndextr = $(nextIndextrs[ci]);
                                $nextIndextr.children('td').each(function (j) { // 遍历 tr 的各个 td
                                    if (j < left) {
                                        $(this).css("display", "none");
                                    }
                                });
                            }
                        }
                    }
                }

                //设置待审核单元格背景颜色
                var audit = data[i].audit;
                if (audit === '0' || audit === '-1') {
                    $checktr.addClass("changeGray");
                }
            }
            //得到数据总量
            console.log(count);
            // $("#fromManageTable").rowspan(0)
        }
    });

    InitParentMenu();
    table.on('tool(fromManage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'viewProgress') {
            viewProgress(data);
        }
    });

    //选中行
    checkOn('fromManage');

    //头工具栏事件
    table.on('toolbar(fromManage)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'addOrder':
                addOrder();
                break;
            case 'zhipai':
                zhipai();
                break;
            case 'jiaogaoState':
                jiaogaoState();
                break;
            case 'editOrder':
                editOrder();
                break;
            case 'editPartTime':
                editPartTime();
                break;
            case 'addjiesuan':
                addjiesuan();
                break;
            case 'deleteCurrentPart':
                deleteCurrentPart();
                break;
            case 'auditPay':
                auditPay();
                break;
            case 'partTimeAudit':
                partTimeAudit();
                break;
            case 'settlePay':
                settlePay();
                break;
            case 'deleteCurOrder':
                deleteOrder(0);
                break;
            case 'deleteMultiOrder':
                deleteOrder(1);
                break;
            case 'financeRemark':
                financeRemark();
                break;
            case 'export':
                exportExcel(table, ins);
                break;
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

function InitParentMenu() {
    //接单客服
    $.ajax({
        url: ajaxUri + '/webAjax/order/selectAllService?flag=1&partName=' + partNow,
        success: function (result, status, xhr) {
            console.log(result.data);
            var list = result.data;
            layui.each(list, function (index) {
                // var name = list[index].userName ? list[index].userName : list[index].serviceName;
                var name = list[index].userName;
                if (name) {
                    $("#serviceName").append("<option value='" + list[index].serviceId + "'>" + name + "</option>");
                }
            });
            form.render('select');
        }
    });

    //派单客服
    $.ajax({
        url: ajaxUri + '/webAjax/order/selectAllService?flag=2',
        success: function (result, status, xhr) {
            console.log(result.data);
            var list = result.data;
            layui.each(list, function (index) {
                $("#sendServiceName").append("<option value='" + list[index].serviceId + "'>" + list[index].serviceName + "</option>");
            });
            form.render('select');
        }
    });
}

function financeRemark() {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        if (data[0].partQq == null) {
            layer.alert("此订单尚未指派兼职，请确认");
            return;
        }
        var r = data[0].financeRemark ? data[0].financeRemark : '';
        layer.open({
            type: 1 //Page层类型
            ,
            btn: ["确定", "取消"]
            ,
            title: '设置财务备注'
            ,
            skin: 'layui-layer-prompt'
            ,
            content: "<div class=''><input type='text' class='layui-layer-input' value='" + r + "'></div>"
            ,
            yes: function (index, layero) {
                //按钮【按钮一】的回调
                var finRemark = $(layero).find("input[type='text']").val();
                var req = {
                    id: data[0].partId,
                    remark: finRemark
                };
                var jasondata = JSON.stringify(req);
                $.ajax({
                    type: 'POST',
                    contentType: "application/json",
                    data: jasondata,
                    url: ajaxUri + '/webAjax/order/editFinaRemark',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code === 1) {
                            layer.msg('修改成功');
                            reloadTable();
                            layer.closeAll();
                        } else {
                            layer.msg('数据id异常');
                        }
                    },
                    error: function (xml, errstr, err) {
                        layer.alert('服务器异常');
                    }
                });
            }
        });
    } else if (data.length > 1) {
        layer.alert("添加财务备注不能勾选多条数据");
    } else {
        layer.alert("请先勾选一条兼职数据");
    }
}


function deleteOrder(flag) {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length >= 1) {
        if (flag == 0 && data.length > 1) {
            layer.alert("删除当前订单不能勾选多条数据");
            return;
        }
        layer.confirm('确认删除勾选的订单吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var ids = [];
            for (var i = 0; i < data.length; i++) {
                ids.push(data[i].id)
            }
            $.ajax({
                type: 'POST',
                url: ajaxUri + '/webAjax/order/deleteEntity',
                traditional: true,
                data: {ids: ids},
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
    } else {
        layer.alert("请先勾选一条订单");
    }
}

function deleteCurrentPart() {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        if (data[0].partQq == null) {
            layer.alert("此订单尚未指派兼职，请确认");
            return;
        }
        layer.confirm('确认删除这条兼职人员信息吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var deletePart = {};
            deletePart.partId = data[0].partId;
            deletePart.partQq = data[0].partQq;
            deletePart.orderId = data[0].id;
            deletePart.orderNumber = data[0].orderNumber;
            var jasondata = JSON.stringify(deletePart);
            $.ajax({
                type: 'POST',
                url: ajaxUri + '/webAjax/order/deletePart',
                data: jasondata,
                dataType: "json",
                contentType: "application/json",
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
    } else if (data.length > 1) {
        layer.alert("删除当前兼职不能勾选多条数据");
    } else {
        layer.alert("请先勾选一条兼职数据");
    }
}


function viewProgress(data) {
    layer.open({
        type: 2,
        title: false,
        closeBtn: 0,
        shadeClose: true,
        area: ['500px', '400px'],
        content: '/widget/viewPayProgress',
        success: function (layero, index) {
            // 获取子页面的iframe
            var iframe = window['layui-layer-iframe' + index];
            // 向子页面的全局函数child传参
            iframe.initAudit(data.id);
        }
    });
}

function editPartTime() {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        layer.open({
            type: 2,
            title: "修改兼职",
            shadeClose: false,
            shade: 0.8,
            area: ['50%', '60%'],
            content: '/widget/addPartTime',
            success: function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe' + index];
                // 向子页面的全局函数child传参
                iframe.initAudit(data, "edit");
            }
        });
    } else if (data.length > 1) {
        layer.alert("修改时只能勾选一条兼职");
    } else {
        layer.alert("请先勾选一条数据");
    }
}

function zhipai() {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        layer.open({
            type: 2,
            title: "指派兼职",
            shadeClose: false,
            shade: 0.8,
            area: ['60%', '60%'],
            content: '/widget/addPartTime',
            success: function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe' + index];
                // 向子页面的全局函数child传参
                iframe.initAudit(data, "add");
            }
        });
    } else if (data.length > 1) {
        layer.alert("指派时只能勾选一条数据");
    } else {
        layer.alert("请先勾选一条数据");
    }
}

function addOrder() {
    //iframe窗
    var index = layer.open({
        type: 2,
        title: '新建',
        shadeClose: true,
        shade: 0.8,
        area: ['70%', '90%'],
        content: '/wenanPart/orderFormAdd',
        success: function (layero, index) {
            // 获取子页面的iframe
            var iframe = window['layui-layer-iframe' + index];
            // 向子页面的全局函数child传参

            iframe.initEdit("", partNow);
        }
    });
}


function editOrder() {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        //iframe窗
        layer.open({
            type: 2,
            title: '修改',
            shadeClose: true,
            shade: 0.8,
            area: ['70%', '90%'],
            content: '/wenanPart/orderFormAdd',
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
                iframe.initAudit(data, 0);
            }
        });
    } else {
        layer.alert("请至少勾选一条数据");
    }
}

function submitAudit(flag) {
    var title
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (flag == 'audit') {
        title = "订单审核";
    } else if (flag == 'partAudit') {
        title = "兼职审核";
    } else if (flag == 'submit') {
        title = "交稿状态";
        if (data.length > 1) {
            layer.alert("不能勾选多条数据");
            return;
        }
    } else if (flag == 'settle') {
        title = "结算"
    }
    if (data.length >= 1) {
        //iframe窗
        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            shade: 0.8,
            content: '/widget/auditOrder',
            success: function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe' + index];
                // 向子页面的全局函数child传参
                iframe.initAudit(data, flag);
            }
        });
    } else {
        layer.alert("请先勾选一条数据");
    }
}

function auditPay() {
    submitAudit("audit");
}

function partTimeAudit() {
    submitAudit("partAudit");
}

function settlePay() {
    submitAudit("settle");
}

function jiaogaoState() {
    submitAudit("submit");
}


function reloadTable() {
    // var workPayReload = $('#workPayReload');
    table.reload('id', {
        page: {
            curr: 1 //重新从第 1 页开始
        }
        , where: {
            orderDateReq: $('#getOrderDate').val(),
            deliveryDateReq: $('#deliveryDate').val(),
            payState: $('#payState').val(),
            submitState: $('#submitState').val(),
            partInfo: $('#partInfo').val(),
            masterHand: $('#masterHand').val(),
            referrer: $('#referrer').val(),
            serviceId: $('#serviceName').val(),
            sendServiceId: $('#sendServiceName').val(),
            audit: $('#audit').val(),
            partAudit: $('#partAudit').val(),
            partSettleState: $('#partSettleState').val(),
            settleDate: $('#settleDate').val(),
            keyWord: $('#keyWord').val()
        }
    });
}

