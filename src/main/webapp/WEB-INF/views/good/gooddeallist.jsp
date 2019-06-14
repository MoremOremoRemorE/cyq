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
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../../static/layuiadmin/layui/css/layui.css"  media="all">
</head>
<body>

<table class="layui-hide" id="test"></table>


<script src="../../../static/layuiadmin/layui/layui.js" charset="utf-8"></script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url:'${cp}/good/getgooddeallist'
            ,cellMinWidth: 80
            ,cols: [[
                 {field:'gooddealid', title: '商品交易ID', sort: true}
                ,{field:'goodname',  title: '商品名称'}
                 ,{field:'fromusername',  title: '发起用户'}
                ,{field:'tousername',  title: '接受用户', sort: true}
                ,{field:'dealaddress',  title: '交易地址'}
                ,{field:'dealtime', title: '交易时间', minWidth: 150}
            ]]
            ,page: true
        });
    });
</script>

</body>
</html>