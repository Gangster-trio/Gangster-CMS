# cms-web模块开发手册

___

## 目录
<!--ts-->
   * [cms-web模块开发手册](#cms-web模块开发手册)
      * [目录](#目录)
      * [模板编写](#模板编写)
         * [站点主页](#站点主页)
            * [内嵌对象](#内嵌对象)
         * [栏目展示](#栏目展示)
            * [内嵌对象](#内嵌对象-1)
         * [文章展示](#文章展示)
            * [内嵌对象](#内嵌对象-2)
         * [问卷调查](#问卷调查)
            * [内嵌对象](#内嵌对象-3)
            * [提交问卷](#提交问卷)
               * [提交选择题](#提交选择题)
               * [提交问答题](#提交问答题)
      * [自定义指令](#自定义指令)
         * [内嵌指令](#内嵌指令)
         * [指令编写](#指令编写)

<!-- Added by: bigmeng, at: 2018-05-31T20:23+08:00 -->

<!--te-->

---

## 模板编写

高级用法参见[自定义指令](#自定义指令)

### 站点主页

* #### 内嵌对象

    _下列对象需通过`${result.对象名}`使用_:

    |   名称 |  作用  |
    |-------|--------|
    |categoryTreeList  | 该站点所有母兔形成的一棵目录树|
    |site             |当前站点对象，包含当前站点的详细信息|
    |indexCategoryList|当前站点设置为放到主页的栏目（即栏目类型为``主页目录``）
    |indexArticleList |当前站点设置为放到主页的文章（即文章类型为``主页文章``）
    |carouseList      |当前站点类型为``轮播图``文章
    |文章或栏目类型     |当文章或栏目的in_home_page为`true`时，该栏目会被添加到result中，<br>可通过{result.类型名}来使用|

### 栏目展示

* #### 内嵌对象

    |        名称     |         作用           |
    |----------------|-----------------------|
    |categoryTreeList| 该站点所有栏目形成的一棵目录树|
    |articleList     | 栏目中包含的文章列表|
    |category        | 当前栏目的详细信息
    |site            | 当前站点的详细信息


### 文章展示

* #### 内嵌对象

    |   名称   |      作用      |
    |---------|----------------|
    |category        | 文章所属栏目的详细信息
    |site            | 文章所属站点的详细信息
    |tagList         | 文章的标签列表
    |categoryTreeList| 栏目树

### 问卷调查

* #### 内嵌对象

    |  名称  |      作用       |
    |--------|-----------------|
    |page    | 包含了题目和选项的问卷调查页面|

* #### 提交问卷

    * ##### 提交选择题

        提交对象为选项的ID列表

        以`post`方式提交到`/view/survey/submit/check`

    * ##### 提交问答题

        提交对象为键值对形式

        key为题目ID，value为用户输入的答案

        使用`Post`方式提交到`/view/survey/submit/text`

    ---

## 自定义指令

    自定义指令的使用格式:

    <@指令名称 [[参数=]参数]...;[返回对象]>
      body
    </@指令名称>

    通常，名称以list结尾的指令都可以像`<#list></#list>`一样使用，多次循环.

* ### 内嵌指令

    * `cms_content_list`
    
        通过指定的栏目ID返回该栏目下的文章列表，可以使用类似`<#list>`的方式迭代文章
        
        * 参数
        
            参数名称|作用|默认值|必选？|备注
            :-------|-----|:-----:|:-----:|:----:
            categoryId|指定的栏目ID| |Yes|
            size      |每页的条目数(0代表返回所有条目)|`0`|No|`0`代表返回所有条目
            page      |页码数    |`0`|No|
            sort      |排序方式|`article_create_time`|No|数据库中的字段名
            
       * 示例
       
            ```
            <@cms_content_list categoryId=10;article>
                <li>
                    <a href="/view/article/${article.articleId}">
                        ${article.articleTitle}
                    </a>
                </li>
            </@cms_content_list>     
            ```
        
    * `cms_type_list`
    
        返回指定类型的栏目或文章
        
        * 参数
        
            参数名称|作用|默认值|必选？|备注
            -------|-----|:-----:|:-----:|:----:
            cate_type|指定的栏目类型| |No|二者仅能指定一个
            article_type|指定文章类型| |No
            size      |每页的条目数(0代表返回所有条目)|`0`|No|
            page      |页码数    |`0`|No|
            sort      |排序方式(数据库中的字段名)|`article_create_time`|No
            
        * 示例
        
            ```
            <@cms_type_list cate_type="最新动态" sort="category_update_time";category>
                ${category.categoryTitle}
            </@cms_type_list>
            ```
        
    * `cms_index_article`
        
        返回主页文章列表，即类型为`主页文章`的文章列表
        
        参数用法同`cms_type_list`
        
    * `cms_index_category`
        
        返回主页栏目列表，即类型为`主页栏目`的栏目列表
        
        参数用法同`cms_type_list`
        
    * `cms_article`
    
        返回指定ID的文章
        
        * 参数列表
            
            参数名称|作用|默认值|必选？
            ----|----|:----:|:----:
            id|指定文章ID| |Yes
            blob|是否返回文章内容等大字段|false|No
        
        * 示例
            
            ```
            <@cms_article id=2333;articel>
                ${article.articleContent}
            </@cms_article>
            ```
     
    * `cms_category`
    
        返回指定ID的栏目
        
        * 参数列表
            
            参数名称|作用
            ----|----
            id|指定栏目ID
            
    * `cms_survey`

        返回指定ID的调查问卷
        
        * 参数列表
        
            参数名称|作用
            -----|-----
            id|指定问卷ID
        
        返回的问卷包含问卷的基本信息和问卷中所有题目的列表
        
        每个题目包含该题目所有的选项列表
        
        问卷调查结果提交参见[链接](#问卷调查)
         
    * `cms_outerchain`
        
        返回指定站点的所有外链
        
        * 参数列表
            
            参数名称|作用
            ----|----
            siteId|指定站点的ID
            
* ### 指令编写

    如果要添加自定义指令获取更加丰富的功能，可以通过在`com.gangster.cms.web.directive`包中添加实现了`TemplateDirectiveModel`接口的类，并在`com.gangster.web.conf.DirectiveConfig`类中注册指令使之生效
    
    具体可参看JavaDoc
