package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    // Sua lai cho dung voi may cua ban
    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=BusManagementDB;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Loi ket noi CSDL: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection c = getConnection();
        System.out.println(c != null ? "Ket noi thanh cong!" : "Ket noi that bai!");
    }
}
