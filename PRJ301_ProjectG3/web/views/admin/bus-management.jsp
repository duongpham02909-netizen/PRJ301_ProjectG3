<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Xe - Quản Lý Xe Buýt</title>
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
        .modal-content { background: white; padding: 30px; border-radius: 10px; width: 90%; max-width: 600px; }
        .modal-close { float: right; cursor: pointer; font-size: 24px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: 500; }
        .form-group input, .form-group select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 5px; }
        .modal-header { margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>🚌 Admin - Quản Lý Xe</h2>
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
            <h1>Quản Lý Xe Buýt</h1>

            <div style="margin: 20px 0;">
                <button class="btn btn-success" onclick="openModal('addBusModal')">+ Thêm Xe Mới</button>
            </div>

            <h3>Danh Sách Xe</h3>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mã Xe</th>
                        <th>Biển Số</th>
                        <th>Sức Chứa</th>
                        <th>Tuyến</th>
                        <th>Lái Xe</th>
                        <th>Quản Lý</th>
                        <th>Trạng Thái</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>BUS_001</td>
                        <td>29F-12345</td>
                        <td>45</td>
                        <td>Tuyến A</td>
                        <td>Phạm Thái Driver</td>
                        <td>Lê Văn Manager</td>
                        <td><span style="background: #d4edda; padding: 5px 10px; border-radius: 3px;">Active</span></td>
                        <td>
                            <button class="btn btn-primary" onclick="openModal('editBusModal')">Sửa</button>
                            <button class="btn btn-danger">Xóa</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Thêm Xe -->
    <div id="addBusModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Thêm Xe Mới</h2>
                <span class="modal-close" onclick="closeModal('addBusModal')">&times;</span>
            </div>
            <form method="POST">
                <input type="hidden" name="action" value="create">
                <div class="form-group">
                    <label>Mã Xe</label>
                    <input type="text" name="busCode" required>
                </div>
                <div class="form-group">
                    <label>Biển Số</label>
                    <input type="text" name="licensePlate" required>
                </div>
                <div class="form-group">
                    <label>Sức Chứa</label>
                    <input type="number" name="capacity" required>
                </div>
                <div class="form-group">
                    <label>Tuyến Xe</label>
                    <select name="routeId" required>
                        <option value="">-- Chọn tuyến --</option>
                        <option value="1">Tuyến A</option>
                        <option value="2">Tuyến B</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Lái Xe</label>
                    <select name="driverId" required>
                        <option value="">-- Chọn lái xe --</option>
                        <option value="5">Phạm Thái Driver</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quản Lý Xe</label>
                    <select name="managerId" required>
                        <option value="">-- Chọn quản lý --</option>
                        <option value="4">Lê Văn Manager</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">Lưu</button>
                <button type="button" class="btn btn-primary" onclick="closeModal('addBusModal')">Hủy</button>
            </form>
        </div>
    </div>

    <!-- Modal Sửa Xe -->
    <div id="editBusModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Sửa Xe</h2>
                <span class="modal-close" onclick="closeModal('editBusModal')">&times;</span>
            </div>
            <form method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="busId" value="1">
                <div class="form-group">
                    <label>Biển Số</label>
                    <input type="text" name="licensePlate" value="29F-12345" required>
                </div>
                <div class="form-group">
                    <label>Sức Chứa</label>
                    <input type="number" name="capacity" value="45" required>
                </div>
                <div class="form-group">
                    <label>Trạng Thái</label>
                    <select name="status">
                        <option value="active" selected>Active</option>
                        <option value="maintenance">Bảo Dưỡng</option>
                        <option value="inactive">Inactive</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">Lưu</button>
                <button type="button" class="btn btn-primary" onclick="closeModal('editBusModal')">Hủy</button>
            </form>
        </div>
    </div>

    <script>
        function openModal(modalId) { document.getElementById(modalId).classList.add('active'); }
        function closeModal(modalId) { document.getElementById(modalId).classList.remove('active'); }
    </script>
</body>
</html>
