<%@page import="com.kh.inquiry.model.vo.Inquiry"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../member/servletXInquiry.jsp" %>    
<% 
		
	ArrayList<Inquiry> iiList = (ArrayList<Inquiry>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
<meta charset="UTF-8">
<title>Man In Black</title>
<style>
	.inquiry_outer {
            width: 800px;
            margin: auto;
            margin-top: 10px;
            text-align: center;
        }
        
     #inquiry_h2 {
        text-align: center;
        margin-bottom: 30px;
    }
     

	#review_tb {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    #review_tb th,
    #review_tb td {
        border: 0px solid white;
        padding: 10px;
        border-bottom: 1px solid #ddd;

    }

    #review_tb td {
        text-align: center;
        font-size: 12px;
    }

    #review_tb th {
        background-color: black;
        color: white;
        font-size: 11px;
        font-weight: 900;
        text-align: center;

    }
    
    #tbodya {
    	text-decoration: none; 
    	color: black;
    }


</style>
</head>


<body>
	<br>
	<div class="inquiry_outer">
		<% if(iiList.isEmpty()) { %>
		    
		    <br> 	
	        <h5 align="center">문의내역이 없습니다.</h5>
	        
	    <% } else { %>
		
			<h3 id="inquiry_h2">문의내역</h3>
			
			<table id="review_tb">
			    <thead>
			        <tr>
			            <th width="50px">번호</th>
			            <th width="120px">상품명</th>
			            <th width="150">문의 제목</th>
			            <th width="350px">문의 내용</th>
			            <th width="100px">작성일</th>
						<th width="50px">삭제</th>
			        </tr>
			    </thead>
			
					<% for(Inquiry i : iiList) { %>
				        <tbody>
				            <tr height="70px">
				                <td><%= i.getInquiryNo() %></td>
				                <td><%= i.getProductName() %></td>
				                <td><%= i.getInquiryTitle() %></td>
				                <td><%= i.getInquiryComment() %></td>
				                <td><%= i.getInquiryDate() %></td>
								<td><a id="tbodya" href="<%= request.getContextPath() %>/delete.iq?ino=<%= i.getInquiryNo() %>" 
										class="btn btn-outline-secondary btn-sm">X</a></td>
				            </tr>
				            <tr style="border-right: 1px solid lightgray;">
				                <th>답변내용</th>
				                <td colspan="4" style="text-align: left;">
				                	<% if(i.getInquiryAnswer() == null) { %>
				                		아직 답변이 작성되지 않았습니다.
				                	<% } else { %>
				                		<%= i.getInquiryAnswer() %>
				                	<% } %>
				                </td>
								<td></td>
				            </tr>
				            <tr><td colspan="6"></td></tr>
							
				
				        </tbody>
			        
			        <% } %>
			
			</table>
		<% } %>
		
	</div>

</body>

</html>