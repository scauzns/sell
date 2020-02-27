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
        <table class="layui-hide" id="orderTable" lay-filter="currentTableFilter"></table>
        <script type="text/html" id="orderTableBar">
            <a class="layui-btn layui-btn-radius layui-btn-normal" lay-event="getDetails">订单详情</a>
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
<script src="<@spring.url "/layuimini/lib/jquery-3.4.1/jquery-3.4.1.min.js"/>" charset="utf-8"></script>
<script>
    function deal(orderId){
        // var orderId = $(button).parent().parent().parent().children('td').eq(0).children('div').text();
        $.ajax({
            url: "http://localhost:8066/orderService/order/updateOrderStatus?orderId=" + orderId +"&status="+3,
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            type : "GET",
            success : function(data){
                if(data.code === 0){
                    layer.msg('该订单已处理！请刷新^_^',{icon: 1});
                    setTimeout(function () {
                        window.location.reload();
                    }, 1500);
                }else{
                    layer.msg(data.msg);
                }

            }
        })
    };
</script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;




        table.render({
            elem: '#orderTable',
            url: 'http://localhost:8066/orderService/order/orderList',
            cols: [[
                {field: 'orderId',  title: '订单ID'},
                {field: 'totalAmount',title: '总金额', width: 100},
                {field: 'payMoney',title: '实付金额', width: 100},
                {field: 'receiver',title: '收货人', width: 100},
                {field: 'mobile',title: '电话', width: 150},
                {field: 'address',title: '收货地址'},
                {field: 'status',title: '状态', width: 120, templet: function (d) {
                        if(d.status === 1){
                            return '未支付';
                        }else if(d.status === 2){
                            return '待处理<button class="layui-btn-sm layui-btn-radius layui-btn-normal" onclick="deal(' + "'" + d.orderId + "'" +')">处理</button>';
                        }else if(d.status === 3){
                            return '待评价';
                        }else if(d.status === 4){
                            return '已完成';
                        }else if(d.status === 5){
                            return '已失效';
                        }
                    }},
                {field: 'updateTime',  title: '交易时间'},
                {title: '操作', minWidth: 50, templet: '#orderTableBar', fixed: "right", align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true
        });



        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var field = data.field;
            //执行搜索重载
            table.reload('orderTable', {
                page: {
                    curr: 1
                }
                , where: field
            });

            return false;
        });


        //监听行工具事件
        table.on('tool(currentTableFilter)', function (obj) {
            var orderId = obj.data.orderId;
            switch(obj.event) {
                case 'getDetails' :
                    getDetails(orderId);
                    break;
            }
        });


        var getDetails = function(orderId){
            layer.open({
                type: 2
                , scrollbar : true
                , skin: 'layui-layer-rim' //加上边框
                , area: ['900px', '600px']
                , title: '订单详情'
                , content: ['/orderRouter/getOrderDetails?orderId='+orderId, 'no']
            });
        };



    });
</script>
<script>

</script>

</body>
</html>