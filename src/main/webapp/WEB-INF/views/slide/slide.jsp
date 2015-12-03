<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<%@ include file="../header.jsp" %>
<link rel="stylesheet" type="text/css"  href="<c:url value='style/side-nav.css' />" />
<link rel="stylesheet" type="text/css"  href="<c:url value='style/scroll-nav.css' />" />
<link rel="stylesheet" type="text/css"  href="<c:url value='style/slide-carousel.css' />" />

<script type="text/javascript" src="<c:url value='scripts/showSlide.js' />"></script>
<script type="text/javascript" src="<c:url value='scripts/slide-carousel.js' />"></script>
<div class="">
    <div id="leftnav" class="col-xs-3">
        <h3 class="">StoryLine</h3>
        <ul class="nav">
            <li class="active">
                <a href="#models">Background Introduction</a>
                <ul class="nav">
                    <li id="storyline-1" class="active">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#"><span>What happened since last review</span></a>
                    </li>
                    <li id="storyline-2">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#"><span>From perspective of geography, what's new</span></a>
                    </li>
                    <li id="storyline-3">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#"><span>Base on external competition, what are the key challenges</span></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#models">Overwhelming Questions (why?)</a>
                <ul class="nav">
                    <li id="storyline-4">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>Why we grew sales but OI came down</span></a>
                    </li>
                    <li id="storyline-5">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>What/where are the critical issues</span></a>
                    </li>
                    <li id="storyline-6">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>What are the root causes</span></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#models">Key Message/Solutions, new Initiatives, tell me how?</a>
                <ul class="nav">
                    <li id="storyline-7">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>Initiative 1</span></a>
                    </li>
                    <li id="storyline-8">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>Initiative 2</span></a>
                    </li>
                    <li id="storyline-9">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>Action Plan (what /who/where/when)</span></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#models">Summary</a>
                <ul class="nav">
                    <li id="storyline-10">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>Key take away</span></a>
                    </li>
                    <li id="storyline-11">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>What are the key changes after your presentation</span></a>
                    </li>
                    <li id="storyline-12">
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-minus btn-info"></span>
                        </a>
                        <a class="navbar-right">
                            <span class="glyphicon glyphicon-plus btn-info"></span>
                        </a>
                        <a href="#modals-examples"><span>Action Plan (what /who/where/when)</span></a>
                    </li>
                </ul>
            </li>
        </ul>
        <div id="apply-div">
            <input id="apply-btn" class="btn btn-default" type="button" value="CREATE" onclick="createPPT()">
        </div>
    </div>

    <div id="slides-content" class="col-xs-7">
        <div id="search-box">
            <div id="search-div" class="input-group">
                <span class="input-group-addon" >PPT Slides</span>
                <input id="search-txt" type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <button id="search-btn" class="btn btn-default" type="button">Go!</button>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-xs-10 -->
        <div id="slides-box" >
            <div id="view">
                <a id="grid-view" class="btn btn-default">
                    <span class="glyphicon glyphicon-th"></span>
                </a>
                <a id="carousel-view" class="btn btn-default">
                    <span class="glyphicon glyphicon-picture"></span>
                </a>
            </div>
            <div id="slides-grid">
            <c:forEach var="slide" items="${slidesList}" varStatus="status">
                <div class="col-xs-4">
                    <div class="thumbnail">
                        <c:set var="string1" value="${slide.filePath}"/>
                        <img class="slideImg" slides_id="${slide.id}"
                             src="http://7xoiwj.com1.z0.glb.clouddn.com/${fn:substringBefore(string1,'.pptx')}-${slide.page}.jpg" alt=".." />
                    </div>
                </div>
            </c:forEach>
            </div>
            <div id="slides-carousel" class="hasarrow" style="display: none;">
                <div id="img-viewer" class="img-viewer" num="0">
                    <div id="img-viewer-cell" class="img-viewer-cell">
                        <c:forEach var="slide" items="${slidesList}" varStatus="status">
                        <c:set var="string1" value="${slide.filePath}"/>
                        <a class="">
                            <img slides_id="${slide.id}"
                                 src="http://7xoiwj.com1.z0.glb.clouddn.com/${fn:substringBefore(string1,'.pptx')}-${slide.page}.jpg" />
                        </a>
                        </c:forEach>
                    </div>
                </div>
                <div class="change-bigimg pre-img">
                    <div class="arrow"></div>
                </div>
                <div class="change-bigimg next-img">
                    <div class="arrow"></div>
                </div>
                <div class="img-bars">
                    <div class="img-bars-wrapper">
                        <div id="img-bars-content" class="img-bars-content">
                            <c:set var="index" value="0" />
                            <c:forEach var="slide" items="${slidesList}" varStatus="status">
                            <c:set var="string1" value="${slide.filePath}"/>
                            <span num="${index}">
                                <img class="news-smallimg-img" src="http://7xoiwj.com1.z0.glb.clouddn.com/${fn:substringBefore(string1,'.pptx')}-${slide.page}.jpg">
                                <span class="news-smallimg-mask"></span>
                            </span>
                            <c:set var="index" value="${index+1}" />
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>



    </div>
    <div id="slides-outline" class="col-xs-2 nav">
        <a id="slides-outline-1" class="thumbnail active" slides_id="1">
            <span class="navbar-left">1</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg"/>

        </a>
        <a id="slides-outline-2" class="thumbnail" slides_id="1">
            <span class="navbar-left">2</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg"/>
        </a>
        <a id="slides-outline-3" class="thumbnail" slides_id="1">
            <span class="navbar-left">3</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-4" class="thumbnail" slides_id="1">
            <span class="navbar-left">4</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-5" class="thumbnail" slides_id="1">
            <span class="navbar-left">5</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-6" class="thumbnail" slides_id="1">
            <span class="navbar-left">6</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-7" class="thumbnail" slides_id="1">
            <span class="navbar-left">7</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-8" class="thumbnail" slides_id="1">
            <span class="navbar-left">8</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-9" class="thumbnail" slides_id="1">
            <span class="navbar-left">9</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-10" class="thumbnail" slides_id="1">
            <span class="navbar-left">10</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-11" class="thumbnail" slides_id="1">
            <span class="navbar-left">11</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
        <a id="slides-outline-12" class="thumbnail" slides_id="1">
            <span class="navbar-left">12</span>
            <img src="http://7xoiwj.com1.z0.glb.clouddn.com/default-0.jpg" />
        </a>
    </div>
    <div class = "clearDiv"></div>
</div>

</div>
<%@ include file="../footer.jsp" %>