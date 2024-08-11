<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/favicon.ico">
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        #container {
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
        }

        #error-img {
            width: 250px;
            height: 250px;
            margin-top: 20vh;
        }

        #error-message {
            font-size: 25px;
            color: #333;
            margin-top: 10vh;
            text-align: center;
        }
    </style>
</head>
<body>
    <div id="container">
        <img id="error-img" src="<%= request.getContextPath() %>/resources/images/error.png" alt="Error">
        <p id="error-message">예상치 못한 오류가 발생했습니다. 나중에 다시 시도해주세요.</p>
    </div>
</body>
</html>

