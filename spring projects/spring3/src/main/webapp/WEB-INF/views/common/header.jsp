<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">
		  	<ul class="navbar-nav">
			    <li class="nav-item">
			      	<a class="navbar-brand p-0" href="<c:url value="/"/>">
			    		<img src="<c:url value="/resources/img/dog.jfif"/>" alt="logo" style="width:56px; height: 36px;">
			  		</a>
			    </li>
			    <li class="nav-item d-flex align-items-center">
			    	<a class="nav-link" href="#">커뮤니티</a>
			    </li>
			    <li class="nav-item d-flex align-items-center">
			    	<a class="nav-link" href="#">없음</a>
			    </li>
		  	</ul>
		  	
		  	<ul class="navbar-nav">
			    <li class="nav-item">
			    	<a class="nav-link" href="<c:url value="/guest/signup"/>">회원가입</a>
			    </li>
			    <li class="nav-item">
			    	<a class="nav-link" href="<c:url value="/guest/login"/>">로그인</a>
			    </li>
		  	</ul>
	  	</div>
	</nav>
</body>
</html>