<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.Product, com.kh.category.model.vo.Category, com.kh.size.model.vo.Size, com.kh.image.model.vo.Image "%>
<% 

	ArrayList<Product> prList = (ArrayList<Product>)(request.getAttribute("prList"));
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("clist");
	ArrayList<Category> sublist = (ArrayList<Category>)request.getAttribute("sublist");
	ArrayList<Size> sizeList = (ArrayList<Size>)request.getAttribute("sizeList");
	ArrayList<Image> imgList = (ArrayList<Image>)request.getAttribute("imgList");
	
	prList.add(null);
	prList.add(null);
	prList.add(null);
	prList.add(null);
	prList.add(null);
	prList.add(null);
	prList.add(null);
	prList.add(null);
	
	
	imgList.add(null);
	imgList.add(null);
	imgList.add(null);
	imgList.add(null);
	imgList.add(null);
	imgList.add(null);
	imgList.add(null);
	imgList.add(null);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
	<meta charset="UTF-8">
	<title>Man In Black</title>
	<style>
		.wrap {
			width: 1000px;
			margin: auto;
			margin-top: 50px;
		}


		#enroll-form input,
		#enroll-form textarea {
			width: 100%;
			box-sizing: border-box;
		}

		

		

	</style>
</head>
<body>
	<div class="wrap">

		<%@ include file="../common/header.jsp" %>
			<%@ include file="../common/navigator.jsp" %>

				<br>
				<h2 align="center">상품 수정</h2>
				<br>

				<form id="enroll-form" action="<%= contextPath %>/update.pr" method="post"
					enctype="multipart/form-data">

					<input type="hidden" name="pno" value="<%= prList.get(0).getProductNo() %>">
						<table align="center">

							<tr>
								<th width="120">상품명</th>
								<td colspan="3">
									<input type="text" name="productName" required value="<%= prList.get(0).getProductName()%>">
								</td>
							</tr>
							<tr>
								<th>상품 설명</th>
								<td colspan="3">
									<textarea name="productContent" rows="5" style="resize: none;" required><%= prList.get(0).getProductComment() %></textarea>
								</td>
							</tr>
							<tr>
								<th>카테고리 대분류</th>
								<td>
									<select name="categoryNo" style="width: 100px;">
										<% for(int i=0; i<list.size(); i++) { %>
											<option value="<%= list.get(i).getCategoryNo() %>" <%if(list.get(i).getCategoryName().equals(prList.get(0).getCategoryNo())) { %> selected <% } %> >
												<%= list.get(i).getCategoryName() %>
											</option>
										<% } %>
									</select>
								</td>
								<th class="selectSub">카테고리 소분류</th>
								<td>
									
									<select name="subCategoryNo" class="selectSub">
										<!-- option이 들어갈 자리 -->
										<% for(int i=0; i<sublist.size(); i++) { %>
										<option value="<%= sublist.get(i).getSubCategoryNo()%>" <%if(sublist.get(i).getSubCategoryName().equals(prList.get(0).getSubcategoryNo())) { %> selected <% } %>>
											<%= sublist.get(i).getSubCategoryName() %>
											</option>
										<%} %>
									</select>
								</td>
							
							
							<script>
								$(function() {

									$("select[name='categoryNo']").change(function() {

										var categoryNo = $(this).val();
										console.log(categoryNo);
										
										$.ajax({
											url: "<%= contextPath %>/categoryList.pr", // 서버 측 URL
											type: 'get',
											data: { categoryNo: categoryNo }, // 선택된 카테고리 대분류의 기본키값을 서버로 전달
											success: function(list) {
												// 서버로부터 받은 응답을 이용하여 카테고리 소분류의 옵션을 업데이트
												console.log(list);

												let selectElement =  $("select[name='subCategoryNo']");
												let optionsHTML = "";
												$.each(list, function(index, key) {
													
													
													optionsHTML += '<option value="' + key.subCategoryNo + '">' + key.subCategoryName + '</option>';

												});
												selectElement.html(optionsHTML);
												

											},
											error: function() {
												// 오류 처리
												console.error("오류오류");
											}
										});

									});
								})
							</script>
							</tr>
							<tr>
								<th>가격</th>
								<td colspan="3">
									<input type="number" style="width: 80px;" class="nums" name="productPrice" value="<%= prList.get(0).getProductPrice() %>" required>
								</td>
							</tr>
							<tr>
								<th>재고</th>
								<td colspan="3">
									<input type="number" style="width: 80px;" class="nums" name="productStock" value="<%= prList.get(0).getProductStock() %>" required>
								</td>
							</tr>
							<tr>
								<th height="50">사이즈</th>
								<td style="width: 120px;">
									&nbsp;
									<% for(int i=0; i<5; i++) { %>
										<label><b><%= sizeList.get(i).getSizeName() %></b><input style="width: 18px;" type="radio" name="productSize" value="<%=sizeList.get(i).getSizeNo()%>" <% if(sizeList.get(i).getSizeName().equals(prList.get(0).getSizeNo())){ %>checked<%}%>></label>
										<% if (i == 2){ %>
											<br>
											<%} %>
									<% } %>								
									<label><b><%= sizeList.get(11).getSizeName() %></b><input style="width: 20px;" type="radio" name="productSize" checked value="<%=sizeList.get(11).getSizeNo()%>"></label>
								</td>
								<th>
									신발 사이즈
								</th>
								<td>
									&nbsp;
									<% for(int i=5; i<8; i++) { %>
										<label><b><%= sizeList.get(i).getSizeName() %></b><input style="width: 18px;" type="radio" name="productSize" value="<%=sizeList.get(i).getSizeNo()%>"<%if(sizeList.get(i).getSizeName().equals(prList.get(0).getSizeNo())){ %>checked<%}%>></label>
										<% if (i == 7){ %>
											<br>
											<%} %>
									<% } %>
									&nbsp;
									<% for(int i=8; i<11; i++) { %>
									<label><b><%= sizeList.get(i).getSizeName() %></b><input style="width: 18px;" type="radio" name="productSize" value="<%=sizeList.get(i).getSizeNo()%>"<%if(sizeList.get(i).getSizeName().equals(prList.get(0).getSizeNo())){ %>checked<%}%>></label>
									<% } %>
								</td>
							</tr>
							<tr>
								<th>썸네일 이미지</th>
								<td colspan="3" align="center">
									<!-- 이미지를 업로드 (미리보기 기능) -->
									<img id="titleImg" src="<%= prList.get(0).getTitleImg() %>" 
											width="250" height="300" >
								</td>

							</tr>
							<tr>
								<th rowspan="2">상세 이미지</th>
								<td>
									<!-- 이미지를 업로드 (미리보기 기능) -->
									<img id="contentImg1" src="<% if(prList.get(1) != null) { %><%= prList.get(1).getTitleImg() %><% } %>" width="150" height="180">
								</td>
								<td>
									<!-- 이미지를 업로드 (미리보기 기능) -->
									<img id="contentImg2" src="<% if(prList.get(2) != null) { %><%= prList.get(2).getTitleImg() %><% } %>" width="150" height="180">
								</td>
								<td>
									<!-- 이미지를 업로드 (미리보기 기능) -->
									<img id="contentImg3" src="<% if(prList.get(3) != null) { %><%= prList.get(3).getTitleImg() %><% } %>" width="150" height="180">
								</td>
			
							</tr>
							<tr>
								<td>
									<!-- 이미지를 업로드 (미리보기 기능) -->
									<img id="contentImg4" src="<% if(prList.get(4) != null) { %><%= prList.get(4).getTitleImg() %><% } %>" width="150" height="180">
								</td>
								<td>
									<!-- 이미지를 업로드 (미리보기 기능) -->
									<img id="contentImg5" src="<% if(prList.get(5) != null) { %><%= prList.get(5).getTitleImg() %><% } %>" width="150" height="180">
								</td>
								<td>
									<!-- 이미지를 업로드 (미리보기 기능) -->
									<img id="contentImg6" src="<% if(prList.get(6) != null) { %><%= prList.get(6).getTitleImg() %><% } %>" width="150" height="180">
								</td>
							</tr>


						</table>
						
						
						<br><br>
						
						

						<div id="file-area">
							<!-- 대표이미지 : 섬네일은 필수 입력사항 -->
							
							<input type="hidden" name="FileNo1" value="<% if(imgList.get(0) != null) { %><%= imgList.get(0).getImageNo() %><% } %>">
							<input type="hidden" name="FileNo2" value="<% if(imgList.get(1) != null) { %><%= imgList.get(1).getImageNo() %><% } %>">
							<input type="hidden" name="FileNo3" value="<% if(imgList.get(2) != null) { %><%= imgList.get(2).getImageNo() %><% } %>">
							<input type="hidden" name="FileNo4" value="<% if(imgList.get(3) != null) { %><%= imgList.get(3).getImageNo() %><% } %>">
							<input type="hidden" name="FileNo5" value="<% if(imgList.get(4) != null) { %><%= imgList.get(4).getImageNo() %><% } %>">
							<input type="hidden" name="FileNo6" value="<% if(imgList.get(5) != null) { %><%= imgList.get(5).getImageNo() %><% } %>">
							<input type="hidden" name="FileNo7" value="<% if(imgList.get(6) != null) { %><%= imgList.get(6).getImageNo() %><% } %>">
							
							<input type="hidden" name="FileName1" value="<% if(imgList.get(0) != null) { %><%= imgList.get(0).getChangeName() %><% } %>">
							<input type="hidden" name="FileName2" value="<% if(imgList.get(1) != null) { %><%= imgList.get(1).getChangeName() %><% } %>">
							<input type="hidden" name="FileName3" value="<% if(imgList.get(2) != null) { %><%= imgList.get(2).getChangeName() %><% } %>">
							<input type="hidden" name="FileName4" value="<% if(imgList.get(3) != null) { %><%= imgList.get(3).getChangeName() %><% } %>">
							<input type="hidden" name="FileName5" value="<% if(imgList.get(4) != null) { %><%= imgList.get(4).getChangeName() %><% } %>">
							<input type="hidden" name="FileName6" value="<% if(imgList.get(5) != null) { %><%= imgList.get(5).getChangeName() %><% } %>">
							<input type="hidden" name="FileName7" value="<% if(imgList.get(6) != null) { %><%= imgList.get(6).getChangeName() %><% } %>">

							<input type="hidden" name="FileOriginName1" value="<% if(imgList.get(0) != null) { %><%= imgList.get(0).getOriginName() %><% } %>">
							<input type="hidden" name="FileOriginName2" value="<% if(imgList.get(1) != null) { %><%= imgList.get(1).getOriginName() %><% } %>">
							<input type="hidden" name="FileOriginName3" value="<% if(imgList.get(2) != null) { %><%= imgList.get(2).getOriginName() %><% } %>">
							<input type="hidden" name="FileOriginName4" value="<% if(imgList.get(3) != null) { %><%= imgList.get(3).getOriginName() %><% } %>">
							<input type="hidden" name="FileOriginName5" value="<% if(imgList.get(4) != null) { %><%= imgList.get(4).getOriginName() %><% } %>">
							<input type="hidden" name="FileOriginName6" value="<% if(imgList.get(5) != null) { %><%= imgList.get(5).getOriginName() %><% } %>">
							<input type="hidden" name="FileOriginName7" value="<% if(imgList.get(6) != null) { %><%= imgList.get(6).getOriginName() %><% } %>">
							
							
							
							
							<input type="file" id="file1" name="file1" required onchange="loadImg(this, 1);">
							<input type="file" id="file2" name="file2" onchange="loadImg(this, 2);">
							<input type="file" id="file3" name="file3" onchange="loadImg(this, 3);">
							<input type="file" id="file4" name="file4" onchange="loadImg(this, 4);">
							<input type="file" id="file5" name="file5" onchange="loadImg(this, 5);">
							<input type="file" id="file6" name="file6" onchange="loadImg(this, 6);">
							<input type="file" id="file7" name="file7" onchange="loadImg(this, 7);">
						</div>
						
						<script>
							$(function() {
								$("#file1").val(function () {
									$(this).removeAttr("required");
								});
							}) 	
						
						</script>

						<script>

							$(function () {
								/* display : none 이랑 비슷함 */
								$("#file-area").hide();

								$("#titleImg").click(function () {
									$("#file1").click();
								});

								$("#contentImg1").click(function () {
									$("#file2").click();
								});

								$("#contentImg2").click(function () {
									$("#file3").click();
								});

								$("#contentImg3").click(function () {
									$("#file4").click();
								});
								
								$("#contentImg4").click(function () {
									$("#file5").click();
								});

								$("#contentImg5").click(function () {
									$("#file6").click();
								});

								$("#contentImg6").click(function () {
									$("#file7").click();
								});

							});




							function loadImg(inputFile, num) {

								// inputFile: 현재 change 이벤트를 당한
								//  		  input type="file" 요소 객체 (타겟)
								// num : 몇번째 input type="file" 요소 객체인지
								// 		 구분용 num 값

								// console.log(inputFile.files);
								// > input type="file" 요소 객체는
								// 내부적으로 files 라는 속성을 갖고 있음!
								// 해당 input type="file" 요소로 입력받은
								// 파일들의 정보들을 "배열" 형식으로 담고 있음

								// console.log(inputFile.files.length);
								// > 배열명.length 는 배열의 크기
								// 파일 선택시 1, 파일 선택 취소시 0이 나옴
								// (즉, 선택된 파일의 갯수)
								// > 파일의 존재 유무를 가려낼 수 있따.

								if (inputFile.files.length == 1) {
									// 선택된 파일이 있는 경우

									// 선택된 파일을 읽어들여서 그 영역에 맞는 곳에 미리보기
									// 파일을 읽어들일 자바스크립트 객체 생성 FileReader 객체
									let reader = new FileReader(); // 생성자 함수

									// FileReader 객체에서 제공하는
									// 파일을 읽어들이는 메소드 속성 호출

									// > 어느 파일을 읽어들일지 매개변수로 제시
									//   inputFile.files[0]
									reader.readAsDataURL(inputFile.files[0]);
									// > 해당 파일을 읽어들이는 순간
									//   해당 그 파일만의 고유한 url 주소 같은게
									//   만들어져서 부여됨
									// > 각 자리에 맞는 img 태그의 src 속성을
									//   해당 url 주소값으로 넣어주면 미리보기됨

									// 파일 읽기가 완료되었을 때 실행할 함수를 정의
									// (즉, url 주소가 부여되었을 때)
									reader.onload = function (e) {
										// e.target == reader == this

										// e 의 target.result 에
										// 각 파일의 url 주소가 담겨있음
										// 각 영역에 맞춰서 이미지 미리보기
										// attr : src 속성추가
										switch (num) {
											case 1:
												$("#titleImg").attr("src", e.target.result);
												break;
											case 2:
												$("#contentImg1").attr("src", e.target.result);
												break;
											case 3:
												$("#contentImg2").attr("src", e.target.result);
												break;
											case 4:
												$("#contentImg3").attr("src", e.target.result);
												break;
											case 5:
												$("#contentImg4").attr("src", e.target.result);
												break;
											case 6:
												$("#contentImg5").attr("src", e.target.result);
												break;
											case 7:
												$("#contentImg6").attr("src", e.target.result);
												break;

										}

									};

								} else {
									// 선택된 파일이 없는 경우

									// 미리보기 사라지게 하기 
									switch (num) {
										case 1:
											$("#titleImg").attr("src", null);
											break;
										case 2:
											$("#contentImg1").attr("src", null);
											break;
										case 3:
											$("#contentImg2").attr("src", null);
											break;
										case 4:
											$("#contentImg3").attr("src", null);
											break;
										case 5:
											$("#contentImg4").attr("src", null);
											break;
										case 6:
											$("#contentImg5").attr("src", null);
											break;
										case 7:
											$("#contentImg6").attr("src", null);
											break;

									}

								}
							}
						</script>

						<br><br>

						<div align="center">
							<button type="submit" class="btn btn-secondary btn-sm">변경하기</button>
						</div>
				</form>

				<br><br>
				<%@ include file="../common/footer.jsp" %>

	</div>
</body>
</html>