<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>

<html>
<head>
<style>
.tb { border-collapse:collapse;}
.tb th, .tb td {width:800px; height:100px; margin: auto;
					background-image: url("http://mehdishokrany.persiangig.com/KHaterehjoon/2/baran.GIF");
					background-color: #000000;
					text-align: center;
					border:1px solid black;}
.tb td {wedth:800px; height:600px; 
		background-image: url("https://data.whicdn.com/images/293932140/original.gif");
		background-color: #000000;
		}
		 button  {
        font-size: 20px;
        font-weight: bold;
        text-align: center;
        background-color: #ffffff;
        background-color: rgba( 255, 255, 255, 0.5 );
      }
</style>
<base href="${pageContext.request.contextPath }/" />
<title>메 - 어서오십시오 -  인</title>
</head>
<body>

<table class="tb">
<tr>
<th><header><h1><font size="10em" color ="White">
	메뉴</font></h1></header></th>
	</tr>
	<tr>
<td>
	<c:choose>
		<c:when test="${!empty sessionScope.MEMBER }">
		<p><font color = "white">memberId: ${MEMBER.memberId }, email: ${MEMBER.email }, name:
				${MEMBER.name }</font></p>
	<p><span><button onclick="window.location.href='./app/letter/listOfReceiver'">받은 메일함</button></span> | 
	<a href="./app/logout"><font size="10em" color ="white">[로그아웃]</font></a>
	<span><button onclick="window.location.href='./app/letter/listOfSender'">보낸 메일함</button></span> |
	</p>
		</c:when>
		<c:otherwise>
		<p><a href="./app/loginForm"><font size="10em" color ="white">[로그인]</font></a></p>
		<p><a href="./app/register/step1"><font size="10em" color ="white">[회원가입]</font></a></p>
		</c:otherwise>
	</c:choose>
		<p><a href="./app/member/changePwdForm"><font size="10em" color ="white">[비밀번호 변경]</font></a></p>
		<p><a href="./app/member/memberInfo"><font size="10em" color ="white">[사용자 정보]</font></a></p>
		<p><a href="./app/members"><font size="10em" color ="white">[회원목록]</font></a></p>
		<a href="./app/article/list"><font size="10em" color ="white">[게시판]</font></a>
	 </td>
	</tr>
	</table>
</body>
</head>
</html>