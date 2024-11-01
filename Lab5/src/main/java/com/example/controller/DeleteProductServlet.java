package com.example.controller;

import com.example.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO(); // Khởi tạo ProductDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                productDAO.deleteProduct(id);
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu cần
            }
        }
        response.sendRedirect("products"); // Chuyển hướng về danh sách sản phẩm sau khi xóa
    }
}

