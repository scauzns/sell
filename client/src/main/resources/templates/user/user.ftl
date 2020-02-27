<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<@spring.url "/layuimini/lib/layui-v2.5.4/css/layui.css"/>" media="all">
    <link rel="stylesheet" href="<@spring.url "/layuimini/css/public.css"/>" media="all">

</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <table class="layui-hide" id="userTable" lay-filter="currentTableFilter"></table>
        <script type="text/html" id="foodTableBar">
            <a class="layui-btn layui-btn-radius layui-btn-normal" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-radius layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>
        <script type="text/html" id="img_templet">
            <img class="layui-upload-img" width="120px" src="http://localhost:8060{{d.cover}}"/>
        </script>

    </div>
</div>

<#--设置table每一行的高度，适应列元素的最大高度-->
<style type="text/css">
    .layui-table-cell {
        height: auto;
        line-height: 28px;
    }
</style>

<script src="<@spring.url "/layuimini/lib/layui-v2.5.4/layui.js"/>" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#userTable',
            url: 'http://localhost:8066/userService/user/list',
            cols: [[
                {field: 'name',  title: '昵称'},
                {field: 'phone',title: '手机号码'},
                {field: 'gender',title: '性别', templet: function (d) {
                        if(d.gender === 0){
                            return '男';
                        }else{
                            return '女';
                        }
                    }},
                {field: 'status',title: '状态', templet: function (d) {
                        if(d.status === 0){
                            return '正常';
                        }else{
                            return '已冻结';
                        }
                    }},
                {field: 'cover',title: '图片', templet: '#img_templet'},
                {field: 'createTime',  title: '注册时间'}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var field = data.field;
            //执行搜索重载
            table.reload('foodTable', {
                page: {
                    curr: 1
                }
                , where: field
            });

            return false;
        });


        //监听表头按钮事件
        table.on('toolbar(currentTableFilter)', function(obj) {
            switch(obj.event){
                case 'insert' :
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
                , area: ['500px', '550px']
                , title: '添加食物'
                , content: ['/foodRouter/toAddFood', 'no']
            });
        };

        var toEdit = function (data) {
            layer.open({
                type: 2
                , scrollbar : true
                , skin: 'layui-layer-rim' //加上边框
                , area: ['400px', '460px']
                , title: '编辑'
                , content: ['/foodRouter/toEditFood', 'no']
                , success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    // console.log(body.html()); //得到iframe页的body内容
                    //初始化表单数据的值
                    body.find("#id").val(data.id);
                    body.find("#title").val(data.title);
                    body.find("#fDesc").val(data.fDesc);
                    body.find("#price").val(data.price);
                    body.find("#cId").find("option[value='" + data.cId +"']").attr("selected",true);
                }
            });
        };

        var toDel = function(foodId){
            layer.confirm('确认删除?', function(index){
                $.ajax({
                    url: "http://localhost:8066/foodService/food/del/" + foodId,
                    dataType : "json",
                    contentType : "application/json; charset=utf-8",
                    type : "GET",
                    success : function(data){
                        if(data.code === 0){
                            layer.msg(data.msg);
                            table.reload('foodTable'); //数据刷新
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