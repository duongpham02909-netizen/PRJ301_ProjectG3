package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    // SQL Server connection parameters
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BusManagementDB;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa"; // Change to your SQL Server user
    private static final String PASSWORD = "123"; // Change to your SQL Server password
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
