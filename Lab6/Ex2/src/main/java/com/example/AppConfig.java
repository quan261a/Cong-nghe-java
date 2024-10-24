package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Product product1() {
        Product product = new Product();
        product.setId("P001");
        product.setName("Product One");
        product.setPrice(100.0);
        product.setDescription("Description of Product One");
        return product;
    }

    // Bean thứ hai sử dụng constructor
    @Bean
    @Scope("prototype")
    public Product product2() {
        return new Product("P002", "Product Two", 200.0, "Description of Product Two");
    }

    // Bean thứ ba là singleton
    @Bean
    public Product product3() {
        Product product = new Product();
        product.setId("P003");
        product.setName("Product Three");
        product.setPrice(300.0);
        product.setDescription("Description of Product Three");
        return product;
    }
}
