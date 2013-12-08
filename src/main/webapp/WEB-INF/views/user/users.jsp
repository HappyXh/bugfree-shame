<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="users"/>

<%@ include file="../header.jsp" %>

<div id="AllUsersInformation">
    <table class="table">
    <thead>
        <tr>
            <th>Username</th>
            <th>Enable</th>
            <th>Role</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}" varStatus="row">
             <tr>
                 <td><c:out value="${user.userName}"/></td>
                 <td><c:out value="${user.enabled}"/></td>
             </tr>
        </c:forEach>
        <c:forEach var="map" items="${usersWithRoles}" varStatus="row">
            <tr>
                <td><c:out value="${map.key.userName}"/></td>
                <td><c:out value="${map.key.enabled}"/></td>
                <td>
                    <c:if test="${map.value=='ROLE_ADMIN'}">
                        Administrator
                    </c:if>
                    <c:if test="${map.value=='ROLE_REGULAR'}">
                        User
                        <a href="javascript:void(0)" onclick="show_confirm('${map.key.id}');">X</a>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
    </tbody>
    </table>
</div>

<script type="text/javascript">
function show_confirm(authoriseUserId)
{
var r=confirm("Are you sure to authorise this user?");
if (r==true)
  {
      document.authoriseUserForm.authoriseUserId.value = authoriseUserId;
      document.authoriseUserForm.submit();
  }
}
</script>
<form name="authoriseUserForm" method="post" onsubmit="return form_validate();">
     <input type="hidden" id="authoriseUserId" name="authoriseUserId">
</form>

<%@ include file="../footer.jsp" %>