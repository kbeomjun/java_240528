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
				    <button class="btn btn-outline-warning btn-update-community1" data-num="${co.co_num}" data-name="${co.co_name}">수정</button>
				    <button class="btn btn-outline-danger btn-delete-community" data-num="${co.co_num}">삭제</button>
			    </span>
		  	</li>
		</c:forEach>
	</ul>
	<form action="<c:url value="/admin/community/insert"/>" method="post" class="form-insert">
		<div class="input-group mb-3">
		    <input type="text" class="form-control" placeholder="등록할 커뮤니티명을 입력하세요." name="name" required="required">
		    <div class="input-group-append">
		    	<button type="submit" class="btn btn-outline-success col-12">등록</button>
		    </div>
	  	</div>
	</form>
	
	<script type="text/javascript">
		$('.btn-delete-community').click(function(){
			if(confirm("정말 삭제하시겠습니까?\n모든 데이터는 복구가 불가합니다.")){
				var co_num = $(this).data("num");
				$.ajax({
					async : true,
					url : '<c:url value="/admin/community/delete"/>', 
					type : 'post',
					data : {co_num : co_num}, 
					success : function (data){
						if(data){
							alert("커뮤니티를 삭제했습니다.");
						}else{
							alert("커뮤니티를 삭제하지 못했습니다.");
						}
						location.href = '<c:url value="/admin/community"/>';
					}, 
					error : function(jqXHR, textStatus, errorThrown){
						console.log(jqXHR);
					}
				});
			}
		});
		
		var co_name = '';
		$('.btn-update-community1').click(function(){
			$('.form-update').remove();
			var co_num = $(this).data("num");
			co_name = $(this).data("name");
			var str = `
				<form action="<c:url value="/admin/community/update"/>" method="post" class="form-update">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="등록할 커뮤니티명을 입력하세요." name="co_name" required="required" value="\${co_name}">
						<div class="input-group-append">
					    	<button type="submit" class="btn btn-outline-warning btn-update-community2">수정</button>
					    </div>
						<div class="input-group-append">
					    	<a href='<c:url value="/admin/community"/>' class="btn btn-outline-danger btn-cancel">취소</a>
				    	</div>
				  	</div>
				  	<input type="hidden" name="co_num" value="\${co_num}">
				</form>
			`;
			$('.form-insert').remove();
			$('.list-group').after(str);
		});
		
		$(document).on('submit', '.form-update', function(){
			if(co_name == $('.form-control').val()){
				alert('이름이 동일합니다.');
				return false;
			}
			return true;
		})
	</script>
</body>
</html>