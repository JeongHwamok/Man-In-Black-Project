<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="com.kh.member.model.vo.Member"%>
<!DOCTYPE html>
<%
	

%>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <title>선택된 요소 삭제하기</title>
    <style>
        

        .row {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            flex-wrap: wrap;
            /* 여기에 줄바꿈을 추가 */
            width: 800px;
        }

        .close-btn {
        background-color: transparent;
        border: none;
        cursor: pointer;
        color:  black;
        font-size: 16px;
        }

        .image {
            margin-right: 10px;
        }

        .text span {
            display: block;
            /* span을 블록 요소로 변경하여 행으로 나오게 함 */
            margin-right: 10px;
            /* 텍스트 사이의 간격을 위한 마진 추가 */
        }

        .text {
            flex-grow: 1;
        }

        .pagination {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    
	<br>
	<h4>찜 목록</h4>
	<hr>
    <div id="likeContent">
        <!-- 첫 번째 행
            <div class="row">
                <div class="image"><img src="resources/images/dam.png" width="100px" height="120px"></div>
                <div class="text">
                    <span style="font-size: 15px;">상품명이 들어갈 자리</span>
                    <span style="font-size: 10px;">#카테고리, #서브카테고리</span>
                    <span style="font-size: 10px;">사이즈 : </span>
                    <span style="font-size: 13px;">상품가격</span>
                </div>
                <button class="close-btn">X</button>
            </div>
        -->
    </div>

    <script>
    
    	
    
	    $(function(){
			
	    	likeSelectList();
			
			// 1초 간격마다 selectReplyList 함수 실행
			setInterval(likeSelectList, 1000);
		});
    
	    
            function likeSelectList() {
            $.ajax({
                url : "<%= request.getContextPath() %>/like.me",
                type : "get",
                data : {mno : <%= loginUser.getMemberNo() %>},
                success : function(list) {
                	
                	
                	let str2 = "";
                	
                	if(list.length===0){
                		 str2 = "<p>좋아요한 상품이 존재하지 않습니다.</p>";
                	} else {
	    				list.forEach(e => {
	    					str2 += '<input type="hidden" name="pno" value="'+e.productNo+'">';
	    					str2 += '<div class="row">';
	    					str2 += '<div class="image"><img src="'+e.titleImg+'" width="100px" height="120px"></div>';
	    					str2 += '<div class="text">';
	    					str2 += '<span style="font-size: 15px;"><b>'+e.productName+'</b></span>';
	    					str2 += '<span style="font-size: 10px;">#'+e.categoryNo+', #'+e.subcategoryNo+'</span>';
	    					str2 += '<span style="font-size: 10px;">사이즈 : '+e.sizeNo+'</span> <br>';
	    					str2 += "<span style='font-family: Arial, sans-serif; font-size: 13px;'>"+'￦'+"" + e.productPrice.toLocaleString();  + "</span>";
	    					str2 += '</div>';
	    					str2 += '<button class="close-btn" onclick="likesClick();">X</button>';
	    					str2 += '</div>';
	    					str2 += '<hr>';
	    				});
                	}
    				$("#likeContent").html(str2);
                	
                	
                }, 
                error : function() {
					console.log("좋아요 확인용 ajax 통신 실패!");
				}
                
                
            });
         }

            function likesClick() {
         		let likePno = $("input[name='pno']").val();
         		
            	$.ajax({
            		
    				url : "<%= request.getContextPath() %>/ldelete.pr",
    				type : "get",
    				data : {pno : likePno},
    				success : function(result) { 
    					
    					console.log("성공");
    				},
    				error : function() {
    					console.log("좋아요 ajax 통신 실패!");
    					
    				}
    			});
         		
         		
         		
         	}



    </script>
    
    
</body>
</html>