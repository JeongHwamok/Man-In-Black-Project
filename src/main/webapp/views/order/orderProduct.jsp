<%@page import="com.kh.cart.model.vo.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
  Member oruser = (Member)session.getAttribute("loginUser");
  String memberName = oruser.getMemberName();
  String memberPhone = oruser.getMemberPhone();
  String memberAddress = oruser.getMemberAddress();
  %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<title>주문</title>
	<style>
		#wrap {
			width: 1000px;
			height: 975px;
			margin: auto;
			box-sizing: border-box;
		}

		@font-face {
			font-family: 'Hahmlet-Regular';
			src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/Hahmlet-Regular.woff2') format('woff2');
			font-weight: normal;
			font-style: normal;
		}

		body {
			font-family: 'Hahmlet-Regular';
		}
	</style>
</head>
<body>
	<div id="wrap">
		<%@ include file="../common/header.jsp" %>
			<%@ include file="../common/navigator.jsp" %>
			
						<section id="order-product-info">
		  <h2>주문 상품 정보</h2>
		  <%
		    ArrayList<Cart> orderProducts = (ArrayList<Cart>)request.getAttribute("orderProducts");
		    if(orderProducts != null && !orderProducts.isEmpty()) {
		      for(Cart product : orderProducts) {
		  %>
		        <div class="product-info">
		          <div class="product-image">
		            <img src="<%= product.getImagePath() %>" alt="<%= product.getProductName() %>">
		          </div>
		          <div class="product-details">
		            <p class="product-name"><%= product.getProductName() %></p>
		            <p class="product-description"><%= product.getSizeName() %></p>
		            <p class="product-price"><%= product.getCartPrice() * product.getCartCount() %>원</p>
		          </div>
		        </div>
		        <hr>
		  <%
		      }
		    } else {
		  %>
		        <h5 style="text-align: center">주문할 상품이 없습니다.</h5>
		        <hr>
		  <%
		    }
		  %>
		</section>
		
		<section id="delivery-info">
		  <h3>배송지 정보</h3>
		  <label for="name">이름:</label>
		  <input type="text" id="name" name="name" placeholder="이름을 입력하세요" value="<%= memberName %>"><br>
		  <label for="contact">연락처:</label>
		  <input type="text" id="contact" name="contact" placeholder="연락처를 입력하세요" value="<%= memberPhone %>"><br>
		  <label for="address">배송지 주소:</label>
		  <input type="text" id="address" name="address" placeholder="주소를 입력하세요" value="<%= memberAddress %>">
		  <button id="searchAddress">검색</button>
		  <hr>
		</section>
		
		<section id="delivery-request">
		  <h3>배송 요청사항</h3>
		  <select id="delivery-options" name="deliveryOptions">
		    <option value="none" selected>배송 요청사항을 선택해주세요</option>
		    <option value="leave-at-door">문 앞에 놓아주세요</option>
		    <option value="ring-bell">벨 누르시고 놓아주세요</option>
		    <option value="direct-handover">직접 전달해주세요</option>
		  </select>
		  <hr>
		</section>
		
		<section id="payment-amount">
		  <h3>결제금액</h3>
		  <%
		    if(orderProducts != null && !orderProducts.isEmpty()) {
		      int totalAmount = 0;
		      for(Cart product : orderProducts) {
		        totalAmount += product.getCartPrice() * product.getCartCount();
		      }
		  %>
		      <p><%= totalAmount %>원</p>
		  <%
		    }
		  %>
		  <hr>
		</section>
		
		<section id="payment-method">
		  <h3>결제 방법</h3>
		  <input type="checkbox" id="agree-all"> 이용약관 전체동의<br>
		  <input type="checkbox" class="agree-sub"> 개인정보 이용동의<br>
		  <input type="checkbox" class="agree-sub"> 제3자 정보제공 동의<br>
		</section>
		
		<div class="checkout-container">
		  <button class="checkout-button" onclick="submitOrder()">결제하기</button>
		</div>
		
		<script>
		  document.getElementById('agree-all').addEventListener('click', function () {
		    var checkboxes = document.querySelectorAll('.agree-sub');
		    for (var i = 0; i < checkboxes.length; i++) {
		      checkboxes[i].checked = this.checked;
		    }
		  });
		
		  function submitOrder() {
		    // JavaScript function to handle form submission.
		    document.forms['order-form'].submit();
		  }
		</script>

			<%@ include file="../common/footer.jsp" %>
	</div>
</body>
</html>