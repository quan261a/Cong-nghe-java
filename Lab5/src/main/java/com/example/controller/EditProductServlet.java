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

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO(); // Khởi tạo DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            Product product = productDAO.getProductById(id);
            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Sản phẩm không tồn tại!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID sản phẩm không hợp lệ!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi lấy thông tin sản phẩm.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        String errorMessage = null;

        if (name == null || name.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            errorMessage = "Tên sản phẩm và giá không được để trống!";
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("product", new Product(Integer.parseInt(idStr), name, Double.parseDouble(priceStr)));
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            double price = Double.parseDouble(priceStr);
            Product product = new Product(id, name, price);
            productDAO.updateProduct(product);
            response.sendRedirect("products");
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage = "Có lỗi xảy ra khi cập nhật sản phẩm. Vui lòng thử lại.";
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("product", new Product(Integer.parseInt(idStr), name, Double.parseDouble(priceStr))); // Giữ lại thông tin
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorMessage = "Giá sản phẩm không hợp lệ!";
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("product", new Product(Integer.parseInt(idStr), name, Double.parseDouble(priceStr))); // Giữ lại thông tin
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        productDAO.close(); // Đóng kết nối khi servlet bị hủy
    }
}
