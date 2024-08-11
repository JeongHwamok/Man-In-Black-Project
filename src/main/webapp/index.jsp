<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<title>Man In Black</title>
	<style>
		#wrap {
			width: 1000px;
			height: 975px;
			margin: auto;
			box-sizing: border-box;
		}

		
	</style>
</head>

<body>
	<div id="wrap">
		<%@ include file="views/common/header.jsp" %>
			<%@ include file="views/common/navigator.jsp" %>
			<br>
				<%@ include file="views/common/content.jsp" %>
					<br><br>
					<h3 style="font-weight: bold" align="left">실시간 상품</h3>
					<br>
					<!--  content_test 자리에 상품 목록 보여주는 page include -->
					<%@ include file="views/product/productListView.jsp" %>
					<br>
						<%@ include file="views/common/footer.jsp" %>
					
	</div>
</body>
</html>