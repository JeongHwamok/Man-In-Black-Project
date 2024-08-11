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

    #enroll-form table {
      margin: auto;
    }

    #enroll-form input {
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
    #enroll {
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

    /* 하단 아이디 찾기 버튼 */
    #find_input_btn {
      text-align: center;
    }

    #find_btn {
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
  <!-- ../ : 현재 폴더로부터 한번 빠져나가겠다. -->
  <div class="outer">

    <br>
    <div id="logo">
      <img src="resources/images/menin.png" width="100%" height="100%">
    </div>
    <br>

    <!-- 아이디 찾기 요청 시 find.me 로 요청하고싶음!! -->
    <form id="enroll-form" action="<%request.getContextPath();%>/find.me" method="post">
      <!-- 
			menubar.jsp 를 위에서 include 했기 때문에 
			contextPath 변수를 가져다 쓸 수 있다.	
		-->

      <table>
        <div id="button">
          <div id="findId_input_btn">
            <button type="submit" id="findId_btn">아이디 찾기</button>
          </div>
          <div id="findPwd_input_btn">
            <button type="submit" id="findPwd_btn">비밀번호 찾기</button>
          </div>
          <td>&nbsp;<br>&nbsp;</td>

        </div>

        <tr>
          <td>&nbsp * 새로운 비밀번호</td>
        </tr>
        <tr>
          <td>
            <input type="password" name="userPwd" id="enroll" maxlength="15" placeholder="&nbsp; 비밀번호 (8~20자 영문, 숫자 조합)"
              required>
            <input type="checkbox">
          </td>
          <td></td>

        </tr>
        <tr>
          <td>
            <input type="password" maxlength="15" placeholder="&nbsp; 비밀번호 확인" id="enroll" required>
            <input type="checkbox">
            <!-- 
            유효성 검사로 단순 비교 확인 용도의
              값이기 때문에 굳이 name 속성을 부여 X 
          -->
          </td>
          <td></td>
        </tr>
        <td>&nbsp;</td>

      </table>

      <br>

      <div id="find_input_btn">
        <button type="submit" id="find_btn">홈으로</button>
      </div>

    </form>
    <br><br>
  </div>

</body>
</html>