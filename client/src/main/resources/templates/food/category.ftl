<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>类目列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<@spring.url "/layuimini/lib/layui-v2.5.4/css/layui.css"/>" media="all">
    <link rel="stylesheet" href="<@spring.url "/layuimini/css/public.css"/>" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <table class="layui-hide" id="categoryTable" lay-filter="currentTableFilter"></table>
    </div>
    <script type="text/html" id="categoryTableBarHead">
        <div class="layui-btn-container">
            <button class="layui-btn data-add-btn" lay-event="insert">添加</button>
            <button class="layui-btn layui-btn-danger data-delete-btn" lay-event="deleteAll">删除</button>
        </div>
    </script>
    <script type="text/html" id="categoryTableBar">
        <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
    </script>
</div>
<script src="<@spring.url "/layuimini/lib/layui-v2.5.4/layui.js"/>" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#categoryTable',
            toolbar: '#categoryTableBarHead',
            url: '/foodService/getCateList',
            cols: [[
                {type: "checkbox",  fixed: "left"},
                {field: 'id',  title: 'ID', sort: true},
                {field: 'cName',  title: '类目名称'},
                {field: 'cDesc',title: '类目名称'},
                {field: 'createTime',  title: '创建时间'},
                {title: '操作', minWidth: 50, templet: '#categoryTableBar', fixed: "right", align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true
        });


        //监听表头按钮事件
        table.on('toolbar(currentTableFilter)', function(obj) {
            switch(obj.event){
                case 'insert' :
                    console.log("新增事件");
                    toInsert();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            console.log(data);
            switch(obj.event){
                case 'edit' :
                    console.log("编辑事件");
                    toEdit(data);
                    break;
                case 'delete' :
                    console.log("删除事件");
                    toDel(data.id);
                    break;
            }
        });

        var toInsert = function(){
            layer.open({
                type: 2
                , skin: 'layui-layer-rim' //加上边框
                , area: ['500px', '250px']
                , title: '添加类目'
                , content: ['/foodService/toInsertCategory', 'no']
            });
        };

        var toEdit = function (data) {
            layer.open({
                type: 2
                , skin: 'layui-layer-rim' //加上边框
                , area: ['500px', '250px']
                , title: '编辑'
                , content: ['/foodService/toEditCategory', 'no']
                , success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    // console.log(body.html()); //得到iframe页的body内容
                    //初始化表单数据的值
                    body.find("#id").val(data.id);
                    body.find("#cName").val(data.cName);
                    body.find("#cDesc").val(data.cDesc);
                }
            });
        };

        var toDel = function(cId){
            layer.confirm('确认删除?', function(index){
                $.ajax({
                    url: "/foodService/delFoodCategory/" + cId,
                    dataType : "json",
                    contentType : "application/json; charset=utf-8",
                    type : "GET",
                    success : function(data){
                        if(data.code === 0){
                            layer.msg(data.msg);
                            table.reload('categoryTable'); //数据刷新
                            layer.close(index); //关闭弹层
                        }else{
                            layer.msg(data.msg);
                        }

                    }
                })
            });
        };
    });
</script>
<script>

</script>

</body>
</html>