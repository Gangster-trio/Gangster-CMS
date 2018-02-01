<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${site.siteName}</title>
</head>
<body>

    <#macro showTree Tree>
        <#if (Tree.children)??>
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
                <#--<#if article.articleCreateTime??>-->
                    ${article.articleCreateTime?date!}
                <#--</#if>-->
            </a>
        </#list>
    </div>
</div>
    </#list>
<hr>

<#list articleList as article>
    <div>
        <h1>${article.articleTitle}</h1>
        <p>
            <#if article.articleDesc??>
                ${article.articleDesc}
            </#if>
        </p>
    </div>
</#list>
</body>
</html>