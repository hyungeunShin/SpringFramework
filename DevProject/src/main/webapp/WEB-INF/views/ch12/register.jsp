<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h2>Register</h2>
	<form:form modelAttribute="board" method="post" action="/crud/board/register">
		<c:if test="${status eq 'u'}">
			<input type="hidden" name="boardNo" value="${board.boardNo}">
		</c:if>
		<table>
			<tr>
				<td>제목</td>
				<td>
					<!-- <input type="text" id="title" name="title" value=""> -->
					<form:input path="title"/> 
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<!-- <input type="text" id="writer" name="writer" value=""> -->
					<form:input path="writer"/>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<!-- <textarea rows="10" cols="30" name="content" id="content"></textarea> -->
					<form:textarea path="content" rows="10" cols="30" /> 
				</td>
			</tr>
		</table>
		<div>
			<c:set value="등록" var="text"></c:set>
			<c:if test="${status eq 'u'}">
				<c:set value="수정" var="text"></c:set>
			</c:if>
			<input type="button" id="btnRegister" value="${text}">
			<input type="button" id="btnList" value="목록">
		</div>
	</form:form>
</body>
<script type="text/javascript">
let board = $("#board");
let btnRegister = $("#btnRegister");
let btnList = $("#btnList");

/* form = document.querySelector('#board'); */

btnRegister.on("click", function() {
	let title = $("#title").val();
	let content = $("#content").val();
	let writer = $("#writer").val();
	
	if(title == null || title == "") {
		alert("제목을 입력해주세요.");
		return false;
	}
	if(content == null || content == "") {
		alert("내용을 입력해주세요.");
		return false;
	}
	if(writer == null || writer == "") {
		alert("작성자를 입력해주세요.");
		return false;
	}
	
	if($(this).val() == "수정") {
		board.attr("action", "/crud/board/modify");
		/* form.action = "/crud/board/modify"; */
	}
	
	board.submit();
});

btnList.on("click", function() {
	location.href = "/crud/board/list";
});
</script>
</html>