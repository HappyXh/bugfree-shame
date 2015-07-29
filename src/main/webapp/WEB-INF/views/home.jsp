<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<script type="text/javascript" src="<c:url value='/scripts/home.js' />"></script>

<%@ include file="header.jsp" %>
<div id="logo">
    <img src="images/logo.png" class="img-responsive center-block" alt="Responsive image">
</div>
<div class="input-group search-box">
  <input type="text" class="form-control" placeholder="Input your topic" aria-describedby="basic-addon2">
  <a class='input-group-addon btn btn-primary'  href="#"><span class="glyphicon glyphicon-search"></span> Search</a>
</div>

<div class="row story-container">
    <c:forEach var="story" items="${storyList}" varStatus="status">
        <div class="col-xs-4 story-box" onclick="focusOn(this)" ondblclick="select(this,${story.id})">
            <div class="thumbnail">
              <c:set var="string1" value="${story.filePath}"/>
              <img src="${fn:substringBefore(string1,'.ppt')}1.PNG" alt="..."   />
              <div class="caption">
                <h4>${story.title}</h4>
              </div>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="footer.jsp" %>