<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>화면구조</title>

	<style>
		

		#a {
			width: 1000px;
			height: 975px;
			margin: auto;
			box-sizing: border-box;
		}

		div {
			/* border: 1px solid red;      */
			box-sizing: border-box;
		}

		#content_1 {
			width: 200px;
			height: 596px;
		}


		#content_1 li {
			list-style-type: none;
			font-size: 17px;
			font-weight: bold;
			font-family: "Hi Melody", sans-serif;
		}

		#content_1 li:not(:first-child) {
			margin-top: 20px;
			color: #6e6e6e;
			font-family: "Hi Melody", sans-serif;
		}



		#content_1 li:not(:first-child):hover {
			cursor: pointer;
			font-size: 17px;
			color: black;

		}


		/* 부모 div안에 inline-block 요소들 반듯하게 연이어서 보여주는 속성 */
		#content {
			display: flex;
		}

		#content_1,
		#content_2 {
			display: inline-block;
			/* border: 1px solid red; */
		}

		/* 임시스타일 */
		#content_2 {
			width: 800px;
			height: 100%;

		}

		.hidden {
			display: none;
		}
	</style>

</head>
<body>
	<div id="a">
		<div>
			<%@ include file="../common/header.jsp" %>
		</div>
		<div>
			<%@ include file="../common/navigator.jsp" %>
		</div>
		<div id="content">
			<div id="content_1">
				<br>
				<ul>
					<li>
						<div style="font-size: 25px;">내 쇼핑 활동</div>
					</li>
					<li onclick="clicks(1)">내 정보</li>
					<li onclick="clicks(2)">주문내역</li>
					<li onclick="clicks(3)">좋아요</li>
					<li onclick="clicks(4)">리뷰조회</li>
					<li onclick="clicks(5)">문의내역</li>
				</ul>
			</div>
			
			<div id="content_2">
				<br>
				<div id="memberHidden">
					<%@ include file="../member/profile.jsp" %>
				</div>
				<div id="orderDetailHidden" class="hidden">
					<%@ include file="../order/orderDetail.jsp" %>
				</div>
				<div id="likeHidden" class="hidden">
					<%@ include file="../like/likeList.jsp" %>
				</div>
				<div id="reviewHidden" class="hidden">
					<%@ include file="../review/reviewDetail.jsp" %>
				</div>
				<div id="inquryHidden" class="hidden">
					<%@ include file="../inquiry/myInquiryDetail.jsp" %>
				</div>

			</div>
		</div>
		<br>
		<div>
			<%@ include file="../common/footer.jsp" %>
		</div>
	</div>

	<script>
		function clicks(id) {
			// 스타일쪽에 클래스선택자로 숨김처리해주었지만 
			// 클릭할때마다 내용물이 보여지고 다시 숨김처리해줘야 하기때문에
			// 모든 상황에서 숨김처리해주려면 밑에 스타일을 한번 더 줘야함
			document.getElementById('memberHidden').style.display = 'none';
			document.getElementById('orderDetailHidden').style.display = 'none';
			document.getElementById('likeHidden').style.display = 'none';
			document.getElementById('reviewHidden').style.display = 'none';
			document.getElementById('inquryHidden').style.display = 'none';

			// 클릭된 리스트에 따라 해당 내용을 보이게 함
			if (id === 1) {
				document.getElementById('memberHidden').style.display = 'block';
			} else if (id === 2) {
				document.getElementById('orderDetailHidden').style.display = 'block';
			} else if (id === 3) {
				document.getElementById('likeHidden').style.display = 'block';
			} else if (id === 4) {
				document.getElementById('reviewHidden').style.display = 'block';
			} else if (id === 5) {
				document.getElementById('inquryHidden').style.display = 'block';
			}
		}
	</script>
</body>
</html>