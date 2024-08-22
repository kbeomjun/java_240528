<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"/>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container pt-3" style="min-height: calc(100vh - 240px)">
		<h1>게시글 작성</h1>
		<form action="<c:url value="/post/update"/>" method="post">
			<div class="form-group">
				<label for="po_title">제목:</label>
			  	<input type="text" class="form-control" id="po_title" name="po_title" value="${po.po_title}">
			</div>
			<div class="form-group">
				<label for="po_content">내용:</label>
			  	<textarea class="form-control" id="po_content" name="po_content">${po.po_content}</textarea>
			</div>
			<input type="hidden" name="po_num" value="${po.po_num}">
			<button type="submit" class="btn btn-outline-warning col-12">수정</button>
		</form>
	</div>
	
	<script>
	$('#po_content').summernote({
        placeholder: '게시글을 작성해주세요.',
        tabsize: 2,
        height: 200
      });
    </script>
</body>
</html>