<%@page import="com.kh.review.model.vo.Review"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");

%>    
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
<meta charset="UTF-8">
<title>Man In Black</title>

	<style>
        @font-face {
            font-family: 'Hahmlet-Regular';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/Hahmlet-Regular.woff2') format('woff2');
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: 'Hahmlet-Regular';
        }

        .review_outer {
            width: 1000px;
            margin: auto;
            margin-top: 10px;
            text-align: center;
        }

        #review_h2 {
        text-align: left;
        margin-bottom: 30px;
        }

        #review-tb {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        #review-tb th,
        #review-tb td {
            border: 0px solid white;
            padding: 10px;

        }

        #review-table td {
            text-align: center;
            font-size: 11px;
            border-bottom: 1px solid #ddd;
        }

        #tb_head {
            background-color: black;
            color: white;
            font-size: 10px;
            font-weight: 900;
            text-align: center;

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
	<br>
    <div class="review_outer">
		<%@ include file="../common/header.jsp" %>
        <%@ include file="../common/navigator.jsp" %>

            <br>

	        <h5 id="review_h2">상품 전체리뷰</h5>
	        
	        <table id="review-tb">
	            <thead>
	                <tr id="tb_head">
	                    <th>IMAGE</th>
	                    <th width="160px">상품명</th>
	                    <th width="80px">사이즈</th>
	                    <th width="70">수량</th>
	                    <th width="150">리뷰작성일</th>
	                    <th>리뷰내용</th>
	                </tr>
	            </thead>
					<% for(Review r : list) { %>
					
			            <tbody>
		                    
			                <tr>
			                    <td>
			                    	<img src="<%= r.getReviewFilePath() %><%= r.getReviewFileName() %>" width="100" height="100">
			                    </td>
			                    <td><%= r.getProductName() %></td>
			                    <td><%= r.getSizeName() %></td>
			                    <td><%= r.getOrderItemQuantity() %></td>
			                    <td><%= r.getReviewDate() %></td>
			                    <td><%= r.getReviewContent() %></td>
			                </tr>
			                <tr>
			                	<td colspan="7"><hr></td>
			                </tr>
			            </tbody>
			    	<% } %>
	
		    	
	        </table>
   
            <%@ include file="../common/footer.jsp" %>


    </div>
</body>
</html>