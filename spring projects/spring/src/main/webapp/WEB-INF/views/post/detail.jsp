<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
</head>
<body>
	<c:if test="${po ne null}">
		<h1 class="mt-3">게시글 상세</h1>
		<div class="form-group">
			<label for="title">제목:</label>
			<div class="form-control">${po.po_title}</div>
		</div>
		<div class="form-group">
			<label for="writer">작성자:</label>
			<div class="form-control">${po.po_me_id}</div>
		</div>
		<div class="form-group">
			<label for="date">작성일:</label>
			<div class="form-control">
				<fmt:formatDate value="${po.po_date}" pattern="yyyy.MM.dd"/>
			</div>
		</div>
		<div class="form-group">
			<label for="views">조회수:</label>
			<div class="form-control">${po.po_view}</div>
		</div>
		<div class="text-center">
			<a href="#" data-re_state="1" class="btn-up btn btn<c:if test="${re.re_state ne 1}">-outline</c:if>-success">
				추천 <span>${po.po_up}</span>
			</a>
			<a href="#" data-re_state="-1" class="btn-down btn btn<c:if test="${re.re_state ne -1}">-outline</c:if>-danger">
				비추천 <span>${po.po_down}</span>
			</a>
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<div class="form-control" style="min-height: 400px; overflow: scroll;">${po.po_content}</div>
		</div>
		<div class="form-group">
			<label for="content">첨부파일:</label>
			<c:forEach items="${list}" var="fi">
				<a href="<c:url value="/download${fi.fi_name}"/>" class="form-control" download="${fi.fi_ori_name}">
					${fi.fi_ori_name}
				</a>
			</c:forEach>
			<c:if test="${list.size() eq 0}">
				없음
			</c:if>
		</div>
	</c:if>
	<c:if test="${po eq null}">
		<h3>삭제되거나 잘못된 게시글입니다.</h3>
	</c:if>
	<c:url var="url" value="/post/list">
		<c:param name="co_num" value="${cri.co_num}"/>
		<c:param name="page" value="${cri.page}"/>
		<c:param name="type" value="${cri.type}"/>
		<c:param name="search" value="${cri.search}"/>
	</c:url>
	<a href="${url}" class="btn btn-outline-info">목록</a>
	<c:if test="${user.me_id eq po.po_me_id}">
		<c:url var="url" value="/post/update">
			<c:param name="po_num" value="${po.po_num}"/>
			<c:param name="co_num" value="${cri.co_num}"/>
			<c:param name="page" value="${cri.page}"/>
			<c:param name="type" value="${cri.type}"/>
			<c:param name="search" value="${cri.search}"/>
		</c:url>
		<a href="${url}" class="btn btn-outline-warning">수정</a>
		<c:url var="url" value="/post/delete">
			<c:param name="po_num" value="${po.po_num}"/>
			<c:param name="co_num" value="${cri.co_num}"/>
			<c:param name="page" value="${cri.page}"/>
			<c:param name="type" value="${cri.type}"/>
			<c:param name="search" value="${cri.search}"/>
		</c:url>
		<a href="${url}" class="btn btn-outline-danger">삭제</a>
	</c:if>
	
	<hr>
	<div class="comment-container">
		<ul class="comment-list" style="list-style: none; padding: 0">
			<li class="comment-item">
				<div class="clearfix">
					<span class="float-left" style="line-height: 38px;">아이디</span>
					<div class="float-right">
						<button class="btn btn-outline-warning">수정</button>
						<button class="btn btn-outline-danger">삭제</button>
					</div>
				</div>
				<div style="padding-left: 20px; line-height: 38px;">댓글내용</div>
			</li>
		</ul>
		<div class="comment-pagination">
			<ul class="pagination justify-content-center">
			
	 		</ul>
		</div>
		<div class="comment-input-box">
			<div class="input-group mb-3">
		    	<textarea class="form-control" id="cm_content" placeholder="댓글 입력"></textarea>
		    	<div class="input-group-append">
		      		<button class="btn btn-outline-success btn-insert">등록</button>
		    	</div>
		  	</div>
		</div>
	</div>
	
	<script type="text/javascript">
		let cri = {
			page : 1,
			search : '${po.po_num}'
		}	

		getCommentList2(cri);
		function getCommentList2(cri){
			console.log(cri);
			$.ajax({
				async : true, 
				url : '<c:url value="/comment/list2"/>', 
				type : 'post', 
				data : JSON.stringify(cri), 
				contentType : "application/json; charset=utf-8",
				success : function (data){
					$('.comment-container').html(data);
				}, 
				error : function(jqXHR, textStatus, errorThrown){
					console.log(jqXHR)
				}
			});
		}
		
		function getCommentList(cri){
			$.ajax({
				async : true,
				url : '<c:url value="/comment/list"/>', 
				type : 'post',
				data : JSON.stringify(cri), 
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function (data){
					displayCommentList(data.list);
					displayPagination(data.pm);
				}, 
				error : function(jqXHR, textStatus, errorThrown){
					console.log(jqXHR);
				}
			});				
		}
		function displayCommentList(list){
			if(list == null || list.length == 0){
				$('.comment-list').html('<li class="comment-item display-4">등록된 댓글이 없습니다.</li>');
				return;
			}
			
			var str = '';
			for(cm of list){
				var btns = '';
				if(cm.cm_me_id == '${user.me_id}'){
					btns = `
						<div class="float-right">
							<button class="btn btn-outline-warning">수정</button>
							<button class="btn btn-outline-danger">삭제</button>
						</div>
					`;
				}
				str += `
					<li class="comment-item">
						<div class="clearfix">
							<span class="float-left" style="line-height: 38px;">\${cm.cm_me_id}</span>
							\${btns}
						</div>
						<div style="padding-left: 20px; line-height: 38px;">\${cm.cm_content}</div>
					</li>
				`;
			}
			$('.comment-list').html(str);	
		}
		function displayPagination(pm){
			if(pm == null || pm.endPage == 0){
				return;
			}
		
			var str = '';
			if(pm.prev){
				str += `
					<li class="page-item" data-page="\${pm.startPage - 1}">
						<a class="page-link" href="javascript:void(0);">이전</a>
					</li>
				`;
			}
			for(var i = pm.startPage; i <= pm.endPage; i++){
				var active = pm.cri.page == i ? 'active' : '';
				str += `
				    <li class="page-item \${active}" data-page="\${i}">
				    	<a class="page-link" href="javascript:void(0);">\${i}</a>
			    	</li>
				`;
			}		
			if(pm.next){
				str += `
				    <li class="page-item" data-page="\${pm.endPage + 1}">
				    	<a class="page-link" href="javascript:void(0);">다음</a>
			    	</li>
				`;
			}
			$('.comment-pagination>.pagination').html(str);
		}
		$(document).on('click', '.comment-pagination .page-item', function(){
			cri.page = $(this).data('page');
			getCommentList(cri);
		})

		function alertLogin(){
			if('${user.me_id}' != ''){
				return false;
			}
			if(confirm('로그인이 필요한 서비스입니다.\n로그인 하시겠습니까?')){
				location.href = '<c:url value="/login"/>';
			}
			return true;
		}
	
		$(document).on('click', '.btn-insert', function(){
			if(alertLogin()){
				return;
			}		
			
			var cm_content = $('#cm_content').val();
			var cm_po_num = '${po.po_num}';
			var comment = {
				cm_content : cm_content,
				cm_po_num : cm_po_num
			}
			
			$.ajax({
				async : true,
				url : '<c:url value="/comment/insert"/>',
				type : 'post',
				data : JSON.stringify(comment), 
				contentType : "application/json; charset=utf-8",
				success : function (data){
					if(data){
						alert('댓글을 등록했습니다.');
						$('#cm_content').val('');
					}else{
						alert('댓글을 등록하지 못했습니다.');
					}
					getCommentList(cri);
				}, 
				error : function(jqXHR, textStatus, errorThrown){
					console.log(jqXHR);
				}
			});
		});
	</script>
</body>
</html>