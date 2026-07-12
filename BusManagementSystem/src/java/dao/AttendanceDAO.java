package dao;

import model.StudentAttendanceView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public List<StudentAttendanceView> getStudentListForTrip(int tripId) {
        List<StudentAttendanceView> list = new ArrayList<>();
        String sql =
            "SELECT s.student_id, s.student_name, s.student_code, s.class_name, " +
            "sp.stop_id AS pickup_stop_id, sp.stop_name AS pickup_stop_name, " +
            "rp.stop_id AS return_stop_id, rp.stop_name AS return_stop_name, " +
            "a.pickup_time, a.dropoff_time, a.status AS attendance_status, " +
            "CASE WHEN ab.absence_id IS NOT NULL THEN 1 ELSE 0 END AS absent_today " +
            "FROM Trips t " +
            "JOIN Registrations r ON r.route_id = t.route_id AND r.status = 'active' " +
            "JOIN Students s ON s.student_id = r.student_id " +
            "JOIN Stops sp ON sp.stop_id = r.stop_id " +
            "LEFT JOIN Stops rp ON rp.stop_id = r.return_stop_id " +
            "LEFT JOIN Attendance a ON a.trip_id = t.trip_id AND a.student_id = s.student_id " +
            "LEFT JOIN Absences ab ON ab.student_id = s.student_id AND ab.route_id = t.route_id " +
            "AND ab.absence_date = t.trip_date " +
            "WHERE t.trip_id = ? " +
            "ORDER BY sp.stop_order, s.student_name";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tripId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StudentAttendanceView v = new StudentAttendanceView();
                    v.setStudentId(rs.getInt("student_id"));
                    v.setStudentName(rs.getString("student_name"));
                    v.setStudentCode(rs.getString("student_code"));
                    v.setClassName(rs.getString("class_name"));
                    v.setPickupStopId(rs.getInt("pickup_stop_id"));
                    v.setPickupStopName(rs.getString("pickup_stop_name"));
                    int returnStopId = rs.getInt("return_stop_id");
                    v.setReturnStopId(rs.wasNull() ? null : returnStopId);
                    v.setReturnStopName(rs.getString("return_stop_name"));
                    v.setPickupTime(rs.getTimestamp("pickup_time"));
                    v.setDropoffTime(rs.getTimestamp("dropoff_time"));
                    v.setAttendanceStatus(rs.getString("attendance_status"));
                    v.setAbsentToday(rs.getInt("absent_today") == 1);
                    list.add(v);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi getStudentListForTrip: " + e.getMessage());
        }
        return list;
    }

    public boolean markBoarded(int tripId, int studentId, int pickupStopId) {
        return attendanceExists(tripId, studentId)
            ? executeUpdate("UPDATE Attendance SET pickup_stop_id=?, pickup_time=GETDATE(), status='boarded' WHERE trip_id=? AND student_id=?",
                             pickupStopId, tripId, studentId)
            : executeUpdate("INSERT INTO Attendance (trip_id, student_id, pickup_stop_id, pickup_time, status) VALUES (?,?,?,GETDATE(),'boarded')",
                             tripId, studentId, pickupStopId);
    }

    public boolean markDroppedOff(int tripId, int studentId, int dropoffStopId) {
        return attendanceExists(tripId, studentId)
            ? executeUpdate("UPDATE Attendance SET dropoff_stop_id=?, dropoff_time=GETDATE(), status='dropped_off' WHERE trip_id=? AND student_id=?",
                             dropoffStopId, tripId, studentId)
            : executeUpdate("INSERT INTO Attendance (trip_id, student_id, dropoff_stop_id, dropoff_time, status) VALUES (?,?,?,GETDATE(),'dropped_off')",
                             tripId, studentId, dropoffStopId);
    }

    public boolean markNoShow(int tripId, int studentId) {
        return attendanceExists(tripId, studentId)
            ? executeUpdate("UPDATE Attendance SET status='no_show' WHERE trip_id=? AND student_id=?", tripId, studentId)
            : executeUpdate("INSERT INTO Attendance (trip_id, student_id, status) VALUES (?,?,'no_show')", tripId, studentId);
    }

    private boolean attendanceExists(int tripId, int studentId) {
        String sql = "SELECT attendance_id FROM Attendance WHERE trip_id=? AND student_id=?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tripId);
            ps.setInt(2, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Loi attendanceExists: " + e.getMessage());
            return false;
        }
    }

    private boolean executeUpdate(String sql, int... params) {
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) ps.setInt(i + 1, params[i]);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi executeUpdate: " + e.getMessage());
            return false;
        }
    }
}
