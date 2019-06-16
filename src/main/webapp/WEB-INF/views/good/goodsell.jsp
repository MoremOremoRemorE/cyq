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
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../../static/layuiadmin/layui/css/layui.css"  media="all">
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>商品销售</legend>
</fieldset>

<form class="layui-form" action="">
    <%--<div class="layui-form-item">
        <label class="layui-form-label">客户姓名</label>
        <div class="layui-input-block">
            <input type="text" name="title" lay-verify="title" autocomplete="on" placeholder="请输入客户姓名" class="layui-input">
        </div>
    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">客户姓名</label>
        <div class="layui-input-block">
            <input type="text" name="username" style="width:400px;" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？" placeholder="请输入购买客户姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">客户手机号</label>
            <div class="layui-input-inline">
                <input type="tel" name="phone" style="width:400px;" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入购买客户手机号">
            </div>
        </div>
        <%--<div class="layui-inline">
            <label class="layui-form-label">验证邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>--%>
    </div>

    <div class="layui-form-item">
        <%--<div class="layui-inline">
            <label class="layui-form-label">多规则验证</label>
            <div class="layui-input-inline">
                <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input">
            </div>
        </div>--%>
        <div class="layui-inline">
            <label class="layui-form-label">购买日期</label>
            <div class="layui-input-inline">
                <input type="text" name="date" id="date" lay-verify="date" placeholder="请输入购买日期" autocomplete="off" class="layui-input">
            </div>
        </div>
        <%--<div class="layui-inline">
            <label class="layui-form-label">验证链接</label>
            <div class="layui-input-inline">
                <input type="tel" name="url" lay-verify="url" autocomplete="off" class="layui-input">
            </div>
        </div>--%>
    </div>

   <%-- <div class="layui-form-item">
        <label class="layui-form-label">验证身份证</label>
        <div class="layui-input-block">
            <input type="text" name="identity" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">
        </div>
    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">用户地址</label>
        <div class="layui-input-inline">
            <input type="password" name="password" lay-verify="pass" placeholder="请输入用户收货地址" autocomplete="off" class="layui-input" style="width:400px;">
        </div>
    </div>

    <%--<div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">范围</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="price_min" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="price_max" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>--%>
   <%--<div class="layui-form-item">
        <label class="layui-form-label">商品信息</label>
        <div class="layui-input-block">
            <select name="interest" lay-filter="aihao">
                <option value="">请选择</option> 
                 <c:forEach items="${goodlist}" var="goodlist">
          <option value="${goodlist.goodid}">${goodlist.goodname}</option>  
         </c:forEach>
            </select>
        </div>
    </div>--%>

        <%--<div class="layui-inline">--%>
        <div class="layui-form-item">
            <label class="layui-form-label">商品信息</label>
            <div class="layui-input-inline"  style="width:500px;">
                <select name="modules" lay-verify="required" lay-search="">
                    <option value="">直接选择或搜索选择</option>
                     <c:forEach items="${goodlist}" var="goodlist">
                      <option value="${goodlist.goodid}">${goodlist.goodname}</option>  
                     </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">联动选择框</label>
        <div class="layui-input-inline">
            <select name="quiz1">
                <option value="">请选择省</option>
                <option value="浙江" selected="">浙江省</option>
                <option value="你的工号">江西省</option>
                <option value="你最喜欢的老师">福建省</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="quiz2">
                <option value="">请选择市</option>
                <option value="杭州">杭州</option>
                <option value="宁波" disabled="">宁波</option>
                <option value="温州">温州</option>
                <option value="温州">台州</option>
                <option value="温州">绍兴</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="quiz3">
                <option value="">请选择县/区</option>
                <option value="西湖区">西湖区</option>
                <option value="余杭区">余杭区</option>
                <option value="拱墅区">临安市</option>
            </select>
        </div>
        <div class="layui-form-mid layui-word-aux">此处只是演示联动排版，并未做联动交互</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">复选框</label>
        <div class="layui-input-block">
            <input type="checkbox" name="like[write]" title="写作">
            <input type="checkbox" name="like[read]" title="阅读" checked="">
            <input type="checkbox" name="like[game]" title="游戏">
        </div>
    </div>

    <div class="layui-form-item" pane="">
        <label class="layui-form-label">原始复选框</label>
        <div class="layui-input-block">
            <input type="checkbox" name="like1[write]" lay-skin="primary" title="写作" checked="">
            <input type="checkbox" name="like1[read]" lay-skin="primary" title="阅读">
            <input type="checkbox" name="like1[game]" lay-skin="primary" title="游戏" disabled="">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">开关-默认关</label>
        <div class="layui-input-block">
            <input type="checkbox" name="close" lay-skin="switch" lay-text="ON|OFF">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开关-默认开</label>
        <div class="layui-input-block">
            <input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男" checked="">
            <input type="radio" name="sex" value="女" title="女">
            <input type="radio" name="sex" value="禁" title="禁用" disabled="">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">普通文本域</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <!--<div class="layui-form-item layui-form-text">
      <label class="layui-form-label">编辑器</label>
      <div class="layui-input-block">
        <textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="LAY_demo_editor"></textarea>
      </div>
    </div>-->
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="../../../static/layuiadmin/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听指定开关
        form.on('switch(switchTest)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });

        //表单初始赋值
        form.val('example', {
            "username": "贤心" // "name": "value"
            ,"password": "123456"
            ,"interest": 1
            ,"like[write]": true //复选框选中状态
            ,"close": true //开关状态
            ,"sex": "女"
            ,"desc": "我爱 layui"
        })
    });
</script>

