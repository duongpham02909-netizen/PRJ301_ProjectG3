<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản Lý Xe</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f6f9;
            }
            .container {
                max-width: 700px;
                margin: 0 auto;
                background: white;
                padding: 25px;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            }
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background: #e8f4fd;
                padding: 12px 20px;
                border-radius: 6px;
                margin-bottom: 20px;
            }
            .info-table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 25px;
            }
            .info-table th, .info-table td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: left;
            }
            .info-table th {
                background-color: #f8f9fa;
                color: #333;
                width: 35%;
            }
            .form-group {
                margin-bottom: 18px;
            }
            label {
                font-weight: bold;
                display: block;
                margin-bottom: 8px;
                color: #2c3e50;
            }
            select, textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 14px;
            }
            textarea {
                height: 100px;
                resize: vertical;
            }
            .btn-submit {
                background-color: #2ecc71;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                font-weight: bold;
                width: 100%;
            }
            .btn-submit:hover {
                background-color: #27ae60;
            }
            .no-data {
                text-align: center;
                color: #7f8c8d;
                padding: 30px;
                font-style: italic;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <span style="color: #2980b9; font-weight: bold;">Tài xế: ${driverName} (ID: ${driverId})</span>
                <a href="${pageContext.request.contextPath}/driver?action=logout" style="background: #e74c3c; color: white; padding: 6px 12px; text-decoration: none; border-radius: 4px; font-size: 13px; font-weight: bold;">Đăng xuất</a>
            </div>

            <c:choose>
                <c:when test="${not empty trip}">
                    <h3 style="color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 8px;">THÔNG TIN LỊCH TRÌNH HÔM NAY</h3>
                    <table class="info-table">
                        <tr><th>Mã chuyến đi (Trip ID)</th><td><strong>${trip.tripId}</strong></td></tr>
                        <tr><th>Mã số xe (Bus Code)</th><td>${trip.busCode}</td></tr>
                        <tr><th>Biển số xe</th><td>${trip.licensePlate}</td></tr>
                        <tr><th>Ngày chạy</th><td>${trip.tripDate}</td></tr>
                        <tr><th>Giờ xuất bến dự kiến</th><td><span style="color: #e67e22; font-weight: bold;">${trip.scheduledStartTime}</span></td></tr>
                    </table>

                    <h3 style="color: #2c3e50; border-bottom: 2px solid #2ecc71; padding-bottom: 8px;">CẬP NHẬT TÌNH TRẠNG XE</h3>
                    <form action="${pageContext.request.contextPath}/driver" method="post">
                        <input type="hidden" name="tripId" value="${trip.tripId}">

                        <div class="form-group">
                            <label>Trạng thái xe:</label>
                            <select name="status">
                                <option value="normal" ${trip.vehicleStatus == 'normal' ? 'selected' : ''}>Bình thường (Normal)</option>
                                <option value="issue" ${trip.vehicleStatus == 'issue' ? 'selected' : ''}>Có lỗi nhỏ (Issue)</option>
                                <option value="broken" ${trip.vehicleStatus == 'broken' ? 'selected' : ''}>Hỏng hóc nặng (Broken)</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Chi tiết hỏng hóc:</label>
                            <textarea name="note" placeholder="Nhập tình trạng hỏng hóc cụ thể">${trip.vehicleIssueNote}</textarea>
                        </div>

                        <button type="submit" class="btn-submit">Cập Nhật Tình Trạng Xe</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="no-data">Không có lịch trình</div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>