<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>자유게시판 상세보기</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">자유게시판 상세보기 /수정 /삭제</h3>
		</div>
	</div>

	<div class="container">
		<!-- <form name="newUpdate" action="" class="form-horizontal" method="post"> -->
		<input type="hidden" value="${free.boNo}" id="no">
		<div class="form-group row">
			<label class="col-sm-2 control-label" >제목</label>
			<div class="col-sm-5">
				<h3 class="card-title">${free.boTitle}</h3>
			</div>
		</div>
		<div class="card-tools">${free.boWriter} ${free.boDate} ${free.boHit}</div>
		<div class="form-group row">
			<label class="col-sm-2 control-label">내용</label>
			<div class="col-sm-8" style="word-break: break-all;">
				<div class="card-body">${free.boContent}</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-offset-2 col-sm-10 ">
				<p>
					<button type="button" class="btn btn-primary" id="listBtn">목록</button>
					<button type="button" class="btn btn-success" id="upBtn">수정</button>
					<button type="button" class="btn btn-danger" id="delBtn">삭제</button>
				</p>
				<form action="/free/delete.do" id="frm" method="post">
					<input type="hidden" value="${free.boNo}" name="boNo">
				</form>
			</div>
		</div>
		<!-- </form> -->
		<hr>
	</div>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
</body>
<script type="text/javascript">
let listBtn = $('#listBtn');
let upBtn = $('#upBtn');
let delBtn = $('#delBtn');
let boNo = $('#no').val();

listBtn.on('click', function() {
	location.href = "/free/list.do";
})

upBtn.on('click', function() {
	location.href = "/free/update.do?boNo=" + boNo;
})

delBtn.on('click', function() {
	if(confirm("삭제하시겠습니까?")) {
		$('#frm').submit();
	} else {
		return false;
	}
})
</script>
</html>


