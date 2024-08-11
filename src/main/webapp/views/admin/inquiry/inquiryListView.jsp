<%@page import="java.util.HashMap"%>
<%@page import="com.kh.inquiry.model.vo.Inquiry"%>
<%@page import="java.sql.Date"%>
<%@page import="com.kh.review.model.vo.Review"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%
	ArrayList<Inquiry> list = (ArrayList<Inquiry>)request.getAttribute("list");

	ArrayList<Member> mlist = (ArrayList<Member>)request.getAttribute("mlist");
	
	HashMap<Integer, String> memberMap = new HashMap<>();
	for (Member m : mlist) {
		memberMap.put(m.getMemberNo(), m.getMemberId());
	}


%>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600|Open+Sans:400,600,700" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/spur.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
    <script src="resources/js/chart-js-config.js"></script>
    <title>관리자페이지</title>
    <style>
	tr{text-align: center;}
	td{text-align: center;}
	table>tbody>tr:hover {

		background-color : lightgray;
		cursor : pointer;
	}
</style>
</head>
<body>
	<%@ include file="../common/adminCommon.jsp" %>
    <div class="dash">
        <div class="dash-nav dash-nav-dark">
            <header>
                <a href="admin.ad" class="menu-toggle">
                    <i class="fas fa-bars"></i>
                </a>
                <a href="admin.ad" class="spur-logo"><i class="fas fa-bolt"></i> <span>관리자 페이지</span></a>
            </header>
            <nav class="dash-nav-list">
                <a href="<%=request.getContextPath()%>" class="dash-nav-item">
                    <i class="fas fa-home"></i>메인페이지로
                </a>
                <a href="admin.ad" class="dash-nav-item">
                    <i class="fas fa-home"></i>대시보드
                </a>
                <a href="memberList.ad" class="dash-nav-item">
                    <i class="fas fa-chart-bar"></i>회원관리
                </a>
                <a href="productList.ad" class="dash-nav-item">
                    <i class="fas fa-chart-bar"></i>상품관리
                </a>
                <a href="orderList.ad" class="dash-nav-item">
                    <i class="fas fa-chart-bar"></i>주문관리
                </a>
                <a href="reviewList.ad" class="dash-nav-item">
                    <i class="fas fa-chart-bar"></i>리뷰관리
                </a>
                <a href="inquiryList.ad" class="dash-nav-item">
                    <i class="fas fa-chart-bar"></i>문의답변
                </a>
                <a href="noticeList.ad" class="dash-nav-item">
                    <i class="fas fa-chart-bar"></i>공지관리
                </a>
            </nav>
        </div>
        <div class="dash-app">
            <main class="dash-content">
                <div class="container-fluid">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h1 class="dash-title">문의 리스트</h1>
                        <form class="form-inline" method="GET" action="searchInquiry.ad">
                            <input type="text" name="searchName" class="form-control" placeholder="문의 제목 입력" style="margin-right: 5px;">
                            <button type="submit" class="btn btn-primary">검색</button>
                        </form>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card spur-card">
                                <div class="card-body ">
									<table class="table table-in-card">
                                        <thead>
                                            <tr>
                                                <th scope="col">문의번호</th>
                                                <th scope="col">문의제목</th>
                                                <th scope="col">문의내용</th>
                                                <th scope="col">문의일</th>
                                                <th scope="col">답변내용</th>
                                                <th scope="col">답변일</th>
                                                <th scope="col">회원아이디</th>
                                                <th scope="col">상품번호</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if(list.isEmpty()) { %>
                                                <tr>
                                                    <td colspan="9">존재하는 문의가 없습니다.</td>
                                                </tr>
                                            <% } else { %>
                                                <% for(Inquiry i : list) { %>
                                                    <tr>
                                                        <td><%=  i.getInquiryNo() %></td>
                                                        <td><%=  i.getInquiryTitle() %></td>
                                                        <td><%=  i.getInquiryComment() %></td>
                                                        <td><%=  i.getInquiryDate() %></td>
														<td>
														    <% String answer = i.getInquiryAnswer(); %>
														    <%= (answer == null || answer.isEmpty()) ? "미답변" : (answer.length() > 10 ? answer.substring(0, 10) + "..." : answer) %>
														</td>
														<td>
														    <% 
														        Date answerDate = i.getInquiryAnswerDate(); 
														        String answerDateStr = (answerDate == null) ? "미답변" : new java.text.SimpleDateFormat("yyyy-MM-dd").format(answerDate);
														    %>
														    <%= answerDateStr %>
														</td>
                                                        <% 
                                                            String memberId = memberMap.get(i.getMemberNo());
                                                        %>
                                                        <td><%= memberId %></td>
                                                        <td><%=  i.getProductNo()%></td>
                                                    </tr>
                                                <% } %>
                                            <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
 <!-- modal -->
    <div class="modal fade" id="editStatusModal" tabindex="-1" role="dialog" aria-labelledby="editStatusModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title" id="editStatusModalLabel">문의 정보 수정</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <div class="modal-body">
            <form id="statusForm">
                <div class="form-group">
                <label for="inquiryNo">문의번호</label>
                <input type="text" class="form-control" id="inquiryNo" disabled>
                </div>
                <div class="form-group">
                <label for="inquiryTitle">문의제목</label>
                <input type="text" class="form-control" id="inquiryTitle" disabled>
                </div>
                <div class="form-group">
                <label for="inquiryComment">문의내용</label>
                <input type="text" class="form-control" id="inquiryComment" disabled>
                </div>
                <div class="form-group">
                <label for="memberNo">회원아이디</label>
                <input type="text" class="form-control" id="memberNo" disabled>
                </div>
                <div class="form-group">
                <label for="productNo">상품번호</label>
                <input type="text" class="form-control" id="productNo" disabled>
                </div>
                <div class="form-group">
                <label for="inquiryAnswer">답변 내용</label>
                <textarea class="form-control" id="inquiryAnswer" style="height: 200px; text-align: left; vertical-align: top;"></textarea>
                </div>
            </form>
            </div>
            <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            <button type="button" class="btn btn-primary" onclick="updateInquiry()">저장</button>
            </div>
        </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="resources/js/spur.js"></script>
    <script>
	$(document).ready(function() {
	    $('tbody tr').click(function() {
	        var inquiryNo = $(this).find('td:eq(0)').text();
	        var inquiryTitle = $(this).find('td:eq(1)').text();
	        var inquiryComment = $(this).find('td:eq(2)').text();
	        var inquiryDate = $(this).find('td:eq(3)').text();
	        var inquiryAnswer = $(this).find('td:eq(4)').text();
	        var inquiryAnswerDate = $(this).find('td:eq(5)').text();
	        var memberNo = $(this).find('td:eq(6)').text();
	        var productNo = $(this).find('td:eq(7)').text();
	
	        $('#inquiryNo').val(inquiryNo);
	        $('#inquiryTitle').val(inquiryTitle);
	        $('#inquiryComment').val(inquiryComment);
	        $('#inquiryDate').val(inquiryDate);
	        $('#inquiryAnswerDate').val(inquiryAnswerDate);
	        $('#memberNo').val(memberNo);
	        $('#productNo').val(productNo);
	        if (!inquiryAnswer.includes("미답변")) {
	            $('#inquiryAnswer').val(inquiryAnswer.trim());
	        } else {
	            $('#inquiryAnswer').val("");
	        }
	        $('#editStatusModal').modal('show');
	    });
	});
	
	function updateInquiry() {
	    var inquiryNo = $('#inquiryNo').val();
	    var inquiryAnswer = $('#inquiryAnswer').val();
	    var inquiryAnswerDate = $('#inquiryAnswerDate').val();
	
	    $.ajax({
	        url: "<%= request.getContextPath() %>/updateInquiry.ad",
	        type: 'post',
	        data: {
	            inquiryNo: inquiryNo,
	            inquiryAnswer: inquiryAnswer,
	            inquiryAnswerDate: inquiryAnswerDate
	        },
	        success: function(response) {
	            $('#editStatusModal').modal('hide');
	            alert('문의 상태 업데이트 완료!');
	            location.reload();
	        },
	        error: function() {
	            alert('상태 업데이트 실패');
	        }
	    });
	}
	</script>
</body>
</html>
