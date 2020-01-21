<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>更新食物详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="<@spring.url "/layuimini/lib/layui-v2.5.4/css/layui.css"/>" media="all">
</head>
<body>
    <form class="layui-form" style="padding: 20px 30px 0 0;">
        <div class="layui-form-item layui-hide">
            <input type="text" id="id" name="id" placeholder="食物ID" autocomplete="off" class="layui-input">
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">食物名称</label>
            <div class="layui-input-inline">
                <input type="text" id="title" name="title" lay-verify="required" placeholder="类目名称" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">类目名称</label>
<#--            <div class="layui-inline">-->
                <div class="layui-input-inline">
                    <select id="cId" name="cId" lay-verify="">
                        <option value="">请选择</option>
                        <#list categoryList as category>
                            <option value="${category.id}">${category.cName}</option>
                        </#list>
                    </select>
                    <#--<select name="appId" lay-verify="required">
                        <#list app?keys as key>
                            <option value="${key}">${app[key]}</option>
                        </#list>
                    </select>-->
                </div>
<#--            </div>-->
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">食物描述</label>
            <div class="layui-input-inline">
                <input type="text" id="fDesc" name="fDesc" placeholder="类目描述" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">价格（元）</label>
            <div class="layui-input-inline">
                <input type="text" id="price" name="price" placeholder="类目描述" autocomplete="off" class="layui-input">
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
                    url:'/foodService/updateFood',
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