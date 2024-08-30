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
	<h1>게시글 수정</h1>
	<form action="<c:url value="/post/update"/>" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="po_title" value="${po.po_title}">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" id="content" name="po_content">${po.po_content}</textarea>
		</div>
		<div class="form-group file-container">
			<label>첨부파일:</label>
			<c:forEach items="${list}" var="fi">
				<div class="form-control" style="position:relative;">
					<span>${fi.fi_ori_name}</span>
					<a href="#" class="btn-del" data-num="${fi.fi_num}" style="position:absolute; right:10px;">X</a>
				</div>
			</c:forEach>
			<c:forEach begin="1" end="${3 - list.size()}">
				<input type="file" class="form-control" name="fileList">
			</c:forEach>
		</div>
		<button class="btn btn-outline-warning col-12">게시글 수정</button>
		<input type="hidden" name="co_num" value="${cri.co_num}">
		<input type="hidden" name="po_num" value="${po.po_num}">
		<input type="hidden" name="page" value="${cri.page}">
		<input type="hidden" name="search" value="${cri.search}">
		<input type="hidden" name="type" value="${cri.type}">
	</form>
	
	<script>
		$('#content').summernote({
		  tabsize: 2,
		  height: 400
		});
		
		$('.btn-del').click(function(){
			var fi_num = $(this).data('num');
			$(this).parent().remove();
			var input = `<input type="hidden" name="fi_nums" value="\${fi_num}">`;
			var file = `<input type="file" class="form-control" name="fileList">`;
			$('.file-container').append(input);
			$('.file-container').append(file);
		});
		
    </script>
</body>
</html>