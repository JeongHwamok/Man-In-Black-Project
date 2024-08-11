<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<title>Man In Black</title>
	<style>
		
		#navigator {
			height: 36px;
		}

		#navi {
			margin: 0;
			padding: 0;
			list-style-type: none;
			height: 100%;
		}

		#navi>li {
			float: left;
			width: 25%;
			height: 100%;
			text-align: center;
			box-sizing: border-box;
		}

		#navi a {
			text-decoration: none;
			color: rgb(95, 95, 95);
			font-size: 16px;
			font-weight: 900;
			width: 100%;
			height: 100%;
			display: block;
			line-height: 35px;
		}

		#navi a:hover {
			color: black;
			font-size: 16px;
			border-bottom: 3px solid black;
		}
	</style>
</head>
<body>
	<div class="wrap">
		<div id="navigator">
			<ul id="navi">
				<li><a href="<%= request.getContextPath()%>">홈</a></li>
				<li><a href="<%=request.getContextPath()%>/list.no?currentPage=1">이벤트</a></li>
				<li><a href="<%=request.getContextPath()%>/category.do">카테고리</a></li>
				<li><a href="<%=request.getContextPath()%>/search.do?currentPage=1&orderby=date">인기/신상품</a></li>

			</ul>
		</div>
	</div>
</body>
</html>