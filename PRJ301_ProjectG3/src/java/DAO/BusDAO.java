package dao;

import dal.DBContext;
import models.Bus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    public List<Bus> getBusesByRoute(int routeId) {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM [Buses] WHERE route_id = ? AND status = 'active'";
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
        String sql = "SELECT * FROM [Buses] WHERE bus_id = ?";
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
        String sql = "INSERT INTO [Buses] (route_id, bus_code, capacity, license_plate, manager_id, driver_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bus.getRouteId());
            pstmt.setString(2, bus.getBusCode());
            pstmt.setInt(3, bus.getCapacity());
            pstmt.setString(4, bus.getLicensePlate());
            pstmt.setInt(5, bus.getManagerId());
            pstmt.setInt(6, bus.getDriverId());
            pstmt.setString(7, bus.getStatus());
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
        bus.setCreatedDate(rs.getString("created_date"));
        return bus;
    }
}
