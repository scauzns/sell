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
        <table class="layui-hide" id="detailsTable" lay-filter="currentTableFilter"></table>
        <script type="text/html" id="img_templet">
            <img class="layui-upload-img" width="120px" src="http://localhost:8060{{d.foodCover}}"/>
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
            elem: '#detailsTable',
            url: 'http://localhost:8066/orderService/order/queryOrderDetails?orderId=${orderId}' ,
            cols: [[
                {field: 'foodCover',title: '图片', templet: '#img_templet'},
                {field: 'foodTitle',  title: '食物名称'},
                {field: 'sellPrice',title: '售价'},
                {field: 'number',  title: '购买数量'}
            ]],
            limit: 5,
            page: true
        });


    });
</script>
<script>

</script>

</body>
</html>