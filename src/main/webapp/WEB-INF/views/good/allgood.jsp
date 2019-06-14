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
    <title>开启头部工具栏 - 数据表格</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../static/layuiadmin/style/admin.css" media="all">
</head>
<body>

<%--<div class="layui-card layadmin-header">
    <div class="layui-breadcrumb" lay-filter="breadcrumb">
        <a lay-href="">主页</a>
        <a><cite>组件</cite></a>
        <a><cite>数据表格</cite></a>
        <a><cite>开启头部工具栏</cite></a>
    </div>
</div>--%>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">商品信息</div>
                <div class="layui-card-body">

                    <table class="layui-hide" id="test-table-toolbar" lay-filter="test-table-toolbar"></table>
                    <div id="laypage"></div>

                    <script type="text/html" id="test-table-toolbar-toolbarDemo">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" lay-event="export">导出所有数据</button>
                            <button class="layui-btn layui-btn-sm" lay-event="add">新增商品信息</button>
                        </div>
                    </script>

                    <script type="text/html" id="test-table-toolbar-barDemo">
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="../../../static/login/js/jquery-ui.min.js"></script>
<script src="../../../static/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../../static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table','jquery'], function(){
        var admin = layui.admin
            ,table = layui.table
            ,$ = layui.jquery;
        var ins1= table.render({
            elem: '#test-table-toolbar'
            ,url: '${cp}/good/getallgood'
            ,toolbar: '#test-table-toolbar-toolbarDemo'
            ,title: '用户数据表'
            ,cellMinWidth: 80
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                // ,{field:'goodid', title:'ID', width:100, fixed: 'left', unresize: true, sort: true}
                ,{field:'goodname', title:'商品名'}
                ,{field:'price', title:'商品价格', sort: true}
                ,{field:'memprice', title:'会员价', sort: true}
                ,{field:'address', title:'商品地址',  sort: true}
                ,{field:'outtime', title:'售出时间',  sort: true}
                ,{field:'status', title:'商品状态', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#test-table-toolbar-barDemo', width:300}
            ]],
            done: function (res, curr, count) {
                exportData=res.data;
            }
            ,page: true    //假分页
        });

        //头工具栏事件
        table.on('toolbar(test-table-toolbar)', function(obj){
            if(obj.event === 'export'){
                table.exportFile(ins1.config.id,exportData, 'xls');
            }else if(obj.event === 'add'){
                layer.open({
                    type: 2,
                    area: ['1500px', '800px'],
                    title: "添加商品信息",
                    fixed: false, //不固定
                    shade: 0.8,
                    shadeClose: true,
                    content:'${cp}/good/addgood'
                });
            }
        });

        table.on('tool(test-table-toolbar)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除：'+data.goodname+"商品吗?", function(index){
                    $.ajax({
                        url:'${cp}/good/deletegood',
                        type:'post',
                        data:{'id':data.goodid},//向服务端发送删除的id
                        async: false,
                        success:function(data){
                            if(data.msg =='success'){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
                                layer.msg("删除成功",{icon:1});
                            }
                            else{
                                layer.msg("删除失败",{icon:5});
                            }
                        }
                    });
                    layer.close(index);
                });

            } else if(layEvent === 'edit') { //编辑
                    layer.open({
                        type: 2,
                        area: ['1500px', '800px'],
                        title: "修改商品信息",
                        fixed: false, //不固定
                        shade: 0.8,
                        shadeClose: true,
                        content:'${cp}/good/editgood?goodid='+data.goodid+'&goodname='+data.goodname+'&price='+data.price+'&memprice='+data.memprice+'&address='+data.address+'&outtime='+data.outtime
                    });
                }
        });

    });
</script>
</body>
</html>