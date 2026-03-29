package Kha_QuanLySanPham_Voi_Colllection_Streams;

import java.util.*;
import java.util.stream.Collectors;

public class ProductManager {
    static HashMap<Integer, Product> products = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Product Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Display Products");
            System.out.println("5. Filter Products (Price > 100)");
            System.out.println("6. Total Value of Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: addProduct(); break;
                case 2: editProduct(); break;
                case 3: deleteProduct(); break;
                case 4: displayProducts(); break;
                case 5: filterProducts(); break;
                case 6: calculateTotalValue(); break;
                case 0: System.out.println("Exiting..."); System.exit(0);
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addProduct() {
        try {
            System.out.print("Enter Product ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (products.containsKey(id)) {
                System.out.println("ID already exists!");
                return;
            }
            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Product Price: ");
            double price = Double.parseDouble(scanner.nextLine());
            
            products.put(id, new Product(id, name, price));
            System.out.println("Product added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for ID or Price.");
        }
    }

    private static void editProduct() {
        try {
            System.out.print("Enter Product ID to edit: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (!products.containsKey(id)) {
                System.out.println("Product not found.");
                return;
            }
            System.out.print("Enter new Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Product Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Product p = products.get(id);
            p.setName(name);
            p.setPrice(price);
            System.out.println("Product updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void deleteProduct() {
        try {
            System.out.print("Enter Product ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (products.remove(id) != null) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            products.values().forEach(System.out::println);
        }
    }

    private static void filterProducts() {
        System.out.println("Products with price greater than 100:");
        List<Product> filtered = products.values().stream()
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());
        
        if (filtered.isEmpty()) {
            System.out.println("No products found meeting the criteria.");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    private static void calculateTotalValue() {
        double total = products.values().stream()
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total value of all products: " + total);
    }
}
