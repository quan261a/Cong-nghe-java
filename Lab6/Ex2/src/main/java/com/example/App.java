package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy các bean từ context
        Product product1a = (Product) context.getBean("product1");
        Product product1b = (Product) context.getBean("product1");
        Product product2a = (Product) context.getBean("product2");
        Product product2b = (Product) context.getBean("product2");
        Product product3a = (Product) context.getBean("product3");
        Product product3b = (Product) context.getBean("product3");

        // In thông tin để kiểm tra các bean
        System.out.println("Bean 1a: " + product1a);
        System.out.println("Bean 1b: " + product1b);
        System.out.println("Bean 2a: " + product2a);
        System.out.println("Bean 2b: " + product2b);
        System.out.println("Bean 3a: " + product3a);
        System.out.println("Bean 3b: " + product3b);

        // Kiểm tra prototype và singleton
        System.out.println("\nPrototype Test:");
        System.out.println("product1a == product1b? " + (product1a == product1b)); // false
        System.out.println("product2a == product2b? " + (product2a == product2b)); // false

        System.out.println("\nSingleton Test:");
        System.out.println("product3a == product3b? " + (product3a == product3b)); // true

        context.close();
    }
}
