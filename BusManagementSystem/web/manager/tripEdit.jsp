<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Trip" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Cap nhat chuyen xe</title></head>
<body>

<%
    Trip trip = (Trip) request.getAttribute("trip");
%>

    <h3>Cap nhat thong tin chuyen xe: <%= trip.getBusCode() %> - <%= trip.getRouteName() %></h3>

    <form method="post" action="editTrip">
        <input type="hidden" name="tripId" value="<%= trip.getTripId() %>">

        <p>
            Trang thai chuyen:
            <select name="status">
                <option value="scheduled" <%= "scheduled".equals(trip.getStatus()) ? "selected" : "" %>>Chua khoi hanh</option>
                <option value="in_progress" <%= "in_progress".equals(trip.getStatus()) ? "selected" : "" %>>Dang chay</option>
                <option value="completed" <%= "completed".equals(trip.getStatus()) ? "selected" : "" %>>Hoan thanh</option>
                <option value="cancelled" <%= "cancelled".equals(trip.getStatus()) ? "selected" : "" %>>Da huy</option>
            </select>
        </p>

        <p>
            Gio khoi hanh thuc te:
            <input type="time" name="actualStartTime"
                   value="<%= trip.getActualStartTime() != null ? trip.getActualStartTime().toString().substring(0,5) : "" %>">
        </p>

        <p>
            Gio ket thuc thuc te:
            <input type="time" name="actualEndTime"
                   value="<%= trip.getActualEndTime() != null ? trip.getActualEndTime().toString().substring(0,5) : "" %>">
        </p>

        <p>
            Tinh trang xe:
            <select name="vehicleStatus">
                <option value="normal" <%= "normal".equals(trip.getVehicleStatus()) ? "selected" : "" %>>Binh thuong</option>
                <option value="issue" <%= "issue".equals(trip.getVehicleStatus()) ? "selected" : "" %>>Co van de</option>
                <option value="broken" <%= "broken".equals(trip.getVehicleStatus()) ? "selected" : "" %>>Hong</option>
            </select>
        </p>

        <p>
            Ghi chu su co:<br>
            <textarea name="vehicleIssueNote" rows="3" cols="40"><%= trip.getVehicleIssueNote() != null ? trip.getVehicleIssueNote() : "" %></textarea>
        </p>

        <input type="submit" value="Luu">
        <a href="trips">Huy</a>
    </form>
</body>
</html>
