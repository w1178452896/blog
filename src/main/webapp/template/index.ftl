<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>blog</title>
</head>
<body>
<!--导航栏-->
<#include "navbar.ftl">
<div class="container">
    <div class="row">
        <div class="col-md-8 col-xs-12" id="pbliu">
            <#--巨幕-->
            <#include "thumbnail.ftl">
                <div class="contentTitle"><span>热门文章 ></span></div>
            <#--首页内容-->
            <#include "list/blogVos.ftl">
        </div>
        <div class="col-md-3 hidden-sm hidden-xs">
            <#--右侧-->
            <#include "sider.ftl">
        </div>
    </div>
    <#--自动加载-->
    <div class="row" id="more">
        <div class="col-md-8 col-xs-12 text-center">
            <span class="fa fa-spinner fa-pulse fa-3x text-success"></span>
            <button class="btn btn-info" style="display: none" onclick="pull()"><span class="fa fa-arrow-down"></span>更多文章</button>
        </div>
    </div>
</div>
<a href="javascript:(0);" class="text-center" id="topButton">
    <span class="fa fa-2x fa-chevron-up"></span>
    <script>
        var backButton=$('#topButton');
        function backToTop() {
            $('html,body').animate({
                scrollTop: 0
            }, 400);
        }
        backButton.on('click', backToTop);

        $(window).on('scroll', function () {/*当滚动条的垂直位置大于浏览器所能看到的页面的那部分的高度时，回到顶部按钮就显示 */
            if ($(window).scrollTop() > $(window).height())
                backButton.fadeIn();
            else
                backButton.fadeOut();
        });
        $(window).trigger('scroll');
    </script>
</a>
<#include "footer.ftl">
</body>
<script>
    $('.footer').css('visibility', 'hidden');
    /*以下瀑布流文章加载*/
    // 自动加载次数
    var flush = 5;
    //计数器
    var count = 1;
    var pageNum = 2;
    var isbool = true;
    function pull() {
        if (count != 9999) {
            $.ajax({
                url : "/pull",
                type : "post",
                data : {pageNum : pageNum},
                success : function (data) {
                    if (data.length > 0) {
                        for (var i = 0; i < 5; i++) {
                            var blogVo = data[i];
                            $('#pbliu').append('<div class="content"><div class="text-left"><b>作者：<i><span>' + blogVo.user.username + '</span></i></b></div><div class="media"><div class="media-body"><div class="media-heading"><a href="/blog/' + blogVo.blog.id + '" target="_blank">' + blogVo.blog.title + '</a></div><div class="content-p"><p>' + blogVo.blog.content + '......</p></div><div>收藏&nbsp;<span class="label label-primary">' + blogVo.userCount + '</span> &nbsp;|&nbsp;<span class="label label-info">' + blogVo.blog.createTime + '</span></div></div><div class="media-right media-middle"><a href="#"><div class="media-object authorHead"><img src="/resources/head.png" alt="图像无法显示"></div></a><br><button class="btn btn-default btn-sm pull-right"><span class="glyphicon glyphicon-heart" style="color: red"></span> 关注</button></div></div></div>');
                        }
                    } else {
                        count = 9999;
                        $('#more span').remove();
                        $('#more button').html('暂无更多文章').show();
                        $('.footer').css('visibility', 'visible');
                    }
                }
            });
            pageNum++;
            count++;
        }
    }
    $(window).scroll(function () {
        var bottom = $(document).height();
        var scrollTop = $(document).scrollTop();
        var windowHeight = $(window).height();
        if (bottom - scrollTop - windowHeight == 0 && count <= flush) {
            pull();
        }
        if (count > flush) {
            $('#more span').remove();
            $('#more button').show();
            $('.footer').css('visibility', 'visible');
        }
    });
</script>
</html>