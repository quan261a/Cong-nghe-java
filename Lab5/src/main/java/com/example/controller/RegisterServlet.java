package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;
import java.time.LocalDateTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname"); // Đổi từ username sang fullname
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Kiểm tra thông tin đầu vào
        if (fullname == null || password == null || email == null ||
                fullname.isEmpty() || password.isEmpty() || email.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Mã hóa mật khẩu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = new User(fullname, hashedPassword, email, LocalDateTime.now());

        // Kết nối đến cơ sở dữ liệu MySQL trên XAMPP
        String url = "jdbc:mysql://localhost:3307/lab5?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "";

        try {
            // Đăng ký driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword)) {
                UserDAO userDAO = new UserDAO(connection);
                if (userDAO.registerUser(newUser)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", newUser);
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("errorMessage", "Tên người dùng đã tồn tại. Vui lòng chọn tên khác.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Ghi log lỗi
                request.setAttribute("errorMessage", "Đăng ký thất bại. Vui lòng thử lại.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Ghi log lỗi nếu không tìm thấy driver
            request.setAttribute("errorMessage", "Không tìm thấy driver MySQL.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
