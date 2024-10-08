<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
	<title>게시글</title>
</head>
<body>
	<h1>게시글</h1>
	
	<div class="mt-3 mb-3">
		<c:if test="${po ne null}">
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
					<fmt:formatDate value="${po.po_date}" pattern="yyyy.MM.dd HH:mm:ss"/>
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
				<div class="form-control" style="min-height: 400px; height: auto;">${po.po_content}</div>
			</div>
			<div class="form-group">
				<label for="content">첨부파일:</label>
				<c:forEach items="${fiList}" var="fi">
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
	</div>
	
</body>
</html>