<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set value="${member}" var="mem"></c:set>
	<table>
		<thead>
			<tr>
				<td>아이디</td>
				<td><c:out value="${mem.userId}"></c:out></td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><c:out value="${mem.password}"></c:out></td>
			</tr>
			
			<tr>
				<td>이름</td>
				<td><c:out value="${mem.userName}"></c:out></td>
			</tr>
			
			<tr>
				<td>이메일</td>
				<td><c:out value="${mem.email}"></c:out></td>
			</tr>
			
			<tr>
				<td>생년월일</td>
				<td>
					<%-- <c:out value="${mem.dateOfBirth}"></c:out> --%>
					<fmt:formatDate value="${mem.dateOfBirth}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			
			<tr>
				<td>성별</td>
				<td>
					<c:if test="${mem.gender eq 'male'}">
						<c:out value="남자"></c:out>
					</c:if>
					<c:if test="${mem.gender eq 'female'}">
						<c:out value="여자"></c:out>
					</c:if>
					<c:if test="${mem.gender eq 'other'}">
						<c:out value="그 외"></c:out>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td>개발자 여부</td>
				<td>
					<c:if test="${mem.developer eq 'Y'}">
						<c:out value="개발자"></c:out>
					</c:if>
					<c:if test="${mem.developer ne 'Y'}">
						<c:out value="개발자 아님"></c:out>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td>외국인 여부</td>
				<td>
					<c:if test="${mem.foreigner eq true}">
						<c:out value="외국인"></c:out>
					</c:if>
					<c:if test="${mem.foreigner eq false}">
						<c:out value="한국인"></c:out>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td>국적</td>
				<td>
					<c:set value="${mem.nationality}" var="nation"></c:set>
					<c:if test="${nation eq 'korea'}">
						대한민국
					</c:if>
					<c:if test="${nation eq 'germany'}">
						독일
					</c:if>
					<c:if test="${nation eq 'austrailia'}">
						호주
					</c:if>
					<c:if test="${nation eq 'canada'}">
						캐나다
					</c:if>
					<c:if test="${nation eq 'usa'}">
						미국
					</c:if>
				</td>
			</tr>
			
			<!-- 배열 cars -->
			<tr>
				<td>소유차량</td>
				<td>
					<c:set value="${mem.cars}" var="cars"></c:set>
					<c:forEach items="${cars}" var="car">
						${car}
					</c:forEach>
				</td>
			</tr>
			
			<!-- 배열 hobby -->
			<tr>
				<td>취미</td>
				<td>
					<c:set value="${mem.hobby}" var="hobby"></c:set>
					<c:forEach items="${hobby}" var="hobby">
						<c:if test="${hobby eq 'sports'}">
							스포츠
						</c:if>
						<c:if test="${hobby eq 'music'}">
							음악
						</c:if>
						<c:if test="${hobby eq 'movie'}">
							영화
						</c:if>
					</c:forEach>
				</td>
			</tr>
			
			<tr>
				<td>우편번호 + 주소</td>
				<td><c:out value="${mem.address.postCode}" />,<c:out value="${mem.address.location}" /></td>
			</tr>
			
			<!-- 리스트 -->
			<c:set value="${mem.cardList}" var="cardList"></c:set>
			<c:forEach items="${cardList}" var="card">
				<tr>
					<td>카드 번호</td>
					<td><c:out value="${card.no}"></c:out></td>
				</tr>
				<tr>
					<td>카드 유효년월</td>
					<td>
						<%-- <c:out value="${card.validMonth}"></c:out> --%>
						<fmt:formatDate value="${card.validMonth}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
			</c:forEach>
						
			<tr>
				<td>자기소개</td>
				<td><c:out value="${mem.introduction}"></c:out></td>
			</tr>
		</thead>
	</table>
</body>
</html>