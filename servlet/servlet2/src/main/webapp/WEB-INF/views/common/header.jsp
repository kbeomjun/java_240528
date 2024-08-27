<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="navbar-brand" href="<c:url value="/"/>">Home</a>
				</li>
			</ul>
			<ul class="navbar-nav">
				<li class="nav-item dropdown dropdown-community">
					<a class="nav-link dropdown-toggle toggle-community" href="#" id="navbardrop" data-toggle="dropdown">
						커뮤니티
					</a>
					<div class="dropdown-menu menu-community">

					</div>
			    </li>
			    <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						Drop2
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">없음</a>
					</div>
			    </li>
			    <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						Drop3
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">없음</a>
					</div>
			    </li>
			</ul>
			<ul class="navbar-nav ">
			    <c:if test="${user == null}">
			    	<li class="nav-item">
						<a class="nav-link" href="<c:url value="/signup"/>">회원가입</a>
		    		</li>
		    		<li class="nav-item">
						<a class="nav-link" href="<c:url value="/login"/>">로그인</a>
		    		</li>
			    </c:if>
			    <c:if test="${user != null}">
			    	<li class="nav-item">
						<a class="nav-link" href="<c:url value="/mypage"/>">마이페이지</a>
		    		</li>
			    	<li class="nav-item">
						<a class="nav-link" href="<c:url value="/logout"/>">로그아웃</a>
		    		</li>
			    </c:if>
    		</ul>
		</div>
	</nav>
	
	<script type="text/javascript">
		$.ajax({
			url : '<c:url value="/community"/>',
			method : "post",
			success : function(data){
				let list = data.list;
				var str = ``;
				if(list.length == 0){
					str = `<a class="dropdown-item" href="#">없음</a>`;
				}else{
					for(var i = 0; i < list.length; i++){
						str += `
							<a class="dropdown-item item-community" href="<c:url value="/post/list?co_num=\${list[i].co_num}"/>">\${list[i].co_name}</a>
						`;
					}
				}
				$('.menu-community').html(str);
			}, 
			error : function(xhr, status, error){
				console.log("error");
			}
		});
	</script>
</body>
</html>