<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <title>메인페이지 상품 테스트 문서</title>
    <style>
        

        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .item {
            width: calc(33.333% - 10px);
            margin-bottom: 15px;
        }

        .image {
            width: 100%;
            height: auto;
            background-color: #f0f0f0;
            border: 1px solid #ddd;
        }

        .text {
            text-align: center;
        }

        .pagination {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- 반복되는 아이템 시작 -->
        <% for(int i=0; i < 18; i++) { %>
            <div class="item">
                <div class="image">
                    <!-- 여기에 이미지 태그를 넣거나 배경 이미지를 사용할 수 있습니다 -->
                    <img src="resources/images/product.png" width="100%" height="100%">
                </div>
                <div class="text">
                    <h3>상품<%=i %>
                    </h3>
                    <p>가격 : <%=i*1000 %>
                    </p>
                </div>
            </div>
            <% } %>
                <!-- 반복되는 아이템 끝 -->
    </div>

    <div class="pagination">
        <!-- 페이징 -->
        <a href="#">&lt; prev</a>
        <a href="#">1</a>
        <a href="#" class="active">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">next &gt;</a>
    </div>
    <br><br>
</body>
</html>