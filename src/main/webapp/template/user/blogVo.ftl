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
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <script src="/js/editormd.min.js"></script>
    <script src="/resources/editor/lib/marked.min.js"></script>
    <title>${(blogVo.blog.title)!"文章详情"}</title>
    <style>
        .posts {
            margin-top: 15px;
            min-height: 760px;
        }
        .posts .row {
            margin-bottom: 35px;
        }
        .posts .info span {
            margin-right: 5px;
        }
        #post {
            font-size: 16px;
            font-weight: 400;
            line-height: 1.7;
        }
        .footer {
            text-align: center;
        }
        .postImg {
            min-width: 650px;
            min-height: 400px;
        }
    </style>
</head>
<body>
<!--导航栏-->
<#include "../navbar.ftl">
<#--文章-->
<div class="container posts">
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2" style="padding-left: 80px; padding-right: 80px">
            <div class="row">
                <h1><b>${(blogVo.blog.title)!}</b></h1>
            </div>
            <div class="row">
                <div class="col-sm-1" style="padding-left: 0px">
                    <img class="img-circle" src="/resources/head.png" alt="图像无法显示" width="50px" height="50px">
                </div>
                <div class="col-sm-4">
                    <div class="info">
                        <p>
                            <span>${(blogVo.user.username)!}</span>
                            <button class="btn btn-success btn-xs"><span class="fa fa-plus"></span>关注</button>
                        </p>
                        <p>
                            <span class="label label-info">${(blogVo.blog.createTime)!"2018-01-01"}</span>
                            <span class="label label-primary">收藏 ${(blogVo.userCount)!0}</span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row" id="post">
                <p>文 | ${(blogVo.user.username)!}</p>
                <textarea hidden>${(blogVo.blog.content)!"获取数据失败"}</textarea>
            </div>
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
<#include "../footer.ftl">
</body>
<script>
    //解析markdown
    editormd.markdownToHTML("post", {
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        tocm            : false,
        //tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
        markdownSourceCode : false,
        emoji           : false,
        taskList        : false,
        tex             : false,  // 默认不解析
        flowChart       : false,  // 默认不解析
        sequenceDiagram : false
    });
</script>
</html>