/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Student;

/**
 *
 * @author admin
 */
public class StudentDAO extends DBContext {

    public Student getStudentByParent(int parent_id) {
        try {
            // Thay vì select mỗi student_name, hãy lấy hết các cột ra
            String sql = "select student_id, parent_id, student_name, student_code, birth_date, class_name from Students where parent_id=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, parent_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setStudent_id(rs.getInt("student_id"));
                s.setParent_id(rs.getInt("parent_id"));
                s.setStudent_name(rs.getString("student_name"));
                s.setStudent_code(rs.getString("student_code"));
                s.setBirth_date(rs.getDate("birth_date"));
                s.setClass_name(rs.getString("class_name"));
                return s;
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getRouteName(int student_id) {
        String s = null;
        try {
            String sql = "select r.route_name\n"
                    + "from Students s\n"
                    + "join Registrations reg on s.student_id = reg.student_id \n"
                    + "join Routes r on reg.route_id = r.route_id \n"
                    + "where s.student_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, student_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = rs.getString("route_name");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public String getRouteCode(int student_id) {
        String s = null;
        try {
            String sql = "select r.route_code\n"
                    + "from Students s\n"
                    + "join Registrations reg on s.student_id = reg.student_id \n"
                    + "join Routes r on reg.route_id = r.route_id \n"
                    + "where s.student_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, student_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = rs.getString("route_code");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public Map<String, String> getStopName(int student_id) {
        Map<String, String> s = new HashMap<>();
        try {
            String sql = "select \n"
                    + "strart_stop.stop_name as Start_Stop,\n"
                    + "return_stop.stop_name as Return_Stop\n"
                    + "from  Registrations r\n"
                    + "join Stops strart_stop on r.stop_id = strart_stop.stop_id\n"
                    + "join Stops return_stop on r.return_stop_id = return_stop.stop_id\n"
                    + "where r.student_id = ?;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, student_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s.put("startStop", rs.getString("Start_Stop"));
                s.put("returnStop", rs.getString("Return_Stop"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public Map<String, String> getTime_Status(int student_id) {
        Map<String, String> s = new HashMap<>();
        s.put("pickupStatus", "not yet");
        s.put("pickupTime", "not yet");
        s.put("returnStatus", "not yet");
        s.put("returnTime", "not yet");

        try {
            String sql = "SELECT \n"
                    + "    a.status,\n"
                    + "    a.pickup_time,\n"
                    + "    a.dropoff_time,\n"
                    + "    t.scheduled_start_time\n"
                    + "FROM Attendance a\n"
                    + "JOIN Trips t ON a.trip_id = t.trip_id\n"
                    + "WHERE a.student_id = ? \n"
                    + "  AND t.trip_date = CAST(GETDATE() AS DATE)";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, student_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                java.sql.Time startTime = rs.getTime("scheduled_start_time");
                String status = rs.getString("status");
                java.sql.Timestamp pTime = rs.getTimestamp("pickup_time");
                java.sql.Timestamp dTime = rs.getTimestamp("dropoff_time");
                if (startTime != null && startTime.toString().compareTo("12:00:00") < 0) {
                    s.put("pickupStatus", status);
                    s.put("pickupTime", pTime != null ? pTime.toString() : "Chưa lên xe");
                } else {
                    s.put("returnStatus", status);
                    s.put("returnTime", dTime != null ? dTime.toString() : "Chưa xuống xe");
                }
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public String absent(int student_id, int route_id, String absence_date, String reason) {
        try {
            String sql = "insert into Absences(student_id, route_id, absence_date,reason ) values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, student_id);
            st.setInt(2, route_id);
            st.setString(3, absence_date);
            st.setString(4, reason);
            st.executeUpdate();
            st.close();
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public int getRouteID(int student_id) {
        int s = 0;
        try {
            String sql = "select r.route_id\n"
                    + "from Students s\n"
                    + "join Registrations reg on s.student_id = reg.student_id \n"
                    + "join Routes r on reg.route_id = r.route_id \n"
                    + "where s.student_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, student_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = rs.getInt("route_id");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }
}
