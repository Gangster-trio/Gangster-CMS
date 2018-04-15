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
function showAtRight(url) {
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
            // window.location.href = url;
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

/*function setRadioValue(name,value) {
    var radio = document.getElementsByName(name);
    for (var i=0;i<radio.length;i++){
        if (radio[i].value()===value){

        }
    }
}*/

//对操作进行判断是否具有操作权限
function judgePrivilege(siteId, categoryId) {
    var operation = true;
    $.ajax({
        url: '/privilege/category?siteId=' + siteId + '&categoryId=' + categoryId,
        dataType: "json",
        async: false,
        type: "get",
        success: function (data) {
            if (data.code === 2) {
                operation = false;
                layer.msg("sorry ,you have not privilege");
            }
        }
    });
    return operation;
}

//判断对站是否具有一系列的操作
function judgeSitePrivilege(siteId) {
    var operation = true;
    $.ajax({
        url: '/privilege/site?siteId=' + siteId,
        dataType: 'json',
        async: false,
        type: 'get',
        success: function (data) {
            if (data.code === 2) {
                operation = false;
                layer.msg("sorry,you have no privilege");
            }
        }
    });
    return operation;
}

function judgeadmin() {
    var judge = true;
    $.ajax({
        url: '/user/api/judge',
        dataType: 'json',
        async: false,
        type: 'get',
        success: function (data) {
            console.log("传过来的验证消息" + data);
            judge = data.data;
        }
    });
    return judge;
}