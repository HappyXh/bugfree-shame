<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>Overflow by HTML5 UP</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<script src="scripts/jquery-1.10.2.js"></script>
		<%--<script src="scripts/jquery.poptrox.min.js"></script>--%>
		<script type="text/javascript" src="scripts/skel.min.js"></script>
		<script type="text/javascript" src="scripts/init.js"></script>

		<noscript>
			<link rel="stylesheet" href="<c:url value='/css/skel-noscript.css' />">
			<link rel="stylesheet" href="<c:url value='/css/style.css' />">
		</noscript>
	</head>
	<body>

		<!-- Header -->
			<section id="header">
				<header>
					<img src="images/logo.png" alt="" />
				</header>

				<footer>
					<a href="#first" class="button style2 scrolly scrolly-centered">“让撰写PPT变得简单”</a>
				</footer>
			</section>
		
		<%--<!-- Banner -->--%>
			<%--<section id="banner">--%>
				<%--<header>--%>
					<%--<h2>This is Overflow</h2>--%>
				<%--</header>--%>
				<%--<p>A brand new site template designed by <a href="http://n33.co">AJ</a> for <a href="http://html5up.net/">HTML5 UP</a>.<br />--%>
				<%--It’s fully responsive, built on <a href="http://skeljs.org">skelJS</a>, and of course entirely free<br />--%>
				<%--under the <a href="http://html5up.net/license/">Creative Commons license</a>.</p>--%>
				<%--<footer>--%>
					<%--<a href="#first" class="button style2 scrolly">Act on this message</a>--%>
				<%--</footer>--%>
			<%--</section>--%>
		
		<!-- Feature 1 -->
			<article id="first" class="container box style1 right">
				<a  class="image full"><img src="images/pic01.jpg" alt="" /></a>
				<div class="inner">
					<header>
						<h2><br /><br />“清晰的故事提纲
							<br />
							海量专业商务PPT”</h2>
					</header>
				</div>
			</article>
		
		<!-- Feature 2 -->
			<article class="container box style1 left">
				<a  class="image full"><img src="images/pic02.jpg" alt="" /></a>
				<div class="inner">
					<header>
						<h2><br /><br />“智能搜索
							<br />
							一键生成”</h2>
					</header>
				</div>
			</article>

		<!-- Feature 1 -->
		<article id="third" class="container box style1 right">
			<a  class="image full"><img src="images/pic03.jpg" alt="" /></a>
			<div class="inner">
				<header>
					<h2><br /><br />“无需安装<br />
						安全可靠”</h2>
				</header>
			</div>
		</article>
		

		
		<!-- Contact -->
			<article class="container box style3">
				<header>
					<h2>“开启我们的创新之旅”</h2>
				</header>
				<form>
					<div class="row">
						<div class="12u">
							<ul class="actions">

								<li><a href="home" class="button form">Start</a></li>
							</ul>
						</div>
					</div>
				</form>
			</article>
		

		
		<section id="footer">
			<ul class="icons">
				<li><a href="#" class="">Back</a></li>

			</ul>
			<div class="container">
				<p>Designed and built with all the love in the PPT by <a href="#" target="_blank">@Leon</a>
					and <a href="#" target="_blank">@Kevin</a> and <a href="#" target="_blank">@Happy</a>.
				</p>
				<p>鄂ICP备15017626号-1 | PoinThinker - powered by BoDianSiKe</p>

			</div>
		</section>

	</body>
</html>