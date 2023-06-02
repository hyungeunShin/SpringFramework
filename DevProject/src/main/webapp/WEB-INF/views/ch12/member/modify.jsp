<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Modify</h2>
	<form action="/crud/member/modify" method="post" id="member">
		<input type="hidden" id="userNo" name="userNo" value="${member.userNo}">
		<table border="1">
			<tr>
				<td>userId</td>
				<td>
					<input type="text" name="userId" value="${member.userId}" id="userId" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>userName</td>
				<td>
					<input type="text" name="userName" value="${member.userName}" id="userName">
				</td>
			</tr>
			<tr>
				<td>auth-1</td>
				<td>
					<select name="authList[0].auth">
						<option value="">===선택===</option>
						<option value="ROLE_USER" <c:if test="${member.authList[0].auth eq 'ROLE_USER'}">selected</c:if>>사용자</option>
						<option value="ROLE_MEMBER" <c:if test="${member.authList[0].auth eq 'ROLE_MEMBER'}">selected</c:if>>회원</option>
						<option value="ROLE_ADMIN" <c:if test="${member.authList[0].auth eq 'ROLE_ADMIN'}">selected</c:if>>관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>auth-2</td>
				<td>
					<select name="authList[1].auth">
						<option value="">===선택===</option>
						<option value="ROLE_USER" <c:if test="${member.authList[1].auth eq 'ROLE_USER'}">selected</c:if>>사용자</option>
						<option value="ROLE_MEMBER" <c:if test="${member.authList[1].auth eq 'ROLE_MEMBER'}">selected</c:if>>회원</option>
						<option value="ROLE_ADMIN" <c:if test="${member.authList[1].auth eq 'ROLE_ADMIN'}">selected</c:if>>관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>auth-3</td>
				<td>
					<select name="authList[2].auth">
						<option value="">===선택===</option>
						<option value="ROLE_USER" <c:if test="${member.authList[2].auth eq 'ROLE_USER'}">selected</c:if>>사용자</option>
						<option value="ROLE_MEMBER" <c:if test="${member.authList[2].auth eq 'ROLE_MEMBER'}">selected</c:if>>회원</option>
						<option value="ROLE_ADMIN" <c:if test="${member.authList[2].auth eq 'ROLE_ADMIN'}">selected</c:if>>관리자</option>
					</select>
				</td>
			</tr>
		</table>
	</form>
	<button type="button" id="btnModify">수정</button>
	<button type="button" id="btnCancel">취소</button>
</body>
<script type="text/javascript">
let form = document.querySelector('#member');
let btnModify = document.querySelector('#btnModify');
let btnCancel = document.querySelector('#btnCancel');

btnModify.addEventListener("click", function() {
	let userName = document.querySelector('#userName').value;
	
	if(userName == null || userName == "") {
		alert("이름을 입력해주세요");
		return false;
	}
	
	form.submit();
});

btnCancel.addEventListener("click", function() {
	let userNo = document.querySelector('#userNo').value;
	location.href = "/crud/member/read?userNo=" + userNo;
});
</script>
</html>