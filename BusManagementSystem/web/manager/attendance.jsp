<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Trip" %>
<%@ page import="model.StudentAttendanceView" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Diem danh hoc sinh</title></head>
<body>

<%
    Trip trip = (Trip) request.getAttribute("trip");
    List<StudentAttendanceView> students = (List<StudentAttendanceView>) request.getAttribute("students");
%>

    <p><a href="trips">&larr; Quay lai danh sach chuyen</a></p>

    <h3>Diem danh hoc sinh - Xe <%= trip.getBusCode() %> - Tuyen <%= trip.getRouteName() %> - Ngay <%= trip.getTripDate() %></h3>

    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>Ma HS</th>
            <th>Ho ten</th>
            <th>Lop</th>
            <th>Diem don</th>
            <th>Diem tra</th>
            <th>Trang thai</th>
            <th>Thao tac</th>
        </tr>

        <%
            if (students == null || students.isEmpty()) {
        %>
            <tr><td colspan="7">Chua co hoc sinh dang ky tuyen nay.</td></tr>
        <%
            } else {
                for (StudentAttendanceView s : students) {
        %>
        <tr>
            <td><%= s.getStudentCode() %></td>
            <td><%= s.getStudentName() %></td>
            <td><%= s.getClassName() %></td>
            <td><%= s.getPickupStopName() %></td>
            <td><%= s.getReturnStopName() != null ? s.getReturnStopName() : "-" %></td>
            <td>
                <%
                    if (s.isAbsentToday()) {
                        out.print("Phu huynh bao nghi");
                    } else if ("boarded".equals(s.getAttendanceStatus())) {
                        out.print("Da len xe (" + s.getPickupTime() + ")");
                    } else if ("dropped_off".equals(s.getAttendanceStatus())) {
                        out.print("Da xuong xe (" + s.getDropoffTime() + ")");
                    } else if ("no_show".equals(s.getAttendanceStatus())) {
                        out.print("Vang khong bao");
                    } else {
                        out.print("Chua diem danh");
                    }
                %>
            </td>
            <td>
                <%
                    if (!s.isAbsentToday()) {
                        int returnStopId = s.getReturnStopId() != null ? s.getReturnStopId() : s.getPickupStopId();
                %>
                <form method="post" action="attendance" style="display:inline">
                    <input type="hidden" name="tripId" value="<%= trip.getTripId() %>">
                    <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
                    <input type="hidden" name="stopId" value="<%= s.getPickupStopId() %>">
                    <input type="hidden" name="action" value="board">
                    <input type="submit" value="Len xe">
                </form>
                <form method="post" action="attendance" style="display:inline">
                    <input type="hidden" name="tripId" value="<%= trip.getTripId() %>">
                    <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
                    <input type="hidden" name="stopId" value="<%= returnStopId %>">
                    <input type="hidden" name="action" value="dropoff">
                    <input type="submit" value="Xuong xe">
                </form>
                <form method="post" action="attendance" style="display:inline">
                    <input type="hidden" name="tripId" value="<%= trip.getTripId() %>">
                    <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
                    <input type="hidden" name="action" value="noshow">
                    <input type="submit" value="Vang">
                </form>
                <%
                    }
                %>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
