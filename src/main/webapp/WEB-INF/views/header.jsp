<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="<c:url value='/style/bootstrap.css' />" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/style/bootstrap-theme.css' />" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/style/main.css' />" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/style/jquery-ui.css' />">

    <script type="text/javascript" src="<c:url value='/scripts/jquery-1.10.2.js' />"></script>
    <script type="text/javascript" src="<c:url value='/scripts/bootstrap.js' />"></script>
    <script type="text/javascript" src="<c:url value='/scripts/jquery-ui.js' />"></script>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
         <div class="container-fluid">
             <!-- Brand and toggle get grouped for better mobile display -->
             <div class="navbar-header">
               <a class="navbar-brand" href="#">Pointhinker</a>
             </div>
             <!-- Collect the nav links, forms, and other content for toggling -->
             <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="<c:url value='/' />" >Home</a>
                    </li>
                    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                        <li>
                            <a href="<c:url value='/user/profile' />" class="nav_link"><i class="icon-user"></i> User Profile</a>
                        </li>
                        <security:authorize ifAnyGranted="ROLE_ADMIN">
                            <li>
                                <a href="<c:url value='/user/create' />" class="nav_link"><i class="icon-plus"></i> Create User</a>
                            </li>
                            <li>
                                <a href="<c:url value='/user/users' />" class="nav_link"><i class="icon-plus"></i> Users</a>
                            </li>
                        </security:authorize>
                        <li>
                            <a href="#" class="nav_link"><i class="icon-eject"></i> Upload</a>
                        </li>
                        <li>
                            <a href="#" class="nav_link"><i class="icon-eject"></i> About us</a>
                        </li>
                        <li>
                            <a href="<c:url value="/j_spring_security_logout" />" class="nav_link"><i class="icon-eject"></i> Logout</a>
                        </li>
                    </security:authorize>
                </ul>
             </div><!-- /.navbar-collapse -->
         </div><!-- /.container-fluid -->
      </div>
    </nav>

    <!--[if lt IE 9]>
    <div class="alert alert-warning">
        You are using a Legacy Browser - it is not supported. Please upgrade to <a href="http://windows.microsoft.com/en-US/internet-explorer/downloads/ie-9/worldwide-languages">IE9</a>, Firefox, Safari, Chrome or Opera.
    </div>
    <![endif]-->