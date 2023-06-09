<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="content-header">
	<c:set value="등록" var="name" />
	<c:if test="${status eq 'u'}">
		<c:set value="수정" var="name" />
	</c:if>
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>공지사항 ${name}</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">DDIT HOME</a></li>
					<li class="breadcrumb-item active">공지사항 ${name}</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="card card-primary">
				<form action="/notice/insert" method="post" id="noticeForm" enctype="multipart/form-data">
					<c:if test="${status eq 'u'}">
						<input type="hidden" name="boNo" id="boNo" value="${notice.boNo}">
					</c:if>
					<div class="card-header">
						<h3 class="card-title">공지사항 ${name}</h3>
						<div class="card-tools"></div>
					</div>
					<div class="card-body">
						<div class="form-group">
							<label for="boTitle">제목을 입력해주세요</label>
							<input type="text" id="boTitle" name="boTitle" class="form-control" placeholder="제목을 입력해주세요" value="${notice.boTitle}">
						</div>
						<div class="form-group">
							<label for="boContent">내용을 입력해주세요</label>
							<textarea id="boContent" name="boContent" class="form-control" rows="14">${notice.boContent}</textarea>
						</div>
						<div class="form-group">
							<div class="custom-file">
								<label for="inputDescription">파일 선택</label>
								<input type="file" class="custom-file-input" id="boFile" name="boFile" multiple="multiple">
								<label class="custom-file-label" for="boFile">파일을 선택해주세요</label>
							</div>
						</div>
					</div>
					<c:if test="${status eq 'u'}">
						<div class="card-footer bg-white">
							<ul class="mailbox-attachments d-flex align-items-stretch clearfix">
								<c:if test="${not empty notice.noticeFileList}">
									<c:forEach items="${notice.noticeFileList}" var="file">
										<li>
											<span class="mailbox-attachment-icon">
												<i class="far fa-file-pdf"></i>
											</span>
				
											<div class="mailbox-attachment-info">
												<a href="#" class="mailbox-attachment-name">
													<i class="fas fa-paperclip"></i> ${file.fileName}
												</a>
												<span class="mailbox-attachment-size clearfix mt-1">
													<span>${file.fileFancysize}</span>
													<span class="btn btn-default btn-sm float-right attachmentFileDel" data-fileno="${file.fileNo}">
														<i class="fas fa-times"></i>
													</span>
												</span>
											</div>
										</li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
					</c:if>
					<div class="card-footer bg-white">
						<div class="row">
							<div class="col-12">
								<input type="button" id="insertBtn" value="${name}" class="btn btn-primary float-right">
								<c:if test="${status eq 'u'}">
									<input type="button" id="cancelBtn" value="취소" class="btn btn-success float-right">
								</c:if>
								<c:if test="${status ne 'u'}">
									<input type="button" id="listBtn" value="목록" class="btn btn-success float-right">
								</c:if>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script>
CKEDITOR.replace("boContent", {
	footnotesPrefix: "a",
	filebrowserUploadUrl: '/imageUpload'
});

let listBtn = $("#listBtn");
let insertBtn = $("#insertBtn");
let cancelBtn = $("#cancelBtn");
let form = $("#noticeForm");

listBtn.on("click", function() {
	location.href = "notice/list";
});

insertBtn.on("click", function() {
	let title = $("#boTitle").val();
	let content = CKEDITOR.instances.boContent.getData();
	
	if(title == null || title == "") {
		alert("제목을 입력해주세요");
		return false;
	}
	
	if(content == null || content == "") {
		alert("내용을 입력해주세요");
		return false;
	}
	
	if($(this).val() == "수정") {
		form.attr("action", "/notice/update");
	}
	
	form.submit();
});

cancelBtn.on("click", function() {
	let boNo = $("#boNo").val();
	location.href = "/notice/detail?boNo=" + boNo;
});

$(".attachmentFileDel").on("click", function() {
	let no = $(this).data("fileno");
	
	let str = "<input type='hidden' name='delNoticeNo' value='%V'>";
	form.append(str.replace("%V", no));
	$(this).parents("li:first").hide();
});
</script>