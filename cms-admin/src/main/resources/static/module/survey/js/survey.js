var choiceOptionStr = "     <div  class=\"layui-form-item\">\n" +
    "                <label class=\"layui-form-label\">选项：</label>\n" +
    "                <br/>\n" +
    "                <div class=\"layui-input-block\">\n" +
    "                    <input class=\"layui-input\" type=\"text\" style=\"width: 60%\" name=\"optionContent\" lay-verify=\"title\"\n" +
    "                           autocomplete=\"off\"\n" +
    "                           placeholder=\"选项\">\n" +
    "                    <button onclick='deleteChoiceOption(this)' class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\">&#xe640;</i></button>\n" +
    "                </div>\n" +
    "            </div>";

var choiceStr = "    <div  class='choice'>\n" +
    "            <div>\n" +
    "                <div class=\"layui-form-item\">\n" +
    "                    <label class=\"layui-form-label\">问题标题</label>\n" +
    "                    <div class=\"layui-input-block\">\n" +
    "                        <input style='width: 60%' type=\"text\"  name=\"topicQuestion\" lay-verify=\"title\" autocomplete=\"off\"\n" +
    "                               placeholder=\"请输入问题的标题\" class=\"layui-input\">\n" +
    "                    </div>\n" +
    "\n" +
    "                </div>\n" +
    "\n" +
    "                <div class=\"layui-form-item\">\n" +
    "                    <label class=\"layui-form-label\">问题类型</label>\n" +
    "                    <div style='width: 55%' class=\"layui-input-block\">\n" +
    "                        <select style=\"width: 60%\" name=\"topicType\" lay-verify=\"required\">\n" +
    "                            <option value=\"\"></option>\n" +
    "                            <option value=\"单选\">单选</option>\n" +
    "                            <option value=\"多选\">多选</option>\n" +
    "                        </select>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "\n" +
    "                <div  class=\"layui-form-item\">\n" +
    "                    <label class=\"layui-form-label\">选项：</label>\n" +
    "                    <br/>\n" +
    "                    <div  class=\"layui-input-block\">\n" +
    "                        <input class=\"layui-input\" type=\"text\" style=\"width: 60%\" name=\"optionContent\" lay-verify=\"title\"\n" +
    "                               autocomplete=\"off\"\n" +
    "                               placeholder=\"选项\">\n" +
    "                        <button onclick=\"deleteChoiceOption(this)\"\n" +
    "                                class=\"layui-btn layui-btn-primary layui-btn-sm\"><i\n" +
    "                                class=\"layui-icon\">&#xe640;</i></button>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "            <!--添加选项-->\n" +
    "            <div class=\"layui-form-item\">\n" +
    "                <div class=\"layui-input-block\">\n" +
    "                    <button type='button' onclick=\"addChoiceOption(this)\" \"\n" +
    "                            class=\"layui-btn layui-btn-primary layui-btn-sm\"><i class=\"layui-icon\">&#xe608;</i>\n" +
    "                    </button>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "            <!--删除题目-->\n" +
    "            <div class=\"layui-form-item\">\n" +
    "                <div class=\"layui-input-block\">\n" +
    "                    <button onclick=\"deleteQuestion(this)\" class=\"layui-btn\">删除题目\n" +
    "                    </button>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>\n";

var qAStr = "     <div  class='choice' \">\n" +
    "            <div >\n" +
    "                <div class=\"layui-form-item\">\n" +
    "                    <label class=\"layui-form-label\">问题标题</label>\n" +
    "                    <div class=\"layui-input-block\">\n" +
    "                        <input type=\"text\" style=\"width: 60%\" name=\"topicQuestion\" lay-verify=\"title\" autocomplete=\"off\"\n" +
    "                               placeholder=\"请输入问题的标题\" class=\"layui-input\">\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "\n" +
    "                <div class=\"layui-form-item\">\n" +
    "                    <label class=\"layui-form-label\">问题类型</label>\n" +
    "                    <div style=\"width: 55%\" class=\"layui-input-block\">\n" +
    "                        <select name=\"topicType\" lay-verify=\"required\">\n" +
    "                            <option value=\"问答题\">问答题</option>\n" +
    "                        </select>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "            <!--删除题目-->\n" +
    "            <div class=\"layui-form-item\">\n" +
    "                <div class=\"layui-input-block\">\n" +
    "                    <button onclick='deleteQuestion(this)' class=\"layui-btn\">删除题目\n" +
    "                    </button>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>";

// 添加单项选项
function addChoiceOption(ele) {
    // 获取到父亲的父亲的上一个兄弟节点的id
    // var operationId = $(ele).parent().parent().prev().attr('id');
    // $("#" + operationId + "").append(choiceOptionStr);
    // $(ele).parent().parent().prev().append(choiceOptionStr);
    $(ele).parent().parent().prev().append(choiceOptionStr);
}


// 删除单选项
function deleteChoiceOption(ele) {
    // var parentId = $(ele).parent().parent().attr('id');
    // $("#" + parentId + "").remove();
    $(ele).parent().parent().remove();
}


// 添加选择题
function addChoice(str) {
    if (str === undefined) {
        $("#addQuestionForm").append(choiceStr);
    } else {
        $("#addQuestionForm").append(str);
    }
    form.render();
}

// 添加问答题
function addQA(str) {
    if (str === undefined) {
        $("#addQuestionForm").append(qAStr);
    } else {
        $("#addQuestionForm").append(str);
    }
    form.render();
}

// 删除问题
function deleteQuestion(ele) {
    $(ele).parent().parent().parent().remove();
}


function generateData() {

    // page的数据
    var page = {};
    var survey_title = $("#surveyTitle").val();
    var valid_date = $("#validDate").val();
    var valid_dates = valid_date.split(" ~ ");
    var site_id = siteId;
    if (pageId === undefined) {
        page["pageId"] = "";
    } else {
        page["pageId"] = pageId;
    }
    page["pageTitle"] = survey_title;
    page["pageCreateTime"] = valid_dates[0];
    page["pageEndTime"] = valid_dates[1];
    page["pageSiteId"] = site_id;

    // topicList的数据
    var topic_list = [];
    var choice_question = $(".choice");
    choice_question.each(function (i, e) {
        var topic_question = $(this).find("input[name='topicQuestion']").val();
        var topic_type = $(this).find("select[name='topicType']").val();
        var topicId = $(this).find("input[name='topicId']").val();
        if (topicId === undefined) {
            topicId = "";
        }
        var topic = {
            "topicId": topicId,
            "topicQuestion": topic_question,
            "topicType": topic_type,
            "topicPageId": pageId
        };

        var options = $(this).find("input[name='optionContent']");
        var options_List = [];
        options.each(function (i, e) {
            var optionId;
            var nextName = $(this).next().get(0).tagName;
            if (nextName === 'INPUT') {
                optionId = $(this).next().val();
            } else {
                optionId = "";
            }

            var option = {"optionId": optionId, "optionContent": $(this).val(), "topicId": topicId, "optionCount": 0};
            options_List.push(option);
        });

        var single_topic = {"topic": topic, "optionList": options_List};
        topic_list.push(single_topic);
    });

    return JSON.stringify({"page": page, "topicList": topic_list});

}

function addPage() {

    console.log(generateData());
    $.ajax({
        type: 'POST',
        url: '/survey/page/add',
        data: generateData(),
        async: false,
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
            showAtRight("/module/survey/listSurveyPage.html");
        }
    });
}

function updatePage() {

    console.log(generateData());
    $.ajax({
        type: 'POST',
        url: '/survey/page/update',
        data: generateData(),
        async: false,
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
                    layer.msg("更新失败", {icon: 5});
                    break;
                default:
            }
            showAtRight("/module/survey/listSurveyPage.html");
        }
    });
}