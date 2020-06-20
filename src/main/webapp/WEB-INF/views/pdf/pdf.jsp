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
    <title>pdf</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../static/layuiadmin/style/admin.css" media="all">

</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <input name="name" placeholder='用户名' maxlength="16" type='text' autocomplete="off" value="" id="username"/>
    <input type='button' value='生成证书' onclick="download()" id="download">
    <input type='button' value='下载' onclick="realdownload()" id="realdownload">
</div>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../../../static/login/js/jquery-ui.min.js"></script>
<script src="../../../static/layuiadmin/layui/layui.js"></script>
<script>

    layui.config({
        base: '../../../static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index','jquery'], function(){
            $ = layui.jquery;
    });
    function download() {
        var name = $("#username").val();
        $.ajax({
            url:'${cp}/pdf/pdf',
            type:'post',
            data:{'name':name},
            async: false,
            success:function(data){
                if(data.msg =='success'){
                    alert("删除成功",{icon:1});
                    location.href = '../front/login';
                }
                else{
                    layer.msg("删除失败",{icon:5});
                }
            }
        });
    }
    function realdownload() {
        window.location.href="/pdf/downPdf";
    }

</script>
</body>
</html>


