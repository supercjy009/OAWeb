/**
 * Created by p51 on 2018/6/10.
 */
var payUri = "addOrder";
var form, editData;
var progressList = [];
var partSet = "";

$("#close").click(function () {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
});

function init() {
    $('#datetimepicker').datetimepicker({
        format: 'Y-m-d H:i',
        formatTime: 'H:i',
        formatDate: 'Y-m-d',
        step: 30,

    });
    $.datetimepicker.setLocale('ch');
    layui.use(['form', 'laydate'], function () {
        form = layui.form, laydate = layui.laydate;
        //日期
        // laydate.render({
        //     elem: '#date',
        //     type: 'date', //
        // });
        laydate.render({
            elem: '#progressDate',
            type: 'date', //只选年月日
        });
        laydate.render({
            elem: '#dueDate',
            type: 'date', //只选年月日
        });
        laydate.render({
            elem: '#refundDate',
            type: 'date', //只选年月日
        });

        //各种基于事件的操作，下面会有进一步介绍

        //监听提交
        form.on('submit(formDemo)', function (data) {
            // if (data.field.payDate === null || data.field.payDate.length == 0) {
            //     layer.alert("请选择完成日期");
            // } else {
            data.field.partName = partSet;
            var pdata = {};
            //把表单上的也加进去
            if ($("#progressDate").val() != '' && $("#progressAmount").val().trim() != '') {
                pdata.payDate = $("#progressDate").val();
                pdata.payWay = $("#progressWay").val();
                pdata.moneyType = $("#progressMoneyType").val();
                pdata.amount = $("#progressAmount").val();
                progressList.push(pdata);
            }
            data.field.progressList = progressList;
            data.field.deliveryDate = $("#datetimepicker").val();
            if (editData) {
                data.field.id = editData[0].id;
            }
            var jasondata = JSON.stringify(data.field);
            // layer.alert(JSON.stringify(data.field), {
            //     title: '最终的提交信息'
            // })

            $.ajax({
                type: 'POST',
                url: ajaxUri + '/webAjax/order/' + payUri,
                data: jasondata,
                dataType: "json",
                contentType: "application/json",
                complete: function (status) {
                    var str = status.responseJSON;
                    console.log(str.code);
                    if (str.code == 1) {
                        parent.layer.msg(payUri == 'addOrder' ? '添加成功' : "修改成功");
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.reloadTable();
                        parent.layer.close(index);
                    } else if (str.code == -100) {
                        layer.alert('添加失败，订单号已存在.');
                    } else {
                        layer.alert('添加失败，服务器异常.');
                    }
                }
            });
            // }
            return false;
        });

        if (editData) {
            // alert("aaa"+editData[0].id);
            console.log("editData= ==========" + JSON.stringify(editData[0].customerIm))
            form.val('orderEdit', {
                "customerIm": editData[0].customerIm // "name": "value"
                , "customerMail": editData[0].customerMail
                , "orderNumber": editData[0].orderNumber
                , "deliveryDate": editData[0].deliveryDate
                , "orderContent": editData[0].orderContent
                , "orderPrice": editData[0].orderPrice
                , "payState": editData[0].payState
                , "dueDate": editData[0].dueDate
                , "dueMoney": editData[0].dueMoney
                , "remark": editData[0].remark
                , "recommendIm": editData[0].recommendIm
                , "customerAccount": editData[0].customerAccount
                , "refundMoney": editData[0].refundMoney
                , "refundWay": editData[0].refundWay
                , "refundDate": editData[0].refundDate
                , "refundRemark": editData[0].refundRemark
            })
        }
    });
}

$("#addPayProgress").click(function () {
    var data = {};
    if ($("#progressDate").val() != '') {
        data.payDate = $("#progressDate").val();
    } else {
        layer.alert("请选择付款进度日期");
        return;
    }
    data.payWay = $("#progressWay").val();
    data.progressWayName = $("#progressWay").find("option:selected").text();
    data.moneyType = $("#progressMoneyType").val();
    data.progressMoneyName = $("#progressMoneyType").find("option:selected").text();
    if ($("#progressAmount").val().trim() != '') {
        data.amount = $("#progressAmount").val();
    }
    else {
        layer.alert("请填写付款进度金额");
        return;
    }
    progressList.push(data);
    //iframe窗
    $("#payProgress").after("<div class='layui-form-item' id='payProgress'>\n" +
        "<label class='layui-form-label'><span style='color: red'>*</span> </label>" +
        "<label class='layui-form-label label_warp'>" + data.payDate + "</label>" +
        "<label class='layui-form-label label_warp'>" + data.progressWayName + "</label>" +
        "<label class='layui-form-label label_warp'>" + data.progressMoneyName + "</label>" +
        "<label class='layui-form-label label_warp'>" + data.amount + "</label>");
    $("#progressDate").val('');
    $("#progressAmount").val('');
});

function initEdit(data, part) {
    partSet = part;
    if (data && data != "") {
        editData = data;
        payUri = "editOrder";
    }
    //表单初始赋值
    init();
}