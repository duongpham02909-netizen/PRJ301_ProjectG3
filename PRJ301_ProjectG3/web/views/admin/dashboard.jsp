<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin - Quản Lý Xe Buýt</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f5f5f5; }
        .navbar { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 20px; display: flex; justify-content: space-between; }
        .navbar a { color: white; text-decoration: none; margin-left: 20px; padding: 8px 15px; border-radius: 5px; background: rgba(255, 255, 255, 0.1); }
        .container { max-width: 1200px; margin: 30px auto; padding: 20px; }
        .section { background: white; padding: 20px; border-radius: 10px; margin: 20px 0; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
        .menu-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin: 30px 0; }
        .menu-item { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 30px; border-radius: 10px; text-align: center; text-decoration: none; transition: transform 0.3s; display: block; }
        .menu-item:hover { transform: translateY(-5px); }
        .menu-item h3 { margin-top: 15px; }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>🚌 Admin Dashboard</h2>
        <div>
            <a href="dashboard">Dashboard</a>
            <a href="users">Quản Lý User</a>
            <a href="routes">Quản Lý Tuyến</a>
            <a href="buses">Quản Lý Xe</a>
            <a href="../logout">Đăng Xuất</a>
        </div>
    </div>

    <div class="container">
        <div class="section">
            <h1>Trang Quản Trị Hệ Thống</h1>
            <p>Quản lý toàn bộ hệ thống quản lý xe buýt tuyến</p>

            <div class="menu-grid">
                <a href="users" class="menu-item">
                    <div style="font-size: 40px;">👥</div>
                    <h3>Quản Lý User</h3>
                </a>
                <a href="routes" class="menu-item">
                    <div style="font-size: 40px;">🛣️</div>
                    <h3>Quản Lý Tuyến</h3>
                </a>
                <a href="buses" class="menu-item">
                    <div style="font-size: 40px;">🚌</div>
                    <h3>Quản Lý Xe</h3>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
