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
            // Lấy danh sách sản phẩm từ ProductDAO
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.getAllProducts();
            // Đưa danh sách sản phẩm vào thuộc tính để hiển thị trong view
            request.setAttribute("productList", productList);
            // Chuyển tiếp đến index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi lấy danh sách sản phẩm.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nhận dữ liệu sản phẩm từ yêu cầu
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        // Tạo đối tượng Product mới mà không cần id
        Product product = new Product(name, price);
        ProductDAO productDAO = new ProductDAO();

        // Thêm sản phẩm và kiểm tra kết quả
        try {
            productDAO.addProduct(product);
            // Chuyển hướng về danh sách sản phẩm
            response.sendRedirect("products");
        } catch (SQLException e) {
            // Nếu thêm sản phẩm thất bại, đưa thông báo lỗi vào thuộc tính
            request.setAttribute("errorMessage", "Thêm sản phẩm thất bại.");
            // Chuyển tiếp về index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
