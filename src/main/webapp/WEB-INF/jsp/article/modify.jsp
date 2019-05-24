<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>게시글 수정</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h2>개시글 수정</h2>
	<c:if test="${mode=='FAILURE' }">
		<p style="color: red;">사용자 아이디가 다릅니다.</p>
	</c:if>
	<form action="./app/article/modify" method="post">
		<p>제목 :</p>
		<p>
			<input type="text" name="title" maxlength="100" style="width: 100%;"
				required>
		</p>
		<p>내용 :</p>
		<p>
			<textarea name="content" style="width: 100%; height: 200px;" required></textarea>
		</p>
		<p>
			<button type="submit">확인</button>
		</p>
	</form>
</body>
</html>