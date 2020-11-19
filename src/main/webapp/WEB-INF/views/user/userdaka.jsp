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
    <title>打卡</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../static/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" type="text/css" href="../../../static/daka/css/style.css" />
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">打卡</div>
                <div class="layui-card-body">
                    <div class="page">
                        <section class="demo">
                            <nav class="nav">
                                <ul>
                                    <li><a onclick="userup()"><span> 上班打卡</span></a></li>
                                    <li><a onclick="userdown()"><span> 下班打卡</span></a></li>
                                </ul>
                            </nav>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>

<script type="text/javascript" src="../../../static/login/js/jquery-ui.min.js"></script>
<script src="../../../static/login/layui/layui.js" type="text/javascript"></script>
<script>
    function userup(){
        var url = '${cp}/user/userup';
        var flag = 'up';
        $.ajax({
            url: url,
            type: "POST",
            data:  { flag: flag},
            dataType: "json",
            async:false,
            success:function(data){
                if(data.msg =='success'){
                    alert("上班打卡成功");
                }else{
                    alert('上班打卡失败');
                }
            },
            error:function(er){
                alert("错误");
            }
        });
    }
    function userdown() {
        var url = '${cp}/user/userup';
        var flag = 'down';
        $.ajax({
            url: url,
            type: "POST",
            data:  { flag: flag},
            dataType: "json",
            async:false,
            success:function(data){
                if(data.msg =='success'){
                    alert("下班打卡成功");
                }else{
                    alert('下班打卡失败');
                }
            },
            error:function(er){
                alert("错误");
            }
        });
    }

</script>
</body>
</html>