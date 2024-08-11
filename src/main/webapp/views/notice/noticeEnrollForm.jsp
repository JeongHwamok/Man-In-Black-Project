<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<title>Man In Black</title>
	<style>
		
		.outer {
			width: 1000px;
			border: 1px dotted lightgray;
			margin: auto;
			margin-top: 50px;
		}

		#enroll-form>table {
			border: 1px solid lightgray;
		}

		#enroll-form input,
		#enroll-form textarea {
			width: 100%;
			box-sizing: border-box;
		}
	</style>
</head>
<body>

	<%@ include file="../common/header.jsp" %>
		<%@ include file="../common/navigator.jsp" %>

			<div class="outer">

				<br>
				<h2 align="center">공지사항 작성하기</h2>
				<br>

				<form id="enroll-form" action="" method="post">

					<table align="center">
						<tr>
							<th width="50">제목</th>
							<td width="350">
								<input type="text" name="noticeTitle" required>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td></td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea name="noticeContent" rows="10" style="resize : none;" required></textarea>
							</td>
						</tr>
					</table>

					<br><br>

					<div align="center">
						<button type="submit" class="btn btn-secondary btn-sm">
							등록하기
						</button>
						<button type="button" class="btn btn-secondary btn-sm" onclick="history.back();">
							<!-- 
							history.back() : 이전 페이지로 이동
						-->
							뒤로가기
						</button>
					</div>

				</form>

				<br><br>
			</div>
</body>
</html>