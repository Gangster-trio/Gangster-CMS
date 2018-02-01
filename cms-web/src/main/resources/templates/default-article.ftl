<html lang="zh-CN" class="gr__v3_bootcss_com">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>${article.articleTitle!}</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/default-article.css" rel="stylesheet">
</head>

<body data-gr-c-s-loaded="true">

<#--<div class="blog-masthead">-->
<#--<div class="container">-->
<#--<nav class="blog-nav">-->
<#--<a class="blog-nav-item active" href="#">Home</a>-->
<#--<a class="blog-nav-item" href="#">New features</a>-->
<#--<a class="blog-nav-item" href="#">Press</a>-->
<#--<a class="blog-nav-item" href="#">New hires</a>-->
<#--<a class="blog-nav-item" href="#">About</a>-->
<#--</nav>-->
<#--</div>-->
<#--</div>-->

<div class="container">

    <div class="blog-header">
        <h1 class="blog-title">${(category.categoryTitle)!}</h1>
        <p class="lead blog-description">${(category.categoryDesc)!}</p>
    </div>

    <div class="row">

        <div class="col-sm-8 blog-main">

            <div class="blog-post">
                <h2 class="blog-post-title">${article.articleTitle}</h2>
                <p class="blog-post-meta">${(article.articleCreateTime?string("yyyy-MM-dd"))!} by <a
                        href="#">${article.articleAuthor}</a></p>

                <p>${article.articleDesc!"none description"}</p>
                <hr>
            ${article.articleContent!"none content"}
            </div><!-- /.blog-post -->

        <#--<nav>-->
        <#--<ul class="pager">-->
        <#--<li><a href="#">Previous</a></li>-->
        <#--<li><a href="#">Next</a></li>-->
        <#--</ul>-->
        <#--</nav>-->

        </div><!-- /.blog-main -->

        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
            <div class="sidebar-module sidebar-module-inset">
                <h4>About</h4>
                <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet
                    fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
            </div>
            <div class="sidebar-module">
                <h4>Tag</h4>
                <ol class="list-unstyled">
                    <#list tagList as tag>
                        <li><a href="/view/tag/${tag.tagId}">${tag.tagName}</a></li>
                    </#list>
                </ol>
            </div>
            <div class="sidebar-module">
                <h4>Elsewhere</h4>
                <ol class="list-unstyled">
                    <li><a href="#">GitHub</a></li>
                    <li><a href="#">Twitter</a></li>
                    <li><a href="#">Facebook</a></li>
                </ol>
            </div>
        </div><!-- /.blog-sidebar -->

    </div><!-- /.row -->

</div><!-- /.container -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
</body>
</html>