<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="login-box">
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<p class="h4">
				<b>아이디찾기</b>
			</p>
		</div>
		<div class="card-body">
			<p class="login-box-msg">아이디 찾기는 이메일, 이름을 입력하여 찾을 수 있습니다.</p>
			<form action="" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" name="memEmail" id="memEmail" placeholder="이메일을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" name="memName" id="memName" placeholder="이름을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<p id="pid">
						회원님의 아이디는 [<font id="id" color="red" class="" style="font-size: 2.5rem;"></font>] 입니다.
					</p>
				</div>
				<div class="row">
					<div class="col-12">
						<button type="button" class="btn btn-primary btn-block" id="idFindBtn">아이디찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br />
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<p href="" class="h4">
				<b>비밀번호찾기</b>
			</p>
		</div>
		<div class="card-body">
			<p class="login-box-msg">비밀번호 찾기는 아이디, 이메일, 이름을 입력하여 찾을 수 있습니다.</p>
			<form action="" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memId2" name="memId" placeholder="아이디를 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memEmail2" name="memEmail" placeholder="이메일을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memName2" name="memName" placeholder="이름을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<p id="ppw">
						회원님의 비밀번호는 [<font color="red" class="" id="password" style="font-size: 2.5rem;"></font>] 입니다.
					</p>
				</div>
				<div class="row">
					<div class="col-12">
						<button type="button" class="btn btn-primary btn-block" id="pwFindBtn">비밀번호찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br/>
	<div class="card card-outline card-secondary">
		<div class="card-header text-center">
			<h4>MAIN MENU</h4>
			<button type="button" class="btn btn-secondary btn-block" id="loginBtn" onclick="javascript:location.href='/notice/login'">로그인</button>
		</div>
	</div>
</div>

<script type="text/javascript">
let idFindBtn = $("#idFindBtn");
let pwFindBtn = $("#pwFindBtn");

idFindBtn.on("click", function() {
	let email = $("#memEmail").val();
	let name = $("#memName").val();
	
	if(email == null || email == "") {
		alert("이메일을 입력해주세요");
		return false;
	}
	
	if(name == null || name == "") {
		alert("이름을 입력해주세요");
		return false;
	}
	
	let data = {
		memEmail : email,
		memName : name
	};
	
	$.ajax({
		type: "post",
		url: "/notice/idForget",
		contentType : "application/json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
		},
		data: JSON.stringify(data),
		//data: data,
		success: function(res) {
			$("#id").html(res);
		}
	});
});

pwFindBtn.on("click", function() {
	let email = $("#memEmail2").val();
	let name = $("#memName2").val();
	let id = $("#memId2").val();
	
	if(id == null || id == "") {
		alert("아이디를 입력해주세요");
		return false;
	}
	
	if(email == null || email == "") {
		alert("이메일을 입력해주세요");
		return false;
	}
	
	if(name == null || name == "") {
		alert("이름을 입력해주세요");
		return false;
	}
	
	let data = {
		memId: id,
		memName: name,
		memEmail: email
	}
	
	$.ajax({
		type: "post",
		url: "/notice/pwForget",
		contentType: "application/json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
		},
		data: JSON.stringify(data),
		success: function(res) {
			$("#password").html(res);	
		}
	});
});
</script>