<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.common.model.vo.PageInfo, com.kh.product.model.vo.Product" %>
<%
    int rowNumbers = 15;
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	ArrayList<Product> prList = (ArrayList<Product>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
<meta charset="UTF-8">
<title>Man In Black</title>
<style>
    .wrap{
        width: 1000px;
        margin: auto;
        box-sizing: border-box;
    }
    
	.container{
		width: 100%;
		margin: 0px;
		padding: 0px;
		
	}

	
	.item{
		display: inline-block;
        box-sizing: border-box;
        width: 33%;
        padding-left: 3%;
        padding-top: 1%;

	}

	#likeId {
		font-size: 15px; 
		color: red; 
		text-align: left;
	}
	
	.categoryStyle {
		font-size: 12px;
	}
	
	.image {
	
		height: 300px;
	
	}

</style>
</head>
<body>

<div class="wrap">
	
	<div class="container">
		<!-- 반복되는 아이템 시작 -->
		
		
				<div class="item">
					<div class="image">
						<!-- 여기에 이미지 태그를 넣거나 배경 이미지를 사용할 수 있습니다 -->
						
					</div>
					<div class="text">
						
					</div> 
				</div>
		
		
				<!-- 반복되는 아이템 끝 -->
	</div>
	<div class="pagination" align="center">

	</div>
	


	<script>
		$(function(){
			
			selectProductList();
			
			// 1초 간격마다 selectReplyList 함수 실행
			setInterval(selectProductList, 10000);
		});
		
		function selectProductList() {
	
			$.ajax({
				
				url : "<%= request.getContextPath() %>/productList.pr",
				type : "get",
				data: {currentPage : <%= request.getParameter("currentPage") %>},
				success : function(result) {
					let list = result["list"];
					let pi = result["pi"];
					let likeCount = result["likeCount"];
				
					
					let str = "";
					list.forEach(e => {
						
						str += "<div class='item' onclick='itemClick("+e.productNo+");'>";
						str += "<div class='image'>";
						str += "<img src='" + e.titleImg + "' width='100%' height='100%'>";
						str += "</div>"
						str += "<span id='likeId' style='font-size: 13px;'> &#10084;</span><span class='likeCount' style='font-size: 14px;'></span><br><span class='categoryStyle'>"+'#'+e.categoryNo+", #"+ e.subcategoryNo +"</span>";
						str += " <p style='margin: 0px; font-size: 15px; font-weight:900;'>" + e.productName + "</p> ";
						str += "<div class='text'>"
						str += "<p style='font-family: Arial, sans-serif;'>"+'￦'+"" + e.productPrice.toLocaleString();  + " <br></p>"
						str += "</div>"
						str += "</div>"
					});
					
					$(".container").html(str);
					
					
					for (let i = 0; i < likeCount.length; i++) {
                        $(".likeCount").get(i).innerText = likeCount[i] ;
                    }
					
					if(location.pathname != "<%= contextPath %>" + "/"){
						// <!-- <div class="pagination" align="center">
						// 	페이징 
						// 	<a href="#">&lt; prev</a>
						// 	<a href="#">1</a>
						// 	<a href="#" class="active">2</a>
						// 	<a href="#">3</a>
						// 	<a href="#">4</a>
						// 	<a href="#">next &gt;</a>
						// </div> -->
						let startPage = pi.startPage;
						
						let endPage = Math.min(pi.endPage, pi.maxPage);
						let str = "";

						if(pi.currentPage != 1){
								str += '<a href="#">&lt; prev</a>'
							}
						let i;
						for(i = startPage; i <= endPage; i++){
							
							str += '<input type="button" class="btn btn-secondary btn-sm" value="' + i + '" onclick="location.href=';
							str += "<%= contextPath %>";
							str += '/search.do?currentPage=' + i + "'" + '">';
							
						}

						if(pi.currentPage != pi.maxPage){
							str += '<a href="#">next &gt;</a>'
						}

						$(".pagination").html(str);

					}

				},
				error : function() {
				}
				

			});
		}


		function itemClick(pno) {

			location.href="<%= contextPath %>/productDetail.pr?pno="+ pno

		}

		

	</script>
	

</div>
</body>
</html>