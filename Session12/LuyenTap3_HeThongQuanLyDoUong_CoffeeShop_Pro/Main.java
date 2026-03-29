import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Drink> menu = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("--- MENU HỆ THỐNG QUẢN LÝ COFFEESHOP PRO ---");
            System.out.println("1. Thêm món vào Menu");
            System.out.println("2. Hiển thị Menu");
            System.out.println("3. Áp dụng mã giảm giá");
            System.out.println("4. Xóa món");
            System.out.println("5. Thống kê (Giá tiền trung bình)");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addDrink();
                        break;
                    case 2:
                        displayMenu();
                        break;
                    case 3:
                        applyDiscount();
                        break;
                    case 4:
                        removeDrink();
                        break;
                    case 5:
                        statistics();
                        break;
                    case 6:
                        System.out.println("Cảm ơn đã sử dụng hệ thống!");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số!");
            }
        }
    }

    private static void addDrink() {
        System.out.println("Chọn loại đồ uống: 1. Cà phê, 2. Trà trái cây");
        int type = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Nhập ID món: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên món: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá món: ");
        double price = Double.parseDouble(scanner.nextLine());

        if (type == 1) {
            menu.add(new Coffee(id, name, price));
        } else if (type == 2) {
            menu.add(new FruitTea(id, name, price));
        } else {
            System.out.println("Loại không hợp lệ!");
        }
    }

    private static void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu đang trống!");
            return;
        }
        System.out.println("--- DANH MỤC ĐỒ UỐNG ---");
        for (Drink d : menu) {
            System.out.println(d.toString());
            d.prepare();
        }
    }

    private static void applyDiscount() {
        if (menu.isEmpty()) {
            System.out.println("Không có món nào để giảm giá.");
            return;
        }
        System.out.print("Nhập % giảm giá: ");
        double percentage = Double.parseDouble(scanner.nextLine());
        for (Drink d : menu) {
            d.applyDiscount(percentage);
        }
        System.out.println("Đã áp dụng giảm giá cho toàn bộ menu.");
    }

    private static void removeDrink() {
        System.out.print("Nhập ID món cần xóa: ");
        String id = scanner.nextLine();
        Drink found = null;
        for (Drink d : menu) {
            if (d.getId().equalsIgnoreCase(id)) {
                found = d;
                break;
            }
        }
        if (found != null) {
            menu.remove(found);
            System.out.println("Đã xóa món: " + found.getName());
        } else {
            System.out.println("Không tìm thấy món có ID: " + id);
        }
    }

    private static void statistics() {
        if (menu.isEmpty()) {
            System.out.println("Thống kê: 0đ (Menu trống)");
            return;
        }
        double sum = 0;
        for (Drink d : menu) {
            sum += d.getPrice();
        }
        double avg = sum / menu.size();
        System.out.printf("Thống kê: Tổng số món: %d, Giá trị trung bình: %.2f đ\n", menu.size(), avg);
    }
}
