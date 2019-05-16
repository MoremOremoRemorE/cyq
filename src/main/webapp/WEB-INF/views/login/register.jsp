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
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>顺路帮登录</title>
    <link href="../../../static/login/css/default.css" rel="stylesheet" type="text/css" />
    <!--必要样式-->
    <link href="../../../static/login/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="../../../static/login/css/demo.css" rel="stylesheet" type="text/css" />
    <link href="../../../static/login/css/loaders.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class='login'>
    <div class='login_title'>
        <span>用户注册</span>
    </div>
    <div class='login_fields'>
        <div class='login_fields__user'>
            <div class='icon'>
                <img alt="" src='../../../static/login/img/user_icon_copy.png'>
            </div>
            <input name="login" placeholder='用户名' maxlength="16" type='text' autocomplete="off" value="" id="username"/>
            <div class='validation'>
                <img alt="" src='../../../static/login/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='../../../static/login/img/email.png'>
            </div>
            <input name="email" placeholder='邮箱' maxlength="18" type='text' autocomplete="off" id="email">
            <div class='validation'>
                <img alt="" src='../../../static/login/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='../../../static/login/img/lock_icon_copy.png'>
            </div>
            <input name="pwd" placeholder='密码' maxlength="16" type='text' autocomplete="off" id="password">
            <div class='validation'>
                <img alt="" src='../../../static/login/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='../../../static/login/img/lock_icon_copy.png'>
            </div>
            <input name="pwd" placeholder='确认密码' maxlength="16" type='text' autocomplete="off" id="cpassword">
            <div class='validation'>
                <img alt="" src='../../../static/login/img/tick.png'>
            </div>
        </div>

       <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='../../../static/login/img/key.png'>
            </div>
            <input name="code" placeholder='验证码' maxlength="4" type='text' name="ValidateNum" autocomplete="off" id="code">
            <div class='validation' style="opacity: 1; right: -5px;top: -3px;">
                <canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
            </div>
        </div>
        <div class='login_fields__submit'>
            <input type='button' value='注册' id="register">
        </div>
    </div>
    <div class='success'>
    </div>
  <%--  <div class='disclaimer'>
        <p>这里本来是两个迎宾小姐姐的</p>
    </div>--%>
</div>
<div class='authent'>
    <div class="loader" style="height: 44px;width: 44px;margin-left: 28px;">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
    <p>认证中...</p>
</div>
<div class="OverWindows"></div>
<link href="../../../static/login/layui/css/layui.css" rel="stylesheet" type="text/css" />
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../../../static/login/js/jquery-ui.min.js"></script>
<script type="text/javascript" src='../../../static/login/js/stopExecutionOnTimeout.js?t=1'></script>
<script src="../../../static/login/layui/layui.js" type="text/javascript"></script>
<script src="../../../static/login/js/Particleground.js" type="text/javascript"></script>
<script src="../../../static/login/js/Treatment.js" type="text/javascript"></script>
<script src="../../../static/login/js/jquery.mockjax.js" type="text/javascript"></script>
<script type="text/javascript">
    var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持

    var CodeVal = 0;
    Code();
    function Code() {
        if(canGetCookie == 1){
            createCode("AdminCode");
            var AdminCode = getCookieValue("AdminCode");
            showCheck(AdminCode);
        }else{
            showCheck(createCode(""));
        }
    }
    function showCheck(a) {
        CodeVal = a;
        var c = document.getElementById("myCanvas");
        var ctx = c.getContext("2d");
        ctx.clearRect(0, 0, 1000, 1000);
        ctx.font = "80px 'Hiragino Sans GB'";
        ctx.fillStyle = "#E8DFE8";
        ctx.fillText(a, 0, 100);
    }
    $(document).keypress(function (e) {
        // 回车键事件
        if (e.which == 13) {
            $('input[type="button"]').click();
        }
    });
    //粒子背景特效
    $('body').particleground({
        dotColor: '#E8DFE8',
        lineColor: '#133b88'
    });
    $('input[name="pwd"]').focus(function () {
        $(this).attr('type', 'password');
    });
    $('input[type="text"]').focus(function () {
        $(this).prev().animate({ 'opacity': '1' }, 200);
    });
    $('input[type="text"],input[type="password"]').blur(function () {
        $(this).prev().animate({ 'opacity': '.5' }, 200);
    });
    $('input[name="login"],input[name="pwd"]').keyup(function () {
        var Len = $(this).val().length;
        if (!$(this).val() == '' && Len >= 5) {
            $(this).next().animate({
                'opacity': '1',
                'right': '30'
            }, 200);
        } else {
            $(this).next().animate({
                'opacity': '0',
                'right': '20'
            }, 200);
        }
    });
    var open = 0;
    layui.use('layer', function () {
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式判断邮箱是否正确
        //非空验证
        $("#register").click(function () {
            var login = $('input[name="login"]').val();
            var pwd = $('input[name="pwd"]').val();
            var code = $('input[name="code"]').val();
            var cpws = $("#cpassword").val();
            var email = $("#email").val();
            if (login == '') {
                layer.msg('请输入您的账号');
            } else if (pwd == '') {
                layer.msg('请输入密码');
            } else if (code == '' || code.length != 4) {
                layer.msg('输入验证码');
            } else if(pwd!=cpws){
                layer.msg('两次输入的密码不一致');
            }else  if(email==''){
                layer.msg('请输入邮箱');
            } else  if(!reg.test(email)){
                layer.msg('请输入正确的邮箱地址');
            }else{
                var userinfo={};
                userinfo.username=$("#username").val();
                userinfo.code=code;
                userinfo.password=$("#password").val();
                userinfo.sex="男";   //默认为男，进去以后修改信息
                userinfo.email=email;
                var url='${cp}/front/addregister';
                $.ajax({
                    url: url,
                    type: "POST",
                    data:  userinfo,
                    dataType: "json",
                    async:false,
                    success:function(data){
                        if(data.msg =='success'){
                            alert('注册成功');
                            window.location.href="login";
                        }else{
                            layer.msg('该用户名已经被注册');
                        }
                    },
                    error:function(er){
                        layer.msg("注册错误");
                    }
                });

            }
        })

    })
</script>
</body>
</html>
