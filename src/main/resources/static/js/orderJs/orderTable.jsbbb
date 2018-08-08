/**
 * Created by p51 on 2018/5/30.
 */
var header = [ //表头
    {field: 'id', title: '序号', sort: true, fixed: 'left'}
    , {field: 'executor', title: '执行人'}
    , {field: 'completeDate', title: '完成日期'}
    , {field: 'fillFormDate', title: '填表日期'}
    , {field: 'taskName', title: '任务名称'}
    , {field: 'assessStandard', title: '考核标准'}
    , {field: 'taskWeight', title: '任务权重'}
    , {field: 'resourceSupport', title: '资源支持'}
    , {field: 'remark', title: '备注'}
    , {field: 'audit', title: '审批'}
    , {field: 'selfScore', title: '自评得分'}
    , {field: 'leaderScore', title: '上级得分'}
];

layui.use('table', function () {
    var table = layui.table;

    //第一个实例
    table.render({
        elem: '#demo',
        height: 315,
        skin: 'row',
        cellMinWidth: 80,
        url: ajaxUri + '/webAjax/order/queryAllOrder', //数据接口
        page: true, //开启分页
        cols: [header]
    });

});


$("#addTask").click(function () {
    //iframe窗
    layer.open({
        type: 2,
        title: '添加',
        shadeClose: true,
        shade: 0.8,
        area: ['580px', '60%'],
        content: '/wenanPart/orderFormAdd'
    });
});

