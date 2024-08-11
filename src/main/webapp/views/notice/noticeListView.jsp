<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice, com.kh.common.model.vo.PageInfo" %>
    
<%
	ArrayList<Notice> list = (ArrayList<Notice>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
  <meta charset="UTF-8">
  <title>공지사항</title>
  
  	<!-- Bootstrap 연동 코드 -->
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<!-- Popper JS -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
  <style>
  
    #wrap {
      width: 1000px;
      height: 975px;
      margin: auto;
      box-sizing: border-box;
    }
    
    .notice0 {
    	text-align : center;
    }
    
    #notice {
        display: inline-block; /* 가로로 정렬하기 위해 inline-block 사용 */
    }
    
	.outer {
		width : 800px;
		margin : auto;
		margin-top : 50px;
	}

	.list-area {text-align : center;}

	.list-area>tbody>tr:hover {

		/* background-color : lightgray; */
		cursor : pointer;
	}
	
	/* 기본 상태 */
    .form-control {
        border-color: #ccc; /* 회색 */
    }

    /* 클릭시 */
    .form-control:focus {
        box-shadow: 0 0 0 0.2rem #b5babe;
        border : 1px solid #b5babe;
    }
    
    /* 클릭시 */
    .custom-select:focus {
     box-shadow: 0 0 0 0.2rem #b5babe;
     border : 1px solid #b5babe;
       
  </style>
</head>

<body>
  <div id="wrap">
    <%@ include file="/views/common/header.jsp" %>
    <%@ include file="/views/common/navigator.jsp" %>
    
	
	<div class="outer">
		
		<br>
		<h2 align="center">공지사항</h2>
		<br>

		<!-- 이 자리에 글작성 버튼을 배치할 것 -->
		<!-- 로그인한 사용자 중에서도 관리자만 보여져야 하는 버튼 -->
		<% if(loginUser != null && loginUser.getMemberId().equals("admin") ) { %>
			<div align="right" style="width : 850px;">
				<a href="<%= contextPath %>/enrollForm.no" 
				   class="btn btn-secondary btn-sm">
					글작성
				</a>
				<br><br>
			</div>
		<% } %>
		
		<table
			   class="list-area table table-hover" 
			   align="center">

			<thead>
				<tr>
					<th>글번호</th>
					<th width="400">글제목</th>
					<th width="100">작성자</th>
					<th>조회수</th>
					<th width="130">작성일</th>
				</tr>
			</thead>
			<tbody>
				
				<% if(list.size() > 0) { %>
					<% for(Notice n : list){ %>
						<tr onclick="a()">
							<td><%= n.getNoticeNo() %></td>
							<td><%= n.getNoticeTitle() %></td>
							<td>admin</td>
							<td><%= n.getNoticeHit() %></td>
							<td><%= n.getNoticeRDate() %></td>
						</tr>
						
					<% } %>
						
					
				<% } else { %>
					<!-- 조회 결과가 하나도 없다면 -->
					<tr>
						<td colspan="5">
							 존재하는 공지사항이 없습니다.
						</td>
					</tr>
				<% } %>
				
				
			</tbody>

		</table>
		
		<script>
			// 클릭시 상세페이지로 이동하기
			$(function(){
				<% if(list.size() > 0){%>
					$(".list-area>tbody>tr").click(function(){
						let bno = $(this).children().eq(0).text();
						location.href = "<%= contextPath %>/detail.bo?bno=" + bno;
					});
				<% } %>
			});
		</script>
		
		
		
		<nav aria-label="Page navigation" style="text-align: center;">
		  <ul class="pagination" style="display: flex; justify-content: center; align-items: center;">
		    <% if(pi.getCurrentPage() > 1){ %>
		     <li class="page-item">
		       <a class="page-link" href="list.no?currentPage=<%= pi.getCurrentPage() - 1 %>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		       </a>
		     </li>
		    <% } %>
		    
		    <% for(int i = pi.getStartPage(); i <= pi.getEndPage(); i++){ %>
		     <% if(pi.getCurrentPage() != i){ %>
		      <li class="page-item"><a class="page-link" href="list.no?currentPage=<%= i %>"><%= i %></a></li>
		     <% } else { %>
		      <li class="page-item"><a class="page-link" style="color: black;"><%= i %></a></li>
		     <% } %>
		    <% } %>

		    <% if(pi.getCurrentPage() < pi.getMaxPage()){ %>
		     <li class="page-item">
		       <a class="page-link" href="list.no?currentPage=<%= pi.getCurrentPage() + 1 %>" aria-label="Next">
		         <span aria-hidden="true">&raquo;</span>
		       </a>
		     </li>
		    <% } %>
		  </ul>
		</nav>



		<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px">
			<tr>
				<td style="padding-left: 5px" class="align-middle">
					<input type="text" class="form-control" id="search" name="search" placeholder="검색어" value="<%= keyword %>">
				<td style="padding-left: 5px">
					<span>
						<button type="button" class="btn btn-outline-secondary" onclick="searchBtn()">검색</button>
					</span>
				</td>
			</tr>
		</table>
		
		<script>
			function searchBtn(){
				
				let queryString = "keyword=" + $("input[name=search]").val();
				
				
				location.href = "<%= contextPath %>/search.bo?" + queryString;
				
			}
			
			
		</script>
		
		<br><br>

	</div>
  
  <%@ include file="/views/common/footer.jsp" %>
  </div>
</body>

</html>