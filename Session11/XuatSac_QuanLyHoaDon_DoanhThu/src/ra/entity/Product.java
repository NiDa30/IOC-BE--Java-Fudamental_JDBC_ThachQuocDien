package ra.entity;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private ProductStatus status;

    public Product() {}

    public Product(String productId, String productName, double price, ProductStatus status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProd, int index) {
        System.out.println("Nhập thông tin cho sản phẩm thứ " + (index + 1) + ":");
        this.productId = inputProductId(scanner, arrProd);
        this.productName = inputProductName(scanner, arrProd);
        this.price = inputPrice(scanner);
        this.status = inputStatus(scanner);
    }

    private String inputProductId(Scanner scanner, Product[] arrProd) {
        while (true) {
            System.out.print("Nhập mã sản phẩm (Cxxx, Sxxx, Axxx - 4 ký tự): ");
            String id = scanner.nextLine();
            if (id.matches("^[CSA]\\w{3}$")) {
                boolean isExist = false;
                for (int i = 0; i < arrProd.length; i++) {
                    if (arrProd[i] != null && arrProd[i].getProductId().equals(id)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return id;
                } else {
                    System.err.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại.");
                }
            } else {
                System.err.println("Mã sản phẩm không đúng định dạng. Vui lòng nhập lại.");
            }
        }
    }

    private String inputProductName(Scanner scanner, Product[] arrProd) {
        while (true) {
            System.out.print("Nhập tên sản phẩm (10-50 ký tự): ");
            String name = scanner.nextLine();
            if (name.length() >= 10 && name.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < arrProd.length; i++) {
                    if (arrProd[i] != null && arrProd[i].getProductName().equalsIgnoreCase(name)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return name;
                } else {
                    System.err.println("Tên sản phẩm đã tồn tại. Vui lòng nhập lại.");
                }
            } else {
                System.err.println("Tên sản phẩm phải từ 10 đến 50 ký tự. Vui lòng nhập lại.");
            }
        }
    }

    private double inputPrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập giá bán: ");
                double price = Double.parseDouble(scanner.nextLine());
                if (price > 0) {
                    return price;
                } else {
                    System.err.println("Giá bán phải lớn hơn 0. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Giá bán phải là số thực. Vui lòng nhập lại.");
            }
        }
    }

    private ProductStatus inputStatus(Scanner scanner) {
        while (true) {
            System.out.println("Chọn trạng thái sản phẩm:");
            System.out.println("1. AVAILABLE");
            System.out.println("2. OUT_OF_STOCK");
            System.out.println("3. STOP_SELLING");
            System.out.print("Lựa chọn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: return ProductStatus.AVAILABLE;
                    case 2: return ProductStatus.OUT_OF_STOCK;
                    case 3: return ProductStatus.STOP_SELLING;
                    default:
                        System.err.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là số nguyên. Vui lòng nhập lại.");
            }
        }
    }

    public void displayData() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("Mã SP: %-5s | Tên SP: %-20s | Giá: %-10.2f | Trạng thái: %-15s\n",
                productId, productName, price, status);
    }
}
