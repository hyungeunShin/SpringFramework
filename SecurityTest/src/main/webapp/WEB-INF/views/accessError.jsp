<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Access Denied</h3>
	
	<h2>
		<!-- 
			SPRING_SECURITY_403_EXCEPTION.message는 접근이 거부 됬을 떄 'Access is denied'문자열이 출력된다
			security-context.xml에서 security:access-denied-handler 태그 자체로 설정했을 때 메세지가 출력
		 -->
		<c:out value="${SPRING_SECURITY_403_EXCEPTION.message}"></c:out>
	</h2>
	<h2>
		<c:out value="${msg}"></c:out>
	</h2>
</body>
</html>