<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<script type="text/javascript" src="<c:url value='/scripts/home.js' />"></script>

<%@ include file="header.jsp" %>
<div id="logo">
    <img src="images/logo.png" class="img-responsive center-block" alt="Responsive image">
</div>
<div class="input-group search-box">
    <a type="button" class="btn btn-info btn-lg" href="<c:url value='slide' />">Create your PPT</a>

</div>

<div class="row slide-container">
    <c:forEach var="slide" items="${slideList}" varStatus="status">
        <div class="col-xs-4">
            <div class="thumbnail" >
              <c:set var="string1" value="${slide.filePath}"/>
              <img class="slide-img" src="http://7xme1x.com1.z0.glb.clouddn.com/${fn:substringBefore(string1,'.pdf')}-${slide.page}.jpg" alt="..."
                   data-toggle="tooltip" data-placement="left" title="Double Click"/>
              <div class="caption">
                1d ago, 5,070 views
                <div class="actions">
                    <a href="#"><span class="glyphicon glyphicon-heart"></span></a>
                    <a href="#"><span class="glyphicon glyphicon-share-alt"></span></a>
                    <a href="#"><span class="glyphicon glyphicon-save"></span></a>
                </div>
              </div>
            </div>
        </div>
    </c:forEach>
</div>



<%@ include file="footer.jsp" %>