package ra.entity;

import java.util.Scanner;

public class InvoiceDetail {
    private Product product;
    private int quantity;
    private double subTotal;

    public InvoiceDetail() {}

    public InvoiceDetail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subTotal = product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void inputData(Scanner scanner, Product[] arrProd, int prodIndex) {
        this.product = selectProduct(scanner, arrProd, prodIndex);
        this.quantity = inputQuantity(scanner);
        this.subTotal = this.product.getPrice() * this.quantity;
    }

    private Product selectProduct(Scanner scanner, Product[] arrProd, int prodIndex) {
        while (true) {
            System.out.println("Chọn sản phẩm từ danh sách có sẵn:");
            for (int i = 0; i < prodIndex; i++) {
                if (arrProd[i] != null) {
                    System.out.println( (i+1) + ". " + arrProd[i].getProductName() + " - Mã: " + arrProd[i].getProductId());
                }
            }
            System.out.print("Nhập mã sản phẩm cần chọn: ");
            String productId = scanner.nextLine();
            for (int i = 0; i < prodIndex; i++) {
                if (arrProd[i] != null && arrProd[i].getProductId().equalsIgnoreCase(productId)) {
                    return arrProd[i];
                }
            }
            System.err.println("Mã sản phẩm không tồn tại. Vui lòng thử lại.");
        }
    }

    private int inputQuantity(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập số lượng mua (lớn hơn 0): ");
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > 0) {
                    return quantity;
                } else {
                    System.err.println("Số lượng mua phải lớn hơn 0. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Số lượng phải là số nguyên. Vui lòng nhập lại.");
            }
        }
    }

    public void displayData() {
        System.out.printf("Tên sản phẩm: %-20s | Số lượng: %-5d | Thành tiền: %-10.2f\n",
                product.getProductName(), quantity, subTotal);
    }
}
