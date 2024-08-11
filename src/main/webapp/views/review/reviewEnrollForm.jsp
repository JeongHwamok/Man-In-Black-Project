<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	// int result = request.getAttribute("result");

	int orderItemNo = Integer.parseInt(request.getParameter("orderItemNo"));
	String productImg = request.getParameter("productImg");
	String productName = request.getParameter("productName");
	String memberNo = request.getParameter("memberNo");
	int productNo = Integer.parseInt(request.getParameter("productNo"));
	String imageChangeName = request.getParameter("imageChangeName");
	//System.out.println(orderItemNo);
	//System.out.println(productImg);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <title>Man In Black</title>
    <style>
        @font-face {
            font-family: 'Hahmlet-Regular';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/Hahmlet-Regular.woff2') format('woff2');
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: 'Hahmlet-Regular';
        }

        .enroll_outer {
            width: 1000px;
            height: 975px;
            margin: auto;
            box-sizing: border-box;
            text-align: center;
        }

        .outer2 {
            /* border: 1px solid lightgray; */
        }

        #review_tb { width: 80%; margin: auto; border-spacing: 0; border-color: lightgray;}
        #review_tb textarea {width: 99%;}
        #review_tb td {text-align: left;}
        #review_tb hr {border: 1px solid lightgray;}
        
        .product-image {
	        width: 150px;
	        height: 180px;

    	}
    </style>
</head>
<body>
    	<div class="enroll_outer">
            <%@ include file="/views/common/header.jsp" %>
            <%@ include file="/views/common/navigator.jsp" %>
            <br>
            <div class="outer2">
                <h3>리뷰 작성하기</h3>
                <br>
                <form action="<%= contextPath %>/insert.re" 
                        method="post" enctype="multipart/form-data">
                    
                    <!-- insert.re 로 넘길때 데이터 hidden 으로 생성 -->
                    <input type="hidden" name="orderItemNo" value="<%= orderItemNo %>">
                    <input type="hidden" name="productImg" value="<%= productImg %>">
                    <input type="hidden" name="productName" value="<%= productName %>">
                    <input type="hidden" name="memberNo" value="<%= memberNo %>">
                    <input type="hidden" name="productNo" value="<%= productNo %>">
                    
                    <table  id="review_tb">
                        <tr height="50px">
                            <th>
                                <img src="resources/thumbnail_upfiles/<%= imageChangeName %>" class="product-image">
                            </th>
                            <td><%= productName %></td>
                        </tr>
                        <tr height="50">
                            <th colspan="2">
                                <hr>
                            </th>
                        </tr>
                        <tr>
                            <th>상세후기</th>
                            <th>
                                <textarea name="reviewContent" 
                                            id="reviewContent" 
                                            cols="30" rows="10" 
                                            style="resize: none;"
                                            placeholder="다른 고객님에게 도움이 되도록 상품에 대한 솔직한 평가를 남겨주세요." required></textarea>
                            </th>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="font-size: small;">
                                - 상품과 관계없는 내용은 삭제 처리 될 수 있습니다. <br>
                                - 작성된 리뷰는 삭제 전까지 ‘상품 리뷰’에 공개되고, ‘마이페이지 > 리뷰 조회’에서 삭제가 가능합니다.
                            </td>
                        </tr>
                        <tr height="50">
                            <th colspan="2">
                                <hr>
                            </th>
                        </tr>
                        <tr height="50">
                            <th>사진 첨부</th>
                            <td>
                                &nbsp;<input type="file" name="reviewUpFile" id="reviewUpFile">
                            </td>
                        </tr>
                        
                    </table>
                    <br><br>
					<button type="submit" id="submitButton" disabled class="btn btn-secondary btn-sm">작성 완료</button>&nbsp;&nbsp;
                    <button type="button" onclick="location.href = '<%= contextPath %>/myPage.me';" class="btn btn-dark btn-sm">작성 취소</button>
                </form>
                <br>
            </div>


        </div>
        
        <script>
        
        	// 리뷰내용과 파일을 삽입하지 않으면 버튼이 눌리지 않게 함
		    $(document).ready(function() {
		        $('#reviewUpFile').on('change', function() {
		            var fileName = $(this).val();
		            if (fileName) {
		            	
		                $('#submitButton').prop('disabled', false);
		             	// 파일명이 있으면(파일이 선택되었으면) submit 버튼 활성화
		            } else {
		                $('#submitButton').prop('disabled', true);
		            }
		        });
		    });
		</script>
				  
		
</body>
</html>