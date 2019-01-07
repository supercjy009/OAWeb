/**
 * Created by p51 on 2018/6/10.
 */
var payUri = "addEntity";
var form, editData;

$("#close").click(function () {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
});

$(function () {
    init();
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
            if (editData) {
                data.field.id = editData[0].id;
            }
            data.field.masterHand = data.field.masterHand == 'on';
            var jasondata = JSON.stringify(data.field);
            // layer.alert(JSON.stringify(data.field), {
            //     title: '最终的提交信息'
            // })

            $.ajax({
                type: 'POST',
                url: ajaxUri + '/webAjax/partUser/' + payUri,
                data: jasondata,
                dataType: "json",
                contentType: "application/json",
                complete: function (status) {
                    var str = status.responseJSON;
                    console.log(str.code);
                    if (str.code === 1) {
                        parent.layer.msg(payUri == 'addEntity' ? '添加成功' : "修改成功");
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.reloadTable();
                        parent.layer.close(index);
                    } else {
                        parent.layer.alert(str.message);
                    }
                }
            });

            return false;
        });

        if (editData) {
            $("#passWord").attr("lay-verify", "")
            // alert("aaa"+editData[0].id);
            form.val('payEdit', {
                "partQq": editData[0].partQq // "name": "value"
                , "passWord": editData[0].passWord
                , "major": editData[0].major
                , "englishLevel": editData[0].englishLevel
                , "acceptableSubject": editData[0].acceptableSubject
                , "education": editData[0].education
                , "school": editData[0].school
                , "age": editData[0].age
                , "partPhone": editData[0].partPhone
                , "partAlipay": editData[0].partAlipay
                , "masterHand": editData[0].masterHand
                , "referrer": editData[0].referrer
            })
        }
    });
}

function initEdit(data) {
    editData = data;
    payUri = "editEntity";
    //表单初始赋值
    init();
}