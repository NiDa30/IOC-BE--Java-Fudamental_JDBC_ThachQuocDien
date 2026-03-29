package Gioi_QuanLySanPham_Va_TinhToanGiaTriTrongCuaHang;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1. Create product list
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1500.5));
        products.add(new Product("Smartphone", 899.99));
        products.add(new Product("Mouse", 25.0));
        products.add(new Product("Keyboard", 45.0));
        products.add(new Product("Headphones", 120.0));

        // 2. Implementation of Interface logic
        ProductProcessor processor = new ProductProcessorImpl();

        // 3. Check for expensive products (> 100) using Predicate
        Predicate<Product> isExpensive = p -> p.getPrice() > 100;
        
        System.out.println("--- Kiểm tra sản phẩm đắt tiền (> 100) ---");
        if (processor.hasExpensiveProduct(products)) {
            List<Product> expensiveList = products.stream()
                    .filter(isExpensive)
                    .collect(Collectors.toList());
            System.out.println("Các sản phẩm đắt tiền tìm thấy:");
            expensiveList.forEach(System.out::println);
        } else {
            System.out.println("Không có sản phẩm đắt tiền");
        }

        // 4. Calculate total value
        double totalValue = processor.calculateTotalValue(products);
        System.out.println("\n--- Tổng giá trị sản phẩm ---");
        System.out.println("Tổng: " + totalValue);

        // 5. Print entire list using Static method from Interface
        System.out.println("\n--- Hiển thị danh sách đầy đủ ---");
        ProductProcessor.printProductList(products);
    }
}