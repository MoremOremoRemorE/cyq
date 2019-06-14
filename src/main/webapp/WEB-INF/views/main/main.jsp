<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <link href="/favicon.ico" />
    <meta charset="utf-8">
    <title>layuiAdmin 控制台主页一</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../static/layuiadmin/style/admin.css" media="all">
</head>
<body>
<%--<img src="../../../static/login/img/timg.jpg" height="100%" width="100%"/>--%>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">商品销售统计</div>
                <div class="layui-card-body">
                    <div id ="goodout">
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">用户访问统计</div>
                <div class="layui-card-body">
                    <div id ="usercount">
                    </div>
                </div>
            </div>

        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">商品入库统计</div>
                <div class="layui-card-body">
                    <div id ="goodin">
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">当前时间（充数）</div>
                <div class="layui-card-body">
                    <div id ="time">
                    </div>
                </div>
            </div>
        </div>
       </div>
    </div>
</div>
<script src="https://code.highcharts.com.cn/jquery/jquery-1.8.3.min.js"></script>
<script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
<script src="https://code.highcharts.com.cn/highcharts/highcharts-3d.js"></script>
<script src="https://code.highcharts.com.cn/highcharts/highcharts-more.js"></script>
<script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<script src="../../../static/layuiadmin/layui/layui.js?t=1"></script>
<script>
    layui.config({
        base: '../../layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'console']);

    //3d
    var chart = Highcharts.chart('goodout',{
        chart: {
            type: 'column',
            margin: 75,
            options3d: {
                enabled: true,
                alpha: 10,
                beta: 0,
                depth: 100,
                viewDistance: 200,      // 视图距离，它对于计算角度影响在柱图和散列图非常重要。此值不能用于3D的饼图
                frame: {                // Frame框架，3D图包含柱的面板，我们以X ,Y，Z的坐标系来理解，X轴与 Z轴所形成
                    // 的面为bottom，Y轴与Z轴所形成的面为side，X轴与Y轴所形成的面为back，bottom、
                    // side、back的属性一样，其中size为感官理解的厚度，color为面板颜色
                    bottom: {
                        size: 10
                    },
                    side: {
                        size: 1,
                        color: 'transparent'
                    },
                    back: {
                        size: 1,
                        color: 'transparent'
                    }
                }
            },
        },
        title: {
            text: '商品统计数据'
        },
        subtitle: {
            text: '包含所有的商品'
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis: {
            categories: Highcharts.getOptions().lang.shortMonths
        },
        yAxis: {
            title: {
                text: null
            }
        },
        series: [{
            name: '商品销售',
            data: [2, 3, null, 4, 0, 5, 1, 4, 6, 3]
        }]
    });

    var chart = Highcharts.chart('goodin',{
        chart: {
            type: 'column',
            margin: 75,
            options3d: {
                enabled: true,
                alpha: 10,
                beta: 0,
                depth: 100,
                viewDistance: 200,      // 视图距离，它对于计算角度影响在柱图和散列图非常重要。此值不能用于3D的饼图
                frame: {                // Frame框架，3D图包含柱的面板，我们以X ,Y，Z的坐标系来理解，X轴与 Z轴所形成
                    // 的面为bottom，Y轴与Z轴所形成的面为side，X轴与Y轴所形成的面为back，bottom、
                    // side、back的属性一样，其中size为感官理解的厚度，color为面板颜色
                    bottom: {
                        size: 10
                    },
                    side: {
                        size: 1,
                        color: 'transparent'
                    },
                    back: {
                        size: 1,
                        color: 'transparent'
                    }
                }
            },
        },
        title: {
            text: '商品统计数据'
        },
        subtitle: {
            text: '包含所有的商品'
        },
        plotOptions: {
            column: {
                depth: 25,
                color:'#1A4D11'
            }
        },
        xAxis: {
            categories: Highcharts.getOptions().lang.shortMonths
        },
        yAxis: {
            title: {
                text: null
            }
        },
        series: [{
            name: '商品销售',
            data: [2, 3, null, 4, 0, 5, 1, 4, 6, 3]
        }]
    });

    var chart = Highcharts.chart('usercount', {
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: '2014年某网站不同浏览器访问量占比'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '浏览器占比',
            data: [
                ['Firefox',   45.0],
                ['IE',       26.8],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['Safari',    8.5],
                ['Opera',     6.2],
                ['Others',   0.7]
            ]
        }]
    });

    /*
 * 获取当前时间
 */
    function getNow() {
        var now = new Date();
        return {
            hours: now.getHours() + now.getMinutes() / 60,
            minutes: now.getMinutes() * 12 / 60 + now.getSeconds() * 12 / 3600,
            seconds: now.getSeconds() * 12 / 60
        };
    }
    /**
     * Pad numbers
     */
    function pad(number, length) {
        // Create an array of the remaining length + 1 and join it with 0's
        return new Array((length || 2) + 1 - String(number).length).join(0) + number;
    }
    var now = getNow();
    // Create the chart
    var chart = Highcharts.chart('time',{
        chart: {
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false,
            height: 400
        },
        credits: {
            enabled: false
        },
        title: {
            text: 'Highcharts时钟'
        },
        pane: {
            background: [{
                // default background
            }, {
                // reflex for supported browsers
                backgroundColor: Highcharts.svg ? {
                    radialGradient: {
                        cx: 0.5,
                        cy: -0.4,
                        r: 1.9
                    },
                    stops: [
                        [0.5, 'rgba(255, 255, 255, 0.2)'],
                        [0.5, 'rgba(200, 200, 200, 0.2)']
                    ]
                } : null
            }]
        },
        yAxis: {
            labels: {
                distance: -20
            },
            min: 0,
            max: 12,
            lineWidth: 0,
            showFirstLabel: false,
            minorTickInterval: 'auto',
            minorTickWidth: 1,
            minorTickLength: 5,
            minorTickPosition: 'inside',
            minorGridLineWidth: 0,
            minorTickColor: '#666',
            tickInterval: 1,
            tickWidth: 2,
            tickPosition: 'inside',
            tickLength: 10,
            tickColor: '#666',
            title: {
                text: 'Powered by<br/>Highcharts',
                style: {
                    color: '#BBB',
                    fontWeight: 'normal',
                    fontSize: '8px',
                    lineHeight: '10px'
                },
                y: 10
            }
        },
        tooltip: {
            formatter: function () {
                return this.series.chart.tooltipText;
            }
        },
        series: [{
            data: [{
                id: 'hour',
                y: now.hours,
                dial: {
                    radius: '60%',
                    baseWidth: 4,
                    baseLength: '95%',
                    rearLength: 0
                }
            }, {
                id: 'minute',
                y: now.minutes,
                dial: {
                    baseLength: '95%',
                    rearLength: 0
                }
            }, {
                id: 'second',
                y: now.seconds,
                dial: {
                    radius: '100%',
                    baseWidth: 1,
                    rearLength: '20%'
                }
            }],
            animation: false,
            dataLabels: {
                enabled: false
            }
        }]
    }, function (chart) {
        setInterval(function () {
            now = getNow();
            var hour = chart.get('hour'),
                minute = chart.get('minute'),
                second = chart.get('second'),
                // run animation unless we're wrapping around from 59 to 0
                animation = now.seconds === 0 ?
                    false :
                    {
                        easing: 'easeOutElastic'
                    };
            // Cache the tooltip text
            chart.tooltipText =
                pad(Math.floor(now.hours), 2) + ':' +
                pad(Math.floor(now.minutes * 5), 2) + ':' +
                pad(now.seconds * 5, 2);
            hour.update(now.hours, true, animation);
            minute.update(now.minutes, true, animation);
            second.update(now.seconds, true, animation);
        }, 1000);
    });
    // Extend jQuery with some easing (copied from jQuery UI)
    $.extend($.easing, {
        easeOutElastic: function (x, t, b, c, d) {
            var s=1.70158;var p=0;var a=c;
            if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
            if (a < Math.abs(c)) { a=c; var s=p/4; }
            else var s = p/(2*Math.PI) * Math.asin (c/a);
            return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
        }
    });
</script>
</body>
</html>

