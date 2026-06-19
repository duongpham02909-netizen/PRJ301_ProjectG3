<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Tuyến Xe - Quản Lý Xe Buýt</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f5f5f5; }
        .navbar { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 20px; display: flex; justify-content: space-between; }
        .navbar a { color: white; text-decoration: none; margin-left: 20px; padding: 8px 15px; border-radius: 5px; background: rgba(255, 255, 255, 0.1); }
        .container { max-width: 1200px; margin: 20px auto; padding: 20px; }
        .section { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
        .btn { padding: 10px 15px; border: none; border-radius: 5px; cursor: pointer; margin: 5px; }
        .btn-primary { background: #667eea; color: white; }
        .btn-danger { background: #dc3545; color: white; }
        .btn-success { background: #28a745; color: white; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background: #f0f4ff; padding: 12px; text-align: left; border-bottom: 2px solid #667eea; }
        td { padding: 12px; border-bottom: 1px solid #ddd; }
        tr:hover { background: #f9f9f9; }
        .modal { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 1000; }
        .modal.active { display: flex; justify-content: center; align-items: center; }
        .modal-content { background: white; padding: 30px; border-radius: 10px; width: 90%; max-width: 500px; }
        .modal-close { float: right; cursor: pointer; font-size: 24px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: 500; }
        .form-group input, .form-group textarea { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 5px; }
        .modal-header { margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>🚌 Admin - Quản Lý Tuyến</h2>
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
            <h1>Quản Lý Tuyến Xe</h1>

            <div style="margin: 20px 0;">
                <button class="btn btn-success" onclick="openModal('addRouteModal')">+ Thêm Tuyến Mới</button>
            </div>

            <h3>Danh Sách Tuyến Xe</h3>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên Tuyến</th>
                        <th>Mã Tuyến</th>
                        <th>Mô Tả</th>
                        <th>Trạng Thái</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
               <tbody>
    <c:forEach var="route" items="${routes}">
        <tr>
            <td>${route.routeId}</td>
            <td>${route.routeName}</td>
            <td>${route.routeCode}</td>
            <td>${route.description}</td>
            <td>
                <span style="background: #d4edda; padding: 5px 10px; border-radius: 3px;">
                    ${route.status}
                </span>
            </td>
            <td>
                <button class="btn btn-primary">
                    Sửa
                </button>

                <button class="btn btn-danger">
                    Xóa
                </button>
            </td>
        </tr>
    </c:forEach>
</tbody>
            </table>
        </div>
    </div>

    <!-- Modal Thêm Tuyến -->
    <div id="addRouteModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Thêm Tuyến Mới</h2>
                <span class="modal-close" onclick="closeModal('addRouteModal')">&times;</span>
            </div>
            <form method="POST">
                <input type="hidden" name="action" value="create">
                <div class="form-group">
                    <label>Tên Tuyến</label>
                    <input type="text" name="routeName" required>
                </div>
                <div class="form-group">
                    <label>Mã Tuyến</label>
                    <input type="text" name="routeCode" required>
                </div>
                <div class="form-group">
                    <label>Mô Tả</label>
                    <textarea name="description" rows="4"></textarea>
                </div>
                <button type="submit" class="btn btn-success">Lưu</button>
                <button type="button" class="btn btn-primary" onclick="closeModal('addRouteModal')">Hủy</button>
            </form>
        </div>
    </div>

    <!-- Modal Sửa Tuyến -->
    <div id="editRouteModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Sửa Tuyến</h2>
                <span class="modal-close" onclick="closeModal('editRouteModal')">&times;</span>
            </div>
            <form method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="routeId" value="1">
                <div class="form-group">
                    <label>Tên Tuyến</label>
                    <input type="text" name="routeName" value="Tuyến A - Tây Hồ" required>
                </div>
                <div class="form-group">
                    <label>Mô Tả</label>
                    <textarea name="description" rows="4">Tuyến đưa đón từ quận Tây Hồ</textarea>
                </div>
                <button type="submit" class="btn btn-success">Lưu</button>
                <button type="button" class="btn btn-primary" onclick="closeModal('editRouteModal')">Hủy</button>
            </form>
        </div>
    </div>

    <script>
        function openModal(modalId) { document.getElementById(modalId).classList.add('active'); }
        function closeModal(modalId) { document.getElementById(modalId).classList.remove('active'); }
    </script>
</body>
</html>
