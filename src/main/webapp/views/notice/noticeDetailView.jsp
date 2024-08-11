<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.notice.model.vo.Notice" %>
<% Notice n = (Notice) request.getAttribute("n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 상세조회</title>
    <style>
		#wrap {
     	 width: 1000px;
     	 height: 975px;
    	  margin: auto;
    	  box-sizing: border-box;
   		 }	

        body {
            font-family: 'Noto Sans KR', sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 800px;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 20px;
            text-align: left;
            border-bottom: 1px solid #dee2e6;
        }
        th {
            background-color: #f1f1f1;
        }
        .post-content {
            padding: 20px;
            line-height: 1.6;
        }
        .post-content p {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
	<div id="wrap">	
	<%@ include file="/views/common/header.jsp" %>
   	<%@ include file="/views/common/navigator.jsp" %>
    <br><br>
	<div class="container">
		
        <table>
            <tr>
                <th colspan="2" style="font-size: 1.5rem; padding: 30px;">공지사항 상세정보</th>
            </tr>
            <tr>
                <td style="width: 25%;">게시글 번호 : </td>
                <td><%= n.getNoticeNo() %></td>
            </tr>
            <tr>
                <td>게시글 종류 :</td>
                <td><%= n.getNoticeType() %></td>
            </tr>
            <tr>
                <td>제목 :</td>
                <td><%= n.getNoticeTitle() %></td>
            </tr>
            <tr>
                <td>작성자 :</td>
                <td>관리자</td>
            </tr>
            <tr>
                <td>작성일 :</td>
                <td><%= n.getNoticeRDate() %></td>
            </tr>
            <tr>
                <td>조회수 :</td>
                <td><%= n.getNoticeHit() %></td>
            </tr>
            <tr>
                <td colspan="2" class="post-content">
                    <strong><%= n.getNoticeContent() %></strong><br>
                </td>
            </tr>
        </table>
        
	</div>
    <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<br><br><br><br>
	<%@ include file="/views/common/footer.jsp" %>
</div>
</body>
</html>