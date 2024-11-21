package com.example.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDTO orderDTO) {
        Orders newOrder = orderService.addOrder(orderDTO);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = orderService.getOrderById(id);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders updatedOrder) {

        Orders order = orderService.updateOrder(id, updatedOrder);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        boolean isDeleted = orderService.deleteOrder(id);

        if (isDeleted) {
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK); // Xóa thành công
        } else {
            return new ResponseEntity<>("Order not found with id " + id, HttpStatus.NOT_FOUND); // Không tìm thấy
        }
    }
}
