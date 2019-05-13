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
    <div class="layui-btn" style="margin-left: 60%">
        <button class="layui-btn" id="btn-add">新增</button>
    </div>
    <table id="table1" class="layui-table" lay-filter="table1"></table>

</div>
<!-- 操作列 -->
<script type="text/html" id="oper-col">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">添加</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="../../../static/layuiadmin/layui/layui.js"></script>
<script src="../../../js/utils/utils"></script>
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

        $('#btn-add').click(function () {
            add({
                id: '-1',
                goodsortname: '商品'
            });
        })

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
           // layer.alert(JSON.stringify(obj.data));
            if (layEvent === 'del') {

                if(obj.data.isParent==true){
                    layer.msg('只能删除末级节点');
                }else{
                    del(obj);
                /*    layer.msg('删除' + data.goodsortname);*/
                }

            } else if (layEvent === 'edit') {
                edit(obj.data);
            }else if(layEvent === 'add'){
                add(obj.data);
            }
        });

        var flag = false;
        function del(obj) {
        layer.confirm("你确定删除数据吗？此操作不能撤销！", {icon: 3, title: '提示'},
            function(index){      //确认后执行的操作
                $.ajax({
                    url:'${cp}/good/deletgoodsort',
                    type:'post',
                    data:{'id':obj.data.id},//向服务端发送删除的id
                    async: false,
                    success:function(data){
                        if(data.msg =='success'){
                            flag=true;
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);
                            layer.msg("删除成功",{icon:1});
                            renderTable();
                        }
                        else{
                            layer.msg("删除失败",{icon:5});
                        }
                    }
                })
            },
            function(index){      //取消后执行的操作
                flag = false;
            });
        }

        function add(pObj) {
            if(utils.isNotEmpty(pObj)&&pObj.goodsortstatus=='停用'){
                layer.alert("该商品分类已经停用，请联系管理员启用。");
            }else{
            layer.open({
                type: 2,
                area: ['500px', '350px'],
                title: "修改权限",
                fixed: false, //不固定
                shade: 0.8,
                shadeClose: true,
                content:'${cp}/good/addgoodsort?goodsortid='+pObj.id+'&goodsortname='+pObj.goodsortname
            });
         }
        }

        function edit(pObj) {
            layer.open({
                type: 2,
                area: ['500px', '270px'],
                title: "商品添加",
                fixed: false, //不固定
                shade: 0.8,
                shadeClose: true,
                content:'${cp}/good/editgoodsort?goodsortid='+pObj.id+'&goodsortname='+pObj.goodsortname+'&goodsortstatus='+pObj.goodsortstatus
            });
        }
    });
</script>
</body>
</html>
