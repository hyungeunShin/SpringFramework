<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content">
<div class="container-fluid">
<div class="row" style="justify-content: center;">
<div class="col-md-6">
<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">Quick Example</h3>
	</div>

	<form action="/notice/generalFormPost" id="frm" method="post" enctype="multipart/form-data">
		<div class="card-body">
			<div class="form-group">
				<label for="boTitle">제목</label>
				<input type="text" class="form-control" id="boTitle" name="boTitle" placeholder="제목을 입력해주세요">
			</div>
			<div class="form-group">
				<label for="boContent">내용</label>
				<textarea rows="3" cols="5" name="boContent" id="boContent"></textarea>
			</div>
			<div class="form-group">
				<label for="boWriter">작성자</label>
				<input type="text" class="form-control" id="boWriter" name="boWriter" placeholder="작성자를 입력해주세요">
			</div>
			<div class="form-group">
				<label for="boFile">File input</label>
				<div class="input-group">
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="boFile" name="boFile" multiple="multiple">
						<label class="custom-file-label" for="boFile">Choose file</label>
					</div>
				</div>
			</div>
		</div>

		<div class="card-footer">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</form>
</div>
</div>
</div>
</div>
</section>
<script type="text/javascript">
CKEDITOR.replace("boContent");
</script>
