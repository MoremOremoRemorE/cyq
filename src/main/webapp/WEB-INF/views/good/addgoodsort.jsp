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
    <title>新增商品信息</title>
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
                            <label class="layui-form-label" style="width: 100px">上级商品分类名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="goodname" value="${goodsortname}" readonly class="layui-input">
                                <input type="hidden" name="goodsortid" value="${goodsortid}"  class="layui-input" >
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label" style="width: 100px">商品分类名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="goodname1" value=""  class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label" style="width: 100px">状态</label>
                            <div class="layui-input-inline">
                                <select name="goodsortstatus" lay-verify="">
                                    <option value="0" <c:if test="${goodsortstatus=='启用'}">selected="selected"</c:if>>启用</option>
                                    <option value="1" <c:if test="${goodsortstatus=='停用'}">selected="selected"</c:if>>停用</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="userinfo">确认添加</button>
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
<script src="../../../js/utils/utils"></script>
<script src="../../../js/goods/addgoodsort"></script>
<script>
    var baseUrl='${cp}';
</script>
</body>
</html>