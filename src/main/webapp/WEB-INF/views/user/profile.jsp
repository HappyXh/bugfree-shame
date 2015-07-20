<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Profile"/>

<%@ include file="../header.jsp" %>

<c:choose>
	<c:when test="${not empty error}">
		<div class="page-action">
			<div class="alert alert-danger col-sm-offset-2 col-sm-4">
				Failed to change password!
			</div>
		</div>
	</c:when>
	<c:when test="${not empty success}">
		<div class="page-action">
			<div class="alert alert-success col-sm-offset-2 col-sm-4">
				Password changed successfully.
			</div>
		</div>
	</c:when>
	<c:when test="${not empty namesuccess}">
        <div style="font-size: 16pt; margin-bottom: 10px; border-radius: 5px;">
            <div style="background-color:green; width:530px; text-align:center;">
             User Profile updated successfully
            </div>
        </div>
    </c:when>
</c:choose>

<div id="userInformation" class="container">
    <table class="table">
        <tr>
            <th class="first">Name</th>
            <c:if test="${empty showUser}">
                <td>${user.userName}</td>
            </c:if>
            <c:if test="${not empty showUser}">
                <td>${showUser.userName}</td>
            </c:if>
        </tr>
        <tr>
            <th class="first">Enable</th>
            <td>${user.enabled}</td>
        </tr>
    </table>

    <div class="table">

            <c:if test="${showUser.id==user.id}">
                <a href="changePassword">Change Password</a>
                <span id="line"></span>
                <a href="updateProfile">Update Profile</a>
            </c:if>

            <c:if test="${empty showUser}">
                <a href="changePassword">Change Password</a>
                <span id="line"></span>
                <a href="updateProfile">Update Profile</a>
            </c:if>


    </div>

</div>


</script>


<%@ include file="../footer.jsp" %>