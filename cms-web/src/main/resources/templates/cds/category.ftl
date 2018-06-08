<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${result.category.categoryTitle!'No title'}</title>


<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet'
          type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="${requst.contextPath}/${BaseSkinPath}/clean-blog.min.css" rel="stylesheet">

</head>

<body>

    <#import "header.ftl" as header/>
    <@header.header site=result.site></@header.header>
<!-- Page Header -->
<header class="masthead"
        style="background-image: url('${result.category.categoryThumb!'/pic/43656d72-5def-4228-b1e5-28f328dd4480.jpg'}')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <h1>${result.category.categoryTitle!'No title'}</h1>
                    <span class="subheading">${result.category.categoryDesc!'No Description'}</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">

          <#if result.articleList?size==0 >
              No Article.
          </#if>

          <#list result.articleList as article>

          <div class="post-preview">
              <a href="/view/article/${article.articleId}">
                  <h2 class="post-title">
                      ${article.articleTitle!"No title"}
                  </h2>
                  <h3 class="post-subtitle">
                      ${article.articleDesc!"No description"}
                  </h3>
              </a>
              <p class="post-meta">Posted by ${article.articleAuthor!"anonymous"}
                  <!-- <a href="/view/article/${article.articleId}">Start Bootstrap</a> -->
                  on ${(article.articleCreateTime?string("yyyy年MM月dd"))!}</p>
          </div>
          <hr>

          </#list>

            <!-- Pager -->
            <div class="clearfix">
                <a class="btn btn-primary float-right" href="#">Older Posts &rarr;</a>
            </div>
        </div>
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
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
<!-- Custom scripts for this template -->
<#--<script src="/js/clean-blog.min.js"></script>-->
</body>

</html>
