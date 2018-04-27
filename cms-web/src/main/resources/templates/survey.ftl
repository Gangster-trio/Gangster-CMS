<!DOCTYPE html>
<html lang="zh">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

    <title>result.page.questionPageTitle</title>
</head>

<body>
<div>
    <#list result.page.topicList as topic>
        <table>
            <tr>
                <td>${topic.topicQuestion}<br></td>
            </tr>
            <tr>
                <td>
                     <#if topic.topicType == "单选">
                         <#list topic.optionList as option>
                            <label><input class="survey_check" name="${topic.topicId}" type="radio"
                                          value="${option.optionId}"/>${option.optionContent}</label>
                         </#list>
                     </#if>
                     <#if topic.topicType =="多选">
                         <#list topic.optionList as option>
                            <label><input class="survey_check" name="${topic.topicId}" type="checkbox"
                                          value="${option.optionId}"/>${option.optionContent}</label>
                         </#list>
                     </#if>
                    <#if topic.topicType == "问答">
                    <label>
                        <input class="survey_text" name="${topic.topicId}"/>
                    </label>
                    </#if>
                </td>
            </tr>
        </table>
    </#list>
    <input type="button" value="click me!" onclick="uploadData()">
</div>
<script>
    var CHECK_SUBMIT_URL = "/view/survey/submit/check";
    var TEXT_SUBMIT_URL = "/view/survey/submit/text";

    function uploadData() {
        var upload_check = $(".survey_check:checked");
        var upload_text = $(".survey_text");
        console.log(upload_check);
        var check_arr = [];
        upload_check.each(function (i, e) {
            check_arr.push($(this).val());
        });
        var text_map = {};
        upload_text.each(function (i, e) {
            text_map[$(this).attr("name")] = $(this).val();
        });
        console.log(check_arr);
        console.log(text_map);
        var check_json = JSON.stringify(check_arr);
        var text_json = JSON.stringify(text_map);
        // $.post(CHECK_SUBMIT_URL,check_json);
        // $.post(TEXT_SUBMIT_URL,text_json);
        $.ajax({
            type:"post",
            url:CHECK_SUBMIT_URL,
            data:check_json,
            contentType:"application/json"
        });
        $.ajax({
            type:"post",
            url:TEXT_SUBMIT_URL,
            data:text_json,
            contentType:"application/json"
        });
        alert("Okay.")
    }
</script>
</body>
