<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
	<style type="text/css">
		.list-community{
			list-style: none; display: flex; flex-wrap: wrap;
		}
		.item-community{
			width: 33.33%; height: 80px; box-sizing: border-box; padding: 10px;
		}
		.link-community{
			display: block; border: 1px solid black; box-sizing: border-box;
			height: 100%; text-align: center; line-height: 58px; font-size: 24px;
			text-decoration: none; color: black;
		}
		.link-community:hover{
			text-decoration: none; color: white; background-color: tomato;
		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		<h1 class="mt-3">커뮤니티 목록</h1>
		<ul class="list-community">
			<c:forEach items="${list}" var="co" varStatus="vs">
				<li class="item-community">
					<span class="link-community">
						<span>${co.co_name}</span>
						<button class="btn btn-outline-warning btn-update" date-num="${co.co_num}">수정</button>
						<button class="btn btn-outline-danger btn-delete" date-num="${co.co_num}">삭제</button>
					</span>
				</li>
			</c:forEach>
		</ul>
		<form class="input-group mb-3" action="<c:url value="/admin/community/insert"/>" method="post">
			<input type="text" name="co_name" class="form-control">
			<div class="input-group-append">
				<button type="submit" class="btn btn-outline-success">등록</button>
			</div>
		</form>
	</div>
</body>
</html>