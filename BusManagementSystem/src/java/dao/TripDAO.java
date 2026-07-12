package dao;

import model.Trip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {

    // Danh sach chuyen xe cua cac xe do manager nay phu trach, theo ngay
    public List<Trip> getTripsByManagerAndDate(int managerId, Date tripDate) {
        List<Trip> list = new ArrayList<>();
        String sql = "SELECT t.trip_id, t.bus_id, t.route_id, t.trip_date, "
                   + "t.scheduled_start_time, t.actual_start_time, "
                   + "t.scheduled_end_time, t.actual_end_time, "
                   + "t.status, t.vehicle_status, t.vehicle_issue_note, "
                   + "b.bus_code, r.route_name "
                   + "FROM Trips t "
                   + "JOIN Buses b ON t.bus_id = b.bus_id "
                   + "JOIN Routes r ON t.route_id = r.route_id "
                   + "WHERE b.manager_id = ? AND t.trip_date = ? "
                   + "ORDER BY t.scheduled_start_time";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, managerId);
            ps.setDate(2, tripDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Loi getTripsByManagerAndDate: " + e.getMessage());
        }
        return list;
    }

    public Trip getTripById(int tripId, int managerId) {
        String sql = "SELECT t.trip_id, t.bus_id, t.route_id, t.trip_date, "
                   + "t.scheduled_start_time, t.actual_start_time, "
                   + "t.scheduled_end_time, t.actual_end_time, "
                   + "t.status, t.vehicle_status, t.vehicle_issue_note, "
                   + "b.bus_code, r.route_name "
                   + "FROM Trips t "
                   + "JOIN Buses b ON t.bus_id = b.bus_id "
                   + "JOIN Routes r ON t.route_id = r.route_id "
                   + "WHERE t.trip_id = ? AND b.manager_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tripId);
            ps.setInt(2, managerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Loi getTripById: " + e.getMessage());
        }
        return null;
    }

    // Cap nhat thong tin chuyen xe: trang thai chuyen, gio khoi hanh/ket thuc thuc te,
    // tinh trang xe va ghi chu su co (dung cho man hinh "Sua")
    public boolean updateTrip(int tripId, String status, Time actualStart, Time actualEnd,
                               String vehicleStatus, String vehicleIssueNote) {
        String sql = "UPDATE Trips SET status = ?, actual_start_time = ?, actual_end_time = ?, "
                   + "vehicle_status = ?, vehicle_issue_note = ? WHERE trip_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setTime(2, actualStart);
            ps.setTime(3, actualEnd);
            ps.setString(4, vehicleStatus);
            ps.setString(5, vehicleIssueNote);
            ps.setInt(6, tripId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi updateTrip: " + e.getMessage());
            return false;
        }
    }

    private Trip mapRow(ResultSet rs) throws SQLException {
        Trip t = new Trip();
        t.setTripId(rs.getInt("trip_id"));
        t.setBusId(rs.getInt("bus_id"));
        t.setRouteId(rs.getInt("route_id"));
        t.setTripDate(rs.getDate("trip_date"));
        t.setScheduledStartTime(rs.getTime("scheduled_start_time"));
        t.setActualStartTime(rs.getTime("actual_start_time"));
        t.setScheduledEndTime(rs.getTime("scheduled_end_time"));
        t.setActualEndTime(rs.getTime("actual_end_time"));
        t.setStatus(rs.getString("status"));
        t.setVehicleStatus(rs.getString("vehicle_status"));
        t.setVehicleIssueNote(rs.getString("vehicle_issue_note"));
        t.setBusCode(rs.getString("bus_code"));
        t.setRouteName(rs.getString("route_name"));
        return t;
    }
}
