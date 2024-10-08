<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<ul class="comment-list" style="list-style: none; padding: 0">
		<c:forEach items="${list}" var="cm">
			<li class="comment-item">
				<div class="clearfix">
					<span class="float-left" style="line-height: 38px;">${cm.cm_me_id}</span>
					<c:if test="${cm.cm_me_id eq user.me_id}">
						<div class="float-right">
							<button class="btn btn-outline-warning btn-update-comment" data-num="${cm.cm_num}">수정</button>
							<button class="btn btn-outline-danger btn-delete-comment" data-num="${cm.cm_num}">삭제</button>
						</div>
					</c:if>
				</div>
				<div style="padding-left: 20px; line-height: 38px;">${cm.cm_content}</div>
			</li>
		</c:forEach>
	</ul>
	<div class="comment-pagination">
		<ul class="pagination justify-content-center">
			<c:if test="${pm.prev}">
				<li class="page-item" data-page="${pm.starPage + 1}">
			    	<a class="page-link" href="javascript:void(0);">이전</a>
		    	</li>
			</c:if>
			<c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="i">
				<c:set var="active" value=""/>
				<c:if test="${pm.cri.page == i}">
					<c:set var="active" value="active"/>	
				</c:if>
				<li class="page-item ${active}" data-page="${i}">
			    	<a class="page-link" href="javascript:void(0);">${i}</a>
		    	</li>
			</c:forEach>
			<c:if test="${pm.next}">
				<li class="page-item" data-page="${pm.endPage + 1}">
			    	<a class="page-link" href="javascript:void(0);">다음</a>
		    	</li>
			</c:if>
		</ul>
	</div>
	<div class="comment-input-box">
		<div class="input-group mb-3">
	    	<textarea class="form-control" id="cm_content" placeholder="댓글 입력"></textarea>
	    	<div class="input-group-append">
	      		<button class="btn btn-outline-success btn-insert-comment">등록</button>
	    	</div>
	  	</div>
	</div>