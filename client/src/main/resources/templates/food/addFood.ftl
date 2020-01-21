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
        <label class="layui-form-label">食物名称</label>
        <div class="layui-input-inline">
            <input type="text" name="title" lay-verify="required" placeholder="食物名称" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">类目名称</label>
        <div class="layui-input-inline">
            <select name="cId" lay-verify="required">
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
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">食物描述</label>
        <div class="layui-input-inline">
            <input type="text" id="fDesc" name="fDesc" placeholder="食物描述" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">价格（元）</label>
        <div class="layui-input-inline">
            <input type="text" id="price" name="price" placeholder="元" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">封面</label>
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-primary" id="upload_img"><i class="layui-icon">&#xe67c;</i>上传图片</button>
            <!-- 预览区域 -->　
            <div class="layui-upload-list">
                <img class="layui-upload-img" width="100px" id="preShow"/>
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-hide">
        <div class="layui-input-inline">
            <input type="text" id="cover" name="cover" placeholder="封面路径" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button id="formSubmit" class="layui-btn" lay-submit lay-filter="formSubmit">确定</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="<@spring.url "/layuimini/lib/layui-v2.5.4/layui.js"/>" charset="utf-8"></script>
<script>
    layui.use(['form','upload'], function(){
        var $ = layui.jquery,
            layer = layui.layer,
            upload = layui.upload,
            form = layui.form;

        upload.render({
            elem: '#upload_img'
            ,url: '/foodService/uploadFoodImg'
            // ,auto: false //选择文件后不自动上传
            // ,bindAction: '#formSubmit' //指向一个按钮触发上传
            ,choose: function(obj){
                //将每次选择的文件追加到文件队列
                var files = obj.pushFile();

                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function(index, file, result){
                    // console.log(index); //得到文件索引
                    // console.log(file); //得到文件对象
                    // console.log(result); //得到文件base64编码，比如图片
                    $('#preShow').attr('src', result);
                    //obj.resetFile(index, file, '123.jpg'); //重命名文件名，layui 2.3.0 开始新增

                    //这里还可以做一些 append 文件列表 DOM 的操作

                    //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
                    //delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
                });
            }
            ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                layer.load(); //上传loading
            }
            ,done: function(res, index, upload){
                console.log(res);
                if(res.code === 0){
                    $("#cover").val(res.data);
                }
                layer.closeAll('loading'); //关闭loading
            }
            ,error: function(index, upload){
                layer.closeAll('loading'); //关闭loading
            }
        });

        //监听提交
        form.on('submit(formSubmit)', function(data){
            console.log(data.field);
            var index = parent.layer.getFrameIndex(window.name);//获取当前弹出层的层级
            var field = data.field;
            $.ajax({
                url:'/foodService/addFood',
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