<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
	<style type="text/css">
		.comment-list{
			list-style: none; padding: 0;
		}
		.comment-list>.comment-item{
			margin-bottom: 20px;
		}
		.comment-list>.comment-item.reply{
			padding-left: 50px;
		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		<h1 class="mt-3">게시글 상세</h1>
		<div class="form-group">
			<label for="title">제목:</label>
			<div class="form-control">${post.po_title}</div>
		</div>
		<div class="form-group">
			<label for="writer">작성자:</label>
			<div class="form-control">${post.po_me_id}</div>
		</div>
		<div class="form-group">
			<label for="date">작성일:</label>
			<div class="form-control"><fmt:formatDate value="${post.po_date}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		</div>
		<div class="form-group">
			<label for="views">조회수:</label>
			<div class="form-control">${post.po_views}</div>
		</div>
		<div class="text-center">
			<a href="#" data-re_state="1" 
				class="btn-up btn btn<c:if test="${re.re_state ne 1}">-outline</c:if>-danger">추천 <span>${post.po_up}</span></a>
			<a href="#" data-re_state="-1"	
				class="btn-down btn btn<c:if test="${re.re_state ne -1}">-outline</c:if>-danger">비추천 <span>${post.po_down}</span></a>
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<div class="form-control" style="min-height: 400px; overflow: scroll;">${post.po_content}</div>
		</div>
		<hr>
		<div>
			<h3>댓글 목록</h3>
			<ul class="comment-list">
				<li class="comment-item">
					<div>작성자 아이디(시간)</div>
					<div>댓글 내용</div>
				</li>
				<li class="comment-item reply">
					<div>작성자 아이디(시간)</div>
					<div>대댓글 내용</div>
				</li>
			</ul>
			<div class="comment-pagination"></div>
			<div class="comment-insert-box input-group mb-3">
				<textarea class="col-12 input-comment-insert"></textarea>
				<button class="btn btn-outline-success btn-comment-insert">등록</button>
			</div>
		</div>
		<a href="<c:url value="/post/list?co_num=${post.po_co_num}"/>" class="btn btn-outline-primary">목록</a>
		<c:if test="${user ne null && post.po_me_id == user.me_id}">
			<a href="<c:url value="/post/update?po_num=${post.po_num}"/>" class="btn btn-outline-warning">수정</a>
			<a href="<c:url value="/post/delete?po_num=${post.po_num}"/>" class="btn btn-outline-danger">삭제</a>
		</c:if>
	</div>
	
	<script type="text/javascript">
		var cri = {
			po_num : '${post.po_num}',
			page : 1
		}
		
		getCommentList(cri);
		
		$('.btn-up, .btn-down').click(function(e){
			e.preventDefault();
			
			if(!checkLogin()){
				return;
			}
			
			let re_state = $(this).data('re_state');
			let po_num = '${post.po_num}';
			$.ajax({
				url : '<c:url value="/post/recommend"/>',
				method : "get", //원하는 방식 선택
				data : { //보낸 데이터를 객체로
					re_state : re_state,
					po_num : po_num
				},
				success : function(data){
					let res = data.result;
					if(res == 1 && re_state == 1){
						alert('추천했습니다.');
					}else if(res == 1 && re_state == -1){
						alert('비추천했습니다.');
					}else{
						alert(`\${re_state == 1?'추천':'비추천'}을 취소했습니다.`);
						re_state = 0;
					}
					checkRecommendBtns(re_state);
					
					let post = JSON.parse(data.post);
					$('.btn-up span').text(post.po_up);
					$('.btn-down span').text(post.po_down);
				}, 
				error : function(xhr, status, error){
					//xhr : XHLHttpRequest 객체, 요청과 관련된 정보를 제공
					//status :HTTP 상태 코드, 요청이 실패한 원인
					//error : 에러에 대한 추가 정보
					console.log("error");
				}
			});
		});
		function checkRecommendBtns(re_state){
			$('.btn-up, .btn-down').removeClass('btn-danger');
			$('.btn-up, .btn-down').addClass('btn-outline-danger');
			if(re_state != 0){
				$(re_state == 1?'.btn-up':'.btn-down').removeClass('btn-outline-danger');
				$(re_state == 1?'.btn-up':'.btn-down').addClass('btn-danger');
			}
		}
		
		$(document).on('click', ".pagination .page-item", function(){
			if($(this).hasClass('disabled')){
				return;
			}
			let page = $(this).data('page');
			cri.page = page;
			getCommentList(cri);
		});
		function getCommentList(cri){
			$.ajax({
				url : '<c:url value="/comment/list"/>',
				method : "post",
				data : cri,
				success : function(data){
					let list = data.list;
					displayCommentList(list);
					let pm = JSON.parse(data.pm);
					displayPagination(pm);
				},
				error : function(xhr, status, error){
					console.log("error");
					console.log(xhr.responseText);
				}
			});
		}
		function displayPagination(pm){
			if(pm.totalCount == 0){
				return;
			}
			
			str = `<ul class="pagination justify-content-center">`;
			
			var disabled = pm.prev ? '' : 'disabled';
			str +=				
			    `<li class="page-item \${disabled}" data-page="\${pm.startPage - 1}">
			    	<a class="page-link" href="javascript:void(0);">이전</a>
		    	</li>`;
			
	    	for(var i = pm.startPage; i <= pm.endPage; i++){
	    		var active = pm.cri.page == i ? 'active' : '';
				str +=
				    `<li class="page-item \${active}" data-page="\${i}">
				    	<a class="page-link" href="javascript:void(0);">\${i}</a>
			    	</li>`;
			}
			
	    	var disabled = pm.next ? '' : 'disabled';
			str +=
			    `<li class="page-item \${disabled}" data-page="\${pm.endPage + 1}">
			    	<a class="page-link" href="javascript:void(0);">다음</a>
		    	</li>`;
			
	    	str += `</ul>`;
			$('.comment-pagination').html(str);
		}
		function displayCommentList(list){
			var str = '';
			if(list.length == 0){
				str = `<li>등록된 댓글이 없습니다.</li>`;
				$('.comment-list').html(str);
				return;
			}
			
			for(cm of list){
				var btns = '';
				if(cm.cm_me_id == '${user.me_id}'){
					btns += `<a href="javascript:void(0);" class="btn-comment-delete text-danger" 
								data-cm_num="\${cm.cm_num}" data-cm_ori_num="\${cm.cm_ori_num}">X</a> `;
					btns += `<a href="javascript:void(0);" class="btn-comment-update text-info" 
								data-cm_num="\${cm.cm_num}">수정</a>`;
				}
				
				if(cm.cm_num == cm.cm_ori_num){
					str += `
						<li class="comment-item">
							<div>
								<span>\${cm.cm_me_id}(\${cm.cm_date})</span>
								\${btns}
							</div>
							<div>\${cm.cm_content}</div>
						</li>
					`;
				}
				else{
					str += `
						<li class="comment-item reply">
							<div>
								<span>\${cm.cm_me_id}(\${cm.cm_date})</span>
								\${btns}
							</div>
							<div>\${cm.cm_content}</div>
						</li>
					`;
				}
			}
			$('.comment-list').html(str);
		}
		
		$('.btn-comment-insert').click(function(){
			if(!checkLogin()){
				return;
			}
			
			let cm_content = $('.input-comment-insert').val();
			let cm_ori_num = 0;
			let po_num = '${post.po_num}';
			
			if(cm_content.trim() == ""){
				alert("댓글을 입력하세요.");
				$('.input-comment-insert').focus();
				return;
			}
			
			let obj = {
					cm_content : cm_content,
					cm_ori_num : cm_ori_num,
					cm_po_num : po_num
			};

			$.ajax({
				url : '<c:url value="/comment/insert"/>',
				method : "post",
				data : obj,
				success : function(data){
					if(data.result){
						alert("댓글을 등록했습니다.");
						cri.page = 1;
						getCommentList(cri);
					}
					else{
						alert("댓글을 등록하지 못했습니다.");
					}
					$('.input-comment-insert').val('');
				},
				error : function(xhr, status, error){
					console.log("error");
					console.log(xhr);
				}
			});
		});
		function checkLogin(){
			if('${user.me_id}' == ''){
				if(confirm('로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?')){
					location.href = '<c:url value="/login"/>';
					return false;
				}else{
					return false;
				}
			}
			return true;
		}
		
		$(document).on('click', '.btn-comment-delete', function(){
			let cm_num = $(this).data('cm_num');
			let cm_ori_num = $(this).data('cm_ori_num');
			
			let obj ={
				cm_num : cm_num,
				cm_ori_num : cm_ori_num
			}
			
			$.ajax({
				url : '<c:url value="/comment/delete"/>',
				method : "post",
				data : obj,
				success : function(data){
					if(data.result){
						alert("댓글을 삭제했습니다.");
						getCommentList(cri);
					}
					else{
						alert("댓글을 삭제하지 못했습니다.");
					}
				},
				error : function(xhr, status, error){
					console.log("error");
					console.log(xhr);
				}
			});
		})
		
		$(document).on('click', '.btn-comment-update', function(){
			$('.comment-update-box').remove();
			let cm_num = $(this).data('cm_num');
			let cm_content = $(this).parent().next().text();
			var str = `
				<div class="comment-update-box input-group mb-3">
					<textarea class="col-12 input-comment-update">\${cm_content}</textarea>
					<button class="btn btn-outline-warning btn-comment-update-complete" data-cm_num="\${cm_num}">수정 완료</button>
				</div>
			`;
			$('.comment-insert-box').after(str);
			$('.comment-insert-box').hide();
		});
		
		$(document).on('click', '.btn-comment-update-complete', function(){
			let cm_num = $(this).data('cm_num');
			let cm_content = $('.input-comment-update').val();
			
			let obj = {
				cm_num : cm_num,
				cm_content : cm_content
			}
			
			$.ajax({
				url : '<c:url value="/comment/update"/>',
				method : "post",
				data : obj,
				success : function(data){
					if(data.result){
						alert("댓글을 수정했습니다.");
						getCommentList(cri);
					}
					else{
						alert("댓글을 수정하지 못했습니다.");
					}
					$('.comment-insert-box').show();
					$('.comment-update-box').remove();
				},
				error : function(xhr, status, error){
					console.log("error");
					console.log(xhr);
				}
			});
		});
	</script>
</body>
</html>