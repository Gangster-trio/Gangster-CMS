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
    <input type="button" value="click me!" onclick="showData()">
</div>
<script>
    function showData() {
        var upload_check = $(".survey_check:checked");
        var upload_text = $(".survey_text");
        console.log(upload_check);
        upload_check.each(function (i, e) {
            console.log(e);
            alert($(this).val() + ":" + $(this).attr("name"));
        });
        upload_text.each(function (i, e) {
            console.log(e);
            alert($(this).attr("name") + ":" + $(this).val());
        })
    }
</script>
</body>
