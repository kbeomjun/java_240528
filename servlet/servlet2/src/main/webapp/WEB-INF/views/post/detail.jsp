<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container pt-3" style="min-height: calc(100vh - 240px)">
		<h1>게시글 조회</h1>
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
			<div class="form-control"><fmt:formatDate value="${po.po_date}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		</div>
		<div class="form-group">
			<label for="views">조회수:</label>
			<div class="form-control">${po.po_views}</div>
		</div>
		<div class="text-center">
			<a href="#" data-re_state="1" 
				class="btn-up btn btn<c:if test="${re.re_state ne 1}">-outline</c:if>-danger">추천 <span>${po.po_up}</span></a>
			<a href="#" data-re_state="-1"	
				class="btn-down btn btn<c:if test="${re.re_state ne -1}">-outline</c:if>-danger">비추천 <span>${po.po_down}</span></a>
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<div class="form-control" style="min-height: 400px; overflow: scroll;">${po.po_content}</div>
		</div>
		<a href="<c:url value="/post/list?co_num=${po.po_co_num}"/>" class="btn btn-outline-primary">목록</a>
		<c:if test="${user ne null && po.po_me_id == user.me_id}">
			<a href="<c:url value="/post/update?po_num=${po.po_num}"/>" class="btn btn-outline-warning">수정</a>
			<a href="<c:url value="/post/delete?po_num=${po.po_num}"/>" class="btn btn-outline-danger">삭제</a>
		</c:if>
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
			<div class="comment-insert-box mb-3">
				<textarea class="col-12 input-comment-insert"></textarea>
				<button class="btn btn-outline-success mt-3 btn-comment-insert">등록</button>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script type="text/javascript">
		
	</script>
</body>
</html>