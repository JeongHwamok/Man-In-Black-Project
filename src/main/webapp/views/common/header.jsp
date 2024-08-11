<%@page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	
	String alertMsg = (String)session.getAttribute("alertMsg");

	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">

<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<title>Man In Black</title>
	<style>
		
		#header {
			height: 144px;
		}

		#header>div {
			float: left;
			height: 100%;
		}

		#header_1 {
			width: 20%;
			position: relative;
		}

		#header_2 {
			width: 70%;
			position: relative;
		}

		#header_3 {
			width: 5%;
			position: relative;
		}

		#header_4 {
			width: 5%;
			position: relative;
		}

		/* 로그아웃 */
		#p_logout {
			padding-top: 95px;
			font-size: 8px;
			font-weight: 900;
			cursor: pointer;
		}

		#header_4>a {
			text-decoration: none;
			color: black;
		}


		/* 검색창 관련 스타일 */
		#search_form {
			width: 80%;
			height: 20%;
			margin: auto;
			position: absolute;
			top: 0;
			bottom: 0;
			left: 0;
			right: 0;
		}

		#search_form>div {
			height: 100%;
			float: left;
		}

		#search_text {
			width: 90%;
		}

		#search_btn {
			width: 10%;
		}

		#search_form input {
			width: 100%;
			height: 100%;
			border: 1px solid #F7F7F7;
		}

		#search_text>#search {
			border-top-right-radius: 15px;
			border-bottom-right-radius: 15px;
			background-color: #F7F7F7;
			border-left-color: #F7F7F7;
		}

		#search_btn>#submit {
			border-top-left-radius: 15px;
			border-bottom-left-radius: 15px;
			background-color: #F7F7F7;
			border-right-color: #F7F7F7;
			font-size: 10px;
			font-weight: bold;
			color: #727272;
		}

		/* 로고, 장바구니, 마이페이지 아이콘 스타일 */
		#header_1 #man {
			width: 140px;
			height: 140px;
			margin: auto;
			position: absolute;
			top: 0;
			bottom: 0;
			left: 0;
			right: 0;
			font-size: 20px;
			font-weight: 900;
		}

		#header_1 #man:hover {
			cursor: pointer;
		}

		#header_3 #img_div1,
		#header_4 #img_div2 {
			width: 45%;
			height: 20%;
			margin: auto;
			position: absolute;
			top: 0;
			bottom: 0;
			left: 0;
			right: 0;
		}
	</style>
</head>

<body>
	<script>
		// 자바스크립트 구문을 쓸 수 있는 영역
		// script 태그 내에서 스크립틀릭과 같은 jsp 요소를 쓸 수 있다.
		
		<%
			// 자바영역
		%>

			// 알람 문구를 담을 자바스크립트 변수 선언
			let msg = "<%= alertMsg %>";
		// "성공적으로 로그인이 되었습니다." / "null"

		if (msg != "null") { // 
			// 알람문구가 있다면
			alert(msg);
			// 알림창을 띄워준 후 session 에 담긴 해당 메세지는
			// 지워줘야만 함
			// > 안그러면 menubar.jsp가 로딩 될때마다 (새로고침)
			//   매번 alert 가 계속 뜨기 때문
			<% session.removeAttribute("alertMsg");%>
		}


	</script>
	<!-- 오프라인 방식 -->
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<div class="wrap">
		<div id="header">
			<div id="header_1">
				<div id="man">
					<a href="<%= request.getContextPath()%>" width="100%" height="100%">
						<img src="resources/images/menin.png" width="100%" height="100%">
					</a>
				</div>
			</div>
			<div id="header_2">
				<form id="search_form" action="search.do" method="get">
					<div id="search_btn">
						<input id="submit" type="submit" value="검색" class="search-button">
					</div>
					<div id="search_text">
						<input id="search" type="search" name="keyword" placeholder="search..">
					</div>
				</form>
			</div>
			<div id="header_3">
				<div id="img_div1">
					<a href="<%= request.getContextPath()%>/cart.do" width="100%" height="100%">
						<img src="resources/images/cart.png" id="person" alt="person" width="110%"
											height="110%">
					</a>
				</div>
			</div>
			<div id="header_4">
				<div id="img_div2">
					<% if(loginUser==null) { %>
						<a href="<%= contextPath%>/loginPage.me" width="100%" height="100%">
							<% } else { %>
								<a href="<%= contextPath%>/myPage.me" width="100%" height="100%">
									<% } %>
										<img src="resources/images/person2.png" id="person" alt="person" width="110%"
											height="110%">
								</a>
				</div>
				<% if(loginUser !=null) { %>
					
						<p id="p_logout" align="center" style="font-size: 12px;"><a style="color:black;" href="<%= contextPath%>/logout.me">로그아웃</a></p>
					
					<% } %>
			</div>
		</div>
	</div>
</body>
</html>