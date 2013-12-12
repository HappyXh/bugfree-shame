<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<%@ include file="header.jsp" %>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Publish Time</th>
        <th>Operations</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="postWithLike" items="${postsWithLike}" varStatus="row">
        <tr>
            <td>
                <a href="<c:url value='/posts/${postWithLike.key.postId}' />">
                    <c:out value="${postWithLike.key.title}"/>
                </a>
            </td>
               <td>
               <a href="<c:url value='/user/${users[row.index].id}' />">
               <c:out value="${postWithLike.key.authorName}"/></td>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <td><fmt:formatDate value="${postWithLike.key.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>

                <c:choose>
                <c:when test="${not postWithLike.value}">
                    <td><a href="javascript:void(0)" onclick="like_confirm('${postWithLike.key.postId}');">Like</a></td>
                </c:when>
                <c:otherwise><td>Liked</td></c:otherwise>
                </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script type="text/javascript">
function like_confirm(likedPostId)
{
    alert("you liked this post")
    document.LikePostForm.likePost.value = likedPostId;
    document.LikePostForm.submit();
}

</script>
<form name="LikePostForm" method="post" onsubmit="return form_validate();">
     <input type="hidden" id="likePost" name="likePost">
</form>

<%@ include file="footer.jsp" %>