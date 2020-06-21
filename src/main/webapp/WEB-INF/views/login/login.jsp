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
    <link href="../../../js/live2d/assets/waifu.css" rel="stylesheet" type="text/css" />
</head>
<body>
<img class='login'>
    <div class='login_title'>
        <span>用户登录</span>
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
                <img alt="" src='../../../static/login/img/lock_icon_copy.png'>
            </div>
            <input name="pwd" placeholder='密码' maxlength="16" type='text' autocomplete="off" id="password">
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
            <input type='button' value='登录' id="login">
           <%-- <input type='button' value='注册' id="register">--%>
        </div>
        <div class='login_fields__submit'>
            <input type='button' value='注册' id="register">
        </div>
    </div>
    <div class='success'>
    </div>
    <img class='disclaimer'>
        <p>备案号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.miit.gov.cn/" style="color: red">苏ICP备19045704号</a></p>

    </div>
    <div class="waifu">
        <div class="waifu-tips"></div>
        <canvas id="live2d" class="live2d"></canvas>
        <div class="waifu-tool">
            <span class="fui-home"></span>
            <span class="fui-chat"></span>
            <span class="fui-eye"></span>
            <span class="fui-user"></span>
            <span class="fui-photo"></span>
            <span class="fui-info-circle"></span>
            <span class="fui-cross"></span>
        </div>
    </div>
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
<script src="../../../js/live2d/assets/waifu-tips.js" type="text/javascript"></script>
<script src="../../../js/live2d/assets/live2d.js" type="text/javascript"></script>
<%--<script src="../../../js/live2d/assets/autoload.js" type="text/javascript"></script>--%>
<script src="../../../static/login/js/jquery.mockjax.js" type="text/javascript"></script>
<script type="text/javascript">
    var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持

    var CodeVal = 0;
    Code();
    initLive2D();
    function Code() {
        if(canGetCookie == 1){
            createCode("AdminCode");
            var AdminCode = getCookieValue("AdminCode");
            showCheck(AdminCode);
        }else{
            showCheck(createCode(""));
        }
    }
    function initLive2D() {
        // live2d_settings['modelId'] = 5;                  // 默认模型 ID
        live2d_settings['modelTexturesId'] = 1;          // 默认材质 ID
        live2d_settings['modelStorage'] = false;         // 不储存模型 ID
        live2d_settings['modelId'] = 1;
        live2d_settings['modelTexturesId'] = 87;
        live2d_settings['waifuEdgeSide']='left:300';
        live2d_settings['waifuDraggable']='disable';
        initModel("../../../js/live2d/assets/waifu-tips.json")
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
        //非空验证
        $("#login").click(function () {
            var login = $('input[name="login"]').val();
            var pwd = $('input[name="pwd"]').val();
            var code = $('input[name="code"]').val();
            if (login == '') {
                layer.msg('请输入您的账号');
            } else if (pwd == '') {
                layer.msg('请输入密码');
            } else if (code == '' || code.length != 4) {
                layer.msg('输入验证码');
            } else {

                var userinfo={};
                userinfo.username=$("#username").val();
                userinfo.code=code;
                userinfo.password=$("#password").val();
                //          var url="http://localhost:8080/front/logincheck";
                var url='${cp}/front/logincheck';
                $.ajax({
                    url: url,
                    type: "POST",
                    data:  userinfo,
                    dataType: "json",
                    //     async:false,
                    success:function(data){
                        if(data.code=='200'){
                        if(data.data =='success') {
                            $('.login').addClass('test'); //倾斜特效
                            setTimeout(function () {
                                $('.login').addClass('testtwo'); //平移特效
                            }, 300);
                            setTimeout(function () {
                                $('.authent').show().animate({right: -320}, {
                                    easing: 'easeOutQuint',
                                    duration: 600,
                                    queue: false
                                });
                                $('.authent').animate({opacity: 1}, {
                                    duration: 200,
                                    queue: false
                                }).addClass('visible');
                            }, 500);
                            setTimeout(function () {
                                $('.authent').show().animate({right: 90}, {
                                    easing: 'easeOutQuint',
                                    duration: 600,
                                    queue: false
                                });
                                $('.authent').animate({opacity: 0}, {
                                    duration: 200,
                                    queue: false
                                }).addClass('visible');
                                $('.login').removeClass('testtwo'); //平移特效
                            }, 2000);
                            setTimeout(function () {
                                $('.authent').hide();
                                $('.login').removeClass('test');
                                if (data.data == 'success') {
                                    //登录成功
                                    $('.login div').fadeOut(100);
                                    $('.success').fadeIn(1000);
                                    $('.success').html('欢迎' + userinfo.username);
                                    window.location.href = "index";
                                } else {
                                    AjaxErro(data);
                                }
                            }, 2400);
                        }else if(data.data =='pserr'){
                            setTimeout(layer.msg('密码错误，请重新输入密码'),3000);
                            //     alert('密码错误，请重新输入密码');
                        }else{
                            setTimeout(layer.msg('该用户尚未注册，请先注册'),function (){
                                window.location.href="register";
                            },3000);
                            $("#username").val("");
                            $("#password").val("");
                            $("#code").val("");
                            //     alert('该用户尚未注册，请先注册');
                        }}else {
                            alert(data.msg);
                        }
                    },
                    error:function(er){
                        alert("用户验证异常,请联系维护人员!");
                    }
                });
            }
        })
        $("#register").click(function () {
            var reusername = $("#username").val();

            if(reusername!=""){
                layer.msg("已经输入用户名了,请输入密码");
            }else{
                window.location.href="register";
            }
        })

    })
</script>
</body>
</html>
