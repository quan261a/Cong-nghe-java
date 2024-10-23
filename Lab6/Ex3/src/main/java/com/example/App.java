package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TextEditor editor = context.getBean(TextEditor.class);
        editor.input("Hello, Spring!");
        editor.save("example", "This is content.");

        context.close();
    }
}
