<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Result</h2>
	<table>
		<tr>
			<td>국적</td>
			<td>${member.nationality}</td>
		</tr>
		<tr>
			<td>소유차량</td>
			<td>
				<c:forEach items="${member.carList}" var="car">
					${car}<br>
				</c:forEach>
			</td>
		</tr>
		
	</table>
</body>
</html>