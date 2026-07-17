<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng Nhập</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f6f9;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .login-container {
                background: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0,0,0,0.1);
                width: 350px;
            }
            h2 {
                text-align: center;
                color: #2c3e50;
                margin-bottom: 20px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                font-weight: bold;
                display: block;
                margin-bottom: 5px;
                color: #555;
            }
            input[type="text"], input[type="password"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            .btn-login {
                background-color: #3498db;
                color: white;
                padding: 12px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                width: 100%;
                font-size: 16px;
                font-weight: bold;
            }
            .btn-login:hover {
                background-color: #2980b9;
            }
            .error-msg {
                color: #e74c3c;
                background: #fde8e7;
                padding: 10px;
                border-radius: 4px;
                border: 1px solid #f5c6cb;
                margin-bottom: 15px;
                font-size: 14px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>HỆ THỐNG XE BUÝT</h2>
            <c:if test="${not empty error}">
                <div class="error-msg">${error}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label>Tên đăng nhập:</label>
                    <input type="text" name="username" required placeholder="driver01">
                </div>
                <div class="form-group">
                    <label>Mật khẩu:</label>
                    <input type="password" name="password" required placeholder="driver123456">
                </div>
                <button type="submit" class="btn-login">Đăng Nhập</button>
            </form>
        </div>
    </body>
</html>