<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h1>10. 파일 업로드 Ajax 방식 요청 처리</h1>
	<hr>
	
	<p>Ajax 방식으로 전달한 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리</p>
	<div>
		<input type="file" id="inputFile"><br>
		<hr>
		<img id="preview">
	</div>
</body>
<script type="text/javascript">
$("#inputFile").on('change', function(event) {
	let files = event.target.files;
	let file = files[0];
	
	if(isImageFile(file)) { //이미지 파일일때
		//비동기 처리 시, 파일데이터를 전송할 때에는 formData()를 이용하여 데이터를 전송
		let formData = new FormData();
		formData.append("file", file);
		
		//formData는 key/value 형식으로 데이터가 저장된다
		//dataType: 응답(response)데이터를 받을 때 타입
		//processData : 데이터 파라미터를 data라는 속성으로 넣는데, jquery 내부적으로 쿼리스트링을 구성
		//				파일 전송의 경우 쿼리스트링을 사용하지 않으므로 해당 설정을 false로 비활성화한다
		//contentType: contentType 설정 시, 사용하는데 해당 설정의 기본값은 'application/x-www-form-urlencoded; charset=utf-8'이다
		//			   그래서 기본값으로 나가지 않고 'multipart/form-data'로 나갈 수 있도록 false로 설정한다
		// 			request요청에서 contentType을 확인해보면 'multipart/form-data; boundary=----WebKitFormBoundary[HashKey]'
		//			와 같은 값으로 전송된 것을 확인 할 수 있다.
		
		$.ajax({
			type: "post",
			url: "/ajax/uploadAjax",
			data : formData,
			dataType: "text",
			processData : false,
			contentType: false,
			success : function(res) {
				alert(res);
				if(res === "UPLOAD SUCCESS") {
					let file = event.target.files[0];
					let reader = new FileReader();
					reader.onload = function(e) {
						$('#preview').attr("src", e.target.result);
					}
					reader.readAsDataURL(file);
				}
			}
		});
	} else { //이미지가 아닐때
		alert("이미지를 넣어주세요");
	}
});

function isImageFile(file) {
	let ext = file.name.split(".").pop().toLowerCase();
	
	//확장자 중 이미지에 해당하는 확장자가 아닌 경우 포함되어 있는 문자가 없으니까 -1(false)
	//확장자 중 이미지에 해당하는 확장자가 포함되어 있다면 0보다 큰 수 일테니 true가 return
	return ($.inArray(ext, ["jpg", "jpeg", "gif", "png"]) === -1) ? false : true;
}

</script>
</html>