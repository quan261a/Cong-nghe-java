package org.example;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final List<Product> products = new ArrayList<>();
    private final Gson gson = new Gson();

    @Override
    public void init() {
        products.add(new Product(1, "Sản phẩm A", 100000.0));
        products.add(new Product(2, "Sản phẩm B", 200000.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        response.setContentType("application/json; charset=UTF-8");

        if (idParam == null) {
            // Trả về danh sách tất cả sản phẩm
            writeResponse(response, 200, "Danh sách sản phẩm", products);
        } else {
            try {
                int id = Integer.parseInt(idParam);
                Optional<Product> product = products.stream().filter(p -> p.getId() == id).findFirst();
                if (product.isPresent()) {
                    writeResponse(response, 200, "Chi tiết sản phẩm", product.get());
                } else {
                    writeResponse(response, 404, "Không tìm thấy sản phẩm", null);
                }
            } catch (NumberFormatException e) {
                writeResponse(response, 400, "ID không hợp lệ", null);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");

        try {
            int id = Integer.parseInt(idParam);
            double price = Double.parseDouble(priceParam);

            if (name == null || name.isEmpty()) {
                writeResponse(response, 400, "Tên sản phẩm không được để trống", null);
                return;
            }

            Optional<Product> existingProduct = products.stream().filter(p -> p.getId() == id).findFirst();
            if (existingProduct.isPresent()) {
                writeResponse(response, 400, "ID sản phẩm đã tồn tại", null);
                return;
            }

            Product product = new Product(id, name, price);
            products.add(product);
            writeResponse(response, 201, "Thêm sản phẩm thành công", product);

        } catch (NumberFormatException e) {
            writeResponse(response, 400, "ID hoặc giá không hợp lệ", null);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");

        try {
            int id = Integer.parseInt(idParam);
            double price = Double.parseDouble(priceParam);

            Optional<Product> existingProduct = products.stream().filter(p -> p.getId() == id).findFirst();
            if (existingProduct.isPresent()) {
                Product product = existingProduct.get();
                product.setName(name);
                product.setPrice(price);
                writeResponse(response, 200, "Cập nhật sản phẩm thành công", product);
            } else {
                writeResponse(response, 404, "Không tìm thấy sản phẩm", null);
            }

        } catch (NumberFormatException e) {
            writeResponse(response, 400, "ID hoặc giá không hợp lệ", null);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            Optional<Product> existingProduct = products.stream().filter(p -> p.getId() == id).findFirst();

            if (existingProduct.isPresent()) {
                products.remove(existingProduct.get());
                writeResponse(response, 200, "Xóa sản phẩm thành công", null);
            } else {
                writeResponse(response, 404, "Không tìm thấy sản phẩm", null);
            }

        } catch (NumberFormatException e) {
            writeResponse(response, 400, "ID không hợp lệ", null);
        }
    }

    private void writeResponse(HttpServletResponse response, int statusCode, String message, Object data) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json; charset=UTF-8");
        Response result = new Response(statusCode, message, data);
        response.getWriter().write(gson.toJson(result));
    }

    private static class Response {
        public int id;
        public String message;
        public Object data;

        public Response(int id, String message, Object data) {
            this.id = id;
            this.message = message;
            this.data = data;
        }
    }
}
