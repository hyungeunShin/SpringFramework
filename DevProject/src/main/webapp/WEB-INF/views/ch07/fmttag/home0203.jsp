<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>3) type 속성을 지정되지 않으면 기본값은 number</h4>
	<p>coin : ${coin}</p>
	<fmt:parseNumber value="${coin}" pattern="0,000.00" var="coinNum"></fmt:parseNumber>
	<p>coinNum : ${coinNum}</p>
</body>
</html>