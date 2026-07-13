<%-- 
    Document   : login
    Created on : 11 Jul 2026, 21:53:53
    Author     : Admin
--%>

﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - Bus Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                min-height: 100vh;
            }
            .auth-image {
                background: linear-gradient(135deg, rgba(44,82,130,0.9), rgba(49,130,206,0.85)),
                    url('https://img.magnific.com/hinh-chup-mien-phi/xe-buyt-truong-hoc-mau-vang_268835-4931.jpg?semt=ais_hybrid&w=740&q=80') center/cover;
                min-height: 100vh;
            }
        </style>
    </head>
    <body>
        <div class="row g-0 min-vh-100">
            <!-- Left image panel -->
            <div class="col-lg-6 auth-image d-none d-lg-flex flex-column justify-content-center align-items-center p-5 text-white">
                <h1 class="fs-2 fw-bold mb-3 text-center">Welcome Back!</h1>
                <p class="text-center opacity-90" style="max-width:350px; line-height:1.6;">
                    Login to access the School Bus Management System and manage transportation information.
                </p>
            </div>

            <!-- Right form panel -->
            <div class="col-lg-6 d-flex align-items-center justify-content-center p-4 bg-light">
                <div style="width:100%; max-width:400px;">
                    <div class="text-center mb-4">
                        <a href="${pageContext.request.contextPath}/" class="text-primary fw-bold fs-5 text-decoration-none">
                            Bus Management System
                        </a>
                    </div>

                    <div class="card shadow-sm border-0 p-4">
                        <h2 class="text-center mb-4 fs-5 fw-bold text-dark">Sign In</h2>

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>
                        <c:if test="${not empty success}">
                            <div class="alert alert-success">${success}</div>
                        </c:if>
                        <c:if test="${not empty info}">
                            <div class="alert alert-info">${info}</div>
                        </c:if>

                        <form method="post" action="${pageContext.request.contextPath}/login">
                            <div class="mb-3">
                                <label for="username" class="form-label fw-medium small">Username</label>
                                <input type="text" id="username" name="username"
                                       value="${username}" required class="form-control"
                                       placeholder="Enter your username" autofocus>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label fw-medium small">Password</label>
                                <input type="password" id="password" name="password" required class="form-control"
                                       placeholder="Enter your password">
                            </div>
                            <div class="d-grid mt-4">
                                <button type="submit" class="btn btn-primary fw-semibold">Login</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

