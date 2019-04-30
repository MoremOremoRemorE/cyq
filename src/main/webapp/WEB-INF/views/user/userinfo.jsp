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
                <div class="layui-card-header">设置我的资料</div>
                <div class="layui-card-body" pad15>

                    <div class="layui-form" lay-filter="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">我的角色</label>
                            <div class="layui-input-inline">
                                <select name="roleid" lay-verify="">
                                    <option value="1" <c:if test="${roleid==1}">selected="selected"</c:if>>超级管理员</option>
                                    <option value="2" <c:if test="${roleid==2}">selected="selected"</c:if>>普通管理员</option>
                          </select>
                            </div>
                           <%-- <div class="layui-form-mid layui-word-aux">当前角色不可更改为其它角色</div>--%>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="username" value="${username}" readonly class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block">
                                <input type="radio" name="sex" value="男" title="男" <c:if test="${sex=='男'}">checked="checked"</c:if>>
                                <input type="radio" name="sex" value="女" title="女" <c:if test="${sex=='女'}">checked="checked"</c:if>>
                            </div>
                        </div>
                       <%-- <div class="layui-form-item">
                            <label class="layui-form-label">头像</label>
                            <div class="layui-input-inline">
                                <input name="avatar" lay-verify="required" id="LAY_avatarSrc" placeholder="图片地址" value="http://cdn.layui.com/avatar/168.jpg" class="layui-input">
                            </div>
                            <div class="layui-input-inline layui-btn-container" style="width: auto;">
                                <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                                    <i class="layui-icon">&#xe67c;</i>上传图片
                                </button>
                                <button class="layui-btn layui-btn-primary" layadmin-event="avartatPreview">查看图片</button >
                            </div>
                        </div>--%>
                        <div class="layui-form-item">
                            <label class="layui-form-label">手机</label>
                            <div class="layui-input-inline">
                                <input type="text" name="phone" value="${phone}" lay-verify="phone" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                      <%--  <div class="layui-form-item">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-inline">
                                <input type="text" name="email" value="" lay-verify="email" autocomplete="off" class="layui-input">
                            </div>
                        </div>--%>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">备注</label>
                            <div class="layui-input-block">
                                <textarea name="note"  placeholder="请输入内容" class="layui-textarea">${note}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="userinfo">确认修改</button>
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
    layui.config({
        base: '../../../static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'set']);

    layui.use(['form'], function(){
        var form = layui.form
            ,layer = layui.layer;
        //监听提交
        form.on('submit(userinfo)', function(data){
           var userinfo={};
           userinfo.username=data.field.username;
           userinfo.roleid=data.field.roleid;
           userinfo.sex=data.field.sex;
           userinfo.phone=data.field.phone;
           userinfo.note=data.field.note;
           var url='${cp}/user/edituserinfo';
           $.post(url,userinfo,function(data){
                if(data.data=="success"){
                    layer.alert("修改成功");
                }
           });
            return false;
        });
    });

</script>
</body>
</html>