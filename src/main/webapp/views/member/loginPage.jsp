<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	
	String alertMsg = (String)session.getAttribute("alertMsg");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 페이지</title>
    <style>
       

        div {
            /* border: 1px solid red; */
            box-sizing: border-box;
        }

        .wrap {
            width: 1000px;
            height: 775px;
            margin: auto;
            box-sizing: border-box;
        }

        #content {
            width: 70%;
            height: 70%;
            margin: auto;
            margin-top: 3%;
            box-sizing: border-box;

        }

        #logo {
            width: 30%;
            height: 50%;
            margin: auto;
        }

        #login_form {
            width: 100%;
        }

        #login_input_id_pwd {
            margin: 0px;
            text-align: center;
        }

        #login_id,
        #login_pwd {
            width: 300px;
            height: 55px;
            margin: 15px 0px 0px 0px;
            border-top-right-radius: 15px;
            border-bottom-right-radius: 15px;
            border-top-left-radius: 15px;
            border-bottom-left-radius: 15px;
            background-color: #f7f7f7;
            border: 1px solid #f7f7f7;
        }

        #login_input_btn {
            text-align: center;
        }

        #login_btn {
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

        #login_form_etc {
            text-align: center;
            margin: auto;
            width: 100%;
        }

        #login_form_etc>a {
            text-decoration: none;
            /* 하이퍼링크 밑줄 제거 */
            color: black;
            font-size: 12px;
            font-weight: 200px;
        }

        /* footer 영역 */
        #footer>div {
            width: 100%;
        }

        #footer_1 {
            height: 20%;
        }

        #footer_2 {
            height: 80%;
        }

        #footer {
            margin-top: 4.4%;
            text-align: center;
            height: 20%;
        }

        #footer_2>p {
            /* border: 1px solid blue;  */
            width: 100%;
            margin: 0px;
            /* 
                p 요소는 기본적으로 기본 스타일로
                위 아래에 margin 이 들어감!!
            */
            box-sizing: border-box;
            font-size: 8px;

        }

        #p1 {
            height: 70%;
            padding: 5px 15px;
        }

        #p2 {
            height: 30%;
            text-align: center;
            /* 가운데 정렬 */
        }

        #footer_1>a {
            text-decoration: none;
            /* a태그 밑줄 없애기 */
            color: black;
            font-weight: 550;
            /* 글자의 굵기 사이즈 조정 */
            margin: 15px;
            /* 바깥 영역 범위 조정 */
            vertical-align: middle;
            /* 수직 구조에서 가운데로 오게끔 해주는 속성 */
            font-size: 10px;
        }
    </style>
</head>
<body>
    <script>
		// 자바스크립트 구문을 쓸 수 있는 영역
		// script 태그 내에서 스크립틀릭과 같은 jsp 요소를 쓸 수 있다.
		
		<%
			// 자바영역
		%>

            // 알람 문구를 담을 자바스크립트 변수 선언
            let msg = "<%= alertMsg %>";
        // "성공적으로 로그인이 되었습니다." / "null"

        if (msg != "null") { // 
            // 알람문구가 있다면
            alert(msg);
			// 알림창을 띄워준 후 session 에 담긴 해당 메세지는
			// 지워줘야만 함
			// > 안그러면 menubar.jsp가 로딩 될때마다 (새로고침)
			//   매번 alert 가 계속 뜨기 때문
			<% session.removeAttribute("alertMsg");%>
		}


    </script>
    <div class="wrap">
        <div id="content">
            <form action="login.me" method="post" id="login_form">
                <div id="logo">
                    <a href="<%= request.getContextPath()%>">
                        <img src="resources/images/menin.png" width="100%" height="100%">
                    </a>
                </div>
                <div id="login_input_id_pwd">
                    <input type="text" id="login_id" placeholder=" 아이디" name="memberId"> <br>
                    <input type="password" id="login_pwd" placeholder=" 비밀번호" name="memberPwd">
                </div>
                <div id="login_input_btn">
                    <input type="submit" id="login_btn" value="로그인">
                </div>
            </form>
            <div id="login_form_etc">
                <a href="<%= request.getContextPath()%>/enrollPage.me">회원가입</a>
                <a href="<%= request.getContextPath()%>/findAccount.me">ID/PWD 찾기</a>
            </div>
        </div>
        <%@ include file="../common/footer.jsp" %>
    </div>
</body>
</html>