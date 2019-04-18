window.onload = function () {
    layui.use('table', function () {
        let table = layui.table;
        table.render({
            elem: '#products'
            , url: 'listProduct' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'check', width: 50, title: '选择', type: 'checkbox'}
                , {field: 'numbers', width: 50, title: '序号', type: 'numbers'}
                , {field: 'id', title: 'ID', width: 300, sort: true}
                , {field: 'name', title: '名称'}
                , {field: 'price', title: '价格', sort: true}
                , {field: 'cid', title: 'cid'}
                , {field: 'opt', width: 200, title: '操作', fixed: 'right', toolbar: '#barDemo'}
            ]]
            , toolbar: true
            , defaultToolbar: ['filter', 'print', 'exports']
            , totalRow: true
        });
        table.on('tool(products)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            let data = obj.data;
            let layEvent = obj.event;

            if (layEvent === 'detail') { //查看
                //do somehing
            } else if (layEvent === 'del') { //删除
                $.ajax({
                    url: "deleteProduct",
                    data: {"id": data.id},
                    complete: function () {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    }
                })
            } else if (layEvent === 'edit') { //编辑
                //do something

                //同步更新缓存对应的值
                obj.update({
                    username: '123'
                    , title: 'xxx'
                });
            }
        });
    });
};