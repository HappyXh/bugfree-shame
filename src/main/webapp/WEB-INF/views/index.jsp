<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Index"/>

<%@ include file="header.jsp" %>


<link rel="stylesheet" href="<c:url value='/style/index.css' />" type="text/css"/>


<script type="text/javascript" src="<c:url value='/scripts/index.js' />"></script>

<div class="layout">
	<div class="main">
		<div id="contentDIV" class="content">
			<!-- 第一页 -->
			<div class="content_section">
				<div class="section">
					<div class="section_mg">
						<img src="images/logo.png" alt="">
					</div>
					<div class="section_text">
						<p>“让撰写PPT变得简单”</p>
						<p>Think your point easy</p>
					</div>
				</div>
			</div>
			<!-- 第二页 -->
			<div class="content_section" >
				<div class="section">
					<div class="section_mg right">
						<img src="images/pic01.jpg" alt="">
					</div>
					<div class="section_text left">
						<p>Clear storylines </p>
						<p>Professional Business Presentations</p>
						<p>“清晰的故事提纲</p>
						<p>海量专业商务PPT”</p>
					</div>
				</div>
			</div>
			<!-- 第三页 -->
			<div class="content_section">
				<div class="section">
					<div class="section_mg left">
						<img src="images/pic02.jpg" alt="">
					</div>
					<div class="section_text right">
						<p>Smart Researching</p>
						<p>Creating deck</p>
						<p>“智能搜索,一键生成”</p>
					</div>
				</div>
			</div>
			<!-- 第四页 -->
			<div class="content_section">
				<div class="section">
					<div class="section_mg right">
						<img src="images/pic03.jpg" alt="">
					</div>
					<div class="section_text left">
						<p>Direct download without</p>
						<p>Add-in</p>
						<p>“无需安装,安全可靠”</p>

					</div>
				</div>
			</div>
			<!-- 第五页 -->
			<div class="content_section">
				<div class="section">

					<div class="section_text">
						<p>Start Innovation Journey</p>
						<p>开启我们的创新之旅</p>
					</div>
					<div class="section_btn">
						<a href="home" class="button form">Start</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<a id="nextA" href="javascript:void(0)" class="arrow" title="下一页"></a>
<ul id="right_nav" class="side_nav" num=0>
	<li><a num=0 href="javascript:void(0)" class="side_nav_a side_nav_a_active"></a></li>
	<li><a num=1 href="javascript:void(0)" class="side_nav_a"></a></li>
	<li><a num=2 href="javascript:void(0)" class="side_nav_a"></a></li>
	<li><a num=3 href="javascript:void(0)" class="side_nav_a"></a></li>
	<li><a num=4 href="javascript:void(0)" class="side_nav_a"></a></li>
</ul>
</body>

</html>