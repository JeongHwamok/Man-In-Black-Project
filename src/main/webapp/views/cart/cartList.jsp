<%@page import="com.kh.cart.model.vo.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    ArrayList<Cart> list = (ArrayList<Cart>)request.getAttribute("list");

	// System.out.println(list);
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <title>장바구니</title>
    <style>
        #wrap {
            width: 1000px;
            height: 975px;
            margin: auto;
            box-sizing: border-box;
        }


        #x-btn {
            background: none;
            border: none;
            cursor: pointer;
            padding: 0;
            float: right;
        }

        #x-btn img {
            width: 15px;
            height: auto;
        }

        #line {
            border: 3px solid;
        }

        #tb {
            width: 100%;
        }

        .pay-btn {
            background-color: rgb(255, 255, 255);
            position: fixed;
            bottom: 0;
            width: 1000PX;
            height: 180px;
        }
        
        #pname {
        	text-decoration: none;
        	color: black;
        }
    </style>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>

<body>
    <div id="wrap">
        <%@ include file="../common/header.jsp" %>
        <%@ include file="../common/navigator.jsp" %>
        <form action="<%= contextPath %>/order.ca" method="post">
            <div>
                <br>
                <h3>장바구니</h3>
                <hr id="line">
                
                <br><br>
                
                <% int sum = 0; %>
                <% if(list.isEmpty()) { %>
                    <br>
                    <h5 style="text-align: center">장바구니가 비어있습니다.</h5>
                <% } else { %>
                	
                    <% for(Cart c : list) { %>
                        <table id="tb">
                            
                                <td colspan="4">
                                    <button type="button" class="btn btn-outline-secondary btn-sm edit-btn" data-toggle="modal" data-target="#editModal" 
                                        data-size="<%= c.getSizeName() %>" data-count="<%= c.getCartCount() %>" data-cart-no="<%= c.getCartNo() %>"> 변경
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" style="text-align: right;">
                                    <a href="<%= contextPath %>/delete.ca?cartNo=<%= c.getCartNo() %>" id="x-btn">
                                        <img src="resources/images/x-btn.png">
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td rowspan="4" width="150" height="150">
                                    <a href="<%= contextPath %>/productDetail.pr?pno=<%= c.getProductNo()%>"><img src="<%= c.getImagePath() + c.getImageChangeName() %>" width="200" height="200"></a>
                                </td>
                                <td colspan="3"><a id="pname" href="<%= contextPath %>/productDetail.pr?pno=<%= c.getProductNo()%>"><%= c.getProductName() %></a></td>
                            </tr>
                            <tr>
                                <td width="50">옵션</td>
                                <td colspan=""><%= c.getSizeName() %></td>
                                <td>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" height="100" id="cartCount"><%= c.getCartCount() %> 개</td>
                            </tr>
                            <tr>
                                <td colspan="3" style="text-align: right;">
                                    <h3 id="price"><%= c.getCartPrice() * c.getCartCount() %>원</h3>
                                    <% sum += c.getCartPrice() * c.getCartCount(); %>
                                </td>
                            </tr>
                        </table>
                        <hr style="border-color: darkgray;">
                    <% } %>
                <% } %>
                <br><br><br><br><br><br><br><br><br><br><br><br><br>
                <div class="pay-btn">
                    <hr>
                    <h5>&nbsp;결제 예정 금액</h5>
                    <h2 id="totalPriceOutput" style="text-align: right;"><%= sum %>원</h2>
                    <input type="hidden" value="<%= sum %>" name="total" id="total">
                    <br>
                    <input type="submit" id="pay-btn" value="주문하기" class="btn btn-dark btn-block"
                        style="height: 60px;" <%= (list.isEmpty()) ? "disabled" : "" %>>
                </div>
                <br><br>
            </div>
        </form>
    </div>
    <% if(!list.isEmpty()) { %>
    <!-- modal -->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">옵션 변경하기</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="editForm" action="<%= contextPath %>/update.ca" method="post">
                        <div class="form-group">
                            <label for="quantityInput">수량</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-secondary quantity-modify" type="button" data-type="decrease">-</button>
                                </div>
                                <input type="number" id="quantityInput" name="updateCount" class="form-control" value="1" readonly>
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary quantity-modify" type="button" data-type="increase">+</button>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="cartNoInput" value="<%= list.get(0).getCartNo() %>" name="cartNo">
                        <input type="hidden" id="updateCountInput" name="updateCount">
                        <input type="hidden" id="sizeInput" name="size">
                        <input type="hidden" id="countInput" name="count">
                        <input type="hidden" id="cartNoInput" name="cartNo">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                            <button type="submit" class="btn btn-primary" id="confirmEdit">확인</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <% } %>
    <div class="wrap">
        <!-- <%@ include file="../common/footer.jsp" %> -->
    </div>
    <br><br><br><br>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
	
	<script>
		$(document).ready(function() {
	        $("#check-all").click(function() {
	            $(".checkbox").prop("checked", $(this).prop("checked"));
	        });
	
	        $(".checkbox").click(function() {
	            if (!$(this).prop("checked")) {
	                $("#check-all").prop("checked", false);
	            } else {
	                if ($(".checkbox:checked").length === $(".checkbox").length) {
	                    $("#check-all").prop("checked", true);
	                }
	            }
	        });
	    });
	
	    $('#editModal').on('show.bs.modal', function (event) {
	        var button = $(event.relatedTarget); // 모달창을 연 버튼
	        var size = button.data('size'); // data-size 속성
	        var count = button.data('count'); // data-count 속성
	        var cartNo = button.data('cart-no'); // data-cart-no 속성
	
	        var modal = $(this);
	        modal.find('#sizeSelect').val(size);
	        modal.find('#quantityInput').val(count);
	        modal.find('#cartNoInput').val(cartNo);
	    });
	
	    $('.quantity-modify').click(function() {
	        var type = $(this).data('type');
	        var quantityInput = $('#quantityInput');
	        var currentQuantity = parseInt(quantityInput.val());
	
	        if (type === 'increase' && currentQuantity < 10) {
	            quantityInput.val(currentQuantity + 1);
	        } else if (type === 'decrease' && currentQuantity > 1) {
	            quantityInput.val(currentQuantity - 1);
	        }
	    });
	
	    $('#confirmEdit').click(function() {
	        var size = $('#sizeSelect').val();
	        var quantity = $('#quantityInput').val();
	        var cartNo = $('#cartNoInput').val();
	
	        $('#editModal').modal('hide');
	    });
	
	    $(document).ready(function() {
	        $('#quantityInput').on('input', function() {
	            var updateCount = $(this).val();
	            $('#updateCountInput').val(updateCount);
	        });
	    });

	</script>
    
</body>
</html>
