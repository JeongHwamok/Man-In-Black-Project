<%@page import="java.util.HashMap"%>
<%@page import="com.kh.order.model.vo.Order"%>
<%@page import="com.kh.inquiry.model.vo.Inquiry"%>
<%@page import="java.sql.Date"%>
<%@page import="com.kh.review.model.vo.Review"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%
	ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("list");
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
                        <h1 class="dash-title">주문내역 리스트</h1>
                        <form class="form-inline" method="GET" action="searchOrder.ad">
                            <input type="text" name="searchName" class="form-control" placeholder="주문번호 입력" style="margin-right: 5px;">
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
                                                <th scope="col">주문번호</th>
                                                <th scope="col">주문상태</th>
                                                <th scope="col">주문일</th>
                                                <th scope="col">배송요청사항</th>
                                                <th scope="col">주문총액</th>
                                                <th scope="col">환불여부</th>
                                                <th scope="col">결제코드</th>
                                                <th scope="col">배송지</th>
                                                <th scope="col">회원아이디</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if(list.isEmpty()) { %>
                                                <tr>
                                                    <td colspan="9">존재하는 주문이 없습니다.</td>
                                                </tr>
                                            <% } else { %>
                                                <% for(Order o : list) { %>
                                                    <tr>
                                                        <td><%= o.getOrderNo() %></td>
                                                        <td><%= o.getOrderStatus() %></td>
                                                        <td><%= o.getOrderDate() %></td>
                                                        <td><%= o.getOrderMessage() %></td>
                                                        <td><%= o.getOrderPrice()%></td>
                                                        <td><%= o.getOrderCancle() %></td>
                                                        <td><%= o.getOrderCode() %></td>
                                                        <td><%= o.getOrderAddress() %></td>
                                                        <% 
                                                            String memberId = memberMap.get(o.getMemberNo());
                                                        %>
                                                        <td><%= memberId %></td>
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
	<!-- Modal -->
	<div class="modal fade" id="editOrderModal" tabindex="-1" role="dialog" aria-labelledby="editOrderModalLabel" aria-hidden="true">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="editOrderModalLabel">주문 상세정보 조회/수정</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                <form id="orderForm">
	                    <div class="form-group">
	                        <label for="orderNo">주문번호</label>
	                        <input type="text" class="form-control" id="orderNo" disabled>
	                    </div>
						<div class="form-group">
						    <label for="orderStatus">주문상태</label>
						    <select id="orderStatus" class="form-control">
						        <option value="배송준비중">배송준비중</option>
						        <option value="배송중">배송중</option>
						        <option value="배송완료">배송완료</option>
						        <option value="환불처리중">환불처리중</option>
						        <option value="환불완료">환불완료</option>
						    </select>
						</div>
	                    <div class="form-group">
	                        <label for="orderDate">주문일</label>
	                        <input type="text" class="form-control" id="orderDate" disabled>
	                    </div>
	                    <div class="form-group">
	                        <label for="orderPrice">주문총액</label>
	                        <input type="text" class="form-control" id="orderPrice" disabled>
	                    </div>
	                    <div class="form-group">
	                        <label for="orderCancle">환불여부</label>
	                        <select id="orderCancle" class="form-control">
	                            <option value="N">N</option>
	                            <option value="Y">Y</option>
	                        </select>
	                    </div>
	                    <div class="form-group">
	                        <label for="orderAddress">배송지</label>
	                        <textarea class="form-control" id="orderAddress" rows="2"></textarea>
	                    </div>
	                </form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	                <button type="button" class="btn btn-primary" onclick="updateOrder()">저장</button>
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
	        var orderNo = $(this).find('td:eq(0)').text();
	        var orderStatus = $(this).find('td:eq(1)').text();
	        var orderDate = $(this).find('td:eq(2)').text();
	        var orderPrice = $(this).find('td:eq(4)').text();
	        var orderCancle = $(this).find('td:eq(5)').text();
	        var orderAddress = $(this).find('td:eq(7)').text();
	
	        $('#orderNo').val(orderNo);
	        $('#orderStatus').val(orderStatus);
	        $('#orderDate').val(orderDate);
	        $('#orderPrice').val(orderPrice);
	        $('#orderCancle').val(orderCancle);
	        $('#orderAddress').val(orderAddress);
	
	        $('#editOrderModal').modal('show');
	    });
	});
	
	function updateOrder() {
	    var orderNo = $('#orderNo').val();
	    var orderStatus = $('#orderStatus').val();
	    var orderCancle = $('#orderCancle').val();
	    var orderAddress = $('#orderAddress').val();
	
	    $.ajax({
	        url: "<%= request.getContextPath() %>/updateOrder.ad",
	        type: 'post',
	        data: {
	            orderNo: orderNo,
	            orderStatus: orderStatus,
	            orderCancle: orderCancle,
	            orderAddress: orderAddress,
	        },
	        success: function(response) {
	            $('#editOrderModal').modal('hide');
	            alert('주문 상태 업데이트 완료!');
	            location.reload();
	        },
	        error: function() {
	            alert('주문 업데이트 실패');
	        }
	    });
	}
	</script>
</body>
</html>
