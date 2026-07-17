package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Trip;

public class DriverDAO extends DBContext {

    public Trip getTodayTrip(int driverId) {
        // lấy ngày hôm nay, đd/MM/yyyy
        java.time.LocalDate today = java.time.LocalDate.now();
        String todayStr = today.toString();

        // đồng bộ sql
        String sql = "SELECT t.trip_id, b.bus_code, b.license_plate, "
                + "CONVERT(VARCHAR, t.trip_date, 105) AS trip_date, "
                + "LEFT(CONVERT(VARCHAR, t.scheduled_start_time, 108), 5) AS scheduled_start_time, "
                + "t.vehicle_status, t.vehicle_issue_note "
                + "FROM Trips t "
                + "JOIN Buses b ON t.bus_id = b.bus_id "
                + "WHERE b.driver_id = ? "
                + "AND t.trip_date = ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, driverId);
            ps.setString(2, todayStr);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapTrip(rs);
                }
            }

            // nếu hôm nay chưa có chuyến, lấy chuyến gần nhất cuar tài xế
            String fallbackSql = "SELECT t.trip_id, b.bus_code, b.license_plate, "
                    + "CONVERT(VARCHAR, t.trip_date, 105) AS trip_date, "
                    + "LEFT(CONVERT(VARCHAR, t.scheduled_start_time, 108), 5) AS scheduled_start_time, "
                    + "t.vehicle_status, t.vehicle_issue_note "
                    + "FROM Trips t "
                    + "JOIN Buses b ON t.bus_id = b.bus_id "
                    + "WHERE b.driver_id = ? ";

            try (PreparedStatement ps2 = con.prepareStatement(fallbackSql)) {
                ps2.setInt(1, driverId);
                try (ResultSet rs2 = ps2.executeQuery()) {
                    if (rs2.next()) {
                        return mapTrip(rs2);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateVehicle(int tripId, String status, String note) {
        String sql = "UPDATE Trips SET vehicle_status = ?, vehicle_issue_note = ? WHERE trip_id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, note);
            ps.setInt(3, tripId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Trip mapTrip(ResultSet rs) throws Exception {
        Trip t = new Trip();
        t.setTripId(rs.getInt("trip_id"));
        t.setBusCode(rs.getString("bus_code"));
        t.setLicensePlate(rs.getString("license_plate"));
        t.setTripDate(rs.getString("trip_date"));
        t.setScheduledStartTime(rs.getString("scheduled_start_time"));
        t.setVehicleStatus(rs.getString("vehicle_status"));
        t.setVehicleIssueNote(rs.getString("vehicle_issue_note"));
        return t;
    }
}
