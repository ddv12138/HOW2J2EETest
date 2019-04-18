window.onload = function () {
    layui.use('table', function () {
        let table = layui.table;
        table.render({
            elem: '#products'
            , height: 312
            , url: 'listProduct' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', title: 'ID', sort: true}
                , {field: 'name', title: '名称'}
                , {field: 'price', title: '价格', sort: true}
                , {field: 'cid', title: 'cid'}
            ]]
        });

    });
};