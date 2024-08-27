<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
	<jsp:include page="/WEB-INF/views/common/head.jsp"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div>
		<div class="container pt-3" style="min-height: calc(100vh - 240px)">
			<h1>마이페이지</h1>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>