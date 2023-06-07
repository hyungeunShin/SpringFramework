<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h2>Remove</h2>
	<form action="/item3/remove" method="post" enctype="multipart/form-data">
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
					<div class="uploadedList"></div>
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
			<button type="submit" id="btnList" onclick="javascript:location.href='/item3/list'">목록</button>
		</div>
	</form>
</body>
<script type="text/javascript">
let itemId = ${item.itemId};
console.log(itemId);

$.getJSON("/item3/getAttach/" + itemId, function(list) {
	$(list).each(function() {
		console.log(this);
		
		var data = this;
		
		let str = "";
		if(checkImageType(data)) {
			str += "<div>";
			str += "<a href='/item3/displayFile?fileName=" + data + "'>";
			//str += "<img src='/item3/displayFile?fileName=" + getThumbnailName(data) + "'>";
			str += "<img src='${pageContext.request.contextPath}/resources/upload" + getThumbnailName(data) + "'>";
			str += "</a>";
			str += "<span>X</span>";
			str += "</div>";
		} else {
			str += "<div>";
			str += "<a href='/item3/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a>";
			str += "<span>X</span>";
			str += "</div>";
		}
		
		$(".uploadedList").append(str);
	});
});

function getThumbnailName(fileName) {
	let front = fileName.substr(0, 12); // /2023/06/07 폴더
	let end = fileName.substr(12);
	
	console.log("front : " + front);
	console.log("end : " + end);
	
	return front + "s_" + end;
};

function getOriginalName(fileName) {
	if(checkImageType(fileName)) {
		return;
	}
	
	let idx = fileName.indexOf("_") + 1;
	
	return fileName.substr(idx);
};

//이미지 확인
function checkImageType(fileName) {
	let pattern = /jpg|gif|png|jpeg/;
	return fileName.match(pattern); //패턴과 일치하면 true/ 아니면 false
};
</script>
</html>