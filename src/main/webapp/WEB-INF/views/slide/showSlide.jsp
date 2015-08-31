<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<link rel="stylesheet" type="text/css"  href="<c:url value='../../style/imgSlider.css' />" />
<script type="text/javascript" src="<c:url value='../../scripts/showSlide.js' />"></script>
<%@ include file="../header.jsp" %>
<div class="container">
    <div id="leftnav" class="col-xs-4">
        <h3 class="">${title}</h3>
        <ul id="slideList">
            <%--<c:set var="slideIdStr" value=""/>--%>
            <%--<c:forEach var="slide" items="${slides}" varStatus="status">--%>
                <%--<c:set var="slideIdStr" value="${slideIdStr},${slide.id}"/>--%>
            <%--</c:forEach>--%>

            <c:set var="index" value='0'/>
            <c:set var="partIndex" value='0'/>
            <c:set var="str1" value='${selectedSlides}'/>
            <c:set var="selectedSlidesArr" value='${ fn:split(str1,",") }'/>
            <c:forEach var="part" items="${parts}" varStatus="status">
               <div id="slidePart${partIndex}">
                <li class="partTitle">
                    <div class="" onMouseover="showDiv(this)" onMouseout="hideDiv(this)">
                        <div class="col-xs-10 slideContent">${part.topic}</div>
                        <div class="col-xs-2 slideEditor" style="display: none">
                        <a class="btn-default glyphicon glyphicon-minus " onclick=deletePart(this)></a>
                        <a class="btn-default glyphicon glyphicon-plus " onclick=addPart(this,"${id}","${selectedSlides}")></a>
                        </div>
                    </div>
                </li>
                <li class="partContent">
                    <ul class="">
                        <c:forEach var="subPart" items="${part.subParts}" varStatus="status">
                            <div id="slideTitleBox${index}" class="slideTitleBox" onMouseover="showDiv(this)" onMouseout="hideDiv(this)">
                                <div class="col-xs-10 slideContent">
                                <c:choose>
                                    <c:when test="${selectedSlidesArr[index]=='1'}" >
                                    <li id="slideTitle${index}" class=" clickable edited" onclick=choose(this,"${id}","${selectedSlides}") >
                                        &nbsp;&nbsp;${subPart.subNumber}: ${subPart.subTopic}</li>
                                    </c:when>
                                    <c:otherwise>
                                    <li id="slideTitle${index}" class=" clickable" onclick=choose(this,"${id}","${selectedSlides}") >
                                        &nbsp;&nbsp;${subPart.subNumber}: ${subPart.subTopic}</li>
                                    </c:otherwise>
                                </c:choose>
                                </div>
                                <div class="col-xs-2 slideEditor" style="display: none">
                                    <a class="btn-default glyphicon glyphicon-minus" onclick="deleteSlide(this)" ></a>
                                    <a class="btn-default glyphicon glyphicon-plus" onclick=addSlide(this,${id},"${selectedSlides}")></a>
                                </div>
                            </div>
                            <c:set var="index" value="${index+1}" />
                        </c:forEach>
                    </ul>
                </li>
               </div>
               <c:set var="partIndex" value="${partIndex+1}" />
            </c:forEach>
        </ul>
    </div>
    <div id="slides-content" class="col-xs-8">
        <div id="search-box">
            <div class="input-group">
                <span class="input-group-addon" >PPT Slides</span>
                <input type="text" class="form-control" placeholder="Search for...">
            <span class="input-group-btn">
              <button class="btn btn-default" type="button">Go!</button>
            </span>
            </div><!-- /input-group -->
        </div><!-- /.col-xs-10 -->
        <div id="slides-box" >
            <c:set var="index" value="0" />
            <c:forEach var="slide" items="${slides}" varStatus="status">
                <div id="slideImgBox${index}" slideId="${slide.id}" class="col-xs-4">
                    <div class="thumbnail">
                        <c:set var="string1" value="${slide.filePath}"/>
                        <img  id="slideImg${index}" class="slideImg" src="${fn:substringBefore(string1,'.ppt')}${slide.page}.PNG" alt="..."
                             <%--ondblclick=choose(this,"${id}","${index}","${slideIdStr}") data-toggle="tooltip" data-placement="left" title="Double Click"--%>
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
            <a onclick="back()" class="btn-default glyphicon glyphicon-arrow-left"></a>
            <div id="imgshow_mask"></div>
            <ul class="imagebg" id="imagebg">
                <li data-sPic="">
                    <%--<img  class="bannerbg_main" src=""--%>
                          <%--style='width:100%; height:100%;' ondblclick="select(${id},'${slideIdStr}',${index},${slide.id})" />--%>
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
    <script type="text/javascript" src="<c:url value='../../scripts/imgSlider.js' />"></script>
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