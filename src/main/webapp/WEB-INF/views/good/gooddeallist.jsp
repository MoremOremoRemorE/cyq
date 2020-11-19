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
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="demoTable">
                    搜索客户：
                    <div class="layui-inline">
                        <input class="layui-input" name="keyword" id="demoReload" autocomplete="off">
                    </div>
                    <button class="layui-btn" data-type="reload">搜索</button>
                </div>
                <table class="layui-hide" id="test"></table>
            </div>
        </div>
    </div>
</div>


<script src="../../../static/layuiadmin/layui/layui.js" charset="utf-8"></script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url:'${cp}/good/getgooddeallist'
            ,cellMinWidth: 80
            ,cols: [[
                 {field:'username', title: '客户姓名'}
                ,{field:'goodname',  title: '商品名称'}
                 ,{field:'price',  title: '商品价格（元）' ,sort: true}
                ,{field:'address',  title: '客户地址' }
                ,{field:'date',  title: '交易时间'}
            ]]
            ,id: 'testReload'
            ,page: true
        });
        var $ = layui.$, active = {
            reload: function () {
                var demoReload = $('#demoReload');

                table.reload('testReload', {
                    where: {
                        keyword: demoReload.val()
                    }
                });
            }
        };
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });


</script>

</body>
</html>