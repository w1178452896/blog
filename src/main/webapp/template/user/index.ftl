<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/nav.css">
    <link rel="stylesheet" href="/css/home.css">
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <title>我的主页</title>
</head>
<body>
<!--导航栏-->
<#include "../navbar.ftl">

<div class="container uIndex">
    <div class="row">
        <div class="col-sm-2 col-sm-offset-2 uIndexHead">
            <a href=""><img src="/resources/head.png" alt="" class="img-circle"></a>
        </div>
        <div class="col-sm-4 uIndexInfo">
            <h4><b><span>${(Session.user.username)!"请登陆"}</span></b></h4>
            <ul class="list-inline">
                <li>
                    <p><span>0</span></p>
                    <button type="button" class="btn btn-default btn-xs" onclick="follow();"><span>关注 ></span></button>
                </li>
                <li>
                    <p><span>0</span></p>
                    <button type="button" class="btn btn-default btn-xs" onclick="fans();"><span>粉丝 ></span></button>
                </li>
                <li>
                    <p><span>0</span></p>
                    <button type="button" class="btn btn-default btn-xs" onclick="article();"><span>文章 ></span></button>
                </li>
            </ul>
        </div>
    </div>
    <div class="row uIndexChoose" style="margin-top: 20px;">
        <div class="col-sm-6 col-sm-offset-2">
            <ul class="nav nav-tabs chooseWen">
                <li class="active"><a href="javascript:article();"><span
                        class="glyphicon glyphicon-align-justify"></span> 文章</a></li>
                <li><a href="javascript:comment();"><span class="glyphicon glyphicon-comment"></span> 最新评论</a></li>
            </ul>
            <ul class="nav nav-tabs choosefan">
                <li><a href="javascript:follow();">关注用户</a></li>
                <li><a href="javascript:fans();">粉丝</a></li>
            </ul>
            <div class="uIndexList" style="margin-top: 20px;"></div>
        </div>
    </div>
</div>
<#--页脚-->
<#include "../footer.ftl">
</body>
<script>
    $(function () {
        article();
    });

    function articleList() {
        $.post('');
    }


    function follow() {
        $(".chooseWen").hide();
        $(".choosefan").show();
        $(".choosefan li:last-child").removeClass("active");
        $(".choosefan li:first-child").addClass("active");

        $(".uIndexList").html("<div class=\"follow\">\n" +
                "                    关注的人\n" +
                "                </div>");
    }

    function fans() {
        $(".chooseWen").hide();
        $(".choosefan").show();
        $(".choosefan li:first-child").removeClass("active");
        $(".choosefan li:last-child").addClass("active");
        $(".uIndexList").html("<div class=\"fans\">\n" +
                "                    粉丝\n" +
                "                </div>");
    }

    function comment() {
        $(".chooseWen").show();
        $(".choosefan").hide();
        $(".chooseWen li:first-child").removeClass("active");
        $(".chooseWen li:last-child").addClass("active");
        $(".uIndexList").html("<div class=\"comment\">\n" +
                "                    评论\n" +
                "                </div>");
    }

    function article() {
        $(".chooseWen").show();
        $(".choosefan").hide();
        $(".chooseWen li:last-child").removeClass("active");
        $(".chooseWen li:first-child").addClass("active");
        $(".uIndexList").html("<div class=\"article\">\n" +
                "                        <table class=\"table table-hover\">\n" +
                "                            <tr><th>创建时间</th><th>文章标题</th><th>文章分类</th><th></th></tr>\n" +
                "                            <tr><td>2018-01-01</td><td>设计模式</td><td>JAVA</td><td><button type=\"button\" class=\"btn btn-default btn-xs\"><span>查看</span></button><button type=\"button\" class=\"btn btn-default btn-xs\"><span>管理</span></button></td></tr>\n" +
                "                            <tr><td>2018-01-01</td><td>设计模式</td><td>JAVA</td><td><button type=\"button\" class=\"btn btn-default btn-xs\"><span>查看</span></button><button type=\"button\" class=\"btn btn-default btn-xs\"><span>管理</span></button></td></tr>\n" +
                "                            <tr><td>2018-01-01</td><td>设计模式</td><td>JAVA</td><td><button type=\"button\" class=\"btn btn-default btn-xs\"><span>查看</span></button><button type=\"button\" class=\"btn btn-default btn-xs\"><span>管理</span></button></td></tr>\n" +
                "                            <tr><td>2018-01-01</td><td>设计模式</td><td>JAVA</td><td><button type=\"button\" class=\"btn btn-default btn-xs\"><span>查看</span></button><button type=\"button\" class=\"btn btn-default btn-xs\"><span>管理</span></button></td></tr>\n" +
                "                            <tr><td>2018-01-01</td><td>设计模式</td><td>JAVA</td><td><button type=\"button\" class=\"btn btn-default btn-xs\"><span>查看</span></button><button type=\"button\" class=\"btn btn-default btn-xs\"><span>管理</span></button></td></tr>\n" +
                "                            <tr><td>2018-01-01</td><td>设计模式</td><td>JAVA</td><td><button type=\"button\" class=\"btn btn-default btn-xs\"><span>查看</span></button><button type=\"button\" class=\"btn btn-default btn-xs\"><span>管理</span></button></td></tr>\n" +
                "                        </table>\n" +
                "                </div>");
    }
</script>
</html>