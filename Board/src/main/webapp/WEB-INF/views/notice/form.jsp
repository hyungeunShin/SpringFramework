<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>공지게시판 등록</title>
</head>
<body>
	<c:set value="등록" var="name"></c:set>
	<c:if test="${status eq 'u'}">
		<c:set value="수정" var="name"></c:set>
	</c:if>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">공지게시판 ${name}</h3>
		</div>
	</div>
	<div class="container">
		<form name="newWrite" action="/notice/insert.do" class="form-horizontal" method="post" id="noticeForm">
			<c:if test="${status eq 'u'}">
				<input name="boNo" type="hidden" class="form-control" value="${notice.boNo}">
			</c:if>
			<div class="form-group row">
				<label class="col-sm-2 control-label">제목</label>
				<div class="col-sm-5">
					<input name="boTitle" type="text" class="form-control" placeholder="제목" id="boTitle" value="${notice.boTitle}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">내용</label>
				<div class="col-sm-8">
					<textarea name="boContent" cols="50" rows="5" class="form-control" placeholder="내용" id="boContent">${notice.boContent}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="button" class="btn btn-primary" value="${name}" id="formBtn">
					
					<c:if test="${status eq 'u'}">
						<a href="/notice/detail.do?boNo=${notice.boNo}">
							<input type="button" class="btn btn-primary" value="취소 ">
						</a>
					</c:if>
					<c:if test="${status ne 'u'}">
						<a href="/notice/list.do">
							<input type="button" class="btn btn-primary" value="목록 ">
						</a>
					</c:if>
				</div>
			</div>
		</form>
		<c:if test="${not empty errors}">
			<p>
				${errors.boTitile}<br/>
				${errors.boContent}<br/>
				${errors.msg}
			</p>
		</c:if>
		<hr>
	</div>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
</body>
<script type="text/javascript">
$(function() {
	CKEDITOR.replace("boContent");
	CKEDITOR.config.allowedContent = true; 
	
	let btn = $('#formBtn');
	btn.on('click', function() {
		let title = $('#boTitle').val();
		let content = CKEDITOR.instances.boContent.getData();
		
		var ext = content.replace(/(<([^>]+)>)/gi, '');
		
		console.log(ext);
		
		if(title.trim() == "" || title.trim() == null) {
			alert("제목을 입력해주세요");
			$('#boTitle').focus();
			return false;
		}
		
		if(ext.trim() == "" || ext.trim() == null) {
			alert("내용을 입력해주세요");
			$('#boContent').focus();
			return false;
		}
		
		if(ext.trim().length > 4000) {
			alert("최대 4000자 입니다.");
			$('#boContent').focus();
			return false;
		}
		
		if($(this).val() == "수정") {
			$('#noticeForm').attr("action", "/notice/update.do");
		}
		
		$('#noticeForm').submit();
	});
})
</script>
</html>



