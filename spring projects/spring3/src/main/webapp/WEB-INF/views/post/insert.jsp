<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
	<h1>게시글 등록</h1>
	<form action="<c:url value="/post/insert/${co_num}"/>" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="po_title">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" id="content" name="po_content"></textarea>
		</div>
		<div class="form-group">
			<label>첨부파일:</label>
			<input type="file" class="form-control" name="fiList">
			<input type="file" class="form-control" name="fiList">
			<input type="file" class="form-control" name="fiList">
		</div>
		<input type="hidden" name="po_co_num" value="${co_num}">
		<button class="btn btn-outline-success col-12">등록</button>
	</form>
	
	<script>
		$('#content').summernote({
		  placeholder: '게시글을 작성해주세요.',
		  tabsize: 2,
		  height: 400
		});
    </script>
</body>
</html>