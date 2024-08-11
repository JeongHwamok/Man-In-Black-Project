<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%
	String contextPath = request.getContextPath();
	
	String alertMsg = (String)session.getAttribute("alertMsg");

	Member loginUser = (Member)session.getAttribute("loginUser");     

	if (loginUser == null || !"admin".equals(loginUser.getMemberId())) {
        session.setAttribute("alertMsg", "관리자만 이용 가능한 서비스입니다.");
        response.sendRedirect(request.getContextPath()+"/loginPage.me");
    }
	
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
<meta charset="UTF-8">
<title>Man In Black</title>
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
	
</body>
</html>