<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxHome</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<style type="text/css">
label {
	display: inline-block;
	width : 100px;
}
</style>
</head>
<body>
	<h3>ajaxHome</h3>
	
	<form action="" method="get">
		<label>boardNo : </label><input type="text" name="boardNo" id="boardNo"><br/>
		<label>title : </label><input type="text" name="title" id="title"><br/>
		<label>content : </label><input type="text" name="content" id="content"><br/>
		<label>writer : </label><input type="text" name="writer" id="writer"><br/>
		<input type="button" id="btn" value="전송">
	</form>
	
	<div>
		<button id="putBtn">Modify(Put)</button>
		<button id="putHeaderBtn">Modify(Put with Header)</button>
		
		<h3>Content Type 매핑</h3>
		<button id="postBtn">Modify(Post)</button>
		<button id="putJsonBtn">Modify(Put Json)</button>
		<button id="putXMLBtn">Modify(Put XML)</button>
		
		<h3>Accept 매핑</h3>
		<button id="readBtn">Read(Json)</button>
		<button id="getJsonBtn">Read(Json)</button>
		<button id="getXmlBtn">Read(Xml)</button>
	</div>
</body>
<script type="text/javascript">
//'==' 는 Equal Operator
//'==='는 Strict Equal Operator 데이터 타입 까지 비교
let putBtn = $('#putBtn');
let putHeaderBtn = $('#putHeaderBtn');
let postBtn = $('#postBtn');
let putJsonBtn = $('#putJsonBtn');
let putXMLBtn = $('#putXMLBtn');
let readBtn = $('#readBtn');
let getJsonBtn = $('#getJsonBtn');
let getXmlBtn = $('#getXmlBtn');


putBtn.on('click', function() {
	let boardNo = $('#boardNo').val();
	let title = $('#title').val();
	let content = $('#content').val();
	let writer = $('#writer').val();
	
	let boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
	};
	
	/* contentType은 보내는 데이터의 타입이다.
	application/json; charset-utf-8이 흔히 쓰인다.
	디폴트는 application/x-www-form-urlencoded; charset=utf-8 이다. */
	
	$.ajax({
		url: "/board/" + boardNo,
		type: 'put',
		data: JSON.stringify(boardObject),
		contentType: "application/json; charset=utf-8",
		success: function(res) {
			console.log(res);
			if(res == "SUCCESS") {
				alert(res);
			}
		},
		error: function(xhr) {
			console.log(xhr.status);
		}
	});
});

putHeaderBtn.on('click', function() {
	let boardNo = $('#boardNo').val();
	let title = $('#title').val();
	let content = $('#content').val();
	let writer = $('#writer').val();
	
	let boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
	};
	
	$.ajax({
		url: "/board/" + boardNo,
		type: "put",
		headers: {
			//"X-HTTP-Method-Override" : "PUT"
			"aaa" : "aaa"
		},
		data: JSON.stringify(boardObject),
		contentType: "application/json; charset=utf-8",
		success: function(res) {
			console.log(res);
			if(res == "SUCCESS") {
				alert(res);
			}
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	})
});

postBtn.on('click', function() {
	let boardNo = $('#boardNo').val();
	let title = $('#title').val();
	let content = $('#content').val();
	let writer = $('#writer').val();
	
	let boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
	};
	
	$.ajax({
		url: "/board/" + boardNo,
		type: "post",
		data: JSON.stringify(boardObject),
		contentType: "application/json; charset=utf-8",
		success: function(res) {
			console.log(res);
			if(res === "SUCCESS") {
				alert(res);
			}
		},
		error: function(xhr) {
			console.log(xhr.status);
		}
	})
});

putJsonBtn.on('click', function() {
	let boardNo = $('#boardNo').val();
	let title = $('#title').val();
	let content = $('#content').val();
	let writer = $('#writer').val();
	
	let boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
	};
	
	console.log(boardNo, title, content, writer);
	
	$.ajax({
		url: "/board/" + boardNo,
		type: "put",
		data: JSON.stringify(boardObject),
		/* data: {
			"boardNo" : boardNo,
			"title" : title,
			"content" : content,
			"writer" : writer
		}, */
		contentType: "application/json; charset=utf-8",
		success: function(res) {
			console.log(res);
			if(res === 'SUCCESS') {
				alert(res);
			}
		}
	})
});

putXMLBtn.on('click', function() {
	let boardNo = $('#boardNo').val();
	let title = $('#title').val();
	let content = $('#content').val();
	let writer = $('#writer').val();
	
	let xmlData = "";
	xmlData += "<Board>";
	xmlData += "<boardNo>" + boardNo + "</boardNo>";
	xmlData += "<title>" + title + "</title>";
	xmlData += "<content>" + content + "</content>";
	xmlData += "<writer>" + writer + "</writer>";
	xmlData += "</Board>";
	
	$.ajax({
		url : "/board/" + boardNo,
		type: "put",
		data: xmlData,
		contentType: "application/xml; charset=utf-8",
		success: function(res) {
			console.log(res);
			if(res === 'SUCCESS') {
				alert(res);
			}
		}
	})
});

readBtn.on('click', function() {
	let boardNo = $('#boardNo').val();
	
	//get방식 비동기 http 요청 수행
	$.get("/board/"+boardNo, function(data) {
		console.log(data);
		alert(JSON.stringify(data));
	});
});

getJsonBtn.on('click', function() {
	let boardNo = $('#boardNo').val();
		
	$.ajax({
		type: "get",
		url: "/board/" + boardNo,
		headers : {
			"Accept" : "application/json"
		},
		success : function(res) {
			console.log(res);
			alert(JSON.stringify(res));
		}
	})
});

getXmlBtn.on('click', function() {
	let boardNo = $('#boardNo').val();
	
	$.ajax({
		type: "get",
		url: "/board/" + boardNo,
		headers : {
			"Accept" : "application/xml"
		},
		success : function(res) {
			console.log(res);
			alert(xmlToString(res));
		}
	})
});

function xmlToString(data) {
	let str;
	console.log(data);
	//window.ActiveXObject는 ActiveObject 를 지원하는 브라우저면
	// Object를 리턴하고 그렇지 않다면 null을 리턴한다.
	if(window.ActiveXObject) {
		str = data.xml;
	} else {
		str = (new XMLSerializer()).serializeToString(data);
	}
	
	return str;
}
</script>
</html>