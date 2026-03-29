import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Product> availableProducts = new ArrayList<>();
    private static ShoppingCart cart = new ShoppingCart();

    public static void main(String[] args) {
        // Khởi tạo danh sách sản phẩm mẫu
        availableProducts.add(new Product("P01", "iPhone 15 Pro Max", 34000000));
        availableProducts.add(new Product("P02", "MacBook Air M2", 28000000));
        availableProducts.add(new Product("P03", "AirPods Pro", 5500000));
        availableProducts.add(new Product("P04", "Apple Watch S9", 10000000));

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("=== CHƯƠNG TRÌNH MÔ PHỎNG GIỎ HÀNG ===");

        while (choice != 0) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm vào giỏ hàng");
            System.out.println("3. Xóa sản phẩm khỏi giỏ hàng");
            System.out.println("4. Xem giỏ hàng");
            System.out.println("5. Thanh toán");
            System.out.println("0. Thoát");
            System.out.println("===========================");
            System.out.print("Lựa chọn của bạn: ");

            try {
                String inputStr = scanner.nextLine();
                choice = Integer.parseInt(inputStr);

                switch (choice) {
                    case 1:
                        showAvailableProducts();
                        break;
                    case 2:
                        addProductToCart(scanner);
                        break;
                    case 3:
                        removeProductFromCart(scanner);
                        break;
                    case 4:
                        cart.displayCart();
                        break;
                    case 5:
                        cart.checkout();
                        break;
                    case 0:
                        System.out.println("Hẹn gặp lại bạn!");
                        break;
                    default:
                        System.out.println("Lỗi: Lựa chọn không hợp lệ (0-5)!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        scanner.close();
    }

    private static void showAvailableProducts() {
        System.out.println("\n--- DANH SÁCH SẢN PHẨM CÓ SẴN ---");
        for (Product p : availableProducts) {
            System.out.println(p);
        }
    }

    private static void addProductToCart(Scanner scanner) throws Exception {
        System.out.print("Nhập mã sản phẩm (P01, P02, ...): ");
        String productId = scanner.nextLine().trim().toUpperCase();
        
        Product foundProduct = null;
        for (Product p : availableProducts) {
            if (p.getId().equalsIgnoreCase(productId)) {
                foundProduct = p;
                break;
            }
        }

        if (foundProduct == null) {
            throw new Exception("Lỗi: Không tìm thấy sản phẩm có mã này!");
        }

        System.out.print("Nhập số lượng: ");
        try {
            int quantity = Integer.parseInt(scanner.nextLine());
            cart.addToCart(foundProduct, quantity);
        } catch (NumberFormatException e) {
            throw new Exception("Lỗi: Số lượng không hợp lệ!");
        }
    }

    private static void removeProductFromCart(Scanner scanner) throws Exception {
        System.out.print("Nhập mã sản phẩm cần xóa khỏi giỏ: ");
        String productId = scanner.nextLine().trim().toUpperCase();
        cart.removeFromCart(productId);
    }
}