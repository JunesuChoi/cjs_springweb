<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>메일 보기</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h2>글 보기</h2>
	<p>
		<a href="./app/letter/listOfSender">글 목록</a> <a href="./app/main">홈 화면</a>
	</p>
			<form action="./app/letter/delete?letterId=${letter.letterId}" method="post">
			<button type="submit">삭제</button>
			</form>
			<hr />
	<p>
		<span>메일 번호 : ${letter.letterId }</span> | <span style="font-weight: bold;">제목 : ${letter.title }</span>
		<p></p>
		<span>받은 사람 ID : ${letter.receiverId }</span> | <span>받은 사람 : ${letter.receiverName }</span> |
		<span>받은 날짜 : ${letter.cdate }</span>
		<p><span>보낸 사람 ID : ${letter.senderId}</span> | <span>보낸 사람 : ${letter.senderName }</span> | 
		<span>보낸 날짜 :${letter.cdate }</span>
		</p>
	<hr />
	<p>내용 : ${letter.contentHtml }</p>
	<hr />
	<button onclick="window.location.href='./app/letter/addForm?receiverId=${letter.senderId }&receiverName=${letter.senderName}'">답장 쓰기</button>
</body>
</html>