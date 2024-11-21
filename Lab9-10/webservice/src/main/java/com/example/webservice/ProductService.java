package com.example.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product newProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());
            return productRepository.save(existingProduct);
        }).orElse(null);
    }

    public Product patchProduct(Long id, Map<String, Object> updates) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Cập nhật từng trường dựa trên key-value trong map
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        existingProduct.setName((String) value);
                        break;
                    case "price":
                        existingProduct.setPrice((value instanceof Number) ? ((Number) value).doubleValue() : null);
                        break;
                    // Có thể thêm các thuộc tính khác nếu cần
                }
            });

            return productRepository.save(existingProduct);
        }
        return null;
    }

    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true; // Xóa thành công
        }
        return false; // Không tìm thấy sản phẩm
    }
}

