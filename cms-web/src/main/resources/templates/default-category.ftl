<html lang="zh_cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>${category.categoryTitle}</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="jumbotron">
    <h1>${category.categoryTitle}</h1>
    <p>${category.categoryDesc!}</p>
</div>
<div class="row">
    <#list articleList as article>
        <div class="col-xs-6 col-lg-4">
            <h2>${article.articleTitle}</h2>
            <p>
                ${article.articleDesc!"none description"}
            </p>
            <p><a class="btn btn-default" href="/view/article/${article.articleId}" role="button">View details »</a></p>
        </div>
    </#list>
</div>
</body>
</html>