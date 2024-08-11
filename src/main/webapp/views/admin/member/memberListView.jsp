<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
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
                <a href="qnaList.ad" class="dash-nav-item">
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
                        <h1 class="dash-title">회원 리스트</h1>
                        <form class="form-inline" method="GET" action="searchMember.ad">
                            <input type="text" name="searchName" class="form-control" placeholder="회원 이름 입력" style="margin-right: 5px;">
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
                                                <th scope="col">회원번호</th>
                                                <th scope="col">아이디</th>
                                                <th scope="col">비밀번호</th>
                                                <th scope="col">이름</th>
                                                <th scope="col">전화번호</th>
                                                <th scope="col">이메일</th>
                                                <th scope="col">생년월일</th>
                                                <th scope="col">주소</th>
                                                <th scope="col">가입일</th>
                                                <th scope="col">상태값</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if(list.isEmpty()) { %>
                                                <tr>
                                                    <td colspan="9">존재하는 회원이 없습니다.</td>
                                                </tr>
                                            <% } else { %>
                                                <% for(Member m : list) { %>
                                                    <tr>
                                                        <td><%=  m.getMemberNo() %></td>
                                                        <td><%=  m.getMemberId()%></td>
                                                        <td><%=  m.getMemberPwd()%></td>
                                                        <td><%=  m.getMemberName()%></td>
                                                        <td><%=  m.getMemberPhone()%></td>
                                                        <td><%=  m.getMemberEmail()%></td>
                                                        <td><%=  m.getMemberBirthDate()%></td>
                                                        <td><%=  m.getMemberAddress()%></td>
                                                        <td><%=  m.getMemberRegDate()%></td>
                                                        
                                                        
                                       						
                                                        <% if (m.getMemberStatus().equals("Y")){ %>    
                                                            <td>가입중</td>
                                                        <%} else if (m.getMemberStatus().equals("N")){ %>
                                                            <td>탈퇴</td>
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
            <h5 class="modal-title" id="editStatusModalLabel">회원 상태 수정</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <div class="modal-body">
            <form id="statusForm">
                <div class="form-group">
                <label for="memberNo">회원번호</label>
                <input type="text" class="form-control" id="memberNo" disabled>
                </div>
                <div class="form-group">
                <label for="memberId">아이디</label>
                <input type="text" class="form-control" id="memberId" disabled>
                </div>
                <div class="form-group">
                <label for="memberName">이름</label>
                <input type="text" class="form-control" id="memberName" disabled>
                </div>
                <div class="form-group">
                <label for="memberStatus">상태</label>
                <select class="form-control" id="memberStatus">
                    <option value="Y">가입중</option>
                    <option value="N">탈퇴</option>
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
                var memberNo = $(this).find('td:eq(0)').text();
                var memberId = $(this).find('td:eq(1)').text();
                var memberName = $(this).find('td:eq(3)').text();
                var memberStatus = $(this).find('td:last').text() === '가입중' ? 'Y' : 'N';
        
                $('#memberNo').val(memberNo);
                $('#memberId').val(memberId);
                $('#memberName').val(memberName);
                $('#memberStatus').val(memberStatus);
        
                $('#editStatusModal').modal('show');
            });
        });
        
        function updateMemberStatus() {
            var memberId = $('#memberId').val();
            var memberStatus = $('#memberStatus').val();
            if (memberId.toLowerCase() === "admin") {
                alert("관리자는 회원 탈퇴를 할 수 없습니다");
            } else {
            // AJAX를 사용하여 서버에 데이터를 전송
            $.ajax({
                url: "<%= request.getContextPath() %>/updateMember.ad",
                type: 'post',
                data: {
                    memberId: memberId,
                    memberStatus: memberStatus
                },
                success: function(response) {
                    // 성공 처리 로직
                    $('#editStatusModal').modal('hide');
                    alert('회원정보 업데이트 완료!');
                    location.reload();
                },
                error: function() {
                    alert('상태 업데이트 실패');
                }
            	});
            }
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
