package DAO;

import dal.DBContext;
import models.Bus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT b.*, r.route_name, d.full_name AS driver_name, m.full_name AS manager_name " +
                     "FROM Buses b " +
                     "LEFT JOIN Routes r ON b.route_id = r.route_id " +
                     "LEFT JOIN Users d ON b.driver_id = d.user_id " +
                     "LEFT JOIN Users m ON b.manager_id = m.user_id " +
                     "ORDER BY b.bus_id DESC";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                buses.add(mapResultSetToBus(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    public List<Bus> getBusesByRoute(int routeId) {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT b.*, r.route_name, d.full_name AS driver_name, m.full_name AS manager_name " +
                     "FROM Buses b " +
                     "LEFT JOIN Routes r ON b.route_id = r.route_id " +
                     "LEFT JOIN Users d ON b.driver_id = d.user_id " +
                     "LEFT JOIN Users m ON b.manager_id = m.user_id " +
                     "WHERE b.route_id = ? AND b.status = 'active'";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, routeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                buses.add(mapResultSetToBus(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    public Bus getBusById(int busId) {
        String sql = "SELECT b.*, r.route_name, d.full_name AS driver_name, m.full_name AS manager_name " +
                     "FROM Buses b " +
                     "LEFT JOIN Routes r ON b.route_id = r.route_id " +
                     "LEFT JOIN Users d ON b.driver_id = d.user_id " +
                     "LEFT JOIN Users m ON b.manager_id = m.user_id " +
                     "WHERE b.bus_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, busId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBus(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createBus(Bus bus) {
        String sql = "INSERT INTO [Buses] (route_id, bus_code, capacity, license_plate, manager_id, driver_id, status, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE())";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bus.getRouteId());
            pstmt.setString(2, bus.getBusCode());
            pstmt.setInt(3, bus.getCapacity());
            pstmt.setString(4, bus.getLicensePlate());
            pstmt.setInt(5, bus.getManagerId());
            pstmt.setInt(6, bus.getDriverId());
            pstmt.setString(7, bus.getStatus() != null ? bus.getStatus() : "active");
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBus(Bus bus) {
        String sql = "UPDATE [Buses] SET route_id = ?, capacity = ?, license_plate = ?, manager_id = ?, driver_id = ?, status = ? WHERE bus_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bus.getRouteId());
            pstmt.setInt(2, bus.getCapacity());
            pstmt.setString(3, bus.getLicensePlate());
            pstmt.setInt(4, bus.getManagerId());
            pstmt.setInt(5, bus.getDriverId());
            pstmt.setString(6, bus.getStatus());
            pstmt.setInt(7, bus.getBusId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBus(int busId) {
        String sql = "DELETE FROM [Buses] WHERE bus_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, busId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Bus mapResultSetToBus(ResultSet rs) throws SQLException {
        Bus bus = new Bus();
        bus.setBusId(rs.getInt("bus_id"));
        bus.setRouteId(rs.getInt("route_id"));
        bus.setBusCode(rs.getString("bus_code"));
        bus.setCapacity(rs.getInt("capacity"));
        bus.setLicensePlate(rs.getString("license_plate"));
        bus.setManagerId(rs.getInt("manager_id"));
        bus.setDriverId(rs.getInt("driver_id"));
        bus.setStatus(rs.getString("status"));
        
        // Safely map created_date as string
        try {
            bus.setCreatedDate(rs.getString("created_date"));
        } catch (SQLException e) {
            // Field not present in result set
        }

        // Map joined columns if they exist
        try {
            bus.setRouteName(rs.getString("route_name"));
        } catch (SQLException e) {
            // Field not present in result set
        }
        try {
            bus.setDriverName(rs.getString("driver_name"));
        } catch (SQLException e) {
            // Field not present in result set
        }
        try {
            bus.setManagerName(rs.getString("manager_name"));
        } catch (SQLException e) {
            // Field not present in result set
        }
        
        return bus;
    }
}
