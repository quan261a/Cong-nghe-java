package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Program <database-url> <username> [password]");
            return;
        }

        String dbUrl = args[0];
        String username = args[1];
        String password = args.length > 2 ? args[2] : ""; // Lấy mật khẩu nếu có, nếu không thì để trống

        try {
            ProductDAO productDAO = new ProductDAO(dbUrl, username, password); // Truyền vào username và password
            System.out.println("Connection successful!");
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("Menu:");
                System.out.println("1. Read product list");
                System.out.println("2. Read a product by input id");
                System.out.println("3. Add a new product");
                System.out.println("4. Update a product");
                System.out.println("5. Delete a product");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character

                switch (choice) {
                    case 1:
                        List<Product> products = productDAO.readAll();
                        products.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Enter product ID: ");
                        int id = scanner.nextInt();
                        Product product = productDAO.read(id);
                        System.out.println(product);
                        break;
                    case 3:
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter product quantity: ");
                        int quantity = scanner.nextInt();
                        Product newProduct = new Product(0, name, price, quantity);
                        Integer newId = productDAO.add(newProduct);
                        System.out.println("New product added with ID: " + newId);
                        break;
                    case 4:
                        System.out.print("Enter product ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        Product productToUpdate = productDAO.read(updateId);
                        if (productToUpdate != null) {
                            System.out.print("Enter new name : ");
                            String updatedName = scanner.nextLine();
                            System.out.print("Enter new price : ");
                            double updatedPrice = scanner.nextDouble();
                            System.out.print("Enter new quantity : ");
                            int updatedQuantity = scanner.nextInt();
                            productToUpdate.setName(updatedName);
                            productToUpdate.setPrice(updatedPrice);
                            productToUpdate.setQuantity(updatedQuantity);
                            productDAO.update(productToUpdate);
                            System.out.println("Product updated.");
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter product ID to delete: ");
                        int deleteId = scanner.nextInt();
                        productDAO.delete(deleteId);
                        System.out.println("Product deleted.");
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } while (choice != 6);

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
