import java.util.Scanner;

public class Product {

    private int id;
    private String name;
    private double price;

    // ID tự tăng
    private static int AUTO_ID = 1;

    // mã kho cố định
    final String WAREHOUSE_CODE = "KHO-01";

    // constructor không tham số
    public Product() {
        id = AUTO_ID++;
    }

    // constructor có tham số
    public Product(String name, double price) {
        this(); // gọi constructor trên
        this.name = name;
        this.price = price;
    }

    // nhập dữ liệu
    public void input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tên sản phẩm: ");
        name = sc.nextLine();

        System.out.print("Nhập giá: ");
        price = sc.nextDouble();
    }

    // in thông tin
    public void print() {
        System.out.println("ID: " + id +
                ", Name: " + name +
                ", Price: " + price +
                ", Warehouse: " + WAREHOUSE_CODE);
    }

    // getter
    public double getPrice() {
        return price;
    }
}