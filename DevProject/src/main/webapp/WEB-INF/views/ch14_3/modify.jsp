<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h2>Modify</h2>
	<form action="/item3/modify" method="post" enctype="multipart/form-data" id="item3">
		<input type="hidden" name="itemId" value="${item.itemId}">
		<table>
			<tr>
				<td>상품명</td>
				<td>
					<input type="text" name="itemName" id="itemName" value="${item.itemName}">
				</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>
					<input type="text" name="price" id="price" value="${item.price}">
				</td>
			</tr>
			<tr>
				<td>사진</td>
				<td>
					<input type="file" id="inputFile">
					<div class="uploadedList"></div>
				</td>
			</tr>
			<tr>
				<td>개요</td>
				<td>
					<textarea rows="10" cols="30" name="description">${item.description}</textarea>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit" id="btnModify">수정</button>
			<button type="submit" id="btnList" onclick="javascript:location.href='/item3/list'">목록</button>
		</div>
	</form>
</body>
<script type="text/javascript">
let inputFile = $('#inputFile');

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

$(".uploadedList").on("click", "span", function() {
	$(this).parent("div").remove();
});

$("#item3").submit(function(event) {
	event.preventDefault();
	
	let that = $(this);
	let str = "";
	
	$(".uploadedList a").each(function(i) {
		let value = $(this).attr("href");
		value = value.substr(28); // ?fileName= 다음에 나오는 값
		
		str += "<input type='hidden' name='files[" + i + "]' value='" + value + "'>";
	});
	
	console.log("str : " + str);
	
	that.append(str);
	that.get(0).submit();  //form의 첫번째를 가져와서 submit() 처리
});

inputFile.on('change', function(event) {
	var files = event.target.files;
	var file = files[0];
	
	let formData = new FormData();
	formData.append("file", file);
	
	$.ajax({
		type: "post",
		url: "/item3/uploadAjax",
		data: formData,
		dataType: "text",
		processData: false,
		contentType: false,
		success: function(data) {
			console.log(data);
			
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
			
			$(".uploadedList").append(str); //추가된 파일(이미지, 파일)들을 div에 추가한다
		}
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