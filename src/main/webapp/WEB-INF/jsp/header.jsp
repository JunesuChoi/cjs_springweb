<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<p>
		<span><button onclick="window.location.href='./'">홈</button></span> | 
		<span><button onclick="window.location.href='./app/members'">회원목록</button></span>
		| <span><button onclick="window.location.href='./app/article/list'">게시판</button></span> |
		<!-- 로그인 여부에 따라 분기 -->
		<c:choose>
			<c:when test="${!empty sessionScope.MEMBER }">
				<!-- 로그인 했을 경우 -->
			<span><button onclick="window.location.href='./app/member/memberInfo'">${MEMBER.name }</button></span> | 
			<span><button onclick="window.location.href='./app/logout'">로그아웃</button></span> |
			<span><button onclick="window.location.href='./app/letter/listOfReceiver'">받은 메일함</button></span> | 
			<span><button onclick="window.location.href='./app/letter/listOfSender'">보낸 메일함</button></span>
			</c:when>
			<c:otherwise>
				<!-- 로그인 하지 않았을 경우 -->
				<span><button onclick="window.location.href='./app/login/loginForm'">로그인</button></span> |
				<span><button onclick="window.location.href='./app/register/step1'">회원가입</button></span>
			</c:otherwise>
		</c:choose>
	</p>
</header>