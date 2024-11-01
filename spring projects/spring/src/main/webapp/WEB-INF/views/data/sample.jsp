<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>공공데이터</h1>
	지역 :
	<select id = "location">
		<option>서울</option>
		<option>부산</option>
		<option>대전</option>
	</select>
	<button id = "btn1">해당 지역 대기오염 정보</button>
	<br><br>
	<table id = "result1" border = "1">
		<thead>
			<tr>
				<th>측정소명</th>
				<th>측정일시</th>
				<th>통합대기환경수치</th>
				<th>미세먼지농도</th>
				<th>일산화탄소농도</th>
				<th>이산화질소농도</th>
				<th>아황산가스농도</th>
				<th>오존농도</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	
	<script>
		$(function() {
	        $("#btn1").click(function() {
	            $.ajax({
	                url: "<c:url value='/data/sample'/>",
	                method: "POST",
	                data: { location: $("#location").val() },
	            	dataType : "json", 
	                success: function(data) {
	                    // 응답을 JSON으로 파싱
	                    const itemArr = data.response.body.items;
	                    let value = "";
	
	                    for (let i in itemArr) {
	                        const item = itemArr[i];
	                        value += "<tr>";
	                        value += "<td>" + item.stationName + "</td>";
	                        value += "<td>" + item.dataTime + "</td>";
	                        value += "<td>" + item.khaiValue + "</td>";
	                        value += "<td>" + item.pm10Value + "</td>";
	                        value += "<td>" + item.coValue + "</td>";
	                        value += "<td>" + item.no2Value + "</td>"; 
	                        value += "<td>" + item.so2Value + "</td>";
	                        value += "<td>" + item.o3Value + "</td>";
	                        value += "</tr>";
	                    }
	
	                    $("#result1 tbody").html(value);
	                },
	                error: function(xhr, status, error) {
	                    console.error("AJAX Error: ", status, error);
	                    console.log("Response: ", xhr.responseText); // 서버 응답 내용 출력
	                }
	            });
	        });
	    });
	</script>
</body>
</html>