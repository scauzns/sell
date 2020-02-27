<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加食物类目</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="<@spring.url "/layuimini/lib/layui-v2.5.4/css/layui.css"/>" media="all">
</head>
<body>
    <form class="layui-form" style="padding: 20px 30px 0 0;">
        <div class="layui-form-item">
            <label class="layui-form-label">类目名称</label>
            <div class="layui-input-inline">
                <input type="text" name="cName" lay-verify="required" placeholder="类目名称" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-inline">
                <input type="text" name="cDesc" placeholder="类目描述" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formSubmit">确定</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
    <script src="<@spring.url "/layuimini/lib/layui-v2.5.4/layui.js"/>" charset="utf-8"></script>
    <script>
        layui.use('form', function(){
            var $ = layui.jquery,
                layer = layui.layer,
                form = layui.form;

            //监听提交
            form.on('submit(formSubmit)', function(data){
                var index = parent.layer.getFrameIndex(window.name);//获取当前弹出层的层级
                var field = data.field;
                $.ajax({
                    url:'http://localhost:8066/foodService/foodCategory/insert',
                    data : JSON.stringify(field),
                    dataType : "json",
                    contentType : "application/json; charset=utf-8",
                    type : "POST",
                    async: false,
                    success : function(data){
                        if(data.code === 0){
                            layer.msg(data.msg);
                            window.parent.location.reload();//刷新父页面
                            parent.layer.close(index);//关闭弹出层
                        } else {
                            layer.msg(data.msg,{icon: 2});
                        }
                    }
                });
            });
        });
    </script>
</body>

</html>