<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Register</h2>
	<form action="/crud/member/register" method="post" id="member">
		<table>
			<tr>
				<td>userId</td>
				<td>
					<input type="text" id="userId" name="userId">
				</td>
			</tr>
			<tr>
				<td>userPw</td>
				<td>
					<input type="text" id="userPw" name="userPw">
				</td>
			</tr>
			<tr>
				<td>userName</td>
				<td>
					<input type="text" id="userName" name="userName">
				</td>
			</tr>
		</table>
		<input type="button" id="btnRegister" value="등록">
		<input type="button" id="btnList" value="목록">
	</form>
</body>
<script type="text/javascript">
let member = document.querySelector('#member');
let btnRegister = document.querySelector('#btnRegister');
let btnList = document.querySelector('#btnList');

btnRegister.addEventListener('click', function() {
	let userId = document.querySelector('#userId').value;
	let userPw = document.querySelector('#userPw').value;
	let userName = document.querySelector('#userName').value;
	
	if(userId == null || userId == "") {
		alert("아이디를 입력해주세요.")
		return false;
	}
	if(userPw == null || userPw == "") {
		alert("비밀번호를 입력해주세요.")
		return false;
	}
	if(userName == null || userName == "") {
		alert("이름을 입력해주세요.")
		return false;
	}
	
	member.submit();
});

btnList.addEventListener('click', function() {
	location.href = "/crud/member/list";
})
</script>
</html>