<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container" >
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="navbar-brand" href="<c:url value="/"/>">Home</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						Drop1
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Link 1</a>
						<a class="dropdown-item" href="#">Link 2</a>
						<a class="dropdown-item" href="#">Link 3</a>
					</div>
			    </li>
			    <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						Drop2
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Link 1</a>
						<a class="dropdown-item" href="#">Link 2</a>
						<a class="dropdown-item" href="#">Link 3</a>
					</div>
			    </li>
			    <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						Drop3
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Link 1</a>
						<a class="dropdown-item" href="#">Link 2</a>
						<a class="dropdown-item" href="#">Link 3</a>
					</div>
			    </li>
			    <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						Drop4
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Link 1</a>
						<a class="dropdown-item" href="#">Link 2</a>
						<a class="dropdown-item" href="#">Link 3</a>
					</div>
			    </li>
			</ul>
			<ul class="navbar-nav ">
			    <li class="nav-item">
					<a class="nav-link" href="<c:url value="/signup"/>">회원가입</a>
	    		</li>
	    		<li class="nav-item">
					<a class="nav-link" href="<c:url value="/login"/>">로그인</a>
	    		</li>
    		</ul>
		</div>
	</nav>
</body>
</html>