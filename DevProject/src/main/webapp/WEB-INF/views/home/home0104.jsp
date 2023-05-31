<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>4. 표현언어(EL)을 이용하여 출력</h3>
	<p>1) 자바빈즈 프로퍼티를 조회하는 경우 '속성명.프로퍼티명'을 지정한다</p>
	<table border="1">
		<tr>
			<td>\${map.userId}</td>
			<td>${map["userId"]}</td>
		</tr>
		<tr>
			<td>\${map.password}</td>
			<td>${map["password"]}</td>
		</tr>
		<tr>
			<td>\${map.userName}</td>
			<td>${map.userName}</td>
		</tr>
		<tr>
			<td>\${map.email}</td>
			<td>${map.email}</td>
		</tr>
	</table>
</body>
</html>