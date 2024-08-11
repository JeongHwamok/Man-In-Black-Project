<%@page import="com.kh.notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
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
                        <h1 class="dash-title">공지사항 리스트</h1>
                        <form class="form-inline" method="GET" action="searchNotice.ad">
                            <input type="text" name="searchName" class="form-control" placeholder="공지사항 제목 입력" style="margin-right: 5px;">
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
                                                <th scope="col">공지사항 번호</th>
                                                <th scope="col">게시글 종류</th>
                                                <th scope="col">제목</th>
                                                <th scope="col">내용</th>
                                                <th scope="col">조회수</th>
                                                <th scope="col">작성일</th>
                                                <th scope="col">상태값</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if(list.isEmpty()) { %>
                                                <tr>
                                                    <td colspan="9">존재하는 공지사항이 없습니다.</td>
                                                </tr>
                                            <% } else { %>
                                                <% for(Notice n : list) { %>
                                                    <tr>
                                                        <td><%=  n.getNoticeNo() %></td>
														<td><%=  n.getNoticeType() %></td>
														<td><%=  n.getNoticeTitle() %></td>
														<td><%=  n.getNoticeContent() %></td>
														<td><%=  n.getNoticeHit()%></td>
														<td><%=  n.getNoticeRDate()%></td>
                                                        
                                                        
                                       						
                                                        <% if (n.getNoticeStatus().equals("Y")){ %>    
                                                            <td>게시중</td>
                                                        <%} else if (n.getNoticeStatus().equals("N")){ %>
                                                            <td>삭제됨</td>
                                                        <%} %>
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
            <h5 class="modal-title" id="editStatusModalLabel">공지사항 수정</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <div class="modal-body">
            <form id="statusForm">
                <div class="form-group">
                <label for="noticeNo">공지번호</label>
                <input type="text" class="form-control" id="noticeNo" disabled>
                </div>

                <div class="form-group">
                    <label for="noticeType">게시글 종류</label>
                    <select class="form-control" id="noticeType">
                        <option value="일반">일반</option>
                        <option value="이벤트">이벤트</option>
                    </select>
                </div>
                <div class="form-group">
                <label for="noticeTitle">제목</label>
                <input type="text" class="form-control" id="noticeTitle">
                </div>
                <div class="form-group">
                <label for="noticeContent">내용</label>
                <input type="text" class="form-control" id="noticeContent">
                </div>
                <div class="form-group">
                <label for="memberStatus">상태값</label>
                <select class="form-control" id="noticeStatus">
                    <option value="Y">게시중</option>
                    <option value="N">삭제됨</option>
                </select>
                </div>
            </form>
            </div>
            <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            <button type="button" class="btn btn-primary" onclick="updateMemberStatus()">저장</button>
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
                var noticeNo = $(this).find('td:eq(0)').text()
                var noticeType = $(this).find('td:eq(1)').text();
                var noticeTitle = $(this).find('td:eq(2)').text();
                var noticeContent = $(this).find('td:eq(3)').text();
                var noticeStatus = $(this).find('td:last').text() === '게시중' ? 'Y' : 'N';
        
                $('#noticeNo').val(noticeNo);
                $('#noticeType').val(noticeType);
                $('#noticeTitle').val(noticeTitle);
                $('#noticeContent').val(noticeContent);
                $('#noticeStatus').val(noticeStatus);
        
                $('#editStatusModal').modal('show');
            });
        });
        
        function updateMemberStatus() {
            var noticeNo = $('#noticeNo').val();
            var noticeType = $('#noticeType').val();
            var noticeTitle = $('#noticeTitle').val();
            var noticeContent = $('#noticeContent').val();
            var noticeHit = $('#noticeHit').val();
            var noticeRDate = $('#noticeRDate').val();
            var noticeStatus = $('#noticeStatus').val();
        
            // AJAX를 사용하여 서버에 데이터를 전송
            $.ajax({
                url: "<%= request.getContextPath() %>/updateNotice.ad", // 서버 URL
                type: 'post', 
                data: {
                    noticeNo: noticeNo,
                    noticeType: noticeType,
                    noticeTitle: noticeTitle,
                    noticeContent: noticeContent,
                    noticeStatus: noticeStatus
                },
                success: function(response) {
                    // 성공 처리 로직
                    $('#editStatusModal').modal('hide');
                    alert('공지사항 업데이트 완료!');
                    location.reload();
                    
                    
                },
                error: function() {
                    alert('상태 업데이트 실패');
                }
            });
        }
        </script>
        <script>
		$(document).ready(function() {
		    $("#searchButton").click(function() {
		        var searchValue = $("#searchInput").val().toLowerCase();
		        $("table tbody tr").filter(function() {
		            $(this).toggle($(this).find('td:eq(1)').text().toLowerCase().indexOf(searchValue) > -1)
		        });
		    });
		});
	</script>
</body>
</html>
