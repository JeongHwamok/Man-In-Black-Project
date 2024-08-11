<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 추가본 1 -->
<%
	String contextPath = request.getContextPath();
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

		#enroll-form table {
			margin: auto;
		}

		#enroll-form input {
			margin: 5px;
		}

		/* 추가본 2 아이디, 비밀번호 */
		input[type="text"],
		input[type="password"],
		input[type="email"],
		input[type="date"] {
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
		
		input[name=memberId] {
		    width: 230px; 
		}

		/* 회원가입 버튼 */
		#enroll_input_btn {
			text-align: center;
		}

		#enroll_btn {
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

		#checkbox {
			-webkit-appearance: none;
			-moz-appearance: none;
			appearance: none;
			width: 20px;
			height: 20px;
			border-radius: 50%;
			/* 체크박스 외곽선 스타일 */
			outline: none;
			cursor: pointer;
		}
		
		/* 추가본 3 */
		.scrollable-content {
            max-height: 100px;
            max-width: 250px;
            overflow-y: auto;
            padding: 10px;
        }
        
		.agreement-text:hover {
		    color: #007bff; 
		    cursor: pointer; 
		}
        .agreement-content {
            margin-bottom: 20px;
        }
	</style>
	
	<!-- 추가본 4 -->
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

	<div class="outer">

		<br>
		<!-- 추가본 5 -->
		<div id="logo">
			<a href="<%= request.getContextPath() %>">
				<img src="resources/images/menin.png" width="100%" height="100%">
			</a>
		</div>
		<br>

		<!-- 회원가입 요청 시 insert.me 로 요청하고싶음!! -->
		<form id="enroll-form" action="<%= contextPath%>/insert.me" method="post">

		

			<table>
				<tr>
					<td>&nbsp * 아이디</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="memberId" minlength="5" maxlength="11"
							placeholder=" &nbsp;아이디(5~11자 영문,숫자 조합)" required >

						<button type="button" class="btn btn-outline-secondary"
									onclick="idCheck();">
							중복확인
						</button>
					<script>
					    function idCheck() {
					        let $memberId = $("#enroll-form input[name=memberId]");
					        let idRegex = /^[a-zA-Z0-9]{5,11}$/;  // Regular expression for ID validation
					
					        if (!idRegex.test($memberId.val())) {
					            alert("아이디는 5~11자의 영문자 또는 숫자로만 구성되어야 합니다.");
					            $memberId.focus();
					            return;  // Stop the function if validation fails
					        }
					
					        $.ajax({
					            url: "<%= contextPath %>/idCheck.me",
					            type: "get",
					            data: { checkId: $memberId.val() },
					            success: function(result) {
					                if (result == "NNNNN") {
					                    alert("이미 존재하거나 탈퇴한 회원의 아이디 입니다.");
					                    $memberId.focus();
					                } else {
					                    if (confirm("사용 가능한 아이디 입니다. 사용하시겠습니까?")) {
					                        validatePassword();  // Perform password validation if needed
					                    } else {
					                        $memberId.focus();
					                    }
					                }
					            },
					            error: function() {
					                console.log("아이디 중복체크용 ajax 통신 실패!");
					            }
					        });
					    }
					</script>
						
					</td>
				</tr>

				<td>&nbsp;</td>

				<tr>
					<td>&nbsp * 비밀번호</td>
				</tr>
				<tr>
					<td>
						<input type="password" id="pw1" name="memberPwd" maxlength="15" 
							placeholder="&nbsp; 비밀번호 (8~20자 영문, 숫자 조합)" onChange="validatePassword()" required><br>
							
						<input type="password" id="pw2" maxlength="15"
							placeholder="&nbsp; 비밀번호 확인" required onChange="validatePassword()"><br>
						<div id="result"></div>
							<script>
							function validatePassword() {
							    var pw1 = document.getElementById('pw1').value;
							    var pw2 = document.getElementById('pw2').value;
							    var resultDiv = document.getElementById('result');
							    var enrollBtn = document.getElementById('enroll_btn');

							    var passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$/;

							    if (passwordRegex.test(pw1)) {
							        if (pw1 === pw2) {
							            resultDiv.innerHTML = '&nbsp; 비밀번호가 일치합니다.';
							            resultDiv.style.color = 'green';
							            enrollBtn.disabled = false; // 비밀번호가 일치하고 유효성 검사를 통과하면 버튼 활성화
							        } else {
							            resultDiv.innerHTML = '&nbsp; 비밀번호가 일치하지 않습니다.';
							            resultDiv.style.color = 'red';
							            enrollBtn.disabled = true; // 비밀번호가 일치하지 않으면 버튼 비활성화
							        }
							    } else {
							        resultDiv.innerHTML = '&nbsp; 비밀번호 (8~20자 영문, 숫자 조합)';
							        resultDiv.style.color = 'red';
							        enrollBtn.disabled = true; // 비밀번호 유효성 검사를 통과하지 못하면 버튼 비활성화
							    }
							}
							</script>
							<div style="position: relative;">
							</div>

					</td>
				</tr>
  				
				<td></td>
				<td>&nbsp;</td>
				<tr>
					<td>&nbsp * 이름</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="memberName" maxlength="6" placeholder="&nbsp; 이름을 입력해주세요"
							required>
					</td>
					<td></td>
				</tr>
				<td>&nbsp;</td>
				<tr>
					<td>&nbsp;&nbsp;* 핸드폰 번호</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="memberPhone" maxlength="11" placeholder="&nbsp; - 제외하고 입력해주세요" required>
					</td>
					<td></td>
				</tr>
				<td>&nbsp;</td>
				<tr>
					<td>&nbsp;&nbsp;* 이메일</td>
				</tr>
				<tr>
					<td>
						<input type="email" name="memberEmail" placeholder="&nbsp; 이메일 주소를 입력해주세요" required>
					</td>
				</tr>
				<td>&nbsp;</td>
				<tr>
					<td>&nbsp;&nbsp;* 생년월일</td>
				</tr>
				<tr>
					<td>
						<input type="date" id="memberBirthDate" name="memberBirthDate" placeholder="&nbsp; YY-MM-DD 형식" required>
					</td>
				</tr>
				<td>&nbsp;</td>
				<tr>
					<td>&nbsp;&nbsp;* 주소</td>
				</tr>

				<tr>
					<td>
						<input type="text" name="memberAddress" placeholder="&nbsp; 주소를 입력해주세요" required><br><br>
						<!-- <input type="text" name="address" placeholder="&nbsp; 상세주소를 입력해주세요">  -->

					</td>
					<td></td>
				</tr>
				
				
				<table>
				<tr>
				    <td>
				        <input type="checkbox" id="checkAll" onclick="checkAllCheckboxes()" required>
				         약관 전체 동의하기
				    </td>
				</tr>
			
				<tr>
                    <td>
                        <input type="checkbox" class="subCheckbox" onchange="checkSubCheckboxes()">
                        <span class="agreement-text" onclick="toggleDetails('ageAgreementDetails')">
                            [ 필수 ] 만 14세 이상입니다.
                        </span>
                    </td>
                </tr>
                  <tr id="ageAgreementDetails" class="agreement-details" style="display: none;">
			        <td colspan="2">
			            <div class="scrollable-content">
			                <div class="agreement-content">
			                    <p>
			                        본 이용약관(이하 "약관")은 회사와 회원 간에 회사가 제공하는 인터넷 관련 서비스(이하 "서비스")의 이용과 관련하여 회사와 회원의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.
			                        회원이 서비스를 이용하기 위해서는 본 약관을 읽고 이에 동의하여야 합니다.
			                        회사는 필요 시 관련 법령 및 정책의 변경에 따라 본 약관을 수정할 수 있습니다. 수정된 약관은 회사가 운영하는 웹사이트 등을 통해 공지하며, 공지 후 회원의 서비스 이용이 있을 경우 해당 서비스 이용에는 수정된 약관이 적용됩니다.
			                        <!-- 추가적인 내용 -->
			                    </p>
			                </div>
			            </div>
			        </td>
			    </tr>
                <tr>
                    <td>
                        <input type="checkbox" class="subCheckbox" onchange="checkSubCheckboxes()">
                        <span class="agreement-text" onclick="toggleDetails('storeAgreementDetails')">
                            [ 필수 ] 맨인블랙 스토어 이용 약관
                        </span>
                    </td>
                </tr>
                <tr id="storeAgreementDetails" class="agreement-details" style="display: none;">
			        <td colspan="2">
			            <div class="scrollable-content">
			                <!-- 스토어 이용 약관 내용 -->
			                <p>제1장 총칙

							제1조(목적)
							이 약관은 주식회사 맨인블랙(이하 “회사”)가 운영하는 사이버 몰에서 제공하는 인터넷 관련 서비스를 이용함에 있어 사이버 몰과 “이용자”의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.
							
							제2조(정의)
							① “몰”이란 회사가 재화 또는 용역(이하 “재화 등”)을 “이용자”에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 등을 거래할 수 있도록 설정한 사이버몰을 말합니다.
							② “이용자”란 “몰”에 접속하여 이 약관에 따라 “회사”가 제공하는 서비스를 받는 회원 및 비회원을 말합니다.
							 <p>
			            </div>
			        </td>
			    </tr>
                <tr>
                    <td>
                        <input type="checkbox" class="subCheckbox" onchange="checkSubCheckboxes()">
                        <span class="agreement-text" onclick="toggleDetails('privacyAgreementDetails')">
                            [ 필수 ] 개인정보 수집 및 이용 동의
                        </span>
                    </td>
                </tr>
                <tr id="privacyAgreementDetails" class="agreement-details" style="display: none;">
			        <td colspan="2">
			            <div class="scrollable-content">
			                <!-- 개인정보 수집 및 이용 동의 내용 -->
			                <p>(주)무신사는 아래의 목적으로 개인정보를 수집 및 이용하며, 회원의 개인정보를 안전하게 처리하는데 최선을 다하고 있습니다. 아래의 내용을 확인 후 동의하여 주시기 바랍니다.
								1.수집 및 이용 목적
								
								회원 가입 의사 확인, 회원제 서비스 제공
								이용자 식별, 본인 인증
								서비스 및 상품 제공에 관한 계약 이행 및 요금 정산
							</p>
			            </div>
			        </td>
			    </tr>
		</table>
		<script>
		    function checkAllCheckboxes() {
		        var checkAll = document.getElementById('checkAll');
		        var subCheckboxes = document.getElementsByClassName('subCheckbox');
		
		        // 약관 전체 동의 체크박스의 상태에 따라 하위 체크박스들의 상태 변경
		        for (var i = 0; i < subCheckboxes.length; i++) {
		            subCheckboxes[i].checked = checkAll.checked;
		        }
		    }
		
		    function checkSubCheckboxes() {
		        var subCheckboxes = document.getElementsByClassName('subCheckbox');
		        var checkAll = document.getElementById('checkAll');
		
		        // 하위 체크박스들이 모두 선택되어 있는지 확인
		        var allChecked = true;
		        for (var i = 0; i < subCheckboxes.length; i++) {
		            if (!subCheckboxes[i].checked) {
		                allChecked = false;
		                break;
		            }
		        }
		
		        // 모든 하위 체크박스가 선택되어 있다면 약관 전체 동의하기 체크박스도 선택
		        checkAll.checked = allChecked;
		    }
		    
		    function toggleDetails(elementId) {
		        var element = document.getElementById(elementId);
		        if (element.style.display === 'none') {
		            element.style.display = 'table-row';
		        } else {
		            element.style.display = 'none';
		        }
		    }
		    
		</script>
		
		
						
			</table>

			<br>

			<div id="enroll_input_btn">
				<button type="submit" id="enroll_btn" onclick="validatePassword()" disabled>회원가입</button>
			</div>

		</form>

		<br><br>
		<%@ include file="../common/footer.jsp" %>
	</div>
	
	
	
	
	
	
	
	
	
</body>
</html>