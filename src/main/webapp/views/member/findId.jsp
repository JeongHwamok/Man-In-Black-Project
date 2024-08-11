<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
  <meta charset="UTF-8">
  <title>Man In Black</title>
  <style>
    body {
      font-family: 'Hahmlet-Regular';
    }


    #logo {
      width: 30%;
      height: 50%;
      margin: auto;
    }

    #button {
      display: flex;
      justify-content: center;
      ;
    }

    #findId-form table {
      margin: auto;
    }

    #findId-form input {
      margin: 5px;
    }

    /* 상단 아이디 찾기 버튼 */
    #findId_input_btn {
      text-align: center;
    }

    #findId_btn {
      width: 200px;
      height: 45px;
      margin: 30px 0px 10px 0px;
      background-color: gray;
      color: white;
      border: 1px solid gray;
    }

    /* 상단 비밀번호 찾기 버튼 */
    #findPwd_input_btn {
      text-align: center;
    }

    #findPwd_btn {
      width: 200px;
      height: 45px;
      margin: 30px 0px 10px 0px;
      background-color: black;
      color: white;
      border: 1px solid black;
    }


    /* 이메일 */
    #emailInput{
    
      width: 300px;
      height: 40px;
      margin: 15px 0px 0px 0px;
      border-top-right-radius: 5px;
      border-bottom-right-radius: 5px;
      border-top-left-radius: 5px;
      border-bottom-left-radius: 5px;
      background-color: #f7f7f7;
      border: 1px solid #f7f7f7;
    }


    #find_btn, #loginPageBtn {
      width: 308px;
      height: 55px;
      margin: 30px 0px 10px 0px;
      border-top-right-radius: 15px;
      border-bottom-right-radius: 15px;
      border-top-left-radius: 15px;
      border-bottom-left-radius: 15px;
      background-color: black;
      color: white;
      border: 1px solid black;
    }
  </style>
</head>
<body>
  <div class="outer">
    <br>
    <!-- 아이디 찾기 요청 시 로그인페이지로 이동 -->
    <form id="findId-form" action="<%= request.getContextPath() %>/loginPage.me" method="post">
      <table>
      	<tr>
	        <td id="email" style="display : none; "></td>
	    </tr>
	    
        <tr>
          <td>
            <input type="text" id="emailInput" name="email" placeholder="&nbsp; 이메일 주소를 입력해주세요">
          </td>
        </tr>
        
        <tr>
        	 <td>
	          	<button id="loginPageBtn" style="display: none;">로그인 페이지로 이동</button>
	          </td>
        </tr>
      </table>

      <br>

      <div id="find_input_btn">
        <button type="button" id="find_btn" onclick="idFind();">아이디 찾기</button>
        <script>
        	function idFind() {
        		let $email = $("#findId-form input[name=email]").val();
        		
        		$.ajax({
        			url : "<%= request.getContextPath() %>/findId.me",
        			type : "get",
        			data : { email : $email },
        			success : function(result) {
        				
        				if(result !== "NotFound") {
        					$("#email").text("아이디 : " + result);
        					
        					// 성공 시 아이디 보이기
        					$("#email").show();
        					$("#idResult").text(result);
        					$("#idResult").show();
        					
        					// 입력란과 버튼 숨기기
        					$("#email1").hide();
        					$("#emailInput").hide();
        					$("#find_btn").hide();
        					
        					// 로그인 페이지로 이동 버튼 보이기
        	                $("#loginPageBtn").show();

        				} else {
        					
        					alert("해당 이메일로 등록된 아이디가 없습니다.");
        				}
        			},
        			error : function() {
        				console.log("아이디 찾기용 ajax 통신 실패!");
        				
        			}
        		});
        	}
        	
        	// 로그인 페이지로 이동 버튼 클릭 이벤트
        	$("#loginPageBtn").click(function() {
        	    window.location.href = "loginPage.me"; // 로그인 페이지 URL로 이동
        	});
        
        </script>
      </div>

    </form>
    <br><br>
  </div>
</body>
</html>