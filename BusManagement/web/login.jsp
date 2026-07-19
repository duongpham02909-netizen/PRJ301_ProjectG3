<%-- 
    Document   : login
    Created on : 11 Jul 2026, 21:53:53
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập - Hệ Thống Quản Lý Xe Buýt</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .login-container {
            background: white;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.25);
            width: 100%;
            max-width: 420px;
            padding: 40px 35px;
            border-top: 5px solid #667eea;
        }
        .logo {
            text-align: center;
            margin-bottom: 30px;
        }
        .logo h1 {
            font-size: 30px;
            color: #764ba2;
            margin-bottom: 8px;
            font-weight: 700;
        }
        .logo p {
            color: #718096;
            font-size: 14px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #4a5568;
            font-weight: 600;
            font-size: 14px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid #cbd5e0;
            border-radius: 6px;
            font-size: 14px;
            color: #2d3748;
            transition: all 0.3s;
            outline: none;
        }
        input[type="text"]:focus, input[type="password"]:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);
        }
        .submit-btn {
            width: 100%;
            padding: 13px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s, box-shadow 0.2s;
            margin-top: 10px;
        }
        .submit-btn:hover {
            transform: translateY(-1px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }
        .error-message {
            background-color: #fff5f5;
            color: #c53030;
            padding: 12px 15px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 14px;
            border-left: 4px solid #f56565;
            font-weight: 500;
        }
        .demo-credentials {
            background-color: #f7fafc;
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            padding: 15px;
            margin-top: 25px;
            font-size: 12px;
            color: #718096;
            line-height: 1.6;
        }
        .demo-credentials strong {
            display: block;
            margin-bottom: 8px;
            color: #4a5568;
            font-size: 13px;
        }
        .demo-credentials p {
            margin: 3px 0;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="logo">
            <h1>🚌 Bus Management</h1>
            <p>Hệ Thống Quản Lý Đưa Đón Học Sinh</p>
        </div>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="error-message">
                <%= error %>
            </div>
        <%
            }
        %>

        <form method="post" action="login">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" required autofocus placeholder="Nhập tên đăng nhập...">
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" required placeholder="Nhập mật khẩu...">
            </div>

            <button type="submit" class="submit-btn">Đăng Nhập</button>
        </form>

        <div class="demo-credentials">
            <strong>Tài khoản demo kiểm thử:</strong>
            <p>🔑 <strong>Admin:</strong> admin01 / admin123456</p>
            <p>🔑 <strong>Manager:</strong> manager01 / manager123456</p>
            <p>🔑 <strong>Parent:</strong> parent01 / parent123456</p>
            <p>🔑 <strong>Driver:</strong> driver01 / driver123456</p>
        </div>
    </div>
</body>
</html>


