<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/resources/img/apple-icon.png">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/favicon.png">
<title>대덕인재개발원 CRUD 연습</title>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
<link href="${pageContext.request.contextPath}/resources/css/nucleo-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/nucleo-svg.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
<link id="pagestyle" href="${pageContext.request.contextPath}/resources/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body class="">
	<main class="main-content  mt-0">
		<section>
			<div class="page-header min-vh-100">
				<div class="container">
					<div class="row">
						<div
							class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
							<div
								class="position-relative bg-gradient-info h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center"
								style="background-image: url('${pageContext.request.contextPath}/resources/img/illustrations/illustration-lock.jpg'); background-size: cover;">
							</div>
						</div>
						<div
							class="col-xl-4 col-lg-5 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5">
							<div class="card card-plain">
								<div class="card-header">
									<h4 class="font-weight-bolder">회원가입</h4>
									<p class="mb-0">회원등록 후, 저희 서비스와 함께해요!</p>
									<c:if test="${not empty error}">
										<span style="font-weight: bold; color: red;">${error.error}</span>
									</c:if>
								</div>
								<div class="card-body">
									<form role="form" action="/member/signup" method="post">
										<div class="input-group input-group-outline mb-3">
											<input type="text" class="form-control" name="memId" id="memId" placeholder="아이디" value="${member.memId}">
											<!-- <button type="button" id="idcheck" class="text-primary text-gradient font-weight-bold" style="margin-left: 5px; border: none;">중복확인</button> -->
										</div>
										<div class="input-group input-group-outline mb-3">
											<input type="text" class="form-control" name="memPw" id="memPw" placeholder="비밀번호" value="${member.memPw}">
										</div>
										<div class="input-group input-group-outline mb-3">
											<input type="text" class="form-control" id="pwcheck" placeholder="비밀번호 확인">
										</div>
										<div class="input-group input-group-outline mb-3">
											<input type="text" class="form-control" name="memName" id="memName" placeholder="이름" value="${member.memName}">
										</div>
										<div class="form-check mb-3">
											<input class="form-check-input" type="radio" name="memGender" id="customRadio1" value="남자" checked>
											<label class="custom-control-label" for="customRadio1">남자</label>
											<input class="form-check-input" type="radio" name="memGender" id="customRadio1" value="여자">
											<label class="custom-control-label" for="customRadio1">여자</label>
										</div>
										<div class="input-group input-group-outline mb-3">
											<input type="text" class="form-control" name="memPhone" id="memPhone" placeholder="전화번호" value="${member.memPhone}">
										</div>
										<div class="input-group input-group-outline mb-3">
											<input type="text" class="form-control" name="memEmail" id="memEmail" placeholder="이메일" value="${member.memEmail}">
										</div>
										<div class="form-check form-switch">
											<input class="form-check-input" type="checkbox"	id="agreecheck" name="memAgree" value="true">
											<label class="form-check-label" for="flexSwitchCheckDefault">개인정보 동의</label>
											<p class="mb-2 text-sm mx-auto">
												개인정보 동의 
												<a href="#"	class="text-primary text-gradient font-weight-bold">상세보기 </a>
											</p>
										</div>
										<div class="text-center">
											<button type="button" class="btn btn-lg bg-gradient-primary btn-lg w-100 mt-4 mb-0" id="signupBtn">가입하기</button>
										</div>
									</form>
								</div>
								<div class="card-footer text-center pt-0 px-lg-2 px-1">
									<p class="mb-2 text-sm mx-auto">
										우리 서비스 회원이세요? 
										<a href="/member/signInForm" class="text-primary text-gradient font-weight-bold">로그인</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
	<script src="${pageContext.request.contextPath}/resources/js/core/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/material-dashboard.min.js?v=3.0.4"></script>
</body>
<script type="text/javascript">
let btn = $("#signupBtn");
//let idCheckFlag = false;

let error = "${error.error}";

if(error == null || error != "") {
	alert(error);	
}

/* $("#memId").on("change", function() {
	idCheckFlag = false;
}); */

btn.on("click", function() {
	//var aa = document.querySelector('#agreecheck').checked;
	//console.log(aa)
	
	let id = $("#memId").val();
	let pw = $("#memPw").val();
	let check  = $("#pwcheck").val();
	let name = $("#memName").val();
	let phone = $("#memPhone").val();
	let email = $("#memEmail").val();
	let agree = $("#agreecheck");
	
	if(id == null || id == "") {
		alert("아이디를 입력해주세요.");
		return false;
	}
	
	if(pw == null || pw == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	}
	
	if(pw != check) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
	
	if(name == null || name == "") {
		alert("이름을 입력해주세요.");
		return false;
	}
	
	if(phone == null || phone == "") {
		alert("전화번호를 입력해주세요.");
		return false;
	}
	
	if(email == null || email == "") {
		alert("이메일을 입력해주세요.");
		return false;
	}
	
	if(!agree.prop("checked")) {
		alert("개인정보를 동의해주세요.");
		return false;
	}
	
	/* if(idCheckFlag) {
		alert("아이디 중복체크를 해주세요.");
		return false;
	} */
	
	$("form").submit();
});

/* $("#idcheck").on("click", function() {
	let id = $("#memId").val();
	
	$.ajax({
		url: "/member/idcheck/" + id,
		type: "post",
		success: function(res) {
			console.log(res);
			if(res == 0) {
				alert("사용 가능한 아이디입니다.");
				idCheckFlag = true;
			} else {
				alert("이미 존재하는 아이디입니다.");
			}
		}
	})
}); */
</script>
</html>