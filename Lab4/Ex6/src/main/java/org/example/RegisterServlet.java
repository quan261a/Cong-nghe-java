package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập mã hóa UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Lấy thông tin từ form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] favoriteIDE = request.getParameterValues("favorite_ide[]");
        String toeicScore = request.getParameter("toeic");
        String message = request.getParameter("message");

        // Kiểm tra thông tin nhập vào và phản hồi tương ứng
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || country == null || country.equals("Select a country")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Vui lòng nhập đầy đủ thông tin bắt buộc.\"}");
            return;
        }

        // Tạo JSON phản hồi
        String jsonResponse = "{";
        jsonResponse += "\"name\": \"" + name + "\", ";
        jsonResponse += "\"email\": \"" + email + "\", ";
        jsonResponse += "\"birthday\": \"" + (birthday != null ? birthday : "") + "\", ";
        jsonResponse += "\"birthtime\": \"" + (birthtime != null ? birthtime : "") + "\", ";
        jsonResponse += "\"gender\": \"" + (gender != null ? gender : "") + "\", ";
        jsonResponse += "\"country\": \"" + country + "\", ";
        jsonResponse += "\"favoriteIDE\": \"" + (favoriteIDE != null ? Arrays.toString(favoriteIDE) : "") + "\", ";
        jsonResponse += "\"toeicScore\": \"" + (toeicScore != null ? toeicScore : "") + "\", ";
        jsonResponse += "\"message\": \"" + (message != null ? message : "") + "\"";
        jsonResponse += "}";

        // Phản hồi kết quả
        out.print(jsonResponse);
    }
}

