<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý User - Quản Lý Xe Buýt</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f5f5f5; }
        .navbar { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 15px 20px; display: flex; justify-content: space-between; align-items: center; }
        .navbar a { color: white; text-decoration: none; margin-left: 20px; padding: 8px 15px; border-radius: 5px; background: rgba(255, 255, 255, 0.1); }
        .container { max-width: 1200px; margin: 20px auto; padding: 20px; }
        .section { background: white; padding: 20px; border-radius: 10px; margin: 20px 0; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
        .btn { padding: 10px 15px; border: none; border-radius: 5px; cursor: pointer; margin: 5px; }
        .btn-primary { background: #667eea; color: white; }
        .btn-danger { background: #dc3545; color: white; }
        .btn-success { background: #28a745; color: white; }
        .form-inline { display: flex; gap: 10px; margin-bottom: 20px; flex-wrap: wrap; }
        .form-inline input, .form-inline select { padding: 8px 12px; border: 1px solid #ddd; border-radius: 5px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background: #f0f4ff; padding: 12px; text-align: left; border-bottom: 2px solid #667eea; }
        td { padding: 12px; border-bottom: 1px solid #ddd; }
        tr:hover { background: #f9f9f9; }
        .modal { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 1000; }
        .modal.active { display: flex; justify-content: center; align-items: center; }
        .modal-content { background: white; padding: 30px; border-radius: 10px; width: 90%; max-width: 500px; }
        .modal-header { margin-bottom: 20px; }
        .modal-close { float: right; cursor: pointer; font-size: 24px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: 500; }
        .form-group input, .form-group select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 5px; }
        .alert { padding: 12px; border-radius: 5px; margin-bottom: 15px; }
        .alert-success { background: #d4edda; color: #155724; }
        .alert-danger { background: #f8d7da; color: #721c24; }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>🚌 Admin - Quản Lý User</h2>
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
            <h1>Quản Lý Người Dùng</h1>

            <div style="margin: 20px 0;">
                <button class="btn btn-success" onclick="openModal('addUserModal')">+ Thêm User Mới</button>
            </div>

            <h3>Danh Sách User</h3>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên Đăng Nhập</th>
                        <th>Email</th>
                        <th>Tên Đầy Đủ</th>
                        <th>Vai Trò</th>
                        <th>Trạng Thái</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${users}">
                        <tr>
                            <td>${u.userId}</td>
                            <td>${u.username}</td>
                            <td>${u.email}</td>
                            <td>${u.fullName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${u.role == 'admin'}">
                                        <span style="background: #d1ecf1; padding: 5px 10px; border-radius: 3px;">Admin</span>
                                    </c:when>
                                    <c:when test="${u.role == 'manager'}">
                                        <span style="background: #d1ecf1; padding: 5px 10px; border-radius: 3px;">Manager</span>
                                    </c:when>
                                    <c:when test="${u.role == 'driver'}">
                                        <span style="background: #e2e3e5; padding: 5px 10px; border-radius: 3px;">Driver</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="background: #fff3cd; padding: 5px 10px; border-radius: 3px;">Parent</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${u.status == 'active'}">
                                        <span style="background: #d4edda; padding: 5px 10px; border-radius: 3px;">Active</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="background: #f8d7da; padding: 5px 10px; border-radius: 3px;">Inactive</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <button class="btn btn-primary" onclick="openEditUserModal('${u.userId}', '${u.email}', '${u.fullName}', '${u.status}', '${u.role}')">Sửa</button>
                                <button class="btn btn-danger" onclick="confirmDeleteUser('${u.userId}', '${u.username}')">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Thêm User -->
    <div id="addUserModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Thêm User Mới</h2>
                <span class="modal-close" onclick="closeModal('addUserModal')">&times;</span>
            </div>
            <form method="POST">
                <input type="hidden" name="action" value="create">
                <div class="form-group">
                    <label>Tên Đăng Nhập</label>
                    <input type="text" name="username" required>
                </div>
                <div class="form-group">
                    <label>Mật Khẩu</label>
                    <input type="password" name="password" required>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" required>
                </div>
                <div class="form-group">
                    <label>Tên Đầy Đủ</label>
                    <input type="text" name="fullName" required>
                </div>
                <div class="form-group">
                    <label>Vai Trò</label>
                    <select name="role" required>
                        <option value="parent">Phụ Huynh</option>
                        <option value="manager">Quản Lý Xe</option>
                        <option value="driver">Lái Xe</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">Lưu</button>
                <button type="button" class="btn btn-primary" onclick="closeModal('addUserModal')">Hủy</button>
            </form>
        </div>
    </div>

    <!-- Modal Sửa User -->
    <div id="editUserModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Sửa User</h2>
                <span class="modal-close" onclick="closeModal('editUserModal')">&times;</span>
            </div>
            <form method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="userId" id="edit_userId">
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" id="edit_email" required>
                </div>
                <div class="form-group">
                    <label>Tên Đầy Đủ</label>
                    <input type="text" name="fullName" id="edit_fullName" required>
                </div>
                <div class="form-group">
                    <label>Vai Trò</label>
                    <select name="role" id="edit_role" required>
                        <option value="parent">Phụ Huynh</option>
                        <option value="manager">Quản Lý Xe</option>
                        <option value="driver">Lái Xe</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Trạng Thái</label>
                    <select name="status" id="edit_status">
                        <option value="active">Active</option>
                        <option value="inactive">Inactive</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">Lưu</button>
                <button type="button" class="btn btn-primary" onclick="closeModal('editUserModal')">Hủy</button>
            </form>
        </div>
    </div>

    <script>
        function openModal(modalId) {
            document.getElementById(modalId).classList.add('active');
        }
        
        function closeModal(modalId) {
            document.getElementById(modalId).classList.remove('active');
        }

        function openEditUserModal(id, email, fullName, status, role) {
            document.getElementById('edit_userId').value = id;
            document.getElementById('edit_email').value = email;
            document.getElementById('edit_fullName').value = fullName;
            document.getElementById('edit_status').value = status;
            document.getElementById('edit_role').value = role;
            openModal('editUserModal');
        }

        function confirmDeleteUser(id, username) {
            if (confirm("Bạn có chắc chắn muốn xóa người dùng '" + username + "' không?")) {
                window.location.href = "users?action=delete&userId=" + id;
            }
        }
    </script>
</body>
</html>
