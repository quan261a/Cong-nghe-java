package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean(name = "product1")
    public Product product1(
            @Value("${product1.id}") String id,
            @Value("${product1.name}") String name,
            @Value("${product1.price}") double price,
            @Value("${product1.description}") String description) {
        return new Product(id, name, price, description);
    }

    @Bean(name = "product2")
    public Product product2(
            @Value("${product2.id}") String id,
            @Value("${product2.name}") String name,
            @Value("${product2.price}") double price,
            @Value("${product2.description}") String description) {
        return new Product(id, name, price, description);
    }
}
