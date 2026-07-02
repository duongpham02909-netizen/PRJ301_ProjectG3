package DAO;

import dal.DBContext;
import models.Route;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    public List<Route> getAllRoutes() {
        List<Route> list = new ArrayList<>();
        String sql = "SELECT * FROM Routes ORDER BY route_id DESC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Route r = new Route();
                r.setRouteId(rs.getInt("route_id"));
                r.setRouteName(rs.getString("route_name"));
                r.setRouteCode(rs.getString("route_code"));
                r.setDescription(rs.getString("description"));
                r.setStatus(rs.getString("status"));
                r.setCreatedDate(rs.getString("created_date"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Route getRouteById(int id) {
        String sql = "SELECT * FROM Routes WHERE route_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Route r = new Route();
                    r.setRouteId(rs.getInt("route_id"));
                    r.setRouteName(rs.getString("route_name"));
                    r.setRouteCode(rs.getString("route_code"));
                    r.setDescription(rs.getString("description"));
                    r.setStatus(rs.getString("status"));
                    r.setCreatedDate(rs.getString("created_date"));
                    return r;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createRoute(Route r) {
        String sql = "INSERT INTO Routes (route_name, route_code, description, status, created_date) VALUES (?, ?, ?, ?, GETDATE())";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getRouteName());
            ps.setString(2, r.getRouteCode());
            ps.setString(3, r.getDescription());
            ps.setString(4, r.getStatus() != null ? r.getStatus() : "active");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRoute(Route r) {
        String sql = "UPDATE Routes SET route_name = ?, description = ?, status = ? WHERE route_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getRouteName());
            ps.setString(2, r.getDescription());
            ps.setString(3, r.getStatus());
            ps.setInt(4, r.getRouteId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRoute(int id) {
        String sql = "DELETE FROM Routes WHERE route_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
