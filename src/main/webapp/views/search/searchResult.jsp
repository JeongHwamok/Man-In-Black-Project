<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.HashMap, com.kh.common.model.vo.PageInfo, com.kh.product.model.vo.Product, java.text.DecimalFormat" %>
<%
	ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	HashMap<String, String> qStrMap = (HashMap<String, String>)	request.getAttribute("qStrMap");
	DecimalFormat formatter = new DecimalFormat("#,###"); // 가격 , 붙여서 포메팅해주는 객체 
	
	String qStr = qStrMap.toString().replace("{", "").replace("}", "").replace(", ", "&");
	String pageNext = qStr.replace("currentPage=" + pi.getCurrentPage(), "currentPage=" + (pi.getCurrentPage() + 1));
	String pagePrev = qStr.replace("currentPage=" + pi.getCurrentPage(), "currentPage=" + (pi.getCurrentPage() - 1));
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<title>Man In Black</title>
	<style>
		
		.wrap{
        width: 1000px;
        margin: auto;
        box-sizing: border-box;
  		  }
    
		#filter{
			height: 48px;
			margin: auto;
			line-height: 48px;
		}
		
		.container{
			width: 100%;
		
		}
	
		
		.item{
			display: inline-block;
	        box-sizing: border-box;
	        width: 33%;
	        padding-left: 3%;
	        padding-top: 1%;
	
		}
		
		.pagination{
			display: flex;
			justify-content: center;
			align-items: center;
		}
		#likeId {
		font-size: 15px; 
		color: red; 
		text-align: left;
		}
		
		.categoryStyle {
			font-size: 12px;
		}
		.image {
	
		height: 300px;
		}
	</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<%@ include file="../common/navigator.jsp" %>
	<div class="wrap">
		<div id="filter" align="right">
			<a href="javascript:void(0)" style="color: black; text-decoration: none;" value="date" onclick="orderby(this)"><strong>최근 상품순</strong></a> |
			<a href="javascript:void(0)" style="color: black; text-decoration: none;" value="priceAsc" onclick="orderby(this)"><strong>가격 낮은순</strong></a> |
			<a href="javascript:void(0)" style="color: black; text-decoration: none;" value="priceDesc" onclick="orderby(this)"><strong>가격 높은순</strong></a> |
			<a href="javascript:void(0)" style="color: black; text-decoration: none;" value="like" onclick="orderby(this)"><strong>좋아요 많은순</strong></a>
		</div>
	</div>
	
	<script>
		function orderby(obj){
			
			let qStr = "<%= qStr %>";

			let map = new Map;
			qStr.split("&").map((x) => map.set(x.split("=")[0], x.split("=")[1]));
			
			map.set("orderby", $(obj).attr("value"));
			map.set("currentPage", 1);

			qStr = "";
			map.forEach(function(value, key, map){
				qStr += key + "=" + value + "&";
			});

			qStr = qStr.substring(0, qStr.lastIndexOf("&"));

			location.href = location.origin + "<%= contextPath %>/search.do?" + qStr;
		}
	</script>
	<div class="wrap">
	
		<div class="container">
			<!-- 반복되는 아이템 시작 -->
				<%if(list.size() > 0){ %>
					<%for(Product p : list){%>
							<div class="item" onclick="itemClick(<%= p.getProductNo() %>)">
								<div class="image">
									<!-- 여기에 이미지 태그를 넣거나 배경 이미지를 사용할 수 있습니다 -->
									<img src='<%= p.getTitleImg()  %>' width='100%' height='100%'>
								</div>
								<span id="likeId"> &#10084;</span><span class="likeCount" style="font-size: 14px;"></span><br>
								<span class='categoryStyle'>#<%=p.getCategoryNo() %>, #<%=p.getSubcategoryNo() %></span>
								<div class="text">
									<p style='margin: 0px; font-size: 15px; font-weight:900;'><%=  p.getProductName() %></p>
									<p style='font-family: Arial, sans-serif;'>￦<%= formatter.format(p.getProductPrice()) %><br></p>
								</div> 
							</div>
						<%}%>
					<%} else {%>
						<p align="center">검색된 상품이 없습니다.</p>
					<%}%>
			<!-- 반복되는 아이템 끝 -->
			</div>
		<div class="pagination" align="center">
			<%if(pi.getCurrentPage() > 1){ %>
				<input type="button" class="btn btn-secondary btn-sm" onclick="location.href='?<%= pagePrev %>'" value="&lt; prev">
			<% } %>
			
			<%for(int i = pi.getStartPage(); i <= pi.getEndPage(); i++){%>
				<% if(i == pi.getCurrentPage()){ %>
					<input type="button" class="btn btn-secondary btn-sm" disabled value="<%= i %>" onclick="location.href='?<%= qStr.replace("currentPage=" + pi.getCurrentPage(), "currentPage=" + i) %>'">
				<% } else { %>
					<input type="button" class="btn btn-secondary btn-sm" value="<%= i %>" onclick="location.href='?<%= qStr.replace("currentPage=" + pi.getCurrentPage(), "currentPage=" + i) %>'">
				<% } %>
			<%}%>
			
			<% if(pi.getCurrentPage() != pi.getMaxPage()){ %>
				<input type="button" class="btn btn-secondary btn-sm" onclick="location.href='?<%= pageNext%>'" value="next &gt;">
			<% } %>
		</div>
		<script>
			function itemClick(pno) {
	
				location.href="<%= contextPath %>/productDetail.pr?pno="+ pno
	
			}
		</script>
		<script>
			$(function() {
				likeCheck();
				
				//setInterval(likeCheck, 1000);
			});	
			
			function likeCheck() {
				$.ajax({
					
					url : "<%= contextPath%>/productList.pr",
					data: {currentPage : <%= request.getParameter("currentPage") %>,
						   qStr : "<%= qStr %>" },
					success : function(result) {
						let pi = result["pi"];
						let likeCount = result["likeCount"];
						
						for (let i = 0; i < pi.listCount; i++) {
							if(typeof $(".likeCount").get(i) !== "undefined") {
	                       		$(".likeCount").get(i).innerText = likeCount[i];
							} else {
							    continue;	
							}
	                    };
					
	                    
	                   
					},
					error : function() {
						console("실행됨");
					}
				});
			
			}
		</script>

	</div>
	
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>