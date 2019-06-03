<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<!-- 회원 목록 -->
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>회원 목록</title>
<style type="text/css">
table {
	margin-top: 10px;
	border-collapse: collapse;
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
	width: 100%;
	text-align: center;
}
th, td {
	padding: 5px 0;
}
th {
	border-bottom: 1px solid gray;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h2>회원 목록</h2>
	<p>전체 ${totalCount }건</p>
	<form action="./app/members">
		<input type="number" name="page" value="${param.page }" placeholder="페이지"
			min="1" max="${totalCount / 100 + 1 }" step="1" style="width: 50px;">
		<button type="submit">조회</button> 
		<button type="button" onclick="location.href='./app/main'">홈 화면 </button>
		</form>
	<table>
		<thead>
			<tr>
				<th>메일 쓰기</th>
				<th>회원번호</th>
				<th>이메일</th>
				<th>이름</th>
				<th>등록일시</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${members}">
				<tr>
					<td><a href="./app/letter/addForm?receiverId=${member.memberId }&receiverName=${member.name}">메일 쓰기 </a></td>
					<td>${member.memberId }</td>
					<td>${member.email }</td>
					<td>${member.name }</td>
					<td>${member.cdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</head>
</html>