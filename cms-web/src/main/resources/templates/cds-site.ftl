<!DOCTYPE html>
<!-- saved from url=(0023)http://cds.tyut.edu.cn/ -->
<html xmlns:data-iview="http://www.w3.org/1999/xhtml" class="gr__cds_tyut_edu_cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${result.site.siteName}</title>


    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <link rel="stylesheet" type="text/css" href="cds/iconfont.css">
    <!--公共样式-->
    <link rel="stylesheet" type="text/css" href="cds/common.css">
    <!--首页样式-->
    <link rel="stylesheet" href="cds/index.css">
    <!--轮播图-->
    <link rel="stylesheet" type="text/css" href="cds/slide.css">
    <!--修复ie78-->
    <!--[if IE 8]>
    <link rel="stylesheet" type="text/css" href="http://cds.tyut.edu.cn/templets/1/cds/new/css/ie78.css"/>
    <![endif]-->
    <!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="http://cds.tyut.edu.cn/templets/1/cds/new/css/ie78.css"/>
    <![endif]-->

<body data-gr-c-s-loaded="true">
<div class="pre-header">
    <div class="container">
        <ul>
            <li>
                <a href="http://cds.tyut.edu.cn/#">
                    <i class="icon iconfont"></i>
                    <span>书记信箱</span>
                </a>
            </li>
            <li>
                <a href="http://cds.tyut.edu.cn/#">
                    <i class="icon iconfont"></i>
                    <span>院长信箱</span>
                </a>
            </li>
            <li>
                <a href="http://mail.tyut.edu.cn/">
                    <i class="icon iconfont"></i>
                    <span>教工信箱</span>
                </a>
            </li>
            <li>
                <a href="http://mail.tyut.edu.cn/">
                    <i class="icon iconfont"></i>
                    <span>学生信箱</span>
                </a>
            </li>
        </ul>
    <#--TODO: search-->
        <div class="search2">
            <form id="searchDataForm" action="http://cds.tyut.edu.cn/cms/9/search.do" method="post">
                <input type="hidden" name="categoryId" value="159" label="栏目编号">
                <!--必须存在-->
                <input type="text" id="search2" name="article_content" class="searchtext">
                <input type="submit" name="submit2" id="submit2" class="submit2 icon iconfont" value="">
            </form>
        </div>
        <div class="clearfloat"></div>
    </div>
</div>
<div class="header clearfix">
    <div class="container">
        <a href="http://www2017.tyut.edu.cn/" class="header-logo header-logo-tyut">
            <img src="cds/logo-tyut3.png">
        </a>
        <a href="http://cds.tyut.edu.cn/" class="header-logo header-logo-bigdata">
            <img src="cds/logo-bigdata.png">
        </a>
    </div>

    <!--首页粒子图-->
    <div class="main-canvas">
        <div id="particles">
            <canvas class="pg-canvas" width="1524" height="260"></canvas>
            <div class="main-canvas-intro">
                <!-- <h1>新科技引领未来，大数据开创时代</h1>-->
                <h1>${result.site.siteDesc}</h1>
                <p>${result.site.siteName}</p>
            </div>
        </div>
    </div>

    <div class="container">

        <!--首页主导航-->
        <#macro showTopBar categoryList>
            <#if categoryList??>
                <#list categoryList as cate>
                <li class="dropdown">
                    <a href="/view/category/${cate.categoryId}"> <span>${cate.categoryTitle}</span></a>
                    <@cms_content_list categoryId=cate.categoryId>
                    <ul class="dropdown-content">
                        <a href="/view/category/${cate.categoryId}"></a>
                        <#list ret as article>
                            <li>
                                <a href="/view/article/${article.articleId}">${article.articleTitle}</a>
                            </li>
                        </#list>
                    </ul>
                    </@cms_content_list>
                </li>
                </#list>
            </#if>

        </#macro>

        <ul class="nav">
            <#if result.topBar??>
                    <@showTopBar categoryList=result.topBar/>
            </#if>
        </ul>

        <div class="index_sideSlip_mask sideSlip_mask_off"></div>
        <div class="clearfloat"></div>
    </div>
</div>
<div class="main2">
    <div class="container clearfix">

    <#--TODO: 轮播图或者文章-->
        <div class="main2-left">
            <img src="cds/1524103175491.png">
            <a href="http://cds.tyut.edu.cn/html/1/159/191/210/519.html"
               class="main2-left-title twoLineOverflowSolution">
                <p class="twoLineOverflowSolution">
                    太原理工大学大数据学院2018年人才招聘启事
                </p>
            </a>
        </div>

        <div class="main2-right">
            <div class="main3-card main3-card1">
                <@cms_type_list cate_type="news">
                    <#list ret as category>
                    <div class="main3-card_title clearfix">
                        <h2>
                            <i class="icon iconfont"></i>
                            ${category.categoryTitle}
                        </h2>
                        <a href="/view/category/${category.categoryId}" class="more">
                            更多
                            <i class="icon iconfont"></i>
                        </a>
                    </div>
                        <@cms_content_list categoryId=category.categoryId>
                            <#list ret as article>
                                <#--<#if article??>-->
                                   <a href="/view/article/${article.articleId}" class="main2-right-item clearfix">
                                      <span class="main2-right-item-title oneLineOverflowSolution">
                                          ${article.articleTitle}
                                      </span>
                                       <span class="main2-right-item-time">${article.articleCreateTime?date}</span>
                                   </a>
                                <#--</#if>-->
                            </#list>
                        </@cms_content_list>
                    </#list>
                </@cms_type_list>
            </div>
        </div>
    </div>
</div>
<!--main2 结束-->


<div class="main3">
    <div class="container clearfix">
         <@cms_type_list cate_type="main3">
             <#list ret as category>
                 <div class="main3-card main3-card1">
                     <div class="main3-card_title clearfix">
                         <h2>
                             <i class="icon iconfont"></i>
                             ${category.categoryTitle}
                         </h2>
                         <a href="/view/category/${category.categoryId}" class="more">更多
                             <i class="icon iconfont"></i>
                         </a>
                     </div>
                     <ul class="main3-card_ul">
                         <@cms_content_list categoryId=category.categoryId>
                             <#list ret as article>
                                 <li>
                                     <a href="/view/article/${article.articleId}">
                                         <div class="rili shortTime">
                                             <span>${article.articleCreateTime?date}</span>
                                             <!-- TODO: 月份 -->
                                             <p>${article.articleCreateTime?string("MM")}</p>
                                         </div>
                                         <div class="main3-card_ul_infos">
                                             <h3>${article.articleTitle}</h3>
                                             <h5>${article.articleDesc}</h5>
                                         </div>
                                         <div class="clearfloat"></div>
                                     </a>
                                 </li>
                             </#list>
                         </@cms_content_list>
                     </ul>
                 </div>
             </#list>
         </@cms_type_list>
    </div>
</div>
<!--main3 结束-->

<!--main4 结束-->
<a href="http://cds.tyut.edu.cn/html/1/159/193/index.html" class="main5">
    <img src="cds/main5-teacher2.png" class="main5-teacher2">
    <img src="cds/main5-teacher.png">
</a>
<!--main5 结束-->
<div class="main6">
    <div class="container clearfix">
        <a href="http://jwc.tyut.edu.cn/" class="main6-pic">
            <img src="cds/main7-pic1.png">
        </a>
        <a href="http://202.207.247.60/" class="main6-pic">
            <img src="cds/main7-pic2.png">
        </a>
        <a href="http://www.gs.tyut.edu.cn/" class="main6-pic">
            <img src="cds/main7-pic3.png">
        </a>
        <a href="http://student.tyut.edu.cn/" class="main6-pic">
            <img src="cds/main7-pic4.png">
        </a>
        <a href="http://gongqingtuan.tyut.edu.cn/" class="main6-pic">
            <img src="cds/main7-pic5.png">
        </a>
        <a href="http://202.207.247.49/" class="main6-pic">
            <img src="cds/main7-pic6.png">
        </a>
        <a href="http://cds.tyut.edu.cn/html/1/159/226/index.html" class="main6-pic">
            <img src="cds/main7-pic7.png">
        </a>
    </div>
</div>
<div class="main7">
    <div class="container clearfix">
        <div class="main7-left main7-card">
            <h2>校内网站</h2>
            <a href="http://www2017.tyut.edu.cn/">太原理工大学</a>
            <a href="http://202.99.199.151:8080/sxsg">高分山西中心</a>
            <a href="http://office.tyut.edu.cn/">太原理工大学校长办公室</a>
            <a href="http://www.lib.tyut.edu.cn/">太原理工大学图书馆</a>
            <a href="http://www.tyut.edu.cn/math/a/index.htm">太原理工大学数学学院</a>
            <a href="http://www.tyut.edu.cn/cs/">太原理工大学软件学院</a>

        </div>
        <div class="main7-right main7-card">
            <h2>关于我们</h2>
            <a>
                <i class="iconfont"></i>tyut-bigdata</a>
            <a>
                <i class="iconfont"></i>668410372</a>
            <a>
                <i class="iconfont"></i>0351-3176792</a>
            <a>
                <i class="iconfont"></i>山西省晋中市榆次区大学街</a>
            <a>
                <i class="iconfont"></i>209号太原理工大学明向校区</a>
        </div>
        <img src="cds/main7-logo-bigdata.png" class="main7-logo">
    </div>
</div>
<!--main7 结束-->
<div class="main8">
    <div class="container clearfix">
        <p>© 2017 COLLEGE OF DATA SCIENCE TAIYUAN UNIVERSITY OF TECHNOLOGY</p>
    </div>
</div>
<!--main8 结束-->
<div id="scroll" class="scroll">
    <div id="toTop" class="scrollItem">
        <i class="icon iconfont"></i>
    </div>
</div>
<!--<script src="http://cds.tyut.edu.cn/templets/1/cds/js/jquery-1.9.1.min.js" type="text/javascript" charset="utf-8"></script>-->
<script src="cds/jquery-1.7.1.min.js"></script>
<!--canvas粒子图-->
<script type="text/javascript" src="cds/jquery.particleground.min.js"></script>
<!--轮播图-->
<script type="text/javascript" src="cds/jquery.easing.js"></script>
<script src="cds/iviewPosition.js" type="text/javascript" charset="utf-8"></script>
<script src="cds/iview.js"></script>
<script>
    tt_ivew();
    $(document).ready(function () {
        $('#iview').iView({
            pauseTime: 7000,
            pauseOnHover: true,
            directionNavHoverOpacity: 0,
            timer: "Bar",
            timerDiameter: "50%",
            timerPadding: 0,
            timerStroke: 7,
            timerBarStroke: 0,
            timerColor: "#FFF",
            timerPosition: "bottom-right",
            controlNav: true
        });
    });
</script>
<!--轮播图结束-->
<script src="cds/sideSlip__function.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

    window.onload = function () {

        if (!document.getElementsByClassName) {
            document.getElementsByClassName = function (className, element) {
                var children = (element || document).getElementsByTagName('*');
                var elements = new Array();
                for (var i = 0; i < children.length; i++) {
                    var child = children[i];
                    var classNames = child.className.split(' ');
                    for (var j = 0; j < classNames.length; j++) {
                        if (classNames[j] === className) {
                            elements.push(child);
                            break;
                        }
                    }
                }
                return elements;
            };
        }

        // var noticeP = document.getElementsByClassName("main3-card_ul")[0].getElementsByTagName("p");
        // for (var i = 0; i < noticeP.length; i++) {
        //     var pValue = noticeP[i].innerHTML;
        //     noticeP[i].innerHTML = pValue.substring(5, 7);
        // }

        //      首页粒子图
        $('#particles').particleground();

        //锚点平滑滚动
        var speed = 500; //自定义滚动速度

        $("#toTop").click(function () {
            $("html,body").animate({
                "scrollTop": 0
            }, speed);
        });
        //回到底部
        var windowHeight = parseInt($("body").css("height")); //整个页面的高度
        $("#toBottom").click(function () {
            $("html,body").animate({
                "scrollTop": windowHeight
            }, speed);
        });
    };

    //	侧滑栏
    tt_bind('menu');

</script>


<div id="translate-man-app" class="content-3WfBL_0" style="display: none;">
    <div data-v-2b7a7e32="" class="outputBox-qe9A4_0">
        <div data-v-2b7a7e32="" class="outputBox-3oESn_0">
            <span data-v-2b7a7e32="" class="outputBox-13Ovx_0"></span>
            <!---->
        </div>
        <div data-v-2b7a7e32="" class="outputBox-1GLb__0">
            <!---->
            <div data-v-2b7a7e32="" class="outputBox-onVZH_0">
                <img src="chrome-extension://fapgabkkfcaejckbfmfcdgnfefbmlion/static/sound.svg" class="icon-tprjJ_0">
            </div>
        </div>
        <div data-v-2b7a7e32="" class="outputBox-2sJgr_0"></div>
        <div data-v-2b7a7e32="" class="outputBox-17RAm_0" style="display: none;">
            <!---->
            <!---->
            <!---->
            <div data-v-2b7a7e32=""></div>
        </div>
    </div>
</div>
</body>

</html>