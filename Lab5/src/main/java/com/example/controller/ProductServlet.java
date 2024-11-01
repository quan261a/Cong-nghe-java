package com.example.controller;

import com.example.dao.ProductDAO;
import com.example.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.getAllProducts();
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi vào console
            request.setAttribute("errorMessage", "Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        String errorMessage = null;

        // Kiểm tra xem các trường có rỗng hay không
        if (name == null || name.trim().isEmpty() || priceStr == null || priceStr.trim().isEmpty()) {
            errorMessage = "Tên sản phẩm và giá không được để trống!";
        } else {
            try {
                ProductDAO productDAO = new ProductDAO();
                double price = Double.parseDouble(priceStr.trim());
                Product product = new Product(name, price);
                productDAO.addProduct(product);
                response.sendRedirect("products");
                return;
            } catch (NumberFormatException | SQLException e) {
                errorMessage = "Giá sản phẩm không hợp lệ!";
            }
        }

        // Xử lý lỗi nếu có
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
