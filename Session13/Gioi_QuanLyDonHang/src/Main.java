import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final OrderManager manager = new OrderManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("****************** MENU QUẢN LÝ ĐƠN HÀNG ******************");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Sửa đơn hàng");
            System.out.println("3. Xóa đơn hàng");
            System.out.println("4. Hiển thị danh sách đơn hàng");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn:\n");
            
            String choiceStr = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                continue;
            }

            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    editOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    manager.display();
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addOrder() {
        String code = inputNotEmpty("Nhập mã đơn hàng:");
        System.out.println("Nhập tên khách hàng:");
        String name = scanner.nextLine();
        manager.add(new Order(code, name));
        System.out.println("Đơn hàng đã được thêm thành công.");
    }

    private static void editOrder() {
        manager.display();
        System.out.println("Nhập mã đơn hàng cần sửa:");
        String code = scanner.nextLine();
        int index = manager.findIndexByCode(code);
        if (index != -1) {
            System.out.println("Nhập tên khách hàng mới:");
            String newName = scanner.nextLine();
            manager.update(index, new Order(code, newName));
            System.out.println("Đơn hàng đã được sửa thành công.");
        } else {
            System.out.println("Không tìm thấy đơn hàng với mã: " + code);
        }
    }

    private static void deleteOrder() {
        manager.display();
        System.out.println("Nhập mã đơn hàng cần xóa:");
        String code = scanner.nextLine();
        int index = manager.findIndexByCode(code);
        if (index != -1) {
            manager.delete(index);
            System.out.println("Đơn hàng đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy đơn hàng với mã: " + code);
        }
    }

    private static String inputNotEmpty(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("\nVui lòng ko để trống !");
            } else {
                return input;
            }
        }
    }
}