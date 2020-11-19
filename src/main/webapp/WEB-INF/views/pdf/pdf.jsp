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
    <input type='button' value='下载' onclick="realdownload()" id="realdownload"></br>
    <input type='button' value='生成证书数量' onclick="countPDF()" id="countPDF">
    <form action="${cp}/pdf/morePDF" method="POST" id="userinfolist" target="target">
        <textarea rows="40" cols="100" name="userinfolist">
            输入用户名列表，用户名之间用;隔开，最后一个用户名后面需要;号。
        </textarea>
        <input type='submit' value='一键生成'>
    </form>
    <iframe name="target" id="target" height="100%" width="100%"></iframe>
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
    $(function(){
       var msg;
       $("#target").load(function () {
            msg=$(this);
       })
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
                    layer.msg("成功",{icon:1});
                } else{
                    layer.msg("失败",{icon:5});
                }
            }
        });
    }
    function realdownload() {
        var name = $("#username").val();
        window.location.href="/pdf/downPdf?name="+name;
    }
    function countPDF() {
        $.ajax({
            url:'${cp}/pdf/count',
            type:'post',
            async: false,
            success:function(data){
                alert('当前去重总数：'+data);
            }
        });

    }
    function morePDF() {
        var form = document.getElementById('userinfolist');
        form.submit();
      /*  $.ajax({
            url:'${cp}/pdf/morePDF',
            type:'post',
            async: false,
            success:function(data){
                alert('00000');
            }
        })*/
    }

</script>
</body>
</html>


