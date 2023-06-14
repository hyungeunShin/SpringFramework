<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Insert title here</title>
</head>
<body class="bg-gray-200">
	<main class="main-content  mt-0">
		<div class="page-header align-items-start min-vh-100" style="background-image: url('https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80');">
			<span class="mask bg-gradient-dark opacity-6"></span>
			<div class="container my-auto">
				<div class="row">
					<div class="col-lg-4 col-md-8 col-12 mx-auto">
						<div class="card z-index-0 fadeIn3 fadeInBottom">
							<div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
								<div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
									<h4 class="text-white font-weight-bolder text-center mt-2 mb-0">로그인</h4>
								</div>
							</div>
							<div class="card-body">
								<form role="form" class="text-start" action="/member/login" method="post">
									<div class="input-group input-group-outline my-3">
										<input type="email" class="form-control" placeholder="아이디" name="memId" id="memId" value="${map.memId}">
									</div>
									<div class="input-group input-group-outline mb-3">
										<input type="password" class="form-control" placeholder="비밀번호" name="memPw" id="memPw" value="${map.memPw}">
									</div>
									<div class="text-center">
										<button type="button" class="btn bg-gradient-primary w-100 my-4 mb-2" id="loginBtn">로그인</button>
									</div>
									<c:if test="${not empty error}">
										<span style="font-weight: bold; color: red;">${error.error}</span>
									</c:if>
									<c:if test="${not empty res}">
										<span style="font-weight: bold; color: red;">${res}</span>
									</c:if>
									<p class="mt-4 text-sm text-center">
										아직 회원이 아니세요? 
										<a href="/member/signupForm" class="text-primary text-gradient font-weight-bold">회원가입</a>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script src="${pageContext.request.contextPath}/resources/js/core/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/material-dashboard.min.js?v=3.0.4"></script>
</body>
<script type="text/javascript">
let btn = $("#loginBtn");

let error = "${error.error}";

if(error != "") {
	alert(error);	
} 

let res = "${res}";
if(res != "") {
	alert(res);
}

btn.on("click", function() {
	let id = $("#memId").val();
	let pw = $("#memPw").val();
	
	console.log(id, pw);
	
	if(id == null || id == "") {
		alert("아이디를 입력해주세요.");
		return false;
	}
	
	if(pw == null || pw == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	}
	
	$('form').submit();
});
</script>
</html>