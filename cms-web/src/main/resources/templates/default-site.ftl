<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${result.site.siteName!'No title'}</title>

    <!-- Bootstrap core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<#--<!-- Bootstrap core CSS &ndash;&gt;-->
<#--<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">-->

    <!-- Custom fonts for this template -->
<#--<link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">-->
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet'
          type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="/css/clean-blog.min.css" rel="stylesheet">

</head>

<body>

<#import "default-header.ftl" as header/>
<@header.header site=result.site TreeList=result.categoryTreeList></@header.header>

<!-- Page Header -->
<header class="masthead" style="background-image: url('/img/home-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <h1>${result.site.siteName!'No Site Name'}</h1>
                    <span class="subheading">${result.site.siteDesc!'No Description'}</span>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="container">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
        <#list result.carouselList as carousel>
            <li data-target="#myCarousel" <#if carousel_index == 0>class="active"</#if>
                data-slide-to="${carousel_index}"></li>
        </#list>
        </ol>
        <div class="carousel-inner">
        <#list result.carouselList as carousel>
            <div <#if carousel_index == 0>class="carousel-item active" <#else>class="carousel-item"</#if>>
                <a href="/view/article/${carousel.articleId}">
                    <img style="width: 1200px;height: 500px;" src="${carousel.articleThumb!}"
                         alt="${carousel.articleTitle!"have no title!"}">
                </a>
                <div class="carousel-caption">${carousel.articleTitle!"have no title"}</div>
            </div>
        </#list>
            <!-- 轮播（Carousel）导航 -->
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="icon-prev" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="icon-next" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>


    <div class="row">
    <@cms_index_category ret="cate_list";category>
        <@cms_content_list categoryId=category.categoryId size=3;article>
                <div class="col-sm-4" style="border-radius:.4rem; margin:20px 0px 20px 0px">
                    <h3><a href="/view/category/${category.categoryId}">${category.categoryTitle}</a></h3>
                    <a href="/view/article/${article.articleId}">${article.articleTitle}</a><br>
                </div>
        </@cms_content_list>
    </@cms_index_category>

        <div class="mx-auto">
            <hr>
        <#list result.articleList as article>

            <div>
                <a href="/view/article/${article.articleId}">
                    <h5 class="">
                        ${article.articleTitle!"No title"}
                    </h5>
                    <p class="">
                        ${article.articleDesc!"No description"}
                    </p>
                </a>
                <p class="post-meta">Posted by ${article.articleAuthor!"anonymous"}
                    <!-- <a href="/view/article/${article.articleId}">Start Bootstrap</a> -->
                    on ${(article.articleCreateTime?string("yyyy年MM月dd"))!}</p>
            </div>
            <hr>

        </#list>

            <!-- Pager -->
            <!-- <div class="clearfix">
                <a class="btn btn-primary float-right" href="#">Older Posts &rarr;</a>
            </div> -->
        </div>
    </div>
</div>

<div>
    <div>

    </div>
</div>

<hr>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <ul class="list-inline text-center">
                    <li class="list-inline-item">
                        <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                  </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                  </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                  </span>
                        </a>
                    </li>
                </ul>
                <p class="copyright text-muted">Copyright &copy; Your Website 2018</p>
            </div>
        </div>
    </div>
</footer>

<#--<!-- Bootstrap core JavaScript &ndash;&gt;-->
<#--<script src="/vendor/jquery/jquery.min.js"></script>-->
<#--<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>-->

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-carousel.min.js"></script>
<!-- Custom scripts for this template -->
<#--<script src="/js/clean-blog.min.js"></script>-->
</body>

<script>
    $(function () {
        $('.carousel').carousel()
    })
</script>

</html>
