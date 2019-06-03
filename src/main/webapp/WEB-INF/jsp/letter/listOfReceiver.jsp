<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>받은 편지함</title>
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
	<h2>받은 메일</h2>
		<p>전체 ${totalCount }건</p>
	<form action="./app/letter/listOfReceiver">
		<input type="number" name="page" value="${param.page }" placeholder="페이지"
			min="1" max="${totalCount / 100 + 1 }" step="1" style="width: 50px;">
		<button type="submit">조회</button>
		<button type="button" onclick="location.href='./app/member/memberInfo'">사용자 정보 </button>
		<button type="button" onclick="location.href='./app/main'">홈 화면 </button>
		<button type="button" onclick="location.href='./app/members'">메일 쓰기 </button>
		<button type="button" onclick="location.href='./app/letter/addForm?receiverId=${sessionScope.MEMBER.memberId }&receiverName=${sessionScope.MEMBER.name}'">내게 메일 쓰기</button>
		</form>
	<table>
		<thead>
			<tr>
				<th>메일 ID</th>
				<th>제목</th>
				<th>보낸 사람 ID</th>
				<th>보낸 사람</th>
				<th>받은 일시</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="letter" items="${letterList}">
				<tr>
					<td><a href="./app/letter/view?letterId=${letter.letterId }">${letter.letterId }</a></td>
					<td><a href="./app/letter/view?letterId=${letter.letterId }">${letter.title }</a></td>
					<td>${letter.senderId }</td>
					<td>${letter.senderName }</td>
					<td>${letter.cdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</head>
</html>