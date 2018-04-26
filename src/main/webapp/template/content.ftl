<#list blogVoList as blogVo>
<div class="content">
    <div class="text-right">
        <span>${blogVo.user.username}</span>
    </div>
    <div class="media">
        <div class="media-body">
            <div class="media-heading">${blogVo.blog.title}</div>
            <div class="content-p">
                <p>${blogVo.blog.content}</p>
            </div>
        </div>
        <div class="media-right media-middle">
            <a href="#">
                <!--<img class="media-object" src="..." alt="...">-->
                <div class="media-object authorHead">
                    <img src="/resources/head.png" alt="图像无法显示">
                </div>
            </a>
            <br>
            <button class="btn btn-default btn-sm center-block">
                <span class="glyphicon glyphicon-heart" style="color: red"></span> 关注
            </button>
        </div>
    </div>
    <div.content-info>
        收藏&nbsp;<span class="label label-primary">${blogVo.userCount}</span> &nbsp;|&nbsp;<span
            class="label label-info">${blogVo.blog.createTime}</span>
    </div.content-info>
</div>
</#list>