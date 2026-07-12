package dao;

import model.User;
import java.sql.*;

public class UserDAO {

    public User checkLogin(String username, String password) {
        String sql = "SELECT user_id, username, full_name, role FROM Users "
                   + "WHERE username = ? AND password = ? AND status = 'active'";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setUserId(rs.getInt("user_id"));
                    u.setUsername(rs.getString("username"));
                    u.setFullName(rs.getString("full_name"));
                    u.setRole(rs.getString("role"));
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi checkLogin: " + e.getMessage());
        }
        return null;
    }
}
