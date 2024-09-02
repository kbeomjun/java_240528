<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	
</head>
<body>
	<h1 class="mt-3">커뮤니티 관리</h1>
	<ul class="list-group mt-3 mb-3">
		<c:forEach items="${list}" var="co">
		  	<li class="list-group-item d-flex justify-content-between align-items-center">
			    <span>${co.co_name}</span>
			    <span>
				    <span class="badge badge-primary badge-pill">${co.co_count}</span>
				    <button class="btn btn-outline-warning">수정</button>
				    <button class="btn btn-outline-danger">삭제</button>
			    </span>
		  	</li>
		</c:forEach>
	</ul>
	<form action="<c:url value="/admin/community/insert"/>" method="post">
		<div class="input-group mb-3">
		    <input type="text" class="form-control" placeholder="등록할 커뮤니티명을 입력하세요." name="name" required="required">
		    <div class="input-group-append">
		    	<button type="submit" class="btn btn-outline-success col-12">등록</button>
		    </div>
	  	</div>
	</form>
</body>
</html>