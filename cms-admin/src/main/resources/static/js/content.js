function generate_category_data() {
    var category_title = $("#categoryTitle").val();
    // no
    var category_order = $("#categoryOrder").val();
    // no
    // var category_type = $("select[name=categoryType] option[selected]").text();
    var category_type = $("#categoryType").val();
    alert(category_type);
    var category_skin = $("#categorySkin").val();
    var category_parent_id = $("#category_id").val();
    // var category_in_homepage = $("input[name=categoryInHomepage][checked]").val();
    var category_in_homepage = $("#categoryInHomepage").val();
    var category_desc = $("#categoryDesc").val();
    var category = {
        "categoryId": "",
        "categoryTitle": category_title,
        "categoryCreateTime": new Date().getTime(),
        "categoryUpdateTime": "",
        "categoryParentId": category_parent_id,
        "categoryLevel": "",
        "categorySiteId": siteId,
        "categoryStatus": "",
        "categoryDesc": category_desc,
        "categoryOrder": category_order,
        "categorySkin": category_skin,
        "categoryType": category_type,
        "categoryInHomepage": category_in_homepage,
        "categoryHit": 0
    };
    return JSON.stringify(category);

}

function add_category() {
    $.ajax({
        type: "POST",
        url: "/category/add",
        data: generate_category_data(),
        contentType: 'application/json',
        dataType: 'json',
        error: function (resp) {
            layui.layer.alert("failed " + resp);
        },
        success: function (resp) {
            switch (resp.code) {
                case 0:
                    layer.msg(resp.message, {icon: 6});
                    break;
                case 1:
                    layer.msg("添加失败", {icon: 5});
                    break;
                default:
            }
            showAtRight("/module/category/listCategory.html");
        }
    });
}

function update_category() {
    $.ajax({
        type: "POST",
        url: "/category/update/" + categoryId,
        data: generate_category_data(),
        contentType: 'application/json',
        dataType: 'json',
        error: function (resp) {
            layui.layer.alert("failed " + resp);
        },
        success: function (resp) {
            switch (resp.code) {
                case 0:
                    layer.msg(resp.message, {icon: 6});
                    break;
                case 1:
                    layer.msg("添加失败", {icon: 5});
                    break;
                default:
            }
            showAtRight("/module/category/listCategory.html");
        }
    });
}

function generate_article_data() {
    var article = {
        "article_id": $("#articleId").val(),
        "article_title": $("#articleTitle").val(),
        "article_Type": $("#articleType").val(),
        "article_Author": $("#articleAuthor").val(),
        "article_url": "",
        "article_order": $("#articleOrder").val(),
        "article_siteId": getCookie("siteId"),
        "article_categoryId": $("#category_id").val(),
        "articleCreateTime": new Date().getTime(),
        "articleUpdateTime": "",
        "articleThumb": $("#articleThumb").val(),
        "articleHit": 0,
        "articleDesc": $("#articleDesc").val(),
        "articleStatus": 0,
        "articleSkin": $("#articleSkin").val(),
        "articleInHomepage": "",
        "articleReleaseTime": new Date().getTime(),
        "articleReleaseStatus": "",
        "articleContent": layedit.getContent(editor_text)
    };
    return JSON.stringify({
        "article": article,
        "categoryName": $("#belongCategory").val(),
        "tags": $("#tags").val(),
        "files": $("#fileNames").val()
    });
}

function update_article() {
    $.ajax({
        type: 'post',
        url: '/article/update/' + articleId,
        data: generate_article_data(),
        async: false,
        contentType: "application/json",
        dataType: 'json',
        error: function (resp) {
            layui.layer.alert("failed. " + resp.message)
        },
        success: function (resp) {
            layui.layer.alert(resp.message);
            showAtRight("/module/article/listArticle.html");
        }
    });
}