/**
 * Created by p51 on 2018/5/30.
 */
var table;

var header = [ //表头
    {type: 'checkbox'}
    , {title: '序号', type: 'numbers'}
    , {field: 'username', title: '用户名', width: 200}
    , {field: 'passwordStr', title: '密码', width: 200}
    , {field: 'roleDescription', title: '分组', width: 200}
];


layui.use(['table', 'form'], function () {
    var form = layui.form;

    table = layui.table;
    //第一个实例
    table.render({
        id: 'id',
        elem: '#userTable',
        // skin: 'row',
        height: full,
        url: ajaxUri + '/webAjax/userManage/queryAllOrder', //数据接口
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            // layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            limit: 1000,
            limits: [1000,500, 100, 50, 20, 10]
        },
        cols: [header],
        done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            var data = res.data;
            //得到数据总量
            console.log(count);
        }
    });

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
        layer.confirm('确认删除这条用户信息吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            $.ajax({
                type: 'POST',
                url: ajaxUri + '/webAjax/userManage/deleteEntity',
                data: {uid: data[0].uid},
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
        content: '/role/userAdd',
        success: function (layero, index) {
            // 获取子页面的iframe
            var iframe = window['layui-layer-iframe' + index];
            // 向子页面的全局函数child传参

            iframe.initEdit();
        }
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
            content: '/role/userAdd',
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

