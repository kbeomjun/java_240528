<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
	<title>게시글 목록</title>
</head>
<body>
	<h1>게시글 목록</h1>
	
	<div class="mt-3 mb-3">
		<c:forEach items="${colist}" var="co">
			<c:if test="${co.co_num eq pm.cri.co_num}">
				<a href="<c:url value="/post/list/${co.co_num}"/>" class="btn btn-outline-info btn-community active">${co.co_name}</a>
			</c:if>
			<c:if test="${co.co_num ne pm.cri.co_num}">
				<a href="<c:url value="/post/list/${co.co_num}"/>" class="btn btn-outline-info btn-community">${co.co_name}</a>
			</c:if>
		</c:forEach>
	</div>
	
	<div class="mb-3" style="min-height: 277px;">
		<table class="table table-hover">
	    	<thead>
	      		<tr>
	        		<th>번호</th>
	        		<th>제목</th>
	        		<th>작성자</th>
	        		<th>작성일</th>
	        		<th>추천수</th>
	        		<th>조회수</th>
	      		</tr>
	    	</thead>
	    	<tbody>
	    		<c:forEach items="${polist}" var="po">
			      	<tr>
				        <td>${po.po_num}</td>
				        <td>
				        	<a href="<c:url value="/post/detail/${po.po_num}"/>">${po.po_title}</a>
				        </td>
				        <td>
				        	<c:url var="url" value="/post/list/${pm.cri.co_num}">
								<c:param name="type" value="po_me_id"/>
								<c:param name="search" value="${po.po_me_id}"/>
							</c:url>
				        	<a href="${url}">${po.po_me_id}</a>
			        	</td>
				        <td>
				        	<fmt:formatDate value="${po.po_date}" pattern="yyyy.MM.dd"/>
			        	</td>
				        <td>0</td>
				        <td>${po.po_view}</td>
			      	</tr>
	    		</c:forEach>
	    		<c:if test="${polist.size() eq 0}">
	    			<tr>
	    				<th class="text-center" colspan="6">등록된 게시글이 없습니다.</th>
	    			</tr>
	    		</c:if>
	    	</tbody>
		</table>
	</div>
	
	<ul class="pagination justify-content-center">
		<c:if test="${pm.prev}">
			<c:url var="url" value="/post/list/${pm.cri.co_num}">
				<c:param name="page" value="${pm.cri.startPage - 1}"/>
				<c:param name="type" value="${pm.cri.type}"/>
				<c:param name="search" value="${pm.cri.search}"/>
			</c:url>
		    <li class="page-item">
		    	<a class="page-link" href="${url}">이전</a>
	    	</li>
		</c:if>
		<c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="i">
			<c:choose>
				<c:when test="${pm.cri.page eq i}">
					<c:set var="active" value="active"/>				
				</c:when>
				<c:otherwise>
					<c:set var="active" value=""/>
				</c:otherwise>
			</c:choose>
			<c:url var="url" value="/post/list/${pm.cri.co_num}">
				<c:param name="page" value="${i}"/>
				<c:param name="type" value="${pm.cri.type}"/>
				<c:param name="search" value="${pm.cri.search}"/>
			</c:url>
		    <li class="page-item ${active}">
		    	<a class="page-link" href="${url}">${i}</a>
	    	</li>
		</c:forEach>
		<c:if test="${pm.next}">
			<c:url var="url" value="/post/list/${pm.cri.co_num}">
				<c:param name="page" value="${pm.cri.endPage + 1}"/>
				<c:param name="type" value="${pm.cri.type}"/>
				<c:param name="search" value="${pm.cri.search}"/>
			</c:url>
			<li class="page-item">
				<a class="page-link" href="${url}">다음</a>
			</li>
		</c:if>
 	</ul>
 	
 	<form class="input-group mb-3" action="<c:url value="/post/list/${pm.cri.co_num}"/>" method="get">
 		<div class="input-group-prepend">
	 		<select class="form-control" name="type">
	 			<option value="" <c:if test="${pm.cri.type == ''}">selected</c:if>>전체</option>
	 			<option value="po_me_id" <c:if test="${pm.cri.type == '작성자'}">selected</c:if>>작성자</option>
	 			<option value="po_title" <c:if test="${pm.cri.type == '제목'}">selected</c:if>>제목</option>
	 		</select>
 		</div>
		<input type="text" class="form-control" name="search">
	    <div class="input-group-append">
	    	<button class="btn btn-outline-info">검색</button>
	    </div>
 	</form>
 	
 	<c:if test="${pm.cri.co_num ne 0 && user.me_id ne null}">
	 	<a href="<c:url value="/post/insert/${pm.cri.co_num}"/>" class="btn btn-outline-success">글쓰기</a>
 	</c:if>
 	
 	<script type="text/javascript">
 		$('.btn-community').click(function(e){
 			if($(this).hasClass('active')){
 				e.preventDefault();
 				location.href = '<c:url value="/post/list/0"/>';
 			}
 		});
 	</script>
</body>
</html>