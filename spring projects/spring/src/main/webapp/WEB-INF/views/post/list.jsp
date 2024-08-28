<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<h1>게시글 목록</h1>
	<div class="mb-3">
		<c:forEach items="${list}" var="co">
			<c:choose>
				<c:when test="${co.co_num eq pm.cri.co_num}">
					<a href="<c:url value="/post/list?co_num=${co.co_num}"/>" class="btn btn-info">${co.co_name}</a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value="/post/list?co_num=${co.co_num}"/>" class="btn btn-outline-info">${co.co_name}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<table class="table table-hover mt-3">
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
	    		<c:forEach items="${postList}" var="po">
			      	<tr>
				        <td>${po.po_num}</td>
				        <td>
				        	<a href="#">${po.po_title}</a>
				        </td>
				        <td>${po.po_me_id}</td>
				        <td>
				        	<fmt:formatDate value="${po.po_date}" pattern="yyyy-MM-dd"/>
			        	</td>
				        <td>0</td>
				        <td>${po.po_view}</td>
			      	</tr>
	    		</c:forEach>
	    		<c:if test="${postList.size() eq 0}">
	    			<tr>
	    				<th class="text-center" colspan="6">등록된 게시글이 없습니다.</th>
	    			</tr>
	    		</c:if>
	    	</tbody>
		</table>
	</div>
</body>
</html>