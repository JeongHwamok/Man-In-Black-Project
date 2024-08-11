<%@page import="com.kh.review.model.vo.Review"%>
<%@page import="com.kh.inquiry.model.vo.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.util.ArrayList, com.kh.product.model.vo.Product, java.text.DecimalFormat"%>
<%

	ArrayList<Product> prList = (ArrayList<Product>)(request.getAttribute("prList"));
	ArrayList<Inquiry> iList = (ArrayList<Inquiry>)request.getAttribute("iList");
	ArrayList<Review> rList = (ArrayList<Review>)request.getAttribute("rList");
	DecimalFormat formatter = new DecimalFormat("#,###"); // 가격 , 붙여서 포메팅해주는 객체 
	String price = formatter.format(prList.get(0).getProductPrice());
	String size = prList.get(0).getSizeNo();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <title>Man In Black</title>

    <style>
		@font-face {
			
			src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/Hahmlet-Regular.woff2') format('woff2');
			
		}

		.heart {
			font-family: 'Hahmlet-Regular';
		}
		
        .heart-icon {
        	float : right;
            width: 50px;
            height: 50px;
            cursor: pointer;
        }
        .heart-icon .heart {
            color: #c7c6c6;
            transition: color 0.1s;
            font-size: 25px;
        }
        .heart-icon .heart.active {
            color: red;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5),
                        1px 1px 0 #000, 
                        -1px -1px 0 #000, 
                        1px -1px 0 #000, 
                        -1px 1px 0 #000;
        
        }
   
		
        .include {
            width: 1000px;
            margin: auto;
            /* box-sizing: border-box; */
        }

        .outer,
        .outer>div {
            width: 975px;
            margin: auto;
            box-sizing: border-box;
            /* border: 1px solid ; */
            text-align: center;
        }

        #cloth-main {
            width: 100%;
            height: 100%;
            margin: auto;
            text-align: center;
        }

        #clost-info {
            width: 100%;
            height: 100px;
            margin: auto;
            text-align: center;
        }

        #text {
            padding: 2px 4px;
            background-color: rgb(247, 247, 247);
            color: rgb(153, 153, 153);
            height: 22px;
            font-size: 12px;
            border-radius: 9999px;
            font-weight: 500;
            text-align: center;
        }

        .pay_cart_btn {
            
            position: fixed;
            bottom: 0;
            width: 1000PX;
            height: 70px;

            text-align: center;
        }

		.pay_btn {
            background-color: rgb(255, 255, 255);
            position: fixed;
            bottom: 0;
            width: 1000px;
            height: 70px;
            text-align: center;
            border-top-left-radius: 0px;
            border-bottom-left-radius: 0px;
        }

        .cart_btn {
            background-color: rgb(255, 255, 255);
            position: fixed;
            bottom: 0;
            width: 80px;
            height: 70px;
            border: 1px solid lightgray;
            border-top-left-radius: 6px;
            border-bottom-left-radius: 6px;
            text-align: center;
            padding: 6px;
        }

        #cart_btn img {
            display: block;
            margin: auto;
            height: 55px;
            width: 50px;
        }

        #cart_btn:hover img {
            transform: scale(1.1); 
        }

        .modal {
            margin: auto;
        }

        #imgs-dv .imgs {
            display: block;
        }

        #imgs-dv {

            padding: 100px;
        }
        
        #inquiry_tb>tbody>tr:hover{
		    background-color: rgb(221, 221, 221);
		    color: rgb(20, 20, 20);
		  	cursor: pointer;
        }
        
        #inquiry_btn {
            float: right;
        }
        
        #inquiry_btn2 {
        	float: right;
        }
        
        #all_review {
        	float: right;
            color: black;
            text-decoration: none;
            padding: 5px;
        }

    </style>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- 온라인 방식 -->

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


</head>
<body>
    <div class="include">
        <%@ include file="../common/header.jsp" %>
            <%@ include file="../common/navigator.jsp" %>
    </div>

	<br><br><br><br>
    <div class="outer">
        <div class="cloth-main" style="width: 600px; height: 720px;">
        	<% for (int i=0; i<prList.size(); i++) { %>
        		<% if(prList.get(i).getImageLevel() == 1) { %>
            	<img src="<%=prList.get(i).getTitleImg() %>" style="width: 100%; height:100%; margin: auto;">
            	<% } %>
           	<% } %>
        </div>
        <br><br><br><br>

        <div id="cloth-info" style="text-align: left;">
            MAN IN BLACK
         
         <div style="width: 100px" class="heart-icon" onclick="toggleLike(this)">
         		
	    		<span style="font-size: 25px; font-family: Arial, sans-serif; ">Like</span><span class="heart">&#10084;</span>
	   		
	    </div>
		
	    <script>
		 // 현재 이 게시글에 로그인한 사용자가 좋아요를 눌렀었나 검사
            
			<% if(loginUser != null) { %>
			$(function() {
                
				$.ajax({
					url : "<%= contextPath %>/likeCheck.pr",
					type : "get",
					data : {pno : <%= prList.get(0).getProductNo()%>},
					success : function(result) {
						
						console.log(result);
						
						if(result == 1) { // 좋아요누른상태
							
                            $(".heart").addClass("active");
						
						} else { // 좋아요안누른상태
							
							
						}
					},
					error : function() {
						console.log("좋아요 확인용 ajax 통신 실패!");
					}
				});
            })
            <% } %>
	        function toggleLike(heartIcon) {
	        	 <% if(loginUser != null) { %>
                var heart = heartIcon.querySelector(".heart");
	            heart.classList.toggle("active");
	            
	            	// 클레스에 요소가 있는 지없는지 검사
	             if(heart.classList.contains("active")) {
	         	// TB_LIKE 테이블에 좋아요 insert 요청
		            $.ajax({
						url : "<%= contextPath %>/linsert.pr",
						type : "get",
						data : {pno : <%= prList.get(0).getProductNo()%>},
						success : function() {
							
							// likeCheck();
							// selectLikeCount();
							console.log("성공");
						},
						error : function() {
							console.log("좋아요 ajax 통신 실패!");
						}
					});
	            } else { 
	            	console.log("좋아요 delete 요청");
	            	$.ajax({
						url : "<%= contextPath %>/ldelete.pr",
						type : "get",
						data : {pno : <%= prList.get(0).getProductNo()%> },
						success : function(result) { 
							
							// likeCheck();
							//selectLikeCount();
							console.log("성공");
						},
						error : function() {
							console.log("좋아요 ajax 통신 실패!");
						}
					});
	            } 
	        	 
	            <% } else { %>
	            	alert("로그인후 이용가능한 서비스입니다.")
	            	location.href = "<%=request.getContextPath() %>/loginPage.me";
	            <% } %>
	        }

            
	    </script>

            <h1><%= prList.get(0).getProductName()  %></h1>
            <h5 style="font-family: Arial, sans-serif;">￦<%= price %></h5> 
            <p style="font-size: 14px;">#<%= prList.get(0).getCategoryNo() %>, #<%= prList.get(0).getSubcategoryNo() %> <br>사이즈: <%=prList.get(0).getSizeNo() %></p> 
            <h3><%= prList.get(0).getProductComment() %></h3>
            <br>

            <span id="text">배송</span> 전 상품 <b>무료배송</b>
            <hr>
        </div>

        <br>

        <div id="review" style="text-align: left;">
	        <% if(rList.isEmpty()) { %>
	        
		        <!-- 문의내역이 없을 때 -->
		        <br>
		        <h5 style="text-align: center">작성된 리뷰내역이 없습니다.</h5>
		        
	        <% } else { %>
            <h4>최신리뷰</h4>
            <a id="all_review" href="<%= contextPath %>/selectAll.re?pno=<%= prList.get(0).getProductNo() %>">전체리뷰보기</a>
            <table width="100%" style="background-color: rgba(235, 231, 231, 0.24)">
            	<% for(Review r : rList) { %>
	                <tr>
	                    <td width="150px">
	                        <img src="resources/review_upfiles/<%= r.getReviewFileName() %>" style="width: 150px; height: 150px; padding: 10px;">
	                        
	                    </td>
	                    <td style="padding-left: 20PX;">
	                        <%= r.getReviewContent() %>
	                    </td>
	                    <td width="200px" align="center">
							<%= r.getReviewDate() %>        
	                    </td>
	                </tr>
	                
                <% } %>
            <% } %>
                
            </table>
        </div>

        <br>
        
        <hr>
        
        <div style="text-align: left;">
            <h5>상품정보</h5>
        </div>

        	<% for(int i=1; i<prList.size(); i++) { %>
            <div style=" width: 500px; height: 600px;" >
                <img class="imgs" src="<%= prList.get(i).getTitleImg() %>" width=100% height=100%>
            </div>
            <br>
            <% } %>
            
        
        
        
        <!-- 하단 구매하기 & 장바구니 버튼-->
        <div class="pay_cart_btn">
            <div class="pay_btn">
                <button type="button" id="pay-btn" class="btn btn-dark btn-block" style="height: 70px;" onclick="location.href='<%= contextPath %>/insert.ca?pno=<%= prList.get(0).getProductNo() %>&sname=<%= prList.get(0).getSizeNo() %>&price=<%= prList.get(0).getProductPrice() %>'">
                    구매하기
                </button>
            </div>

        </div>
        
        <hr><br>
        <h4 align="left">상품 문의</h4>
        
        <% if(loginUser != null) { %>
	            
            <!-- 글작성 버튼은 로그인한 회원만 보여져야 함 -->
            <div>
                <br>
                <a id="inquiry_btn2" href="<%= contextPath %>/enrollForm.iq?pno=<%= prList.get(0).getProductNo() %>&memberId=<%= loginUser.getMemberId() %>" class="btn btn-secondary btn-sm">
                    문의작성
                </a>
                <br><br>
                
            </div>
	                
        <% } %>
           
           
        <% if(iList.isEmpty()) { %>
        
	        <!-- 문의내역이 없을 때 -->
	        <br>
	        <h5 style="text-align: center">작성된 상품문의가 없습니다.</h5>
	        
	        
	        
        <% } else { %>
	        <div>
	             
	                
	                <table width="100%" border="1" id="inquiry_tb" class="inquiry_area">
	                    <thead style="background-color: rgb(46, 46, 46); color: white;">
	                        
	                        <tr>
                                <th width="50">문의번호</th>
	                            <th width="300">문의제목</th>
	                            <th width="80">작성자</th>
	                            <th width="150">작성일</th>
	                        </tr>
	                    </thead>
	                    <% for(Inquiry i : iList) { %>
		                    <tbody>
		                        <tr>
                                    <td><%= i.getInquiryNo() %></td>
		                            <td style="text-align: left;"><%= i.getInquiryTitle() %></td>
		                            <td><%= i.getMemberId() %></td>
		                            <td><%= i.getInquiryDate() %></td>
		                        </tr>
		                    </tbody>
		        
		
		                
	                <% } %>
                </table>
	            </div>
            
            <% } %>
                        
        </div>
        
        <script>
        	$(function() {
        		
        		$(".inquiry_area>tbody>tr").click(function() {
        			
        			let ino = $(this).children().eq(0).text();
        			
        			location.href = "<%= contextPath %>/detail.iq?ino=" + ino;
        			
        		});
        		
        	});
        </script>

        

       

        <br><br><br><br><br>
        <div class="include">
            <%@ include file="../common/footer.jsp" %>
        </div>

        <br><br><br><br>
     </div>

</body>
</html>