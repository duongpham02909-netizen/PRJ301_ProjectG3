<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Báo Cáo Thống Kê - Quản Lý Xe Buýt</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f5f5f5; }
        .navbar { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 20px; display: flex; justify-content: space-between; }
        .navbar a { color: white; text-decoration: none; margin-left: 20px; padding: 8px 15px; border-radius: 5px; background: rgba(255, 255, 255, 0.1); }
        .container { max-width: 1200px; margin: 20px auto; padding: 20px; }
        .section { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); margin: 20px 0; }
        .stats-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin: 20px 0; }
        .stat-card { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 20px; border-radius: 10px; text-align: center; }
        .stat-card h3 { font-size: 32px; margin: 10px 0; }
        .stat-card p { font-size: 14px; opacity: 0.9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background: #f0f4ff; padding: 12px; text-align: left; border-bottom: 2px solid #667eea; }
        td { padding: 12px; border-bottom: 1px solid #ddd; }
        tr:hover { background: #f9f9f9; }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>🚌 Admin - Báo Cáo Thống Kê</h2>
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
            <h1>Báo Cáo Thống Kê Hệ Thống</h1>

            <div class="stats-grid">
                <div class="stat-card">
                    <p>Tổng Người Dùng</p>
                    <h3>45</h3>
                    <p>6 phụ huynh, 8 quản lý, 10 lái xe, 1 admin</p>
                </div>
                <div class="stat-card">
                    <p>Tổng Học Sinh</p>
                    <h3>250</h3>
                    <p>Đã đăng ký tuyến xe</p>
                </div>
                <div class="stat-card">
                    <p>Tổng Tuyến Xe</p>
                    <h3>8</h3>
                    <p>Hoạt động</p>
                </div>
                <div class="stat-card">
                    <p>Tổng Xe Buýt</p>
                    <h3>12</h3>
                    <p>Đang hoạt động</p>
                </div>
            </div>
        </div>

        <div class="section">
            <h2>Báo Cáo Chi Tiết Chuyến Xe Hôm Nay</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tuyến</th>
                        <th>Xe</th>
                        <th>Giờ Khởi Hành</th>
                        <th>Giờ Kết Thúc</th>
                        <th>Số Học Sinh Lên</th>
                        <th>Trạng Thái</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Tuyến A</td>
                        <td>BUS_001</td>
                        <td>06:48</td>
                        <td>07:15</td>
                        <td>28/45</td>
                        <td><span style="background: #d4edda; padding: 5px 10px; border-radius: 3px;">Hoàn Thành</span></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Tuyến B</td>
                        <td>BUS_002</td>
                        <td>06:50</td>
                        <td>-</td>
                        <td>32/45</td>
                        <td><span style="background: #fff3cd; padding: 5px 10px; border-radius: 3px;">Đang Chạy</span></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="section">
            <h2>Báo Cáo Tình Trạng Xe</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mã Xe</th>
                        <th>Biển Số</th>
                        <th>Tuyến</th>
                        <th>Tình Trạng</th>
                        <th>Ghi Chú</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>BUS_001</td>
                        <td>29F-12345</td>
                        <td>Tuyến A</td>
                        <td><span style="background: #d4edda; padding: 5px 10px; border-radius: 3px;">Bình Thường</span></td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>BUS_002</td>
                        <td>29F-12346</td>
                        <td>Tuyến B</td>
                        <td><span style="background: #f8d7da; padding: 5px 10px; border-radius: 3px;">Có Vấn Đề</span></td>
                        <td>Hệ thống điều hòa bị hỏng</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
