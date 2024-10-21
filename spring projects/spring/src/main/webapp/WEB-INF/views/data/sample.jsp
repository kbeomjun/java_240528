<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<style type="text/css">
		.carousel{box-shadow: 0 0 15px;}
		.carousel-inner img {width: 100%; height: 400px;}
	</style>
</head>
<body>
	<h1>공공데이터</h1>
	<table>
		<thead>
			<tr>
				<th></th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
	
	<script type="text/javascript">
		fetch("<c:url value="/data/sample"/>", {
			method : "post"
		})
			.then(res => json())
			.then(res => {
				console.log(res);
			})
	</script>
</body>
</html>