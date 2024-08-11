<%@page import="com.kh.cart.model.vo.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.text.DecimalFormat"%>
<% 
  Member oruser = (Member)session.getAttribute("loginUser");
  String memberName = oruser.getMemberName();
  String memberPhone = oruser.getMemberPhone();
  String memberAddress = oruser.getMemberAddress();
  
  ArrayList<Cart> list = (ArrayList<Cart>)request.getAttribute("list");
  int total = Integer.parseInt(request.getAttribute("total").toString());
  DecimalFormat formatter = new DecimalFormat("#,###"); // 가격 , 붙여서 포메팅해주는 객체


  // System.out.println(list);
  %>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
	<meta charset="UTF-8">
	<title>주문</title>
	<style>
		#wrap {
			width: 1000px;
			height: 975px;
			margin: auto;
			box-sizing: border-box;
		}

		
		.labelC {

		display: inline-block;
		width: 130px;
		padding-left: 30px;
		}

		.labelI {
			border: 1px solid gray;
			width: 300px;
		}

		.orca_tb {
			width: 100%;
		}

		.orca_tb td{
			vertical-align: middle;
		}

		

		.thtd {
			font-size: 17px;

		}
	</style>
</head>
<body>
	<div id="wrap">
		<%@ include file="../common/header.jsp" %>
			<%@ include file="../common/navigator.jsp" %>
			<br>
	<form id="payForm" action="<%= contextPath %>/order.caor" method="post">
		<section id="order-product-info">
		    <h2>주문 상품 정보</h2>
		    <% if(list.isEmpty()) { %>
		    	 <h5 style="text-align: center">주문할 상품이 없습니다.</h5>
		         <hr>
		    
		    <% } %>
			
				<table class="orca_tb table table-hover" style="text-align: center;">
					<tr style="background-color: black; color:white;">
						<th width="160px">이미지</th>
						<th width="400px">상품명</th>
						<th width="160px">수량</th>
						<th width="160px">사이즈</th>
						<th>가격</th>


					</tr>

				<% for(Cart c : list) { %>	
					<tr>
						<td style="vertical-align: middle;"><img src="<%= c.getImagePath() + c.getImageChangeName() %>" width="150px"></td>
						<td style="vertical-align: middle;" class="thtd"><b><%= c.getProductName() %></b></td>
						<td style="vertical-align: middle;" class="thtd"><%= c.getCartCount() %></td>
						<td style="vertical-align: middle;" class="thtd"><%= c.getSizeName() %></td>
						<td style="vertical-align: middle;" class="thtd">￦<%= formatter.format(c.getCartPrice()*c.getCartCount()) %></td>
					</tr>
					<% } %>
				</table>
		    	
		    	 
		    	
		    
		</section>

			<hr>

			<br>
		<h4>배송지 정보</h4>
			<section id="delivery-info">
			  <label class="labelC" for="name">이름:</label>
			  <input class="labelI" type="text" id="name" name="name" placeholder="이름을 입력하세요" value="<%= memberName %>" readonly><br>
			  <label class="labelC" for="phone">연락처:</label>
			  <input class="labelI" type="text" id="phone" name="phone" placeholder="연락처를 입력하세요" value="<%= memberPhone %>" readonly><br>
			  <label class="labelC" for="address">배송지 주소:</label>
			  <input class="labelI" type="text" id="address" name="address" placeholder="주소를 입력하세요" value="<%= memberAddress %>" readonly>
			  
			</section>
			<br>

			<hr>
			
			<br>
			<section id="delivery-request">
				<h4 style="display: inline-block;">배송 요청사항</h4>
				&nbsp;&nbsp;
				<select id="delivery-options" name="deliveryOptions">
			    <option value="none" selected>배송 요청사항을 선택해주세요</option>
			    <option value="leave-at-door">문 앞에 놓아주세요</option>
			    <option value="ring-bell">벨 누르시고 놓아주세요</option>
			    <option value="direct-handover">직접 전달해주세요</option>
			  </select>
			 </section>
			 <br>
			<hr>
			
			<br>
			<section id="payment-amount">
			  <h4>결제금액</h4>
			  <p style="font-size: 40px;">￦<%= formatter.format(total) %></p>
			  <input type="hidden" name="total" value="<%= total %>">
			</section>
			<br>

			<hr>
			
			<br>
			<section id="payment-method">
			  <h4>약관 동의</h4>
			  <input type="checkbox" id="agree-all"> 이용약관 전체동의<br>
			  <input type="checkbox" class="agree-sub"> 개인정보 이용동의<br>
			  <input type="checkbox" class="agree-sub"> 제3자 정보제공 동의<br>
			</section>
			
			<br><br>
			<div class="checkout-container" align="center">
			  <button type="submit" class="checkout-button btn btn-dark" onclick="verifyCheckbox(); return false;">결제하기</button>
			</div>

			<br><br>
			
		</form>
				<%@ include file="../common/footer.jsp" %>
		</div>
	
	<script>
	
		$(document).ready(function() {
		    // Toggle all sub-checkboxes when "전체동의" checkbox is clicked
		    $("#agree-all").click(function() {
		        var isChecked = $(this).is(":checked");
		        $(".agree-sub").prop("checked", isChecked);
		    });
		
		    // Check if all sub-checkboxes are checked when a sub-checkbox is clicked
		    $(".agree-sub").click(function() {
		        var allChecked = $(".agree-sub").length === $(".agree-sub:checked").length;
		        $("#agree-all").prop("checked", allChecked);
		    });
		});
				
		function verifyCheckbox() {
			
			if($("input[type=checkbox]:checked").length == 3){
				var IMP = window.IMP;
				IMP.init('imp70212867');
				var result = payment();
			} else {
				alert("약관에 동의하지 않으셨습니다.");
				return false;
			}	
		}
		
		async function payment(){
			const response = await PortOne.requestPayment(
				{
					// Store ID 설정
					pg: 'kakaopay',
					pay_method: 'card',
					storeId: "store-7e7b8b35-0e5d-454d-9262-238566d87dbb",
					// 채널 키 설정
					channelKey: "channel-key-81eea7e0-1d2f-4068-9b3a-a92a5e8b5866",
					paymentId: "payment-" + crypto.getRandomValues(new BigUint64Array(1)),
					orderName: "MANINBLACK",
					totalAmount: <%= total %>,
					currency: "CURRENCY_KRW",
					 payMethod: "EASY_PAY"
				})
				
				if (response.code != null) {
				    // 오류 발생
				    
				    console.log(${SERVER_BASE_URL});
				    return alert(response.message);
				
				}
				console.log(response);
				var paymentIdInput = document.createElement("input");
				paymentIdInput.setAttribute("type", "hidden");
				paymentIdInput.setAttribute("name", "paymentId");
				paymentIdInput.setAttribute("value", response.paymentId);

				document.getElementById("payForm").appendChild(paymentIdInput);
				
				document.getElementById("payForm").submit();
					
				
			}
	</script>
</body>
</html>