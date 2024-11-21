package com.example.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Orders addOrder(OrderDTO orderDTO) {
        List<Product> products = productRepository.findAllById(orderDTO.getProductIds());

        Orders newOrder = new Orders();
        newOrder.setOrderNumber(orderDTO.getOrderNumber());
        newOrder.setTotalPrice(orderDTO.getTotalPrice());
        newOrder.setProducts(products);

        return orderRepository.save(newOrder);
    }

    public Orders getOrderById(Long id) {
        Optional<Orders> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Orders updateOrder(Long id, Orders updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderNumber(updatedOrder.getOrderNumber());
            order.setTotalPrice(updatedOrder.getTotalPrice());
            order.setProducts(updatedOrder.getProducts());
            return orderRepository.save(order);
        }).orElse(null); // Trả về null nếu không tìm thấy đơn hàng
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true; // Xóa thành công
        }
        return false;
    }
}
