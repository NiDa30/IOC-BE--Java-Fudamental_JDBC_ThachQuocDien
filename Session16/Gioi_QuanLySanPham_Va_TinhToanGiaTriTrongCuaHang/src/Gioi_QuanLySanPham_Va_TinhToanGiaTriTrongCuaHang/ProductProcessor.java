package Gioi_QuanLySanPham_Va_TinhToanGiaTriTrongCuaHang;

import java.util.List;

public interface ProductProcessor {
    /**
     * Abstract method to calculate the total value of products.
     */
    double calculateTotalValue(List<Product> products);

    /**
     * Static method to print all products in the list.
     */
    static void printProductList(List<Product> products) {
        System.out.println("Danh sách sản phẩm:");
        products.forEach(System.out::println);
    }

    /**
     * Default method to check if there is any product with price > 100.
     */
    default boolean hasExpensiveProduct(List<Product> products) {
        return products.stream().anyMatch(p -> p.getPrice() > 100);
    }
}
