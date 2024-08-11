<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.cart.model.vo.Cart, com.kh.order.model.vo.Order" %>
<%
	ArrayList<Cart> list = (ArrayList<Cart>) request.getAttribute("list");
	Order previousOrder = (Order) request.getAttribute("previousOrder");
	Member orderMember = (Member) request.getAttribute("orderMember");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.include {
			width: 1000px;
			margin: auto;
			box-sizing: border-box;
		}

		.outer {
			width: 975px;
			margin: auto;
			box-sizing: border-box;
		}

		#tb {
			width: 100%;
		}
	</style>
	<!-- Latest compiled and minified CSS -->
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

	<div class="include">
		<%@ include file="../common/header.jsp" %>
			<!-- <%@ include file="../common/navigator.jsp" %> -->
	</div>

	<form action="">
		<div class="outer">
			<br>

			<h5 align="center">주문번호 <%= previousOrder.getOrderNo() %></h5>
			<h3 align="center">주문과 결제가 정상적으로 완료되었습니다.</h3>

			<br><br>

			<h4>상품정보</h4>

			<br>

			<table id="tb">
				<% for(int i = 0; i < list.size(); i++){ %>
				<tr>
					<td colspan="3">
					</td>
				</tr>
				<tr>
					<td rowspan="3" width="150" height="150">
						<img src="<%= list.get(i).getImagePath() %><%= list.get(i).getImageChangeName() %>" width="200" height="200">
					</td>
					<td colspan="2"><%= list.get(i).getProductName() %></td>
				</tr>
				<tr>
					<td width="50">옵션</td>
					<td><%= list.get(i).getSizeName() %></td>
				</tr>
				<tr>
					<td colspan="2" height="100"><%= list.get(i).getCartCount() %>개</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: right;">
						<h3><%= list.get(i).getCartCount() * list.get(i).getCartPrice() %>원</h3>
					</td>
				</tr>
				<% } %>

			</table>

			<br><br><br>

			<h4>배송정보</h4>
			<br>
			<table class="table table-bordered" align="center">
				<tr>
					<td width="200">주문자</td>
					<td><%= orderMember.getMemberName() %></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><%= orderMember.getMemberPhone() %></td>
				</tr>
				<tr>
					<td>배송지</td>
					<td><%= previousOrder.getOrderAddress() %></td>
				</tr>
				<tr>
					<td>배송 요청사항</td>
					<td><%= previousOrder.getOrderMessage() %></td>
				</tr>
			</table>

			<br><br><br>

			<div align="center">
				<button type="button" class="btn btn-dark" onclick="location.href = '<%= contextPath %>';">
					홈으로 가기
				</button>

			</div>

		</div>
	</form>

	<br><br>

	<div class="include">
		<%@ include file="../common/footer.jsp" %>
	</div>
</body>
</html>