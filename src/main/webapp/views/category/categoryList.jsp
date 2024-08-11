<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.category.model.vo.Category" %>

<% ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list"); %>
<% ArrayList<Category> subList = (ArrayList<Category>)request.getAttribute("subList"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
  <meta charset="UTF-8">
  <title>카테고리</title>
  <style>
    #wrap {
      width: 1000px;
      height: 975px;
      margin: auto;
      box-sizing: border-box;
    }


    .details {
      background-color: #ffffff;
      border: 1px solid #ddd;
      border-bottom: none;
      margin-bottom: 5px;
      position: relative;
    }

    .details>a {
      background-color: #f9f9f9;
    }

    .summary {
      font-weight: bold;
      padding: 10px;
      cursor: pointer;
      display: flex;
      justify-content: space-between;
      /* 타이틀과 화살표를 양 끝으로 정렬 */
      align-items: center;
    }

    .details:last-child {
      border-bottom: 1px solid #ddd;
      /* 마지막 details 요소의 테두리를 추가 */
    }

    .details[open] .summary::after {
      content: "▲";
    }

    .details:not([open]) .summary::after {
      content: "▼";
    }

    .details[open] .summary::after,
    .details:not([open]) .summary::after {
      position: absolute;
      right: 10px;
    }

    .cate a {
      display: block;
      text-decoration: none;
      color: #333;
    }

    .cate a:hover {
      background-color: #f0f0f0;
    }

    #img {
      width: 100%;
      box-sizing: border-box;
    }
  </style>
</head>
<body>
  <div id="wrap">
    <%@ include file="/views/common/header.jsp" %>
      <%@ include file="/views/common/navigator.jsp" %>
        <br>
        <table id="img" class="tb">
          <tr height="300">
			<td>
			    <a href="javascript:void(0)">
			        <img id="slacks" src="resources/images/category1.jpg" height="300" width="244" alt="슬랙스">
			    </a>
			    <!--슬랙스-->
			</td>
			<td>
			    <a href="javascript:void(0)">
			        <img id="hoodie" src="resources/images/category2.jpg" height="300" width="244" alt="후드티">
			    </a>
			    <!--후드티-->
			</td>
			<td>
			    <a href="javascript:void(0)">
			        <img id="hat" src="resources/images/category3.jpg" height="300" width="244" alt="모자">
			    </a>
			    <!--모자-->
			</td>
			<td>
			    <a href="javascript:void(0)">
			        <img id="sneakers" src="resources/images/category4.jpg" height="300" width="244" alt="스니커즈">
			    </a>
			    <!--스니커즈-->
			</td>
          </tr>
        </table>
        
        <script>
		$(document).ready(function() {
		    $("#slacks").click(function() {
		        window.location.href = "<%= request.getContextPath() %>/search.do?category=10";
		    });
		
		    $("#hoodie").click(function() {
		        window.location.href = "<%= request.getContextPath() %>/search.do?category=4";
		    });
		
		    $("#hat").click(function() {
		        window.location.href = "<%= request.getContextPath() %>/search.do?category=27";
		    });
		
		    $("#sneakers").click(function() {
		        window.location.href = "<%= request.getContextPath() %>/search.do?category=21";
		    });
		});
		</script>


        <!-- a href 값은 안넣어도 됨 임시로 넣은것 -->
        <div class="cate">
         <% for(Category c : list){ %>
          <details class="details" id="details">
           <summary class="summary"><%= c.getCategoryName() %></summary>
            <% for(Category sc : subList){ %>
             <% if(c.getCategoryNo() == sc.getCategoryNo()){ %>
			  <a href="<%= request.getContextPath()%>/search.do?category=<%= sc.getSubCategoryNo() %>"><%= sc.getSubCategoryName() %></a>
             <% } %>
            <% } %>
            </details>
         <% } %>
        </div>

        <br><br>
        <script>
          // 드롭다운은 하나만 열리게 처리하는 js
          var allDetails = document.querySelectorAll('.details');
          allDetails.forEach(function (details) {
            details.querySelector('.summary').addEventListener('click', function () {
              if (details.hasAttribute('open')) {
                return;
              }

              allDetails.forEach(function (otherDetails) {
                if (otherDetails !== details) {
                  otherDetails.removeAttribute('open');
                }
              });
            });
          });
        </script>
        <%@ include file="/views/common/footer.jsp" %>
  </div>
</body>
</html>