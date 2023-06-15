<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Board Register : access to member</h2>
	
	<p>principal : <sec:authentication property="principal"/></p>
	<p>member : <sec:authentication property="principal.member"/></p>
	<p>사용자 이름 : <sec:authentication property="principal.member.userName"/></p>
	<p>사용자 아이디 : <sec:authentication property="principal.member.userId"/></p>
	<p>사용자 pw : <sec:authentication property="principal.member.userPw"/></p>
	<p>사용자 권한 : <sec:authentication property="principal.member.authList"/></p>
	<hr><br>
	
	<sec:authorize access="hasRole('ROLE_MEMBER')">
		열할명 : 일반 회원
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		열할명 : 관리자
	</sec:authorize>
</body>
</html>