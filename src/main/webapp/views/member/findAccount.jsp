<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>화면구조</title>

	<style>
	
		/* 전체 폰트 */
		@font-face {
			font-family: 'Hahmlet-Regular';
			src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/Hahmlet-Regular.woff2') format('woff2');
			font-weight: normal;
			font-style: normal;
		}

		body {
			font-family: 'Hahmlet-Regular';
		}
	
		#logo {
			width: 200px; /* 원하는 로고의 너비 설정 */
			height: auto; /* 높이 자동 조정 */
			margin: 20px auto; /* 화면 중앙 정렬을 위한 마진 설정 */
			display: flex; 
		}
        
		div {
			box-sizing: border-box;
			text-align : center;
		}
		
		 #content {
        display: flex;
        justify-content: center;
    }

		#content_1 li:not(:first-child):hover {
			cursor: pointer;
			font-size: 18px;
			color: #494949;
		}
		
		#content2 {
	        display: flex;
	        justify-content: center;
	    }
		
		/* 상단 아이디 찾기 버튼 */
		#findId_btn {
	      width: 200px;
	      height: 45px;
	      margin: 30px 0px 10px 0px;
	      background-color: gray;
	      color: white;
	    }
	    
		/* 상단 비밀번호 찾기 버튼 */
	    #findPwd_btn {
	      width: 200px;
	      height: 45px;
	      margin: 30px 0px 10px 0px;
	      background-color: black;
	      color: white;
	    }

		.hidden {
			display: none;
		}
	</style>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<!-- 온라인 방식 -->
	
	<!-- Popper JS -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
	
</head>
<body>
	
  <div class="outer">

	<div id="a">
	
	<div id="logo">
		<a href="<%= request.getContextPath() %>">
			<img src="resources/images/menin.png" width="100%" height="100%">
		</a>    
	</div>
	
	<div id="content">
		<div id="findId" style="display: flex;">
				<button id="findId_btn" onclick="toggleContent('findIdContent', 'resetPwdContent')">아이디 찾기</button>
		</div>
		
		<div id="findPwd">
				<button id="findPwd_btn" onclick="toggleContent('resetPwdContent', 'findIdContent')">비밀번호 찾기</button>
		</div>
	</div>
				
	<div id="findIdContent">
        <%@ include file="../member/findId.jsp" %>
    </div>
    <div id="resetPwdContent" class="hidden">
        <%@ include file="../member/resetPwd.jsp" %>
    </div>
    <div>
		<%@ include file="../common/footer.jsp" %>
	</div>


<script>
    function toggleContent(showId, hideId) {
        document.getElementById(showId).style.display = 'block';
        document.getElementById(hideId).style.display = 'none';
    }
</script>
</div>

</body>
</html>