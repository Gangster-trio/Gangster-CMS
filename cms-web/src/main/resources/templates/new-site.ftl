<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>${site.siteName}</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


</head>

<body data-gr-c-s-loaded="true">

    <#macro showTree Tree>
        <#if Tree.children??>
            <li class="dropdown">
                <a href="/view/category/${Tree.id}#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-haspopup="true"
                   aria-expanded="false">
                    ${Tree.name}
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <#list Tree.children as t>
                    <@showTree Tree=t/>
                    </#list>
                </ul>
            </li>
        <#else>
            <li>
                <a href="/view/category/${Tree.id}">${Tree.name}</a>
            </li>
        </#if>
    </#macro>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <!-- The mobile navbar-toggle button can be safely removed since you do not need it in a non-responsive implementation -->
            <a class="navbar-brand" href="#">${site.siteName}</a>
        </div>
        <!-- Note that the .navbar-collapse and .collapse classes have been removed from the #navbar -->
        <div id="navbar">
            <ul class="nav navbar-nav">
            <#list categoryTreeList as categoryTree>
                <@showTree Tree = categoryTree/>
            </#list>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="page-header">
    <#--<h1>Non-responsive Bootstrap</h1>-->
    <#--<p class="lead">Disable the responsiveness of Bootstrap by fixing the width of the container and using the-->
    <#--first-->
    <#--grid system tier. <a href="http://getbootstrap.com/getting-started/#disable-responsive">Read the-->
    <#--documentation</a> for more information.</p>-->
    </div>

    <#list articleList as article>
    <div>
        <h3>
            <a href="/view/article/${article.articleId}">${article.articleTitle}</a>
        </h3>
        <p>${article.articleDesc!'none description'}</p>
    </div>
    </#list>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>