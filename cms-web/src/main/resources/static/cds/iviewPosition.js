var tt_ivew=(function(){
    var windowWidth,
        objHeight,
        attrArr=[],
        returnArr=[];
    function init(obj){
        getAttr(obj);
        getReturn();
    }
    /*初始化参数*/
    function getAttr(obj){
        var i=0,
            len=obj.length;
        for(i;i<len;i++){
            attrArr[i]={};
            attrArr[i].left=obj[i].left||0;
            attrArr[i].top=obj[i].top||0;
            attrArr[i].isPercent=obj[i].isPercent||false;
            attrArr[i].container=document.getElementById(obj[i].container)||document.getElementById("iview");
            attrArr[i].item=document.getElementById(obj[i].id)||null;
            if(attrArr[i].container){
                getSize();
            }
        }
    }
    /*获取各种尺寸*/
    function getSize(){
        windowWidth=document.documentElement.clientWidth||document.body.clientWidth;
        objHeight=parseInt(getStyle(document.getElementById("iview"),'height'));
    }

    /*获取样式函数*/
    function getStyle(obj,attr){
        if(window.getComputedStyle){
            return getComputedStyle(obj,false)[attr];
        }
        else if(attr=='height'){
            return obj.clientHeight
        }
        else if(attr=='width'){
            return obj.clientWidth;
        }
        else{
            return obj.currentStyle[attr];
        }
    }

    /*把百分比换成像素*/
    function changePercent(index){
        return {left:attrArr[index].left/100*windowWidth,
            top:attrArr[index].top/100*objHeight};
    }

    /*返回像素值*/
    function returnPX(index){
        return {left:attrArr[index].left,
            top:attrArr[index].top};
    }

    function getReturn(){
        var i=0,
            len=attrArr.length;
        for(i;i<len;i++){
            returnArr[i]=attrArr[i].isPercent?changePercent(i):returnPX(i);
            changeData(attrArr[i],returnArr[i]);
        }
    }

    /*写入HTML*/
    function changeData(atArr,reArr){
        try{
            if(atArr.item.dataset){
                atArr.item.dataset.x=reArr.left;
                atArr.item.dataset.y=reArr.top;
            }
            else{
                atArr.item.setAttribute('data-x',reArr.left);
                atArr.item.setAttribute('data-y',reArr.top);
            }
        }
        catch(e){
            console.log('请传入ID或传入正确ID');
        }
    }

    /*临时工的入口*/
    function start(){

        var reResize=window.onresize;
        if(reResize){
            window.onresize=function(){
                reResize();
                resizeChange();
            }
        }else{
            window.onresize=function(){
                resizeChange();
            }
        }
        outBox();
    }

    /*resize触发函数*/
    function resizeChange(){
        getSize();
        outBox();
    }

    /*临时工*/
    function outBox(){
        var i=1,
            j=0,
            textLists=[],
            arr=[];
        while(1){
            var textBox=document.getElementById("item"+i);
            if(textBox==null||textBox==undefined){
                break;
            }
            textLists.push(textBox);
            i++;
        }
        for(j;j<textLists.length;j++){
            if(windowWidth>1024){
                arr.push({
                    container:'iview',
                    id:textLists[j].id,
                    left:150,
                    top:objHeight-111,
                    isPercent:false
                });
                textLists[j].style.height=32+'px';
            }
            else if(windowWidth<=1024&&windowWidth>768){
                arr.push({
                    container:'iview',
                    id:textLists[j].id,
                    left:0,
                    top:objHeight-104,
                    isPercent:false
                });
                textLists[j].style.height=24+'px';
            }
            else{
                arr.push({
                    container:'iview',
                    id:textLists[j].id,
                    left:0,
                    top:objHeight-40,
                    isPercent:false
                });
                textLists[j].style.height=16+'px';
            }
            if(navigator.appName=="Microsoft Internet Explorer" &&(navigator.appVersion.split(";")[1].replace(/[ ]/g,"")=="MSIE7.0"||navigator.appVersion.split(";")[1].replace(/[ ]/g,"")=="MSIE8.0")){
            }else{
                textLists[j].style.left=arr[j].left+'px';
                textLists[j].style.top=arr[j].top+'px';
            }
        }
        init(arr);
    }
    return start;
})();
