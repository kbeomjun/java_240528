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
			width: 33.33%; box-sizing: border-box; padding: 10px;
		}
		.link-community{
			display: block; border: 1px solid black; box-sizing: border-box;
			text-align: center; line-height: 40px; font-size: 24px;
			text-decoration: none; color: black; padding: 10px;
		}
		.innert-community{
			display: block;
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
						<span class="inner-community">${co.co_name}</span>
						<button class="btn btn-outline-warning btn-update" data-num="${co.co_num}">수정</button>
						<a class="btn btn-outline-danger btn-del" href="<c:url value="/admin/community/delete?co_num=${co.co_num}"/>">삭제</a>
					</span>
				</li>
			</c:forEach>
		</ul>
		<form class="input-group mb-3" action="<c:url value="/admin/community/insert"/>" method="post" id="form_insert">
			<input type="text" name="co_name" class="form-control">
			<div class="input-group-append">
				<button type="submit" class="btn btn-outline-success">등록</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$('.btn-del').click(function(e){
		if(!confirm('해당 커뮤니티를 삭제하시겠습니까?')){
			e.preventDefault();
			return;
		}
	});
	
	$('.btn-update').click(function(){
		var co_num = $(this).data('num');
		var co_name = $(this).prev().text();
		var str = `
		<form class="input-group mb-3" action="<c:url value="/admin/community/update"/>" method="post" id="form_update">
			<input type="text" name="co_name" class="form-control" value="\${co_name}">
			<div class="input-group-append">
				<button type="submit" class="btn btn-outline-warning">수정</button>
			</div>
			<input type="hidden" name="co_num" value="\${co_num}">
		</form>
		`;
		$('#form_update').remove();
		$('#form_insert').hide();
		$('#form_insert').after(str);
	})
</script>
</html>