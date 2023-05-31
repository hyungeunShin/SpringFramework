<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>7) type 속성을 both로 지정해야 시간 포맷팅을 한다</h4>
	<p>now : ${now}</p>
	<p>date default : <fmt:formatDate value="${now}" type="both" timeStyle="default" dateStyle="default" /></p>
	<p>date short : <fmt:formatDate value="${now}" type="both" timeStyle="short" dateStyle="short" /></p>
	<p>date medium : <fmt:formatDate value="${now}" type="both" timeStyle="medium" dateStyle="medium" /></p>
	<p>date long : <fmt:formatDate value="${now}" type="both" timeStyle="long" dateStyle="long" /></p>
	<p>date full : <fmt:formatDate value="${now}" type="both" timeStyle="full" dateStyle="full" /></p>
</body>
</html>