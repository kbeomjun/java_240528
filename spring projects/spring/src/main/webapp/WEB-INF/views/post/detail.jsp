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
			<div class="form-control"><fmt:formatDate value="${po.po_date}" pattern="yyyy.MM.dd"/></div>
		</div>
		<div class="form-group">
			<label for="views">조회수:</label>
			<div class="form-control">${po.po_view}</div>
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
		<div class="form-group">
			<label for="content">첨부파일:</label>
			<c:forEach items="${list}" var="fi">
				<a href="<c:url value="/download${fi.fi_name}"/>" class="form-control" download="${fi.fi_ori_name}">
					${fi.fi_ori_name}
				</a>
			</c:forEach>
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
</body>
</html>