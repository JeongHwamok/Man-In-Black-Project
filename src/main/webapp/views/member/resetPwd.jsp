<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    .outer {
      width: 1000px;
      margin: auto;
      margin-top: 50px;
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

    #resetPwd-form table {
      margin: auto;
    }

    #resetPwd-form input {
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
    }


    /* 이메일 */
    #enroll, {
      width: 300px;
      height: 40px;
      margin: 15px 0px 0px 0px;
      border-top-right-radius: 5px;
      border-bottom-right-radius: 5px;
      border-top-left-radius: 5px;
      border-bottom-left-radius: 5px;
      background-color: #f7f7f7;
    }

    /* 하단 아이디 찾기 버튼 */
    #find_input_btnPwd {
      text-align: center;
    }
	/* 이메일, 아이디 */
    #emailInputPwd, #idInputPwd {
    
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
    /* 비밀번호 재설정, 로그인페이지로 이동 버튼 */
    #find_btnPwd, #loginPageBtnPwd {
      width: 308px;
      height: 55px;
      margin: 30px 0px 10px 0px;
      border-top-right-radius: 15px;
      border-bottom-right-radius: 15px;
      border-top-left-radius: 15px;
      border-bottom-left-radius: 15px;
      background-color: black;
      color: white;
    }
  </style>
  	
  	<!-- 부트스트랩 CSS 파일 가져오기 -->
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<!-- Popper JS -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
	
</head>
<body>


<div class="outer">
    <!-- 비밀번호 찾기 요청 시 로그인페이지로 이동 -->
    <form id="resetPwd-form" action="<%= request.getContextPath() %>/loginPage.me" method="post">
      <table>
      	<tr>
	        <td id="resetPwd" style="display : none; "></td><br>
	    </tr>
	    
        <tr>
          <td>
            <input type="text" id="emailInputPwd" name="email" placeholder="&nbsp; 이메일 주소를 입력해주세요">
          </td>
        </tr>
        
      	<tr>
          <td>
            <input type="text" id="idInputPwd" name="memberId" placeholder="&nbsp; 아이디를 입력해주세요">
          </td>
        </tr>
		<tr>
		    <td>
		        <button type="button" id="find_btnPwd" onclick="resetPwd()">비밀번호 재설정</button>
		   </td>
        </tr>
        
        <tr>
        	 <td>
	          	<button type="button" id="loginPageBtnPwd" onclick="changePwd()" style="display: none;">로그인 페이지로 이동</button>
	          </td>
        </tr>
      </table>

        <script>
        	function resetPwd() {
        		let $email = $("#resetPwd-form input[name=email]").val();
        		let $memberId = $("#resetPwd-form input[name=memberId]").val();
        		let $newPwd = $("#resetPwd-form input[name=newPwd]").val(); 

        		$.ajax({
        			url : "<%= request.getContextPath() %>/resetPwd.me",
        			type : "get",
        			data : { 
        				email : $email,
        				memberId : $memberId,
        				},
        				
        			success : function(response) {
        				if(response !== "NotFound") {
        					$("#resetPwd").text("비밀번호 : " + response);
        					$("#resetPwd").show();
        					$("#newPwdInput1").show();
                        	$("#newPwdInput2").show();
        					
        					// 입력란과 버튼 숨기기
        					$("#emailInputPwd").hide(); 
							$("#idInputPwd").hide();
							$("#find_btnPwd").hide();
        					
        					// 로그인 페이지로 이동 버튼 보이기
							$("#loginPageBtnPwd").show();

        				} else {
        					
        					alert("이메일과 아이디를 잘못 입력했습니다.");
        				}
        			},
        			error : function() {
        				console.log("비밀번호 찾기용 ajax 통신 실패!");
        				
        			}
        		});
        	}

        	// 로그인 페이지로 이동 버튼 클릭 이벤트
        	$("#loginPageBtnPwd").click(function() {
        	    window.location.href = "loginPage.me"; // 로그인 페이지 URL로 이동
        	});
        </script>

    </form>
    <br><br>
  </div>

</body>
</html>