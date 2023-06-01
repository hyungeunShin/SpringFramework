<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Read</h2>
	<table>
		<tr>
			<td>userId</td>
			<td>${member.userId}</td>
		</tr>	
		<tr>
			<td>userName</td>
			<td>${member.userName}</td>
		</tr>
		<c:if test="${not empty member.authList}">
			<c:forEach items="${member.authList}" var="auth">
				<tr>
					<td>역할</td>
					<td>${auth.auth}</td>
				</tr>
			</c:forEach>	
		</c:if>
	</table>
	<a href="/crud/member/modify?userNo=${member.userNo}">수정</a>
	<button type="button" id="btnRemove">삭제</button>
	<button type="button" id="btnList">목록</button>
</body>
</html>