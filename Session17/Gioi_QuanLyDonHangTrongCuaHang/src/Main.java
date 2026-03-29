import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final OrderManager om = new OrderManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- HỆ THỐNG QUẢN LÝ ĐƠN HÀNG ---");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Thêm khách hàng (Tiện ích)");
            System.out.println("3. Cập nhật khách hàng");
            System.out.println("4. Tạo đơn hàng mới");
            System.out.println("5. Hiển thị tất cả đơn hàng");
            System.out.println("6. Tìm đơn hàng theo khách hàng");
            System.out.println("7. Thoát");
            System.out.print("Vui lòng chọn: ");

            try {
                String choiceStr = scanner.nextLine();
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1: handleAddProduct(); break;
                    case 2: handleAddCustomer(); break;
                    case 3: handleUpdateCustomer(); break;
                    case 4: handleCreateOrder(); break;
                    case 5: om.listAllOrders(); break;
                    case 6: handleSearchOrders(); break;
                    case 7: System.out.println("Tạm biệt!"); return;
                    default: System.err.println("Lựa chọn không hợp lệ (1-7)");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số.");
            } catch (Exception e) {
                System.err.println("Lỗi hệ thống: " + e.getMessage());
            }
        }
    }

    private static void handleAddProduct() {
        try {
            System.out.print("Tên sản phẩm: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Tên không được trống!");

            System.out.print("Giá bán: ");
            BigDecimal price = new BigDecimal(scanner.nextLine().trim());
            if (price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Giá không được âm!");

            Product p = new Product();
            p.setName(name);
            p.setPrice(price);
            om.addProduct(p);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Giá phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleAddCustomer() {
        try {
            System.out.print("Tên khách hàng: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Tên không được trống!");

            System.out.print("Email khách hàng: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) throw new IllegalArgumentException("Email không được trống!");

            Customer c = new Customer();
            c.setName(name);
            c.setEmail(email);
            om.addCustomer(c);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleUpdateCustomer() {
        try {
            System.out.print("Nhập ID khách hàng cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Họ tên mới: ");
            String name = scanner.nextLine().trim();
            System.out.print("Email mới: ");
            String email = scanner.nextLine().trim();

            if (name.isEmpty() || email.isEmpty()) throw new IllegalArgumentException("Không được để trống!");

            Customer c = new Customer();
            c.setName(name);
            c.setEmail(email);
            om.updateCustomer(id, c);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleCreateOrder() {
        try {
            System.out.print("ID khách hàng: ");
            int custId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("ID sản phẩm: ");
            int prodId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Số lượng: ");
            int qty = Integer.parseInt(scanner.nextLine().trim());

            if (qty <= 0) throw new IllegalArgumentException("Số lượng phải > 0!");

            om.createOrder(custId, prodId, qty);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID và số lượng phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleSearchOrders() {
        try {
            System.out.print("Nhập ID khách hàng: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            om.getOrdersByCustomer(id);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID phải là số.");
        }
    }
}