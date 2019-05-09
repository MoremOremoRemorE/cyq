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
    <meta charset="UTF-8">
    <title>tree-table</title>
    <link rel="stylesheet" href="../../../static/layuiadmin/layui/css/layui.css">
    <link rel="stylesheet" href="../../../static/layuiadmin/modules/common.css"/>
</head>
<body>
<div class="layui-container layui-text">
    <div class="layui-btn-group">
        <button class="layui-btn" id="btn-expand">全部展开</button>
        <button class="layui-btn" id="btn-fold">全部折叠</button>
        <button class="layui-btn" id="btn-refresh">刷新表格</button>
    </div>
    <table id="table1" class="layui-table" lay-filter="table1"></table>

</div>
<!-- 操作列 -->
<script type="text/html" id="oper-col">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="../../../static/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../../static/layuiadmin/modules/'
    }).extend({
        treetable: 'treetable'
    }).use(['layer', 'table', 'treetable'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;
        var treetable = layui.treetable;

        // 渲染表格
        var renderTable = function () {
            layer.load(2);
            treetable.render({
                treeColIndex: 1,
                treeSpid: -1,
                treeIdName: 'goodsortid',
                treePidName: 'goodsortpid',
                treeDefaultClose: false,
                treeLinkage: false,
                elem: '#table1',
                url: '${cp}/good/getallgoodsort',
             //   url: 'http://localhost:8080/static/layuiadmin/json/1.json',
                page: false,
                cols: [[
                   // {type: 'goodsortid',title: '商品序号'},
                    {field: 'goodsortid', title: '商品ID'},
                    {field: 'goodsortname', title: '商品名称'},
                    {field: 'goodsortstatus', title: '商品状态'},
                    {field: 'creattime', title: '创建时间'},
                    {field: 'updatetime', title: '修改时间'},
                  //  {field: 'goodsortpid', title: '上级菜单'},
                    {templet: '#oper-col', title: '操作'}
                ]],
                done: function () {
                    layer.closeAll('loading');
                }
            });
        };

        renderTable();

        $('#btn-expand').click(function () {
            treetable.expandAll('#table1');
        });

        $('#btn-fold').click(function () {
            treetable.foldAll('#table1');
        });

        $('#btn-refresh').click(function () {
            renderTable();
        });

        //监听工具条
        table.on('tool(table1)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'del') {
                layer.msg('删除' + data.id);
            } else if (layEvent === 'edit') {
                layer.alert(JSON.stringify(data));
                layer.msg('修改' + data.id);
            }
        });
    });
</script>
</body>
</html>
