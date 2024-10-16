package com.example.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/mydatabase"; // Thay đổi 'mydatabase' thành tên cơ sở dữ liệu của bạn
    private static final String USER = "root"; // Tài khoản MySQL, mặc định là 'root'
    private static final String PASSWORD = ""; // Mật khẩu MySQL, mặc định là trống với XAMPP

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy driver MySQL.", e);
        }
    }
}
