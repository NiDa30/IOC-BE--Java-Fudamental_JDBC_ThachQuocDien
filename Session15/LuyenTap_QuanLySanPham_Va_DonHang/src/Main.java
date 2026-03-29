import ra.entity.Order;
import ra.entity.Product;
import ra.exception.BusinessException;

import java.util.*;

public class Main {
    private static List<Product> productList = new ArrayList<>();
    private static Map<String, Order> orderMap = new HashMap<>(); // Key: String mã đơn, Value: Order object
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n============ MENU ============");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xóa sản phẩm");
            System.out.println("3. Hiển thị sản phẩm");
            System.out.println("4. Tạo đơn hàng");
            System.out.println("5. Thêm sản phẩm vào đơn hàng");
            System.out.println("6. Hiển thị đơn hàng");
            System.out.println("0. Thoát");
            System.out.println("==============================");
            System.out.print("Lựa chọn của bạn: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    try {
                        removeProduct();
                    } catch (BusinessException e) {
                        System.err.println("Lỗi nghiệp vụ: " + e.getMessage());
                    }
                    break;
                case 3:
                    displayProducts();
                    break;
                case 4:
                    createOrder();
                    break;
                case 5:
                    try {
                        addProductToOrder();
                    } catch (BusinessException e) {
                        System.err.println("Lỗi nghiệp vụ: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        displayOrders();
                    } catch (BusinessException e) {
                        System.err.println("Lỗi nghiệp vụ: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn sai, vui lòng chọn lại!");
            }
        }
    }

    private static void addProduct() {
        try {
            System.out.print("Nhập ID sản phẩm: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập tên sản phẩm: ");
            String name = scanner.nextLine();
            System.out.print("Nhập giá sản phẩm: ");
            double price = Double.parseDouble(scanner.nextLine());

            if (price <= 0) {
                throw new BusinessException("Giá sản phẩm phải lớn hơn 0!");
            }

            productList.add(new Product(id, name, price));
            System.out.println("Sản phẩm đã được thêm vào danh sách.");
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Dữ liệu không hợp lệ!");
        } catch (BusinessException e) {
            System.err.println("Lỗi nghiệp vụ: " + e.getMessage());
        }
    }

    private static void removeProduct() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product product = productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BusinessException("Sản phẩm có ID " + id + " không tồn tại để xóa!"));

        productList.remove(product);
        System.out.println("Sản phẩm đã được xóa.");
    }

    private static void displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Kho sản phẩm trống!");
        } else {
            System.out.println("Danh sách sản phẩm:");
            productList.forEach(System.out::println);
        }
    }

    private static void createOrder() {
        System.out.print("Nhập mã đơn hàng (mã duy nhất): ");
        String orderCode = scanner.nextLine();
        
        if (orderMap.containsKey(orderCode)) {
            System.out.println("Mã đơn hàng đã tồn tại!");
            return;
        }

        System.out.print("Nhập ID số thứ tự của đơn (integer): ");
        int orderId = Integer.parseInt(scanner.nextLine());
        
        orderMap.put(orderCode, new Order(orderId));
        System.out.println("Tạo đơn hàng " + orderCode + " thành công.");
    }

    private static void addProductToOrder() {
        System.out.print("Nhập mã đơn hàng cần thêm sản phẩm: ");
        String orderCode = scanner.nextLine();

        Order order = Optional.ofNullable(orderMap.get(orderCode))
                .orElseThrow(() -> new BusinessException("Mã đơn hàng " + orderCode + " không tồn tại!"));

        System.out.print("Nhập ID sản phẩm cần thêm: ");
        int productId = Integer.parseInt(scanner.nextLine());

        Product product = productList.stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElseThrow(() -> new BusinessException("Sản phẩm có ID " + productId + " không tồn tại!"));

        order.addProduct(product);
        System.out.println("Đã thêm sản phẩm '" + product.getName() + "' vào đơn hàng " + orderCode + ".");
    }

    private static void displayOrders() {
        if (orderMap.isEmpty()) {
            System.out.println("Hiện không có đơn hàng nào.");
        } else {
            System.out.println("--- Danh sách tất cả đơn hàng ---");
            for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
                System.out.println("Mã đơn: " + entry.getKey() + " | " + entry.getValue());
            }
        }
    }
}