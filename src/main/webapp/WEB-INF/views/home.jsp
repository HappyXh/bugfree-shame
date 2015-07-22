<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <div class="col-xs-4 story-box" onclick="focusOn(this)" ondblclick="select(this)">
    <div class="thumbnail">
      <img src="images/page.png" alt="..."   />
      <div class="caption">
        <h4>Topic label</h4>
      </div>
    </div>
  </div>
  <div class="col-xs-4 story-box" onclick="focusOn(this)" ondblclick="select(this)">
      <div class="thumbnail">
        <img src="images/page.png" alt="..." ">
        <div class="caption">
          <h4>Topic label</h4>
        </div>
      </div>
  </div>

  <div class="col-xs-4 story-box" onclick="focusOn(this)" ondblclick="select(this)">
    <div class="thumbnail">
     <img src="images/page.png" alt="..." >
     <div class="caption">
       <h4>Topic label</h4>
     </div>
  </div>
 </div>
</div>
<%@ include file="footer.jsp" %>