<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>3) type속성이 currency</h4>
	<p>coin : ${coin}</p>
	<fmt:parseNumber value="${coin }" type="currency" var="aaa"/>
	<p>aaa : ${aaa}</p>
</body>
</html>