<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<%@ include file="header.jsp" %>
<script type="text/javascript" src="<c:url value='/scripts/home.js' />"></script>
<script type="text/javascript" src="<c:url value='/scripts/jquery.zclip.js' />"></script>

<a id="create_btn" type="button" class="btn btn-info" href="<c:url value='slide' />">Create My Deck</a>
<%--<div id="logo">--%>
    <%--<img src="images/logo.png" class="img-responsive center-block" alt="Responsive image">--%>
<%--</div>--%>

<div id="search-presentation" class="input-group input-group-lg">
    <input id="search-txt" type="text" class="form-control" placeholder="Input You Topic, like IBM, BMW..."
           aria-describedby="basic-addon2">
    <span id="search-btn" class="input-group-btn">
        <button class="btn btn-default" type="button">Search!</button>
    </span>
</div>

<div id="story-grid" class="row slide-container">
    <c:forEach var="story" items="${storyList}" varStatus="status">
        <div class="col-xs-4">
            <div class="thumbnail" >
              <c:set var="string1" value="${story.uniName}"/>
              <img class="slide-img" src="http://7xoiwj.com1.z0.glb.clouddn.com/${fn:substringBefore(string1,'.pptx')}-1.jpg" alt="..." />
              <div class="caption">
                  <div class="actions">
                      <a href="#"><span class="glyphicon glyphicon-heart"></span></a>
                      <a class="cp-btn" link="${story.uniName}"><span class="glyphicon glyphicon-share-alt"></span></a>
                      <a href="attachment/${story.uniName}" ><span class="glyphicon glyphicon-save"></span></a>
                  </div>
                  <div class="story-desc"><span>${story.fileName}</span></div>
              </div>
            </div>
        </div>
    </c:forEach>
</div>



<%@ include file="footer.jsp" %>