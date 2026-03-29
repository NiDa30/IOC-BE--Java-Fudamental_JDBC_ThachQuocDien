package ra.run;

import ra.dao.ProductDAO;
import ra.entity.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    private static final ProductDAO productDAO = new ProductDAO();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n********************PRODUCT MANAGEMENT****************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("8. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayAllProducts();
                    break;
                case "2":
                    addNewProduct();
                    break;
                case "3":
                    updateProduct();
                    break;
                case "4":
                    deleteProduct();
                    break;
                case "5":
                    searchProductByName();
                    break;
                case "6":
                    sortProductsByPrice();
                    break;
                case "7":
                    displayStatistics();
                    break;
                case "8":
                    System.out.println("Tạm biệt!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void displayAllProducts() {
        List<Product> products = productDAO.getAll();
        if (products.isEmpty()) {
            System.out.println("Chưa có sản phẩm nào trong danh sách.");
        } else {
            System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
            products.forEach(System.out::println);
        }
    }

    private static void addNewProduct() {
        Product p = inputProduct(null);
        if (p != null) {
            if (productDAO.add(p)) {
                System.out.println("Thêm mới sản phẩm thành công!");
            } else {
                System.out.println("Thêm mới sản phẩm thất bại (Lỗi: Tên sản phẩm đã tồn tại hoặc lỗi CSDL).");
            }
        }
    }

    private static void updateProduct() {
        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        int id = readInt();
        Product existing = productDAO.getById(id);
        if (existing == null) {
            System.out.println("Không tìm thấy sản phẩm có mã này.");
            return;
        }

        System.out.println("Thông tin hiện tại: " + existing);
        Product updated = inputProduct(existing);
        if (updated != null) {
            updated.setProductId(id);
            if (productDAO.update(updated)) {
                System.out.println("Cập nhật sản phẩm thành công!");
            } else {
                System.out.println("Cập nhật sản phẩm thất bại.");
            }
        }
    }

    private static void deleteProduct() {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        int id = readInt();
        Product existing = productDAO.getById(id);
        if (existing == null) {
            System.out.println("Không tìm thấy sản phẩm có mã này.");
            return;
        }

        System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này không (Y/N)? ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            if (productDAO.delete(id)) {
                System.out.println("Xóa sản phẩm thành công!");
            } else {
                System.out.println("Xóa sản phẩm thất bại.");
            }
        } else {
            System.out.println("Đã hủy thao tác xóa.");
        }
    }

    private static void searchProductByName() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String query = scanner.nextLine();
        List<Product> products = productDAO.searchByName(query);
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào khớp với tìm kiếm của bạn.");
        } else {
            System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
            products.forEach(System.out::println);
        }
    }

    private static void sortProductsByPrice() {
        List<Product> products = productDAO.getAll();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào để sắp xếp.");
            return;
        }
        Collections.sort(products, Comparator.comparingDouble(Product::getProductPrice));
        System.out.println("\n--- DANH SÁCH SẢN PHẨM (GIÁ TĂNG DẦN) ---");
        products.forEach(System.out::println);
    }

    private static void displayStatistics() {
        productDAO.displayStatistics();
    }

    // Input with validation
    private static Product inputProduct(Product existing) {
        Product p = new Product();
        
        // Product Name
        while (true) {
            System.out.print("Nhập tên sản phẩm (không trống, tối đa 100 ký tự): ");
            String name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Tên sản phẩm không được để trống.");
            } else if (name.length() > 100) {
                System.out.println("Tên sản phẩm tối đa 100 ký tự.");
            } else {
                p.setProductName(name);
                break;
            }
        }

        // Product Price
        while (true) {
            System.out.print("Nhập giá sản phẩm (> 0): ");
            float price = readFloat();
            if (price <= 0) {
                System.out.println("Giá sản phẩm phải lớn hơn 0.");
            } else {
                p.setProductPrice(price);
                break;
            }
        }

        // Product Title
        while (true) {
            System.out.print("Nhập tiêu đề sản phẩm (tối đa 200 ký tự): ");
            String title = scanner.nextLine();
            if (title == null || title.trim().isEmpty()) {
                System.out.println("Tiêu đề sản phẩm không được để trống.");
            } else if (title.length() > 200) {
                System.out.println("Tiêu đề sản phẩm tối đa 200 ký tự.");
            } else {
                p.setProductTitle(title);
                break;
            }
        }

        // Product Created Date
        while (true) {
            System.out.print("Nhập ngày tạo sản phẩm (dd/mm/yyyy): ");
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateStr, dateFormatter);
                p.setProductCreated(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ. Vui lòng nhập theo định dạng dd/mm/yyyy.");
            }
        }

        // Product Catalog
        while (true) {
            System.out.print("Nhập danh mục sản phẩm: ");
            String catalog = scanner.nextLine();
            if (catalog == null || catalog.trim().isEmpty()) {
                System.out.println("Danh mục sản phẩm không được để trống.");
            } else {
                p.setProductCatalog(catalog);
                break;
            }
        }

        // Product Status
        while (true) {
            System.out.print("Nhập trạng thái (true: Còn hàng, false: Hết hàng): ");
            String statusStr = scanner.nextLine();
            if (statusStr.equalsIgnoreCase("true") || statusStr.equalsIgnoreCase("t") || statusStr.equals("1")) {
                p.setProductStatus(true);
                break;
            } else if (statusStr.equalsIgnoreCase("false") || statusStr.equalsIgnoreCase("f") || statusStr.equals("0")) {
                p.setProductStatus(false);
                break;
            } else {
                System.out.println("Giá trị không hợp lệ. Nhập 'true' hoặc 'false'.");
            }
        }

        return p;
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Phải là số nguyên. Nhập lại: ");
            }
        }
    }

    private static float readFloat() {
        while (true) {
            try {
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Phải là số thực. Nhập lại: ");
            }
        }
    }
}
