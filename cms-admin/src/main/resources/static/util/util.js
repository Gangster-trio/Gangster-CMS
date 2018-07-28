//得到参数数组
function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

//得到指定参数的value
function getUrlVar(name) {
    return getUrlVars()[name];
}

/*
     * 解决ajax返回的页面中含有javascript的办法：
     * 把xmlHttp.responseText中的脚本都抽取出来，不管AJAX加载的HTML包含多少个脚本块，我们对找出来的脚本块都调用eval方法执行它即可
     */
function executeScript(html) {

    var reg = /<script[^>]*>([^\x00]+)$/i;
    //对整段HTML片段按<\/script>拆分
    var htmlBlock = html.split("<\/script>");
    for (var i in htmlBlock) {
        var blocks;//匹配正则表达式的内容数组，blocks[1]就是真正的一段脚本内容，因为前面reg定义我们用了括号进行了捕获分组
        if (blocks = htmlBlock[i].match(reg)) {
            //清除可能存在的注释标记，对于注释结尾-->可以忽略处理，eval一样能正常工作
            var code = blocks[1].replace(/<!--/, '');
            try {
                eval(code) //执行脚本
            }
            catch (e) {
            }
        }
    }
}

//读取cookies
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

    if (arr = document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

//显示右边的信息
//勿动
function showAtRight(url, push = true, setOldSelector = true) {
    $.ajax({
        url: url,
        type: 'GET',
        async: false,
        dataType: 'html',
        timeout: '5000',
        error: function () {
            alert('can\'t get data from ' + url);
        },
        success: function (data) {
            $("#content").html(data);
            if (push) {
                history.pushState(url, "", '#' + url);
            }
            if (setOldSelector) {
                oldSelectNode = $("[id='" + 'sidebar:' + url + "']");
            }
        }
    });
}

//单选框选择值
function chooseValue(name) {
    var radio = document.getElementsByName(name);
    var selectValue = null;
    for (var i = 0; i < radio.length; i++) {
        if (radio[i].checked === true) {
            selectValue = radio[i].value;
            break;
        }
    }
    return selectValue;
}

function article_file_upload() {
    let upload_btn = $("#upload-file");
    upload_btn.text("上传中...");
    layui.layer.msg("上传中...");
    let avtName = "";
    let fileNames = [];
    $.ajax({
        url: "/upToken",
        type: "get",
        async: false,
        success: function (upToken) {
            let avt = $("#uploadImg")[0].files;
            if (avt.length !== 0) {
                avtName = "pic/" + siteId + "/" + Date.now() + "-" + avt[0].name;
                uploadFileToQiniu(avt[0], upToken, avtName);
            }
            let files = $('#choose-file')[0].files;
            for (let i = 0; i < files.length; i++) {
                let n = "file/" + siteId + "/" + Date.now() + "-" + files[i].name;
                fileNames.push(n);
                uploadFileToQiniu(files[i], upToken, n);
            }
        }
    });
    $("#articleThumb").attr("value", avtName);
    console.log("avtName: " + avtName);
    $("#fileNames").attr("value", fileNames.join(','));
    console.log("fileNames: " + fileNames.join(','));
    layui.layer.msg("上传完成");
    upload_btn.text("重新上传");
}

function uploadFileToQiniu(file, upToken, key) {
    let form = new FormData();
    form.append('file', file);
    form.append('token', upToken);
    if (key !== null && key !== undefined) form.append('key', key);
    $.ajax({
        url: "http://up-z1.qiniu.com",
        type: 'POST',
        async: false,
        contentType: false,
        // crossDomain: true,
        data: form,
        processData: false,
        dataType: "json",
        success: function (data) {
            console.log("Upload Success: " + data);
        },
        error: function (xhr, textStatus, errorThrown) {
            alert("code: " + xhr.status + "resp: " + xhr.responseText);
            console.log("upload", xhr);
            console.log("status", textStatus);
            console.log("error", errorThrown);
        }
    })
}

