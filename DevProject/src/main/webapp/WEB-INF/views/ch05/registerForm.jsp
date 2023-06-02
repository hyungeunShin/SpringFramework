<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerForm</title>
<style type="text/css">
label {
	display: inline-block;
	width: 80px;
}
</style>
</head>
<body>
	<h1>registerForm</h1>
	<hr/>
	
	<h4>1,2. 컨트롤러 메소드 매개변수(요청 처리)</h4>
	<hr/>
	
	<p>1) URL 경로 상의 쿼리 파라미터 정보로부터요청 데이터를 취득할 수 있다.</p>
	<a href="register?userId=hong&password=1234">Button1</a>
	
	<p>
		2) URL 경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다<br>
		<font color="red">서버쪽 컨트롤러 메소드에서 @PathVariable을 사용하지 않은 경우 파라미터로 값을 얻을 수 없다.</font>
	</p>
	<a href="/register/hong">Button2</a>
	
	<p>3) HTML Form 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를 얻을 수 있다</p>
	<form action="/register01" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="register01"><br>
	</form>
	
	<p>4) HTML Form 필드가 여러개일 경우에도 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다</p>
	<form action="/register02" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="register02"><br>
	</form>
	
	<p>5) HTML Form 필드가 여러개일 경우에 컨틀로러 매개변수의 위치는 상관이 있을까?</p>
	<form action="/register03" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="register03"><br>
	</form>	
		
	<p>6) HTML Form 필드값이 숫자일 경우에는 컨트롤러 매개변수 타입이 문자열이면 그대로 문자열 형태가 되는가</p>
	<form action="/register04" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="register04"><br>
	</form>	
		
	<p>7) HTML Form 필드 값이 숫자일 경우에는 컨트롤러 매개변수 타입이 숫자형이면 숫자로 타입변환하여 데이터를 얻는가</p>
	<form action="/register05" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="register05"><br>
	</form>
	
	<br><hr><br>
	
	<h4>3. 요청 데이터 처리 어노테이션</h4>
	<hr>
	
	<p>1) URL 경로 상의 경로 변수가 여러 개일때 @PathVariable 어노테이션을 사용하여 특정한 경로 변수명을 지정해준다</p>
	<a href="/register/hong/100">Button3</a><br>
		
	<p>2) HTML 폼의 필드명과 컨틀롤러 매개변수명이 일치하면 요청 처리 가능</p>
	<form action="/register0101" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="register0101"><br>
	</form>
		
	<p>3) HTML 폼 필드명과 컨틀로러 매개변수명이 일치하지 않으면 데이터를 얻는 수 없다</p>
	<form action="/register0201" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="register0201"><br>
	</form>
	
	<p>4) @RequestParam 어노테이션을 사용하여 특정 HTML 폼의 필드명을 지정하여 요청을 처리</p>
	<form action="/register0202" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="register0202"><br>
	</form>
	
	<br><hr><br>
	
	<h4>4. 자바빈즈</h4>
	<hr>
	
	<p>1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리</p>
	<form action="/beans/register01" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="registerJavaBeans01"><br>
	</form>	
		
	<p>2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수와 기본 데이터 타입인 정수 타입 매개변수로 처리한다</p>
	<form action="/beans/register02" method="post">
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="registerJavaBeans02"><br>
	</form>	
		
	<p>3) 여러개의 폼 텍스트 필드 요소값을 매개변수 순서와 상관없이 매개변수명을 기준으로 처리</p>
	<form action="/beans/register03" method="post">
		<label>uid</label> : <input type="text" name="uid" value="50"><br>
		<label>아이디</label> : <input type="text" name="userId" value="hong"><br>
		<label>비밀번호</label> : <input type="text" name="password" value="1234"><br>
		<label>coin</label> : <input type="text" name="coin" value="100"><br>
		<input type="submit" value="registerJavaBeans03"><br>
	</form>	
	
	<br><hr><br>
		
	<h4>5,6. Date 타입 처리</h4>	
	<hr>
	
	<p>1) 쿼리 파라미터(dateOfBirth=1234)로 전달받은 값이 날짜 문자열 형식에 맞지 않아 Date타입으로 변환 실패</p>
	<a href="/registerByGet01?userId=hong&dateOfBirth=1234">Button4</a><br>
	
	<p>2) 쿼리 파라미터(dateOfBirth=2018-09-08)로 전달받은 값이 날짜 문자열 형식으로 설정 시, Date타입으로 받는가?</p>
	<a href="/registerByGet01?userId=hong&dateOfBirth=2018-09-08">Button5</a><br>
	
	<p>3) 쿼리 파라미터(dateOfBirth=20180908)로 전달받은 값이 날짜 문자열 형식으로 설정 시, Date타입으로 받는가?</p>
	<a href="/registerByGet01?userId=hong&dateOfBirth=20180908">Button6</a><br>
	
	<p>
		4) 쿼리 파라미터(dateOfBirth=2018/09/08)로 전달받은 값이 날짜 문자열 형식으로 설정 시, Date타입으로 받는가?<br>
		<font color="green">파라미터가 Date 타입이면 7만 가능하다.</font>
		<font color="red">파라미터가 String 타입이면 7,8,9만 가능하다.</font>
	</p>
	<a href="/registerByGet01?userId=hong&dateOfBirth=2018/09/08">Button7</a><br>	
	
	<p>5) Member 매개변수와 쿼리 파라미터(dateOfBirth=20180908)로 전달받은 값이 날짜 문자열 형식으로 설정 시, Date타입으로 받는가?</p>
	<a href="/registerByGet02?userId=hong&dateOfBirth=20180908">Button8</a><br>
	
	<p>6) Member 매개변수와 폼 방식 요청 전달받은 값이 날짜 문자열 형식으로 설정 시, Date타입으로 받는가?</p>
	<form action="/register" method="post">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>dateOfBirth</label> : <input type="date" name="dateOfBirth"><br>
		<input type="submit" value="button9"><br>
	</form>
	
	<br><hr><br>
		
	<h4>7. 폼 방식 요청 처리</h4>	
	<hr>
	
	<p>1) 폼 텍스트 필드 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리</p>
	<form action="/registerUserId" method="post">
		<label>userId</label> : <input type="text" name="userId"><br>
		<input type="submit" value="registerUserId"><br>
	</form>
	
	<p>2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리</p>
	<form action="/registerMemberUserId" method="post">
		<label>userId</label> : <input type="text" name="userId"><br>
		<input type="submit" value="registerMemberUserId"><br>
	</form>	
	
	<p>3) 폼 비밀번호 필드 요소값을 자바빈즈 매개변수로 처리</p>
	<form action="/registerPassword" method="post">
		<label>userId</label> : <input type="password" name="password"><br>
		<input type="submit" value="registerPassword"><br>
	</form>	
	
	<p>4) 폼 라디오버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리</p>
	<form action="/registerRadio" method="post">
		<label>gender</label><br>
		<input type="radio" name="gender" value="male" checked="checked">male<br>
		<input type="radio" name="gender" value="female">female<br>
		<input type="radio" name="gender" value="other">other<br>
		<input type="submit" value="registerRadio"><br>
	</form>
	
	<p>5) 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리</p>
	<form action="/registerSelect" method="post">
		<label>nationality</label><br>
		<select name="nationality">
			<option value="korea">대한민국</option>
			<option value="germany">독일</option>
			<option value="austrailia">호주</option>
			<option value="canada">캐나다</option>
			<option value="usa">미국</option>
		</select>
		<input type="submit" value="registerSelect"><br>
	</form>
	
	<p>6) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리</p>
	<form action="/registerMultiSelect01" method="post">
		<label>cars</label><br>
		<select name="cars" multiple="multiple">
			<option value="jeep">jeep</option>
			<option value="volvo">volvo</option>
			<option value="bmw">bmw</option>
			<option value="audi">audi</option>
		</select>
		<input type="submit" value="registerMultiSelect01"><br>
	</form>
	
	<p>7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리</p>
	<form action="/registerMultiSelect02" method="post">
		<label>cars</label><br>
		<select name="carArray" multiple="multiple">
			<option value="jeep">jeep</option>
			<option value="volvo">volvo</option>
			<option value="bmw">bmw</option>
			<option value="audi">audi</option>
		</select>
		<input type="submit" value="registerMultiSelect02"><br>
	</form>
	
	<p>8) 복수 선택이 가능한 폼 셀렉트박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리</p>
	<form action="/registerMultiSelect03" method="post">
		<label>cars</label><br>
		<select name="carList" multiple="multiple">
			<option value="jeep">jeep</option>
			<option value="volvo">volvo</option>
			<option value="bmw">bmw</option>
			<option value="audi">audi</option>
		</select>
		<input type="submit" value="registerMultiSelect03"><br>
	</form>
	
	<p>9) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리</p>
	<form action="/registerCheckbox01" method="post">
		<label>hobby</label><br>
		<input type="checkbox" name="hobby" value="sports">Sports<br>
		<input type="checkbox" name="hobby" value="music">Music<br>
		<input type="checkbox" name="hobby" value="movie">Movie<br>
		<input type="submit" value="registerCheckbox01"><br>
	</form>
	
	<p>10) 폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리</p>
	<form action="/registerCheckbox02" method="post">
		<label>hobby</label><br>
		<input type="checkbox" name="hobbyArray" value="sports">Sports<br>
		<input type="checkbox" name="hobbyArray" value="music">Music<br>
		<input type="checkbox" name="hobbyArray" value="movie">Movie<br>
		<input type="submit" value="registerCheckbox02"><br>
	</form>
	
	<p>11) 폼 체크박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리</p>
	<form action="/registerCheckbox03" method="post">
		<label>hobby</label><br>
		<input type="checkbox" name="hobbyList" value="sports">Sports<br>
		<input type="checkbox" name="hobbyList" value="music">Music<br>
		<input type="checkbox" name="hobbyList" value="movie">Movie<br>
		<input type="submit" value="registerCheckbox03"><br>
	</form>
	
	<p>12) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리</p>
	<form action="/registerCheckbox04" method="post">
		<label>boolean</label><br>
		<input type="checkbox" name="developer" value="Y"><br>
		<input type="submit" value="registerCheckbox04"><br>
	</form>
	
	<p>13) 폼 체크박스 요소값을 기본 데이터 타입인 불린 타입 매개변수로 처리</p>
	<form action="/registerCheckbox05" method="post">
		<label>foreigner</label><br>
		<input type="checkbox" name="foreigner" value="true"><br>
		<input type="submit" value="registerCheckbox05"><br>
	</form>
	
	<p>14) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리</p>
	<form action="/registerAddress" method="post">
		<label>postCode</label><input type="text" name="postCode"><br>
		<label>location</label><input type="text" name="location"><br>
		<input type="submit" value="registerAddress"><br>
	</form>
	
	<p>15) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리</p>
	<form action="/registerUserAddress" method="post">
		<label>postCode</label><input type="text" name="address.postCode"><br>
		<label>location</label><input type="text" name="address.location"><br>
		<input type="submit" value="registerUserAddress"><br>
	</form>
	
	<p>16) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리</p>
	<form action="/registerUserCardList" method="post">
		<label>카드1 - 번호 : </label><input type="text" name="cardList[0].no"><br>
		<label>카드1 - 유효년월 : </label><input type="text" name="cardList[0].validMonth"><br>
		<label>카드2 - 번호 : </label><input type="text" name="cardList[1].no"><br>
		<label>카드2 - 유효년월 : </label><input type="text" name="cardList[1].validMonth"><br>
		<input type="submit" value="registerUserCardList"><br>
	</form>
	
	<p>17) 폼 텍스트 영역 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다</p>
	<form action="/registerTextArea" method="post">
		<label>introduction</label><br>
		<textarea rows="6" cols="50" name="introduction"></textarea>
		<input type="submit" value="registerTextArea"><br>
	</form>
	
	<p>18) 폼 텍스트 영역 요소값을 Date 타입 매개변수로 처리</p>
	<form action="/registerDate01" method="post">
		<label>dateOfBirth</label><input type="text" name="dateOfBirth"><br>
		<input type="submit" value="registerDate01"><br>
	</form>
	
	<br><hr><br>
	
	<h3>8. 파일업로드 폼 방식 요청 처리</h3>
	<hr>
	
	<p>1) 파일업로드 폼 파일 요소값을 스프링 MVC가 지원하는 MultipartFile 매개변수로 처리</p>
	<form action="/registerFile01" method="post" enctype="multipart/form-data">
		<input type="file" name="picture"><br>
		<input type="submit" value="업로드">
	</form>
	
	<p>2) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 문자열 매개변수로 처리</p>
	<form action="/registerFile02" method="post" enctype="multipart/form-data">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>이미지</label> : <input type="file" name="picture"><br>
		<input type="submit" value="업로드">
	</form>
	
	<p>3) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리</p>
	<form action="/registerFile03" method="post" enctype="multipart/form-data">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>이미지</label> : <input type="file" name="picture"><br>
		<input type="submit" value="업로드">
	</form>
	
	<p>4) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리</p>
	<form action="/registerFile04" method="post" enctype="multipart/form-data">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>이미지</label> : <input type="file" name="picture"><br>
		<input type="submit" value="업로드">
	</form>
	
	<p>5) 여러개의 파일업로드를 폼 파일 요소값을 여러개의 MultipartFile 매개변수로 처리</p>
	<form action="/registerFile05" method="post" enctype="multipart/form-data">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>이미지</label> : <input type="file" name="picture1"><input type="file" name="picture2"><br>
		<input type="submit" value="업로드">
	</form>
	
	<p>6) 여러개의 파일업로드를 폼 파일 요소값을 여러개의 MultipartFile 타입의 요소를 가진 리스트 컬렉션 타입 매개변수로 처리</p>
	<form action="/registerFile06" method="post" enctype="multipart/form-data">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>이미지</label> : <input type="file" name="pictureList[0]"><input type="file" name="pictureList[1]"><br>
		<input type="submit" value="업로드">
	</form>
	
	<p>7) 여러개의 파일업로드를 폼 파일 요소값과 텍스트 필드 요소값을 MultiFileMember 타입의 자바빈즈 매개변수로 처리</p>
	<form action="/registerFile07" method="post" enctype="multipart/form-data">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>이미지</label> : <input type="file" name="pictureList[0]"><input type="file" name="pictureList[1]"><br>
		<input type="submit" value="업로드">
	</form>
	
	<p>7) 번과 동일한 URL로 요청 진행</p>
	<form action="/registerFile07" method="post" enctype="multipart/form-data">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>이미지</label> : <input type="file" name="pictureList" multiple="multiple"><br>
		<input type="submit" value="업로드">
	</form>
	
	<p>8) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile타입의 배열 매개변수로 처리</p>
	<form action="/registerFile08" method="post" enctype="multipart/form-data">
		<label>userId</label> : <input type="text" name="userId" value="hong"><br>
		<label>password</label> : <input type="text" name="password" value="1234"><br>
		<label>이미지</label> : <input type="file" name="pictureList" multiple="multiple"><br>
		<input type="submit" value="업로드">
	</form>
</body>
</html>