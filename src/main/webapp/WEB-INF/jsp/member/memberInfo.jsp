<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<!-- p.367 [리스트 13.18] 비밀번호 변경 화면 -->
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>개인정보</title>
<style type="text/css">
table {
	margin-top: 10px;
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
	<h2>개인정보</h2>
	<table>
	<thead>
			<tr>
				<th>사용자 ID</th>
				<th>사용자 메일</th>
				<th>사용자 이름</th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>${sessionScope.MEMBER.memberId }</td>
					<td>${sessionScope.MEMBER.email }</td>
					<td>${sessionScope.MEMBER.name }</td>
				</tr>
		</tbody>
	</table>
	<span><button onclick="window.location.href='./app/member/changePwdForm'">비밀번호 변경</button></span>
	<p></p>
	<span><button onclick="window.location.href='./app/letter/listOfReceiver'">받은 메일함</button></span> |
	<span><button onclick="window.location.href='./app/letter/listOfSender'">보낸 메일함</button></span>
	
</body>
</html>