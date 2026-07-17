<%-- 
    Document   : login
    Created on : 11 Jul 2026, 21:53:53
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
    String info = (String) request.getAttribute("info");
    String username = (String) request.getAttribute("username");

    if (username == null) {
        username = "";
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login - Bus Management</title>

        <style>
            *{
                margin:0;
                padding:0;
                box-sizing:border-box;
                font-family:Arial;
            }

            body{
                min-height:100vh;
                background:#f8f9fa;
            }

            .container{
                display:flex;
                min-height:100vh;
            }

            .left{
                width:50%;
                background:
                    linear-gradient(rgba(44,82,130,.9),rgba(49,130,206,.85)),
                    url('https://img.magnific.com/hinh-chup-mien-phi/xe-buyt-truong-hoc-mau-vang_268835-4931.jpg?semt=ais_hybrid&w=740&q=80');
                background-size:cover;
                background-position:center;
                display:flex;
                justify-content:center;
                align-items:center;
                color:white;
                text-align:center;
                padding:40px;
            }

            .left-content{
                max-width:350px;
            }

            .left h1{
                margin-bottom:20px;
            }

            .left p{
                line-height:1.6;
            }

            .right{
                width:50%;
                display:flex;
                justify-content:center;
                align-items:center;
            }

            .login-box{
                width:400px;
            }

            .logo{
                text-align:center;
                margin-bottom:20px;
            }

            .logo a{
                text-decoration:none;
                color:#0d6efd;
                font-size:24px;
                font-weight:bold;
            }

            .card{
                background:white;
                padding:30px;
                border-radius:8px;
                box-shadow:0 0 15px rgba(0,0,0,.15);
            }

            .card h2{
                text-align:center;
                margin-bottom:25px;
            }

            .form-group{
                margin-bottom:15px;
            }

            label{
                display:block;
                margin-bottom:5px;
                font-weight:bold;
            }

            input[type=text],
            input[type=password]{
                width:100%;
                padding:10px;
                border:1px solid #ccc;
                border-radius:4px;
            }

            .btn{
                width:100%;
                padding:12px;
                border:none;
                background:#0d6efd;
                color:white;
                font-size:16px;
                border-radius:4px;
                cursor:pointer;
            }

            .btn:hover{
                background:#0b5ed7;
            }

            .alert{
                padding:10px;
                margin-bottom:15px;
                border-radius:4px;
            }

            .danger{
                background:#f8d7da;
                color:#842029;
            }

            .success{
                background:#d1e7dd;
                color:#0f5132;
            }

            .info{
                background:#cff4fc;
                color:#055160;
            }
        </style>

    </head>

    <body>

        <div class="container">

            <div class="left">
                <div class="left-content">
                    <h1>Welcome Back!</h1>

                    <p>
                        Login to access the School Bus Management System
                        and manage transportation information.
                    </p>
                </div>
            </div>

            <div class="right">

                <div class="login-box">

                    <div class="logo">
                        <a href="<%=request.getContextPath()%>/">
                            Bus Management System
                        </a>
                    </div>

                    <div class="card">

                        <h2>Sign In</h2>

                        <% if (error != null) { %>
                        <div class="alert danger">
                            <%=error%>
                        </div>
                        <% } %>

                        <% if (success != null) { %>
                        <div class="alert success">
                            <%=success%>
                        </div>
                        <% } %>

                        <% if (info != null) { %>
                        <div class="alert info">
                            <%=info%>
                        </div>
                        <% } %>

                        <form action="<%=request.getContextPath()%>/login" method="post">

                            <div class="form-group">
                                <label>Username</label>

                                <input
                                    type="text"
                                    name="username"
                                    value="<%=username%>"
                                    placeholder="Enter your username"
                                    required
                                    autofocus>
                            </div>

                            <div class="form-group">
                                <label>Password</label>

                                <input
                                    type="password"
                                    name="password"
                                    placeholder="Enter your password"
                                    required>
                            </div>

                            <input class="btn" type="submit" value="Login">

                        </form>

                    </div>

                </div>

            </div>

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

