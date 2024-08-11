<%@page import="com.kh.inquiry.model.vo.Inquiry"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	ArrayList<Inquiry> idlist = (ArrayList<Inquiry>)request.getAttribute("list");

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

        .inquiryDetailOuter {
            width: 1000px;
            margin: auto;
            box-sizing: border-box;
            /* border: 1px solid ; */
            text-align: center;
        }

        #inq_tb {
            width: 80%;
        }

        

        #inq_tb td { text-align: left;}
        #reply-area textarea { width: 100%;}
        
        #reply_tb tbody {
        				height: 50px;
        				text-align: left;}

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

    <div class="inquiryDetailOuter">

        <div class="include">
            <%@ include file="../common/header.jsp" %>
            <%@ include file="../common/navigator.jsp" %>
        </div>

        <div>
            <br>
            <h5>상품문의</h5>
            <br>
            <table border="1" align="center" id="inq_tb">
                <tr>
                    <th width="130px">상품명</th>
                    <td><%= idlist.get(0).getProductName() %></td>
                    <th>아이디</th>
                    <td><%= idlist.get(0).getMemberId() %></td>
                </tr>
                <tr>
                    <th>상품문의 제목</th>
                    <td colspan="3"><%= idlist.get(0).getInquiryTitle() %></td>
                </tr>
                <tr height="400px">
                    <th>상품문의 내용</th>
                    <td colspan="3" style="vertical-align: top;"><%= idlist.get(0).getInquiryComment() %></td>
                </tr>
            </table>

        </div>
        
        <br><br>
        
        <!-- 댓글창 -->
        <div id="reply-area">
			<table align="center" width="80%" id="reply_tb">

				<thead>
					<% if(loginUser != null && loginUser.getMemberId().equals("admin")) { %>
					
						<!-- 로그인이 되어있을 경우 -->
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea id="replyContent" cols="50"
										  rows="3" 
										  style="resize : none;"></textarea>
							</td>
							<td>
								<button type="button" class="btn btn-secondary btn-sm"
										onclick="insertReply();">
									댓글등록
								</button>
							</td>
						</tr>
						
					<% } else { %>
	
						<!-- 로그인이 안되어있을 경우 -->
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea id="" cols="50" 
										  rows="3"
										  style="resize : none;"
										  readonly>관리자만 이용 가능한 서비스 입니다.</textarea>
							</td>
							<td>
								<button type="button" class="btn btn-secondary btn-sm"
										disabled>
									댓글등록
								</button>
							</td>
						</tr>
					<% } %>
				</thead>
				<tbody>
				</tbody>

			</table>
		</div>
		
		<script>
			
			// 게시글 상세조회 페이지에서
			// 게시글 상세조회 페이지가 다 로딩된 이후에
			// 해당 게시글에 딸린 댓글들만 조회할 수 있는 요청을 ajax 로 보내기
			
			$(function() {
				
				selectReplyList();
				
				// 1초 간격마다 selectReplyList 함수를 실행			
				setInterval(selectReplyList, 1000);
				
			});
			
			// 댓글작성 요청용 함수
			function insertReply() {
				
				$.ajax({
					url : "<%= contextPath %>/rinsert.iq",
					type : "post",
					data : {
						replyContent : $("#replyContent").val(),
						ino : <%= idlist.get(0).getInquiryNo() %> // ,
						<%-- userNo : <%= loginUser.getUserNo() %> --%>
						// > 로그인 전 게시글 상세보기 페이지 요청 시
						//   loginUser 가 null 이므로 NullPointerException 발생
					},
					success : function(result) {
						
						// console.log(result);
						
						if(result > 0) { // 성공
							
							// 갱신된 댓글 리스트를 조회
							selectReplyList();
							// > 화면이 깜빡거리지 않고도 새로고침 효과를 주기 위해
							
							// textarea 초기화
							$("#replyContent").val("");
						}
						
					},
					error : function() {
						console.log("댓글 작성용 ajax 통신 실패!");
					}
				});
				
			}
			
			// 댓글목록 조회 요청용 함수
			function selectReplyList() {
				
				$.ajax({
					url : "<%= contextPath %>/rlist.iq",
					type : "get",
					data : {ino : <%= idlist.get(0).getInquiryNo() %>},
					success : function(list) {
						
						// console.log(list);
						
						let resultStr = "";
						
						for(let i in list) {
							// i : 0, 1, 2, ..., 마지막인덱스수
							
							resultStr += "<tr>";
							if (list[i].inquiryAnswer == null) {
							    resultStr += "<td colspan='3' align='center'>답변이 아직 작성되지 않았습니다</td>";
							} else {
							    resultStr += "<td>문의답변 )</td>"
							                + "<td>" + list[i].inquiryAnswer + "</td>"
							                + "<td>" + list[i].inquiryAnswerDate + "</td>";
							}
							resultStr += "</tr>";
						} 
						
						$("#reply-area tbody").html(resultStr);
						
					},
					error : function() {
						console.log("댓글리스트 조회용 ajax 통신 실패!");
					}
				});
				
			}

			
			
			
			
		</script>
		
		
		
        

    </div>
</body>
</html>