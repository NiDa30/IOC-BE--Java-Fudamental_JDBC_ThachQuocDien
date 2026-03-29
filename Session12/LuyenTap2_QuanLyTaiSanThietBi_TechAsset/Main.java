

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Asset> assets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("--- MENU QUẢN LÝ TÀI SẢN (TECHASSET) ---");
            System.out.println("1. Nhập tài sản");
            System.out.println("2. Xuất báo cáo (liệt kê và giá trị khấu hao)");
            System.out.println("3. Tìm kiếm theo mã");
            System.out.println("4. Tìm kiếm theo giá mua lớn hơn");
            System.out.println("5. Sửa giá mua (theo mã)");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addAsset();
                        break;
                    case 2:
                        exportReport();
                        break;
                    case 3:
                        searchByCode();
                        break;
                    case 4:
                        searchByMinPrice();
                        break;
                    case 5:
                        updatePurchasePrice();
                        break;
                    case 6:
                        System.out.println("Tạm biệt!");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số!");
            }
        }
    }

    private static void addAsset() {
        System.out.println("Chọn loại tài sản: 1. Máy tính, 2. Thiết bị mạng");
        int type = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Nhập mã tài sản: ");
        String code = scanner.nextLine();
        System.out.print("Nhập tên tài sản: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá mua: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập năm đã sử dụng: ");
        int years = Integer.parseInt(scanner.nextLine());

        if (type == 1) {
            System.out.print("Nhập RAM: ");
            String ram = scanner.nextLine();
            System.out.print("Nhập CPU: ");
            String cpu = scanner.nextLine();
            assets.add(new Computer(code, name, price, years, ram, cpu));
        } else if (type == 2) {
            System.out.print("Nhập số cổng: ");
            int ports = Integer.parseInt(scanner.nextLine());
            assets.add(new NetworkDevice(code, name, price, years, ports));
        } else {
            System.out.println("Loại không hợp lệ!");
        }
    }

    private static void exportReport() {
        if (assets.isEmpty()) {
            System.out.println("Chưa có tài sản nào!");
            return;
        }
        System.out.println("--- DANH SÁCH TÀI SẢN ---");
        for (Asset a : assets) {
            System.out.println(a.toString());
            // Demonstrate polymorphism req: showValue(Asset a)
            showValue(a);
        }
    }

    // This method demonstrates the polymorphism mentioned in section "Tính Đa hình"
    public static void showValue(Asset a) {
        System.out.printf("   ➜ Giá trị hiện tại: %.2f VNĐ\n", a.getMarketValue());
    }

    // --- Searches using overloading (implicitly called based on criteria) ---
    
    private static void searchByCode() {
        System.out.print("Nhập mã tài sản cần tìm: ");
        String code = scanner.nextLine();
        List<Asset> results = search(code);
        displaySearchResults(results);
    }

    private static void searchByMinPrice() {
        System.out.print("Nhập mức giá mua tối thiểu: ");
        double minPrice = Double.parseDouble(scanner.nextLine());
        List<Asset> results = search(minPrice);
        displaySearchResults(results);
    }

    // Overloaded search for assetCode (String)
    public static List<Asset> search(String assetCode) {
        List<Asset> results = new ArrayList<>();
        for (Asset a : assets) {
            if (a.getAssetCode().equalsIgnoreCase(assetCode)) {
                results.add(a);
            }
        }
        return results;
    }

    // Overloaded search for purchasePrice (double)
    public static List<Asset> search(double minPrice) {
        List<Asset> results = new ArrayList<>();
        for (Asset a : assets) {
            if (a.getPurchasePrice() > minPrice) {
                results.add(a);
            }
        }
        return results;
    }

    private static void updatePurchasePrice() {
        System.out.print("Nhập mã tài sản cần sửa giá: ");
        String code = scanner.nextLine();
        List<Asset> found = search(code);
        if (found.isEmpty()) {
            System.out.println("Không tìm thấy mã tài sản này!");
        } else {
            Asset a = found.get(0);
            System.out.println("Đã tìm thấy: " + a.toString());
            System.out.print("Nhập giá mua mới: ");
            double newPrice = Double.parseDouble(scanner.nextLine());
            a.setPurchasePrice(newPrice);
            System.out.println("Cập nhật thành công!");
        }
    }

    private static void displaySearchResults(List<Asset> results) {
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy kết quả nào.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Asset r : results) {
                System.out.println(r.toString());
            }
        }
    }
}
