<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FormHome</title>
</head>
<body>
	<form action="/board/register">
		<input type="submit" value="register(GET)">
	</form>
	<form action="/board/register" method="post">
		<input type="submit" value="register(post)">
	</form>
	<form action="/board/modify">
		<input type="submit" value="modify(get)">
	</form>
	<form action="/board/modify" method="post">
		<input type="submit" value="modify(post)">
	</form>
	<form action="/board/remove" method="post">
		<input type="submit" value="remove(post)">
	</form>
	<form action="/board/list">
		<input type="submit" value="list(get)">
	</form>
</body>
</html>