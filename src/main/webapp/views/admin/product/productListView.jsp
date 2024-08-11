<%@page import="java.util.HashMap"%>
<%@page import="com.kh.size.model.vo.Size"%>
<%@page import="com.kh.category.model.vo.Category"%>
<%@page import="com.kh.product.model.vo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%
    ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("list");
	ArrayList<Size> slist = (ArrayList<Size>) request.getAttribute("slist");
	ArrayList<Category> categoryList = (ArrayList<Category>) request.getAttribute("sCategoryList"); 
	
    HashMap<Integer, String> categoryMap = new HashMap<>();
    for (Category c : categoryList) {
    	categoryMap.put(c.getSubCategoryNo(), c.getSubCategoryName());
    }
    
    HashMap<Integer, String> sizeMap = new HashMap<>();
    for (Size s : slist) {
        sizeMap.put(s.getSizeNo(), s.getSizeName());
    }
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600|Open+Sans:400,600,700" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/spur.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
    <script src="resources/js/chart-js-config.js"></script>
    <title>관리자 페이지</title>
    <style>
        tr { text-align: center; }
        td { text-align: center; }
        table > tbody > tr:hover {
            background-color: lightgray;
            cursor: pointer;
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
                        <h1 class="dash-title">상품 리스트</h1>
                        <form class="form-inline" method="GET" action="searchProduct.ad">
                            <input type="text" name="searchName" class="form-control" placeholder="상품명 입력" style="margin-right: 5px;">
                            <button type="submit" class="btn btn-primary">검색</button>
                            &emsp; &emsp;
                            <button type="button" class="btn btn-success add-product-button" onclick="btnInsert();">상품 추가</button>
                        </form>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card spur-card">
                                <div class="card-body">
                                    <table class="table table-in-card">
                                        <thead>
                                            <tr>
                                                <th scope="col">상품번호</th>
                                                <th scope="col">상품명</th>
                                                <th scope="col">상품설명</th>
                                                <th scope="col">가격</th>
                                                <th scope="col">재고</th>
                                                <th scope="col">추가일</th>
                                                <th scope="col">삭제여부</th>
                                                <th scope="col">사이즈</th>
                                                <th scope="col">상품분류</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if (list.isEmpty()) { %>
                                                <tr>
                                                    <td colspan="9">존재하는 상품이 없습니다.</td>
                                                </tr>
                                            <% } else { %>
                                                <% for (Product p : list) { %>
                                                    <tr>
                                                        <td><%= p.getProductNo() %></td>
                                                        <td><%= p.getProductName() %></td>
                                                        <td><%= p.getProductComment() %></td>
                                                        <td><%= p.getProductPrice() %></td>
                                                        <td><%= p.getProductStock() %></td>
                                                        <td><%= p.getProductDate() %></td>
                                                        <td>
                                                            <% if (p.getProductStatus().equals("Y")) { %>
                                                                판매중
                                                            <% } else if (p.getProductStatus().equals("N")) { %>
                                                                판매중지
                                                            <% } else { %>
                                                                기타
                                                            <% } %>
                                                        </td>
                                                        <% 
                                                            String sizeName = sizeMap.get(Integer.parseInt(p.getSizeNo()));
                                                        %>
                                                        <td><%= sizeName %></td>
                                                        <td><%= categoryMap.get(Integer.parseInt(p.getSubcategoryNo())) %></td>
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

    <script>
        function btnInsert() {
            window.location.href = "<%= request.getContextPath() %>/enroll.pr";
        }
    </script>

    <!-- Modal for product update -->
    <div class="modal fade" id="editProductModal" tabindex="-1" role="dialog" aria-labelledby="editProductModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editProductModalLabel">상품 정보 수정</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="productForm">
                        <div class="form-group">
                            <label for="productNo">상품번호</label>
                            <input type="text" class="form-control" id="productNo" disabled>
                        </div>
                        <div class="form-group">
                            <label for="productName">상품명</label>
                            <input type="text" class="form-control" id="productName" disabled>
                        </div>
                        <div class="form-group">
                            <label for="productComment">상품설명</label>
                            <textarea class="form-control" id="productComment" disabled></textarea>
                        </div>
                        <div class="form-group">
                            <label for="productStatus">삭제여부</label>
                            <select class="form-control" id="productStatus">
                                <option value="Y">판매중</option>
                                <option value="N">판매중지</option>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-warning btn" id="updateBtn">상품정보 수정</a>
                    <button type="button" class="btn btn-primary" onclick="updateProductDetails()">판매상태 변경</button>
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
                var productNo = $(this).find('td:eq(0)').text();
                var productName = $(this).find('td:eq(1)').text();
                var productComment = $(this).find('td:eq(2)').text();
                var productPrice = $(this).find('td:eq(3)').text();
                var productStock = $(this).find('td:eq(4)').text();
                var productStatus = $(this).find('td:eq(6)').text().trim() === '판매중' ? 'Y' : 'N';
                var sizeNo = $(this).find('td:eq(7)').text();
                var subcategoryNo = $(this).find('td:eq(8)').text();

                $('#productNo').val(productNo);
                $('#productName').val(productName);
                $('#productComment').val(productComment);
                $('#productPrice').val(productPrice);
                $('#productStock').val(productStock);
                $('#productStatus').val(productStatus);
                $('#sizeNo').val(sizeNo);
                $('#subcategoryNo').val(subcategoryNo);

                // Update links to handle productNo dynamically
                $('#updateBtn').attr("href", "<%= request.getContextPath() %>/updateForm.pr?pno=" + productNo);
                
                if (productStatus === 'Y') {
                    $('#updateBtn').show(); // Make the button visible
                } else {
                    $('#updateBtn').hide(); // Hide the button
                }

                $('#editProductModal').modal('show');
            });
        });

        function updateProductDetails() {
            var productNo = $('#productNo').val();
            var productStatus = $('#productStatus').val();

            $.ajax({
                url: "<%= request.getContextPath() %>/updateProduct.ad",
                type: 'POST',
                data: {
                    productNo: productNo,
                    productStatus: productStatus,
                },
                success: function(response) {
                    $('#editProductModal').modal('hide');
                    alert('상품 정보 업데이트 완료!');
                    location.reload();
                },
                error: function() {
                    alert('상품 정보 업데이트 실패');
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
