<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="/css/video.css" rel="stylesheet">
</head>
<body>


<div>
    <h1 style="text-align: center">${article.articleDesc}</h1>
</div>

<div style="margin-left: auto;margin-right: auto">
    <video style="margin-left: auto;margin-right: auto" id="my-video" class="video-js" controls preload="auto"
           width="700px"
           height="264px"
           poster="http://vjs.zencdn.net/v/oceans.png" data-setup="{}">
            <#list files as file>
            ${file.fileName}
            <#--<source src="http://vjs.zencdn.net/v/oceans.mp4" type='video/mp4'/>-->
            <source src="${file.fileName}" type='video/mp4'/>
            </#list>

        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading to a web browser that
            <a href="http://videojs.com/html5-video-support/video.ftl" target="_blank">supports HTML5 video</a>
        </p>
    </video>
</div>
<script src="/js/video.js"></script>
</body>
</html>