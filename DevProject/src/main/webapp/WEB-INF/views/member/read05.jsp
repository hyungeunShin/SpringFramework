<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>read05</h1>
	
	<h4>user.address</h4>
	user.address.postCode : ${user.address.postCode} <br>
	user.address.location : ${user.address.location} <br>
	
	<p>user.cardList</p>
	<c:forEach items="${user.cardList}" var="card">
		${card.no}
		${card.validMonth}
	</c:forEach>
</body>
</html>