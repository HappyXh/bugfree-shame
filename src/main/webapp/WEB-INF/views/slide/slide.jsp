<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<%@ include file="../header.jsp" %>
<link rel="stylesheet" type="text/css"  href="<c:url value='style/imgSlider.css' />" />
<script type="text/javascript" src="<c:url value='scripts/showSlide.js' />"></script>
<div class="container">
    <div id="leftnav" class="col-xs-4">
        <h3 class="">Template</h3>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="navbar-right" role="button" data-toggle="collapse" href="#collapse1" aria-expanded="false" aria-controls="collapse1">
                        <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                    <a href="#">
                        Background Introduction
                    </a>

                </h4>

            </div>
            <div class="collapse" id="collapse1">

                <p>
                    <a class="navbar-right" onclick="selectSlide(0,'${slidesList[0].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(0,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(0,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline0" data-type="text" data-placement="right">
                        What happened since last review
                    </a>
                </p>
                <p>
                    <a class="navbar-right" onclick="selectSlide(1,'${slidesList[1].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(1,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(1,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline1" data-type="text" data-placement="right">
                        From perspective of geography, what's new
                    </a>
                </p>
                <p>
                    <a class="navbar-right" onclick="selectSlide(2,'${slidesList[2].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(2,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(2,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline2" data-type="text" data-placement="right">
                        Base on external competition, what are the key challenges
                    </a>
                </p>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a href="#">
                        Overwhelming Questions (why?)
                    </a>
                    <a class="navbar-right" role="button" data-toggle="collapse" href="#collapse2" aria-expanded="false" aria-controls="collapse2">
                        <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                </h4>
            </div>
            <div class="collapse" id="collapse2">
                <p>
                    <a class="navbar-right" onclick="selectSlide(3,'${slidesList[3].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(3,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(3,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline3" data-type="text" data-placement="right">
                        Why we grew sales but OI came down
                    </a>
                </p>
                <p>
                    <a class="navbar-right" onclick="selectSlide(4,'${slidesList[4].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(4,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(4,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline4" data-type="text" data-placement="right">
                        What/where are the critical issues
                    </a>
                </p>
                <p>
                    <a class="navbar-right" onclick="selectSlide(5,'${slidesList[5].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(5,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(5,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline5" data-type="text" data-placement="right">
                        What are the root causes
                    </a>
                </p>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a href="#">
                        Key Message/Solutions, new Initiatives, tell me how?
                    </a>
                    <a class="navbar-right" role="button" data-toggle="collapse" href="#collapse3" aria-expanded="false" aria-controls="collapse5">
                        <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                </h4>
            </div>
            <div class="collapse" id="collapse3">
                <p>
                    <a class="navbar-right" onclick="selectSlide(6,'${slidesList[6].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(6,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(6,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline6" data-type="text" data-placement="right">
                        Initiative 1
                    </a>
                </p>
                <p>
                    <a class="navbar-right" onclick="selectSlide(7,'${slidesList[7].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(7,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(7,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline7" data-type="text" data-placement="right">
                        Initiative 2
                    </a>
                </p>
                <p>
                    <a class="navbar-right" onclick="selectSlide(8,'${slidesList[8].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(8,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(8,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline8" data-type="text" data-placement="right">
                        Action Plan (what /who/where/when)
                    </a>
                </p>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a href="#">
                        Summary
                    </a>
                    <a class="navbar-right" role="button" data-toggle="collapse" href="#collapse4" aria-expanded="false" aria-controls="collapse4">
                        <span class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                </h4>
            </div>
            <div class="collapse" id="collapse4">
                <p>
                    <a class="navbar-right" onclick="selectSlide(9,'${slidesList[89].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(9,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(9,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline9" data-type="text" data-placement="right">
                        Key take away
                    </a>
                </p>
                <p>
                    <a class="navbar-right" onclick="selectSlide(10,'${slidesList[10].features}')">
                        <span class="glyphicon glyphicon-list-alt"></span>
                    </a>
                    <a class="navbar-right" onclick="add(10,this)">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                    <a class="navbar-right" onclick="del(10,this)">
                        <span class="glyphicon glyphicon-minus"></span>
                    </a>
                    <a href="#" id="outline10" data-type="text" data-placement="right">
                        What are the key changes after your presentation
                    </a>
                </p>
            </div>
        </div>
    </div>

    <div id="slides-content" class="col-xs-8">
        <div id="search-box">
            <div id="search-div" class="input-group">
                <span class="input-group-addon" >PPT Slides</span>
                <input id="search-txt" type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <button id="search-btn" disabled class="btn btn-default" type="button" onclick="search()">Go!</button>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-xs-10 -->
        <div id="slides-box" >
            <c:set var="index" value="0" />
            <c:forEach var="slide" items="${slidesList}" varStatus="status">
                <div id="slideImgBox${index}" slideId="${slide.id}" class="col-xs-4">
                    <div class="thumbnail">
                        <c:set var="string1" value="${slide.filePath}"/>
                        <img id="slideImg${index}" class="slideImg" src="http://7xme1x.com1.z0.glb.clouddn.com/${fn:substringBefore(string1,'.pdf')}-${slide.page}.jpg" alt="..."
                            onclick="selectSlide(${index},'${slide.features}')"
                                />
                    </div>
                </div>
                <c:set var="index" value="${index+1}" />
            </c:forEach>
        </div>

        <div id="apply-div">
            <input id="apply-btn" class="btn btn-default" type="button" value="Apply" onclick="createPPT()">
        </div>
        <div id="imageShow">
            <div id="scan-div">
                <input id="scan-btn" class="btn btn-default" type="button" value="Scan" onclick="scan()">
            </div>
            <div id="imgshow_mask"></div>
            <ul class="imagebg" id="imagebg">
                <li data-sPic="">
                </li>
            </ul>

            <div class="scrollbg">
                <div class="scroll">
                    <a id="left_img_btn" class="s_pre" href="javascript:void(0)"></a>
                    <div class="current" id="current"></div>
                    <div class="outScroll_pic">
                        <ul class="scroll_pic cls" id="small_pic"></ul>
                    </div>
                    <a id="right_img_btn" class="s_next" href="javascript:void(0)"></a>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value='scripts/imgSlider.js' />"></script>
    <script type="text/javascript">
        img.init();
        img.play(0);
        //阻止事件冒泡
        function estop(e){
            var e=arguments.callee.caller.arguments[0]||event;
            if (e && e.stopPropagation){
                //因此它支持W3C的stopPropagation()方法
                e.stopPropagation();
            }else{
                //否则，我们需要使用IE的方式来取消事件冒泡
                window.event.cancelBubble = true;
                return false;
            }
        }
    </script>

</div>
<%@ include file="../footer.jsp" %>