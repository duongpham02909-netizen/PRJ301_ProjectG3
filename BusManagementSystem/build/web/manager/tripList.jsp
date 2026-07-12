<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Trip" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Danh sach chuyen xe phu trach</title></head>
<body>
    <h3>Danh sach chuyen xe phu trach</h3>

    <form method="get" action="trips">
        Ngay: <input type="date" name="date" value="<%= request.getAttribute("selectedDate") %>">
        <input type="submit" value="Xem">
    </form>

    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>Ma xe</th>
            <th>Tuyen</th>
            <th>Gio khoi hanh (DK / TT)</th>
            <th>Gio ket thuc (DK / TT)</th>
            <th>Trang thai</th>
            <th>Tinh trang xe</th>
            <th>Thao tac</th>
        </tr>

        <%
            List<Trip> trips = (List<Trip>) request.getAttribute("trips");
            if (trips == null || trips.isEmpty()) {
        %>
            <tr><td colspan="7">Khong co chuyen xe nao trong ngay da chon.</td></tr>
        <%
            } else {
                for (Trip t : trips) {
        %>
        <tr>
            <td><%= t.getBusCode() %></td>
            <td><%= t.getRouteName() %></td>
            <td><%= t.getScheduledStartTime() %> / <%= t.getActualStartTime() != null ? t.getActualStartTime() : "chua" %></td>
            <td><%= t.getScheduledEndTime() %> / <%= t.getActualEndTime() != null ? t.getActualEndTime() : "chua" %></td>
            <td><%= t.getStatus() %></td>
            <td><%= t.getVehicleStatus() %></td>
            <td>
                <a href="editTrip?tripId=<%= t.getTripId() %>">Sua</a>
                &nbsp;|&nbsp;
                <a href="attendance?tripId=<%= t.getTripId() %>">Diem danh</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
