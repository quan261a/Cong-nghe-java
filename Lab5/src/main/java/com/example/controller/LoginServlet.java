package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        // Kết nối đến cơ sở dữ liệu MySQL trên XAMPP
        String url = "jdbc:mysql://localhost:3307/lab5?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "";

        try {
            // Đăng ký driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword)) {
                UserDAO userDAO = new UserDAO(connection);
                User user = userDAO.loginUser(username, password);

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    // Nếu lựa chọn nhớ đăng nhập
                    if ("on".equals(remember)) {
                        Cookie usernameCookie = new Cookie("username", username);
                        usernameCookie.setMaxAge(30 * 24 * 60 * 60);  // 30 ngày
                        response.addCookie(usernameCookie);
                    }

                    response.sendRedirect("index.jsp");
                } else {
                    request.setAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Không tìm thấy driver MySQL.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi đăng nhập. Vui lòng thử lại.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
