<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">
                <span class="logo">Blog</span>
            </a>
        </div>
        <div class="collapse navbar-collapse navbar-right" id="navbar-collapse-1">
            <ul class="nav navbar-nav mynav">
                <#if Session.user ? exists>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="padding-top: 0px; padding-bottom: 0px;">
                            <img src="/resources/head.png" class="img-circle"></a>
                        <ul class="dropdown-menu dropdown-menu-left">
                            <li><a href="user/index"><span class="glyphicon glyphicon-home"></span><span>我的主页</span></a>
                            </li>
                            <li ><a href="user-info"><span class="glyphicon glyphicon-user"></span><span>个人信息</span></a></li>
                            <li ><a href="user-info"><span class="glyphicon glyphicon-eye-open"></span><span>审阅博客</span></a></li>
                            <li ><a href="user-info"><span class="glyphicon glyphicon-comment"></span><span>审阅评论</span></a></li>
                            <li ><a href="/logout"><span class="glyphicon glyphicon-log-out"></span><span>退出</span></a></li>
                        </ul>
                    </li>
                    <li><button class=" btn btn-default navbar-btn"><a href="/pages/editor.html" class="write"><span class="glyphicon glyphicon-pencil"></span> 写文章</a></button></li>
                <#else>
                    <li><a href="/pages/login.html">登录</a></li>
                    <li><a href="/pages/register.html">注册</a></li>
                </#if>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="search">
                    <span class="input-group-addon">search</span>
                </div>
            </form>

        </div>

    </div>

</nav>