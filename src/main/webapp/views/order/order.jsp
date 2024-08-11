<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
  <meta charset="UTF-8">
  <title>주문 페이지</title>
  <style>
    #a {
      width: 1000px;
      height: 975px;
      margin: auto;
      box-sizing: border-box;
    }

    .product-info {
      display: flex;
      align-items: center;
      justify-content: space-between;
    }

    .product-image img {
      width: 100px;
      /* 이미지의 크기를 원하는 대로 조절하세요 */
      height: auto;
    }

    .product-details {
      flex-grow: 1;
      /* 내용이 차지할 수 있는 모든 공간을 차지하도록 설정 */
      padding-left: 20px;
      /* 이미지와 텍스트 사이의 공간 */
    }

    .product-price {
      text-align: right;
      /* 가격을 오른쪽 정렬 */
    }

    .clear {
      clear: both;
    }

    hr {
      clear: both;
      margin-top: 20px;
    }

    .checkout-container {
      text-align: center;
      /* 컨테이너 안의 내용을 중앙 정렬 */
      margin-top: 20px;
      /* 버튼 위에 여백을 추가 */
    }

    .checkout-button {
      padding: 10px 20px;
      /* 버튼 내부 여백 설정 */
      font-size: 16px;
      /* 글자 크기 설정 */
      cursor: pointer;
      /* 마우스 오버시 커서 변경 */
      border: none;
      /* 테두리 제거 */
      background-color: #4CAF50;
      /* 배경색 설정 */
      color: white;
      /* 글자색 설정 */
      border-radius: 5px;
      /* 버튼의 모서리를 둥글게 */
    }

    /* 추가로 버튼에 마우스를 올렸을 때 스타일 변경을 원한다면 */
    .checkout-button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
  <section id="order-product-info">
    <h2>주문 상품 정보</h2>
    <div class="product-info">
      <div class="product-image">
        <img src="resources/images/img-sample.jpg" width="200" height="200">
      </div>
      <div class="product-details">
        <p class="product-name">상품명</p>
        <p class="product-description">상품 설명</p>
        <p class="product-price">가격</p>
      </div>
    </div>
    <hr>
  </section>

  <section id="delivery-info">
    <h2>배송지 정보</h2>
    <section id="delivery-info">
      <label for="name">이름:</label>
      <input type="text" id="name" placeholder="이름을 입력하세요"><br>
      <label for="contact">연락처:</label>
      <input type="text" id="contact" placeholder="연락처를 입력하세요"><br>
      <label for="address">배송지 주소:</label>
      <input type="text" id="address" placeholder="주소를 입력하세요">
      <button id="searchAddress">검색</button>
    </section>
    <hr>
  </section>

  <section id="delivery-request">
    <h2>배송 요청사항</h2>
    <select id="delivery-options">
      <option value="none" selected>배송 요청사항을 선택해주세요</option>
      <option value="leave-at-door">문 앞에 놓아주세요</option>
      <option value="ring-bell">벨 누르시고 놓아주세요</option>
      <option value="direct-handover">직접 전달해주세요</option>
    </select>
  </section>

  <hr>
  </section>

  <section id="payment-amount">
    <h2>결제금액</h2>
    <section id="delivery-request">

      <hr>
    </section>

    <section id="payment-method">
      <h2>결제 방법</h2>
      <input type="checkbox" id="agree-all"> 이용약관 전체동의<br>
      <input type="checkbox" class="agree-sub"> 개인정보 이용동의<br>
      <input type="checkbox" class="agree-sub"> 제3자 정보제공 동의<br>
      <!-- 기타 결제 관련 정보 -->
    </section>

    <div class="checkout-container">
      <button class="checkout-button">결제하기</button>
    </div>

    <script>
      document.getElementById('agree-all').addEventListener('click', function () {
        var checkboxes = document.querySelectorAll('.agree-sub');
        for (var i = 0; i < checkboxes.length; i++) {
          checkboxes[i].checked = this.checked;
        }
      });
    </script>

</body>
</html>