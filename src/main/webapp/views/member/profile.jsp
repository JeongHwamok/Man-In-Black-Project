<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    
</style>

<body>
<% 	
    	Member user=(Member)session.getAttribute("loginUser");
    	String memberId=user.getMemberId(); 
    	String memberName=user.getMemberName(); String memberPhone=(user.getMemberPhone()==null) ? "" : user.getMemberPhone();
        String memberEmail=(user.getMemberEmail()==null) ? "" : user.getMemberEmail(); Date
        memberBirthDate=user.getMemberBirthDate(); String formattedDate="" ; 
        if (memberBirthDate !=null) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
        formattedDate=sdf.format(memberBirthDate);
        }
        String memberAddress=(user.getMemberAddress()==null) ? "" : user.getMemberAddress();
        
        String updatePwd ="";
        
%>
        
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <form id="profile-form" action="<%=request.getContextPath()%>/update.me" method="post">
                        <h2 class="mb-4">사용자 프로필</h2>
                        <table class="table table-bordered">
                            <tr>
                                <th>아이디</th>
                                <td><input type="text" class="form-control" name="memberId" value="<%=memberId %>"
                                        readonly></td>
                            </tr>
                            <tr>
                                <th>이름</th>
                                <td><input type="text" class="form-control" name="memberName" value="<%=memberName %>" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td><input type="tel" class="form-control" name="memberPhone" value="<%=memberPhone %>">
                                </td>
                            </tr>
                            <tr>
                                <th>이메일 주소</th>
                                <td><input type="email" class="form-control" name="memberEmail"
                                        value="<%=memberEmail %>"></td>
                            </tr>
                            <tr>
                                <th>생년월일</th>
                                <td><input type="text" class="form-control" name="memberBirthdate"
                                        value="<%=formattedDate %>" readonly></td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td><input type="text" class="form-control" name="memberAddress"
                                        value="<%=memberAddress %>"></td>
                            </tr>
                        </table>

                        <div class="button-group text-center">
                            <button type="submit" class="btn btn-primary" onclick="return validateForm();">정보변경</button>
                            <button type="button" class="btn btn-secondary" data-toggle="modal"
                                data-target="#changePasswordModal">비밀번호변경</button>
                            <button type="button" class="btn btn-danger" data-toggle="modal"
                                data-target="#deleteAccountModal">회원탈퇴</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- 비밀번호 변경 모달 -->
        <div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog"
            aria-labelledby="changePasswordModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="changePasswordModalLabel">비밀번호 변경</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="passwordForm" action="<%=request.getContextPath()%>/updatePwd.me" method="post">
                        <div class="modal-body">
                            <input type="hidden" name="memberId" value="<%=user.getMemberId() %>">
                            <div class="form-group">
                                <label for="currentPassword">현재 비밀번호</label>
                                <input type="password" name="currentPwd1" class="form-control" id="currentPassword" required>
                            </div>
                            <div class="form-group">
                                <label for="newPassword">새 비밀번호</label>
                                <input type="password" name="updatePwd" class="form-control" id="newPassword" minlength="8" maxlength="20" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">비밀번호 확인</label>
                                <input type="password" name="checkPwd" class="form-control" id="confirmPassword" minlength="8" maxlength="20" required>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" onclick="return validatePwd();">비밀번호
                                변경</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- 회원 탈퇴 모달 -->
        <div class="modal fade" id="deleteAccountModal" tabindex="-1" role="dialog"
            aria-labelledby="deleteAccountModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteAccountModalLabel">회원 탈퇴 경고</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="<%=request.getContextPath()%>/delete.me" method="post">
                        <div class="modal-body">
                            <p>정말로 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없습니다.</p>
                            <div class="form-group">
                                <label for="confirmPasswordDelete">비밀번호 입력</label>
                                <input type="password" name="currentPwd2" class="form-control" id="confirmPasswordDelete" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-danger" onclick="return deleteMemberPwd();">회원 탈퇴</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
	        function validateForm() {
	            let memberPhone = $("input[name=memberPhone]").val();
	            let memberEmail = $("input[name=memberEmail]").val();

	         	// 10-15자리의 숫자만 허용
	            let phoneRegex = /^[0-9]{10,15}$/; 
	            
	         	// 알파벳, 숫자, 점, 특수문자 (+, %, -, _) 등이 포함될 수 있는 문자열, 뒤에 @, 도메인, 최상위 도메인(.com, .net 등)이 이어지는지 확인하는 정규식
	            let emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	            
	
	            if (!phoneRegex.test(memberPhone)) {
	                alert("전화번호가 유효하지 않습니다. 숫자만 허용되며, 10-15자리여야 합니다.");
	                return false;
	            }

	            if (!emailRegex.test(memberEmail)) {
	                alert("이메일 주소가 유효하지 않습니다. 올바른 이메일 주소를 입력하세요.");
	                return false;
	            }
	            
	           
	            
	            return true;
	        }
	        function validatePwd() {

	            let userPwd = "<%= user.getMemberPwd() %>";
	            let currentPwd1 = $("input[name=currentPwd1]").val(); // Password from input field
	            let updatePwd = $("input[name=updatePwd]").val(); // New password from input field
	            let checkPwd = $("input[name=checkPwd]").val(); // Password confirmation
	            
	            
	            if (currentPwd1 !== userPwd) {
	                alert("현재 비밀번호가 일치하지 않습니다.");
	                return false;
	            }

	            if (updatePwd !== checkPwd) {
	                alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
	                return false;
	            }

	            if (currentPwd1 === updatePwd) {
	                alert("현재 비밀번호와 새 비밀번호가 일치합니다. 새로운 비밀번호를 입력하세요.");
	                return false;
	            }
	            
	            return true;
	        }
            
            function deleteMemberPwd() {
            	let userPwd = "<%= user.getMemberPwd() %>";
            	let currentPwd2 = $("input[name=currentPwd2]").val();
            	
            	console.log(userPwd);
            	console.log(currentPwd2);
            	
            	if(userPwd == currentPwd2) {
            		
            		return true;
            	} else {
            		alert("현재 비밀번호랑 일치하지 않습니다.");
            		return false;
            	}
            	
            	
            	
            }
        </script>
</body>

</html>