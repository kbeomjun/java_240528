<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
	<p>안녕하세요. 제 이름은 ${name}입니다.</p>
	
	<h1>데이터 전송 연습(서버로)</h1>
	<form action="<c:url value="/"/>" method="get">
		<input type="text" name="name" placeholder="이름 입력">
		<br>
		<input type="text" name="age" placeholder="나이 입력">
		<br>
		<button type="submit">전송</button>
	</form>
</body>
</html>