package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=BusManagementDB;encrypt=false";
    private final String user = "sa";
    private final String password = "Abc@123456"; // Đổi mật khẩu này đúng với máy của bạn

    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }
}
