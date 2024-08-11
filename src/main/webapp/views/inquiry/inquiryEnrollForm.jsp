<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	int productNo = Integer.parseInt(request.getParameter("pno"));
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

        .inquiryEnroll_tb {
            width: 1000px;
            height: 975px;
            margin: auto;
        }

        #inquiryEnroll_tb { width: 80%; margin: auto; border-spacing: 0; border-color: lightgray;}
        #inquiryEnroll_tb textarea {width: 99%;}
        #inquiryEnroll_tb td {text-align: left;}
        #inquiryEnroll_tb hr {border: 1px solid lightgray;}

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
    <div class="inquiryEnroll_tb">

        <div class="outer2">
            <%@ include file="/views/common/header.jsp" %>
            <%@ include file="/views/common/navigator.jsp" %>

            <br><br>
            <h4 align="center">문의사항 작성하기</h4>
            <br>
            <form action="<%= contextPath %>/insert.iq" method="post">
                
                <table  id="inquiryEnroll_tb">
                    <tr height="50px">
                        <th>문의 제목</th>
                        <td><input type="text" name="inquiryTitle" style="width: 100%;" maxlength="40" required></td>
                    </tr>
                    <tr height="50">
                        <th colspan="2">
                            <hr>
                        </th>
                    </tr>
                    <tr>
                        <th>문의 내용</th>
                        <th>
                            <textarea name="inquiryComment" 
                                        id="inquiryComment" 
                                        cols="30" rows="10" 
                                        style="resize: none; width: 100%;"
                                        placeholder="" maxlength="400" required></textarea>
                        </th>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="font-size: small;">
                            - 상품과 관계없는 내용은 삭제 처리 될 수 있습니다. <br>
                            - 작성된 리뷰는 삭제 전까지 ‘문의사항’에 공개되고, ‘마이페이지 > 문의내역’에서 삭제가 가능합니다.
                        </td>
                    </tr>


                </table>
                <input type="hidden" name="memberId" value="<%= loginUser.getMemberId() %>">
                <input type="hidden" name="productNo" value="<%= productNo %>">
                <br><br>
                <div align="center">
                    <button type="submit" id="submitButton" class="btn btn-dark btn-sm">작성 완료</button>&nbsp;&nbsp;
                    <a type="button" class="btn btn-secondary btn-sm" href="<%=contextPath %>/productDetail.pr?pno=<%= productNo %>">작성 취소</a>
                </div>
            </form>
            <br><br><br>
        </div>

        <%@ include file="../common/footer.jsp" %>
    </div>
    
</body>
</html>