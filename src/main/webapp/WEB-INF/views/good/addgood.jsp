<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
    <c:set var="cp" value="<%=basePath%>" />
    <meta charset="utf-8">
    <title>设置我的资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../static/layuiadmin/style/admin.css" media="all">
</head>
<body>
<form class="layui-form" action="">
    <div class="layui-fluid">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">添加商品信息</div>
                    <div class="layui-card-body" pad15>

                        <div class="layui-form" lay-filter="">
                            <div class="layui-form-item">
                                <label class="layui-form-label">* 商品名称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="goodname" value=""  class="layui-input" lay-verify="required">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">* 商品价格</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="price" value=""  class="layui-input" lay-verify="required|number">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">会员价</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="memprice" value=""  class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">* 商品状态</label>
                                <div class="layui-input-inline">
                                    <select name="status" lay-verify="required">
                                        <option value="0" <c:if test="${status==0}">selected="selected"</c:if>>启用</option>
                                        <option value="1" <c:if test="${status==1}">selected="selected"</c:if>>停用</option>
                                    </select>
                                </div>
                                <%-- <div class="layui-form-mid layui-word-aux">当前角色不可更改为其它角色</div>--%>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">* 商品地址</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="address" value=""  class="layui-input" lay-verify="required">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">售出时间</label>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="outtime" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                           <%-- <div class="layui-form-item">
                                <label class="layui-form-label">商品分类</label>
                                <div class="layui-input-inline">
                                    <div class="layui-unselect layui-form-select downpanel">
                                        <div class="layui-select-title">
                                            <span class="layui-input layui-unselect" id="treeclass">选择栏目</span>
                                            <input type="hidden" name="goodsortid" value="0">
                                            <i class="layui-edge"></i>
                                        </div>
                                        <dl class="layui-anim layui-anim-upbit">
                                            <dd>
                                                <ul id="classtree"></ul>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </div>--%>
                            <div class="layui-form-item">
                                <label class="layui-form-label">* 商品分类</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="goodsort" name="goodsort" value=""  class="layui-input" lay-verify="required"  readonly="readonly">
                                    <input type="text" id="goodsortID" name="goodsortID" value=""  class="layui-input" style="display: none">
                                    <ul id="layUITree" style="display:block"></ul>
                                </div>
                            </div>
                             <div class="layui-form-item">
                                 <label class="layui-form-label">商品图片</label>
                                 <div class="layui-input-inline">
                                     <input name="avatar" lay-verify="required" id="picture" placeholder="图片地址" value="http://cdn.layui.com/avatar/168.jpg" class="layui-input">
                                 </div>
                                 <div class="layui-input-inline layui-btn-container" style="width: auto;">
                                     <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                                         <i class="layui-icon">&#xe67c;</i>上传图片
                                     </button>
                                     <button class="layui-btn layui-btn-primary" layadmin-event="avartatPreview">查看图片</button >
                                 </div>
                             </div>

                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit lay-filter="goodinfo">确认添加</button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="../../../static/jquery/jquery-3.3.1.min.js"></script>
<script src="../../../static/layuiadmin/layui/layui.js"></script>
<script>
    $.ajax({
        async: false,//同步
        cache: false,
        url: "${cp}/good/getLayUITree",
        type: "post",
        datatype:'json',
        contentType : 'application/json',
        error: function () {
            alert('ajax请求失败');
        },
        success: function (data) {
            if (data.code == '200') {
                layui.use('tree', function () {
                    layui.tree({
                        elem: '#layUITree',
                        nodes: [data.data],
                        click: function (node) {
                            if (node.id != '-1') {
                                $("#goodsort").val(node.name);
                                $("#goodsortID").val(node.id);
                            }
                        }
                    });
                });
            } else {
                alert(data.msg);
            }
        }
    });
    layui.config({
        base: '../../../static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'set']);

    layui.use(['form','laydate','tree'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,laydate = layui.laydate
            ,tree = layui.tree;
       /* tree({
            elem: "#classtree"
            ,
            nodes: [{
                name: '常用文件夹',
                id: 1,
                alias: 'changyong',
                children: [{name: '所有未读', id: 11, alias: 'weidu'}, {
                    name: '置顶邮件',
                    id: 12
                }, {name: '标签邮件', id: 13}]
            }, {
                name: '我的邮箱',
                id: 2,
                spread: true,
                children: [{
                    name: 'QQ邮箱',
                    id: 21,
                    spread: true,
                    children: [{
                        name: '收件箱',
                        id: 211,
                        children: [{name: '所有未读', id: 2111}, {name: '置顶邮件', id: 2112}, {name: '标签邮件', id: 2113}]
                    }, {name: '已发出的邮件', id: 212}, {name: '垃圾邮件', id: 213}]
                }, {
                    name: '阿里云邮',
                    id: 22,
                    children: [{name: '收件箱', id: 221}, {name: '已发出的邮件', id: 222}, {name: '垃圾邮件', id: 223}]
                }]
            }]
            ,
            click: function (node) {
                var $select = $($(this)[0].elem).parents(".layui-form-select");
                $select.removeClass("layui-form-selected").find(".layui-select-title span").html(node.name).end().find("input:hidden[name='goodsortid']").val(node.id);
            }
        });
        $(".downpanel").on("click", ".layui-select-title", function (e) {
            $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
            $(this).parents(".downpanel").toggleClass("layui-form-selected");
            layui.stope(e);
        }).on("click", "dl i", function (e) {
            layui.stope(e);
        });*/
        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });
        //监听提交
        form.on('submit(goodinfo)', function(data){
            var goodinfo={};
            goodinfo.goodname=data.field.goodname;
            goodinfo.price=data.field.price;
            goodinfo.memprice=data.field.memprice;
            goodinfo.address=data.field.address;
            goodinfo.status=data.field.status;
            goodinfo.outtime=data.field.outtime;
            goodinfo.goodsortid=data.field.goodsortID      //树形结构暂时没处理，先写死
            var url='${cp}/good/addgoodinfo';
            $.post(url,goodinfo,function(data){
                if(data.msg=="success"){
                    layer.msg("添加成功");
                    window.parent.location.reload();
                }else{
                    layer.msg("已有相同名称的商品");
                }
            });
            return false;
        });
    });

</script>
</body>
</html>