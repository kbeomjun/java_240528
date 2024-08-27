<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"/>
	<style type="text/css">
		.error{color:red; margin-bottom: 5px;}
		.form-group{margin-bottom: 0;}
		.container-body{padding: 30px; margin-top: 30px;}
		.form-group, .form-control{height: 50px};
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container pt-3" style="min-height: calc(100vh - 240px)">
		<h1>마이페이지</h1>
		<div class="row">
	    	<div class="col-sm-4">
	      		<ul class="nav nav-pills flex-column">
	        		<li class="nav-item">
	          			<a class="nav-link active" href="<c:url value="/mypage"/>">테스트 결과</a>
	        		</li>
	        		<li class="nav-item">
	          			<a class="nav-link link-update-pw" href="#">비밀번호 변경</a>
	        		</li>
	        		<li class="nav-item">
	          			<a class="nav-link" href="#">회원탈퇴</a>
	        		</li>
      			</ul>
	      	<hr class="d-sm-none">
	    	</div>
		    <div class="col-sm-8 container-item">
		      	<h2>TITLE HEADING</h2>
		      	<h5>Title description, Sep 2, 2017</h5>
		      	<div class="fakeimg">Fake Image</div>
		      	<p>Some text..</p>
				<p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
		    </div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script type="text/javascript">
		$('.nav-link').click(function(){
			$('.nav-link').removeClass('active');
			$(this).addClass('active');
		});
		
		$('.link-update-pw').click(function(){
			$('.container-item').children().remove();
			var str = `
			<form action="<c:url value="/mypage"/>" method="post" id="form" style="margin-top: 30px">
				<div class="form-group input-group">
					<input type="password" class="form-control" id="me_pw" name="me_pw" placeholder="새 비밀번호">
				</div>
				<div class="error error-pw"></div>
				<div class="form-group input-group">
					<input type="password" class="form-control" id="me_pw2" name="me_pw2" placeholder="비밀번호 확인">
				</div>
				<div class="error error-pw2"></div>
				<button type="submit" class="btn btn-outline-success col-12 mt-3">변경</button>
			</form>
			`;
			$('.container-item').append(str);
		});
		
		let regexPw = /^[a-zA-Z0-9!@#$]{6,15}$/;
		let msgPw = `<span>비번은 영어, 숫자, 특수문자(!@#$)만 포함 6~15자입니다.</span>`;
		let msgPw2 = `<span>비번과 일치하지 않습니다.</span>`;
		
		$(document).on('change', '#me_pw', function(){
			$('.error-pw').children().remove();
			
			if($('#me_pw').val() == ''){
				$('.error-pw').append(msgRequired);
			}else{
				if(!regexPw.test($('#me_pw').val())){
					$('.error-pw').append(msgPw);
				}else{
					$('.error-pw').children().remove();
				}
			}
		});
		
		$(document).on('change', '#me_pw2', function(){
			$('.error-pw2').children().remove();
			
			if($('#me_pw').val() != $('#me_pw2').val()){
				$('.error-pw2').append(msgPw2);
			}else{
				$('.error-pw2').children().remove();	
			}
		});
		
		$(document).on('submit', '#form', function(){
			$('.error').children().remove();
			let flag = true;
			
			if($('#me_pw').val() == ''){
				$('.error-pw').append(msgRequired);
				flag = false;
			}else{
				if(!regexPw.test($('#me_pw').val())){
					$('.error-pw').append(msgPw);
					flag = false;
				}
			}
			
			if($('#me_pw').val() != $('#me_pw2').val()){
				$('.error-pw2').append(msgPw2);
				flag = false;
			}
			
			return flag;
		});
	</script>
</body>
</html>