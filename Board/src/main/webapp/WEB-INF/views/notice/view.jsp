<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<title>공지게시판 상세보기</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">공지게시판 상세보기/수정 /삭제</h3>
		</div>
	</div>

	<div class="container">
		<!-- <form name="newUpdate" action="" class="form-horizontal" method="post"> -->
		<input type="hidden" value="${notice.boNo}" id="bono">
		<div class="form-group row">
			<label class="col-sm-2 control-label">제목</label>
			<div class="col-sm-5">
				<h3 class="card-title">${notice.boTitle}</h3>
			</div>
		</div>
		<div class="card-tools">${notice.boWriter} ${notice.boDate} ${notice.boHit}</div>
		<div class="form-group row">
			<label class="col-sm-2 control-label">내용</label>
			<div class="col-sm-8" style="word-break: break-all;">
				<div class="card-body">${notice.boContent}</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-offset-2 col-sm-10">
				<p>
					<button type="button" class="btn btn-primary" id="listBtn">목록</button>
					<button type="button" class="btn btn-success" id="updateBtn">수정</button>
					<button type="button" class="btn btn-danger" id="deleteBtn">삭제</button>
				</p>
				<form action="/notice/delete.do" method="post" id="frm">
					<input type="hidden" value="${notice.boNo}" name="boNo"> 
				</form> 
			</div>
		</div>
		<!-- </form> -->
		<hr>
	</div>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
</body>
<script type="text/javascript">
let delBtn = $('#deleteBtn');
let updateBtn = $('#updateBtn');
let listBtn = $('#listBtn');
let boNo = $('#bono').val();

delBtn.on('click', function() {
	if(confirm("삭제하시겠습니까?")) {
		$('#frm').submit();
	} else {
		return false;
	}
})

updateBtn.on('click', function() {
	console.log(boNo);
	location.href = "/notice/update.do?boNo=" + boNo;
})

listBtn.on('click', function() {
	location.href = "/notice/list.do";
})
</script>
</html>


