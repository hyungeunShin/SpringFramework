<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Spring form</h4>
	<p>form:form태그를 이용한 폼 생성</p>
	<form:form method="post" action="/formtag/register" modelAttribute="member">
		<table border='1'>
			<tr>
				<td>유저 ID</td>
				<td>
					<form:input path="userId" />
					<font color="red">
						<form:errors path="userId" />
					</font>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName" />
					<font color="red">
						<form:errors path="userName" />
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>