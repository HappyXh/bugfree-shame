<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<link rel="stylesheet" type="text/css"  href="<c:url value='../../style/imgSlider.css' />" />

<script type="text/javascript" src="<c:url value='../../scripts/showSlide.js' />"></script>
<%@ include file="../header.jsp" %>
<div class="container">
    <div id="leftnav" class="col-xs-4">
        <p class="lead">${title}</p>
        <ul class="list-group">
            <c:set var="slideIdStr" value=""/>
            <c:forEach var="slide" items="${slides}" varStatus="status">
                <c:set var="slideIdStr" value="${slideIdStr},${slide.id}"/>
            </c:forEach>

            <c:set var="index" value='0'/>
            <c:forEach var="part" items="${parts}" varStatus="status">
                <li class="list-group-item">${part.number}: ${part.topic}</li>
                <li class="list-group-item">
                    <ul class="list-group">
                        <c:forEach var="subPart" items="${part.subParts}" varStatus="status">

                            <li class="list-group-item" onclick=choose(this,"${id}","${index}","${slideIdStr}") >${subPart.subNumber}: ${subPart.subTopic}</li>
                            <c:set var="index" value="${index+1}" />
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-xs-8">
        <div class="col-lg-10">
            <div class="input-group">
                <span class="input-group-addon" >PPT Slides</span>
                <input type="text" class="form-control" placeholder="Search for...">
            <span class="input-group-btn">
              <button class="btn btn-default" type="button">Go!</button>
            </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-10 -->
        <div id="slides-box" >
            <c:forEach var="slide" items="${slides}" varStatus="status">
                <div class="col-xs-4">
                    <div class="thumbnail">
                        <c:set var="string1" value="${slide.filePath}"/>
                        <img src="${fn:substringBefore(string1,'.ppt')}${slide.page}.PNG" alt="..."   />
                    </div>
                </div>
            </c:forEach>
        </div>

        <div >
            <input class="btn btn-default" type="button" value="Apply" onclick="createPPT('${slideIdStr}')">
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>