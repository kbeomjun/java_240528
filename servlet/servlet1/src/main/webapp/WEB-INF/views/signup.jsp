<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
	<style type="text/css">
		.error{color:red; margin-bottom: 5px;}
		.form-group{margin-bottom: 0;}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		<h1 class="mt-3">회원가입</h1>
		<!-- 
		회원 가입을 위한 화면을 구성
		- 아이디, 비번, 비번확인, 이메일
		서버로 아이디, 비번, 이메일을 전송하고
		서버에서는 전송받은 아이디, 비번, 이메일을 콘솔에 출력
		-->
		<form action="<%=request.getContextPath()%>/signup" method="post" id="form">
			<div class="form-group">
			  <label for="id">아이디:</label>
			  <input type="text" class="form-control" id="id" name="id">
			</div>
			<div class="error error-id"></div>
			<div class="form-group">
			  <label for="pw">비번:</label>
			  <input type="password" class="form-control" id="pw" name="pw">
			</div>
			<div class="error error-pw"></div>
			<div class="form-group">
			  <label for="pw2">비번확인:</label>
			  <input type="password" class="form-control" id="pw2" name="pw2">
			</div>
			<div class="error error-pw2"></div>
			<div class="form-group">
			  <label for="email">이메일:</label>
			  <input type="email" class="form-control" id="email" name="email">
			</div>
			<button type="submit" class="btn btn-outline-success col-12 mt-3">회원가입</button>
		</form>
	</div>
	
	<script type="text/javascript">
		let regexId = /^\w{6,13}$/;
		let regexPw = /^[a-zA-Z0-9!@#$]{6,15}$/;
		let msgId = `<span>아이디는 영어, 숫자만 포함 6~13자입니다.</span>`;
		let msgPw = `<span>비번은 영어, 숫자, 특수문자(!@#$)만 포함 6~15자입니다.</span>`;
		let msgPw2 = `<span>비번과 일치하지 않습니다.</span>`;
		let msgRequired = `<span>필수항목입니다.</span>`;
		
		$('#id').change(function(){
			$('.error-id').children().remove();
			
			if(!regexId.test($('#id').val())){
				$('.error-id').append(msgId);
			}else{
				$('.error-id').children().remove();	
			}
			
		});
		
		$('#pw').change(function(){
			$('.error-pw').children().remove();
			
			if(!regexPw.test($('#pw').val())){
				$('.error-pw').append(msgPw);
			}else{
				$('.error-pw').children().remove();
			}
			
		});
		
		$('#pw2').change(function(){
			$('.error-pw2').children().remove();
			
			if($('#pw').val() != $('#pw2').val()){
				$('.error-pw2').append(msgPw2);
			}else{
				$('.error-pw2').children().remove();	
			}
			
		});
		
		$('#form').submit(function(){
			$('.error').children().remove();
			let flag = true;
			
			if($('#id').val() == ''){
				$('.error-id').append(msgRequired);
				flag = false;
			}else{
				if(!regexId.test($('#id').val())){
					$('.error-id').append(msgId);
					flag = false;
				}
			}
			
			if($('#pw').val() == ''){
				$('.error-pw').append(msgRequired);
				flag = false;
			}else{
				if(!regexPw.test($('#pw').val())){
					$('.error-pw').append(msgPw);
					flag = false;
				}
			}
			
			if($('#pw').val() != $('#pw2').val()){
				$('.error-pw2').append(msgPw2);
				flag = false;
			}
			
			return flag;
		});
	
		$('#form').validate({
			rules : {
				email : {
					required : true,
					email : true
				}
			},
			
			messages : {
				email : {
					required : '필수항목입니다.',
					email : 'email 형식이 아닙니다.'
				}
			},
			
			submitHandler : function(){
				return true;
			}
		})
		$.validator.addMethod('regex', function(value, element, regex){
			var re = new RegExp(regex);
			return this.optional(element) || re.test(value);
		}, '정규표현식을 확인하세요.')	
	</script>
</body>
</html>