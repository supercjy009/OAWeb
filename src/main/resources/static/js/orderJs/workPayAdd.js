/**
 * Created by p51 on 2018/6/10.
 */
var payUri = "addOrder";
var form, editData;
var partSet = "";

$("#close").click(function () {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
});

function init() {
    layui.use(['form', 'laydate'], function () {
        form = layui.form, laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#date',
            type: 'date', //只选年月日
        });

        //各种基于事件的操作，下面会有进一步介绍

        //监听提交
        form.on('submit(formDemo)', function (data) {
            if (data.field.payDate === null || data.field.payDate.length == 0) {
                layer.alert("请选择完成日期");
            } else {
                data.field.partName = partSet;
                if (editData) {
                    data.field.id = editData[0].id;
                }
                var jasondata = JSON.stringify(data.field);
                // layer.alert(JSON.stringify(data.field), {
                //     title: '最终的提交信息'
                // })

                $.ajax({
                    type: 'POST',
                    url: ajaxUri + '/webAjax/workpay/' + payUri,
                    data: jasondata,
                    dataType: "json",
                    contentType: "application/json",
                    complete: function (status) {
                        var str = status.responseJSON;
                        console.log(str.code);
                        if (str.code === 1) {
                            parent.layer.msg(payUri == 'addOrder' ? '添加成功' : "修改成功");
                            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                            parent.reloadTable();
                            parent.layer.close(index);
                        } else {
                            parent.layer.alert('添加失败，服务器异常.');
                        }
                    }
                });
            }
            return false;
        });

        if (editData) {
            // alert("aaa"+editData[0].id);
            form.val('payEdit', {
                "payDate": editData[0].payDate // "name": "value"
                , "payProject": editData[0].payProject
                , "payMoney": editData[0].payMoney
                , "remark": editData[0].remark
                , "getUser": editData[0].getUser
            })
        }
    });
}

function initEdit(data, part) {
    partSet = part;
    if (data && data != "") {
        editData = data;
        payUri = "editOrder";
    }
    //表单初始赋值
    init();
}