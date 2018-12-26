/**
 * Created by p51 on 2018/5/30.
 */
var table, form;

var header = [ //表头
    {checkbox: true, fixed: true},
    {title: '序号', type: 'numbers'}
    // , {field: 'id', title: 'ID', width: 0, style: 'display:none;'}
    , {field: 'createDate', align: 'center', title: '上传日期'}
    , {field: 'userName', align: 'center', title: '上传人'}
    , {field: 'fileName', title: '文件名'}
    , {field: 'type', align: 'center', title: '分类', templet: '#switchTpl'}
];

layui.use(['laydate'], function () {
    $("#createDate").attr("lay-key", getRandomString);
    var laydate = layui.laydate;
    //日期
    laydate.render({
        elem: '#createDate',
        type: 'month', //只选年月
        btns: ['clear', 'confirm'],
        done: function (value, date) {
            reloadTable(value);
        }
    });
});

layui.use('upload', function () {
    var upload = layui.upload;

    //执行实例
    var uploadInst = upload.render({
        elem: '#addFile' //绑定元素
        , accept: "file"
        , url: ajaxUri + '/webAjax/file/addOrder' //上传接口
        , data: {
            partName: partNow
        }, before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            layer.load(); //上传loading
        }
        , done: function (res) {
            //上传完毕回调
            layer.closeAll('loading'); //关闭loading
            layer.alert("上传成功!");
            reloadTable();
        }
        , error: function () {
            //请求异常回调
            layer.closeAll('loading'); //关闭loading
            layer.alert("上传失败...");
        }
    });
});

layui.use(['table', 'form'], function () {
    form = layui.form;

    table = layui.table;
    //第一个实例
    table.render({
        id: 'id',
        elem: '#fileTable',
        skin: 'line',
        height: full,
        // height: 'full-280',
        url: ajaxUri + '/webAjax/file/queryAllOrder?partName=' + partNow, //数据接口
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
            // $('table.layui-table thead tr th:eq(1)').addClass('layui-hide');
            setBttonPermission();
            var data = res.data;
            //得到数据总量
            console.log(count);
        }
    });

    InitParentMenu();

    var $ = layui.$, active = {
        reload: function () {
            // console.log("idddd===" + payDate.val());
            //执行重载
            table.reload('id', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    createDate: $('#createDate').val(),
                    userId: $('#user').val(),
                    type: $('#type').val()
                }
            });
        }
    };

    $('.layui-form .layui-btn').on('click', function () {
        var type = $(this).data('type');
        // console.log(active[type]);
        active[type] ? active[type].call(this) : '';
    });

    form.on('select(typeSele1)', function (data) { //监听下拉框
        reloadTable();
    })

    form.on('select(userSelect)', function (data) { //监听下拉框
        reloadTable();
    })

    form.on('select(typeSelect)', function (data) {
        // var index = tr.data('index');
        // var tr = selectElem.parents('tr').first();
        // 原始的select
        var selectElem = $(data.elem);
        // layer.tips(selectElem.attr("value"), data.othis);
        var id = selectElem.attr("value");
        console.log(id);
        $.ajax({
            type: 'POST',
            url: ajaxUri + '/webAjax/file/setType',
            data: {
                id: id,
                type: data.value
            },
            complete: function (status) {
                var str = status.responseJSON;
                console.log(str.code);
                if (str.code === 1) {
                    layer.msg('设置成功');
                } else {
                    layer.msg('设置成功，服务器异常.');
                }
            }
        });
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


$("#downFile").click(function () {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length === 1) {
        window.open('/webAjax/file/downloadFile?uri=' + data[0].fileUri)
    } else if (data.length > 1) {
        layer.alert("下载时不能勾选多条数据");
    } else {
        layer.alert("请先勾选一条数据");
    }
});

$("#deleteEntity").click(function () {
    var checkStatus = table.checkStatus('id')
        , data = checkStatus.data;
    if (data.length >= 1) {
        layer.confirm('确认删除文件吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            var ids = [];
            for (var i = 0; i < data.length; i++) {
                ids.push(data[i].id)
            }
            $.ajax({
                type: 'POST',
                url: ajaxUri + '/webAjax/file/deleteEntity',
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
        layer.alert("请先勾选一条数据");
    }
});

function InitParentMenu() {
    //接单客服
    $.ajax({
        url: ajaxUri + '/webAjax/file/selectAllService?partName=' + partNow,
        success: function (result, status, xhr) {
            console.log(result.data);
            var list = result.data;
            layui.each(list, function (index) {
                // var name = list[index].userName ? list[index].userName : list[index].serviceName;
                var name = list[index].userName;
                if (name) {
                    $("#user").append("<option value='" + list[index].serviceId + "'>" + name + "</option>");
                }
            });
            form.render('select');
        }
    });
}

function reloadTable(date) {
    // var workPayReload = $('#workPayReload');
    table.reload('id', {
        page: {
            curr: 1 //重新从第 1 页开始
        }
        , where: {
            createDate: date ? date : $('#createDate').val(),
            userId: $('#user').val(),
            type: $('#type').val()
        }
    });
}

