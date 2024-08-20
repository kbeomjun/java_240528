<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"/>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"/></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
	<style type="text/css">
		.error{color:red}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container pt-3" style="min-height: calc(100vh - 240px)">
		<h1>회원가입</h1>
		<form action="<c:url value="/signup"/>" method="post" id="form">
			<div class="form-group">
				<label for="me_id">아이디:</label>
				<input type="text" class="form-control" id="me_id" name="me_id">
			</div>
			<button type="button" class="btn btn-outline-success btn-dup col-12 mb-3">아이디 중복검사</button>
			<div class="form-group">
				<label for="me_pw">비번:</label>
				<input type="password" class="form-control" id="me_pw" name="me_pw">
			</div>
			<div class="form-group">
				<label for="me_pw2">비번확인:</label>
				<input type="password" class="form-control" id="me_pw2" name="me_pw2">
			</div>
			<div class="form-group">
				<label for="me_email">이메일:</label>
				<input type="email" class="form-control" id="me_email" name="me_email">
			</div>
			<button type="submit" class="btn btn-outline-success col-12">회원가입</button>
		</form>
	</div>
	
	<script type="text/javascript">
		var flag = false;
	
		$('#form').validate({
			rules : {
				me_id : {
					required : true,
					regex : /^[a-z0-9]{6,13}$/
				},
				me_pw : {
					required : true,
					regex : /^[a-zA-Z0-9!@#$]{6,15}$/
				},
				me_pw2 : {
					equalTo : me_pw,
				},
				me_email : {
					required : true,
					email : true
				}
			},
			
			messages : {
				me_id : {
					required : '* 필수항목입니다.',
					regex : '* 아이디는 6~13자의 영문 소문자, 숫자만 사용 가능합니다.'
				},
				me_pw : {
					required : '* 필수항목입니다.',
					regex : '* 비밀번호는 6~15자의 영문 대/소문자, 숫자, 특수문자(!@#$)만 사용 가능합니다.'
				},
				me_pw2 : {
					equalTo : '* 비밀번호가 일치하지 않습니다.',
				},
				me_email : {
					required : '* 필수항목입니다.',
					email : '* 이메일 형식이 아닙니다.'
				}
			},
			
			submitHandler : function(){
				if(!flag){
					alert('아이디 중복검사를 하세요.');
					return false;
				}
				return checkId();
			}
		})
		$.validator.addMethod('regex', function(value, element, regex){
			var re = new RegExp(regex);
			return this.optional(element) || re.test(value);
		}, '정규표현식을 확인하세요.')
		
		$('.btn-dup').click(function(){
			var me_id = $('#me_id').val();
			var regex = /^[a-z0-9]{6,13}$/;
			if(!regex.test(me_id)){
				alert('아이디는 6~13자의 영문 소문자, 숫자만 사용 가능합니다.');
				return;
			}
			if(checkId()){
				alert('사용가능한 아이디입니다.');
				flag = true;
			}else{
				alert('이미 사용중인 아이디입니다.');
			}
		});

		$('#me_id').change(function(){
			flag = false;
		});
		
		function checkId(){
			var res = false;
			var me_id = $('#me_id').val();
			
			$.ajax({
				async : false, // 동기화를 시킴 => 확인이 끝날때까지 다음 작업이 진행되지 않음
				url : '<c:url value="/check/id"/>',
				data : {
					me_id : me_id
				},
				success : function(data){
					res = data.result;
				},
				error : function(xhr){
					console.log(xhr);
				}
			});
			return res;
		}
	</script>
</body>
</html>