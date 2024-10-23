package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Product product1 = context.getBean("product1", Product.class);
            System.out.println(product1);

            Product product2 = context.getBean("product2", Product.class);
            System.out.println(product2);
        }
    }
}
