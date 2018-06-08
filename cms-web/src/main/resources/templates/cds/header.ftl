<#macro showTree Category>
            <li class="nav-item">
                <a style="font-size: 12px;font-weight: 600;color: #ffffff" class="nav-link"
                   href="/view/category/${Category.categoryId}">${Category.categoryTitle}</a>
            </li>
</#macro>

<#macro header site>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="/${site.siteUrl}">${site.siteName}</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            Menu
            <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">

            <ul class="navbar-nav ml-auto">
                <@cms_type_list cate_type="topBar";category>
                    <#if category.categorySiteId == site.siteId>
                        <@showTree Category=category></@showTree>
                    </#if>
                </@cms_type_list>
            </ul>

        </div>
    </div>
</nav>
<script>
    window.onload = function () {
        $(".dropdown").hover(function () {
                    $(this).children("a").dropdown("toggle")
                },
                function () {
                    $(this).children("a").dropdown("toggle")
                });
    }
</script>
</#macro>

