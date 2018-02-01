<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${site.siteName}</title>
</head>
<body>

    <#macro showTree Tree>
        <#if Tree.spread>
            <div>${Tree.name}</div>
            <#list Tree.children as child>
                <@showTree Tree=child/>
            </#list>
        </#if>
    </#macro>

    <#list categoryTreeList as categoryTree>
        <@showTree Tree = categoryTree/>
    </#list>




    <#list categoryWithArticleList as categoryWithArticle>
<div>
    栏目标题${categoryWithArticle.categoryTitle}
    <div>
        <#list categoryWithArticle.articleList as article>
            <a href="/view/article/${article.articleId}">
                ${article.articleTitle}
                ${article.articleCreateTime}
            </a>
        </#list>
    </div>
</div>
    </#list>
</body>
</html>