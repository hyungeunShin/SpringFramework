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
	<form method="post" action="/crud/member/remove" id="delForm">
		<input type="hidden" name="userNo" value="${member.userNo}">
	</form>
	<button type="button" id="btnModify">수정</button>
	<button type="button" id="btnRemove">삭제</button>
	<button type="button" id="btnList">목록</button>
</body>
<script type="text/javascript">
let form = document.querySelector('#delForm');
let btnModify = document.querySelector('#btnModify');
let btnRemove = document.querySelector('#btnRemove');
let btnList = document.querySelector('#btnList');

btnModify.addEventListener("click", function() {
	form.action = "/crud/member/modify";
	form.method = "get";
	form.submit();
});

btnRemove.addEventListener("click", function() {
	if(confirm("정말로 삭제하시겠습니까?")) {
		form.submit();
	} 
});

btnList.addEventListener("click", function() {
	location.href = "/crud/member/list";
});
</script>
</html>