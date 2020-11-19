<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <link href="/favicon.ico" />
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
                <div class="layui-card-body" pad15>

                    <div class="layui-form" lay-filter="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="username" value="${username}" readonly class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">角色名称</label>
                            <div class="layui-input-inline">
                                <select name="roleid" lay-verify="">
                                    <option value="1" <c:if test="${roleid==1}">selected="selected"</c:if>>超级管理员</option>
                                    <option value="2" <c:if test="${roleid==2}">selected="selected"</c:if>>普通管理员</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="userinfo">确认修改</button>
                               <%-- <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>--%>
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
           userinfo.roleid=data.field.roleid;
           userinfo.username=data.field.username;
           var url='${cp}/user/edituserinfo';
           $.post(url,userinfo,function(data){
                if(data.data=="success"){
                    window.parent.location.reload();
                    var index=parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }
           });
            return false;
        });
    });

</script>
</body>
</html>