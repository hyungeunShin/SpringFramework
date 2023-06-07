<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Remove</h2>
	<form action="/item2/remove" method="post" enctype="multipart/form-data">
		<input type="hidden" name="itemId" value="${item.itemId}">
		<table>
			<tr>
				<td>상품명</td>
				<td>
					<input type="text" name="itemName" id="itemName" value="${item.itemName}" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>
					<input type="text" name="price" id="price" value="${item.price}" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td>사진</td>
				<td>
					<img src="/item2/display?itemId=${item.itemId}" width="210" height="240">
					<img src="${pageContext.request.contextPath}/resources/upload/${item.pictureUrl}" width="210" height="240">
				</td>
			</tr>
			<tr>
				<td>사진</td>
				<td>
					<img src="/item2/display2?itemId=${item.itemId}" width="210" height="240">
					<img src="${pageContext.request.contextPath}/resources/upload/${item.pictureUrl2}" width="210" height="240">
				</td>
			</tr>
			<tr>
				<td>개요</td>
				<td>
					<textarea rows="10" cols="30" name="description" disabled="disabled">${item.description}</textarea>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit" id="btnRemove">삭제</button>
			<button type="submit" id="btnList" onclick="javascript:location.href='/item/list'">목록</button>
		</div>
	</form>
</body>
</html>