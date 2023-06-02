<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Read04</h1>
	<h4>hobbyArray</h4>
	<c:forEach items="${hobbyArray}" var="hobby">
		${hobby}
	</c:forEach>
	
	<h4>hobbyList</h4>
	<c:forEach items="${hobbyList}" var="hobby">
		${hobby}
	</c:forEach>
	
	<h4>carArray</h4>
	<c:forEach items="${carArray}" var="car">
		${car} 
	</c:forEach>
	
	<h4>carList</h4>
	<c:forEach items="${carList}" var="car">
		${car}
	</c:forEach>
</body>
</html>