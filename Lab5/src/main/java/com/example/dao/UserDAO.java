package com.example.dao;

import com.example.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registerUser(User user) {
        // Kiểm tra xem người dùng đã tồn tại hay chưa
        try {
            if (isUserExists(user.getUsername())) {
                System.out.println("Người dùng đã tồn tại: " + user.getUsername());
                return false; // Người dùng đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "INSERT INTO Users (Username, PasswordHash, Email, CreatedAt) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPasswordHash()); // Lưu mật khẩu đã băm
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setObject(4, user.getCreatedAt()); // Lưu CreatedAt

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đăng ký thành công người dùng: " + user.getUsername());
                return true;
            } else {
                System.out.println("Đăng ký thất bại người dùng: " + user.getUsername());
                return false; // Trả về false nếu không có bản ghi nào được thêm
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public User loginUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE Username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Lấy mật khẩu đã băm từ cơ sở dữ liệu
                String hashedPassword = rs.getString("PasswordHash");
                // So sánh mật khẩu người dùng nhập với mật khẩu đã băm
                if (BCrypt.checkpw(password, hashedPassword)) {
                    return new User(
                            rs.getString("Username"), // Đảm bảo rằng trường này là Username
                            hashedPassword, // Lưu trữ mật khẩu đã băm
                            rs.getString("Email"),
                            rs.getTimestamp("CreatedAt").toLocalDateTime() // Lấy CreatedAt và chuyển đổi thành LocalDateTime
                    );
                }
            }
        }
        return null; // Đăng nhập không thành công
    }

    private boolean isUserExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Users WHERE Username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu người dùng đã tồn tại
            }
        }
        return false; // Trả về false nếu người dùng không tồn tại
    }
}
