
<%@page import="com.kh.inquiry.model.service.InquiryService"%>
<%@page import="com.kh.inquiry.model.vo.Inquiry"%>
<%@page import="com.kh.order.model.service.OrderService"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="com.kh.order.model.vo.OrderItem"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	Member servletX_inquiry_user = (Member)request.getSession().getAttribute("loginUser");
	String inquiry_mId = servletX_inquiry_user.getMemberId();

	ArrayList<Inquiry> iList = new InquiryService().selectMyInquiry(inquiry_mId);
	
	// 화면에 보여질 페이지 정보를 응답데이터로 넘기기
	request.setAttribute("list", iList);

%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
<meta charset="UTF-8">
<title>Man In Black</title>
</head>
<body>

</body>
</html>