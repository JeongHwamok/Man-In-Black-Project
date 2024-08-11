<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<title>Man In Black</title>
	<style>
		

		#footer {
			height: 144px;
			text-align: center;
		}

		#footer>div {
			width: 100%;
		}

		#footer_1 {
			height: 20%;
		}

		#footer_2 {
			height: 80%;
		}

		#footer_2>p {
			width: 100%;
			margin: 0;
			box-sizing: border-box;
			font-size: 11px;
		}

		#p1 {
			height: 70%;
			padding: 5px 15px;
		}

		#p2 {
			height: 30%;
			text-align: center;
		}

		#footer_1>a {
			text-decoration: none;
			/* a태그 밑줄 없애기 */
			color: black;
			font-weight: 550;
			/* 글자의 굵기 사이즈 조정 */
			margin: 15px;
			/* 바깥 영역 범위 조정 */
			vertical-align: middle;
			/* 수직 구조에서 가운데로 오게끔 해주는 속성 */
			font-size: 10px;
		}

		div {
			/* border: 1px solid red; */
			box-sizing: border-box;
		}
	</style>
</head>
<body>
	<div class="wrap">
		<div id="footer">
			<div id="footer_1">
				<a href="">이용약관</a> |
				<a href="">개인정보처리방침</a> |
				<a href="">도움말</a> |
				<a href="">고객센터 문의</a>
			</div>
			<div id="footer_2">
				<p id="p1">
					주식회사 맨인블랙 (MAN IN BLACK) |
					통신판매업신고 :
					제2019-서울당산-01973호 |
					사업자등록번호 :
					295-88-00023 |
					대표자 :
					한상준 <br>
					서울특별시 영등포구 선유동2로 57 이레빌딩 |
					전화 :
					010-8014-1458 (평일 09:00 ~ 18:00) |
					이메일 :
					service@localhost:8888/MIB
				</p>
				<p id="p2">
					© 2012-2024 MIB Shopping Mall. MIB Shopping Mall is not endorsed by nor affiliated with any specific brand or creator unless stated. All product names, logos, and brands are property of their respective owners. All company, product and service names used in this website are for identification purposes only. Use of these names, logos, and brands does not imply endorsement.
					
					All content is the property of MIB Shopping Mall or its content suppliers and protected by international copyright laws. The copyright of external content belongs to their respective owners and is used under agreement or for educational and promotional purposes only.

				</p>
			</div>
		</div>
	</div>
</body>
</html>