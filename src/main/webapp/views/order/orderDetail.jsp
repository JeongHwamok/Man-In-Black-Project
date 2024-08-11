<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="com.kh.order.model.vo.OrderItem"%>
<%@page import="com.kh.order.model.vo.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../member/servletX.jsp" %>
<% 
		
	ArrayList<OrderItem> orlist = (ArrayList<OrderItem>)request.getAttribute("list");


%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
	.order_outer {
            width: 800px;
            margin: auto;
            margin-top: 10px;
            text-align: center;
        }
	
    #order_h2 {
        text-align: center;
        margin-bottom: 30px;
    }

    #order-table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    #order-table th,
    #order-table td {
        border: 0px solid white;
        padding: 10px;
        border-bottom: 1px solid #ddd;

    }

    #order-table td {
        text-align: center;
        font-size: 12px;
    }

    #order-table th {
        background-color: black;
        color: white;
        font-size: 11px;
        font-weight: 900;
        text-align: center;

    }


    /* 상품이미지 깨지지 않게 비율맞춰서 가로,세로 길이 조정하기. */
    .product-image {
        width: 80px;
        height: 100px;

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

<body>

	<br>
	<div class="order_outer">

    <% if(orlist.isEmpty()) { %>
	    
	    <br> 	
        <h5 align="center">구매한 상품이 없습니다.</h5>
        
    <% } else { %>
        <h3 id="order_h2">주문 내역</h3>
        <table id="order-table">
            <thead>
                <tr>
                    <th>주문 번호</th>
                    <th>IMAGE</th>
                    <th>ITEM</th>
                    <th>PRICE</th>
                    <th>수량</th>
                    <th>주문일자</th>
                    <th>배송 상태</th>
                    <th>리뷰</th>
                </tr>
            </thead>
        
        <% for(OrderItem oi : orlist) { %>

	            <tbody>
	                <tr>
	                    <td><%= oi.getOrderItemNo() %></td>
	                    <td><img src="<%= oi.getImagePath() + oi.getImageChangeName() %>"
	                            class="product-image"></td>
	                    <td><%= oi.getProductName() %></td>
	                    <td><%= oi.getOrderItemPrice() * oi.getOrderItemQuantity() %>원</td>
	                    <td><%= oi.getOrderItemQuantity() %></td>
	                    <td><%= oi.getOrderDate() %></td>
	                    <td><%= oi.getOrderStatus() %></td>
	                    <td>
	                    	
	                        <a href="<%= request.getContextPath() %>/enrollform.re?orderItemNo=<%= oi.getOrderItemNo() %>&productImg=<%= oi.getImagePath() %>&productName=<%= oi.getProductName() %>&memberNo=<%= oi.getMemberNo() %>&productNo=<%= oi.getProductNo() %>&imageChangeName=<%= oi.getImageChangeName() %>" 
	                        			class="btn btn-outline-secondary btn-sm">
	                        			리뷰 작성
	                        </a>
	                    </td>
	                        
	                </tr>
	
	            </tbody>

        	<% } %>
        <% } %>
        </table> 

   	</div>
        <br>
        <!-- 페이지 더 추가 예정 -->
        <!-- 참고로 환불, 리뷰기능 더 작성가능 환불 요청 버튼은 그냥 넣어봄
         스타일 더 꾸며야함 -->

</body>
</html>