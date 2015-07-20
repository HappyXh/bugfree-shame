<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<%@ include file="header.jsp" %>
<div id="logo">
    <img src="images/logo.png" class="img-responsive center-block" alt="Responsive image">
</div>
<div class="input-group search-box">
  <input type="text" class="form-control" placeholder="Recipient's username" aria-describedby="basic-addon2">
  <a class='input-group-addon btn btn-default'  href="#"><span class="glyphicon glyphicon-pencil"></span> Search</a>
</div>
<%@ include file="footer.jsp" %>