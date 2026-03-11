import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> list = new ArrayList<>();

        int choice;

        do {

            System.out.println("\n===== MENU SẢN PHẨM =====");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. In danh sách sản phẩm");
            System.out.println("3. Tìm sản phẩm theo khoảng giá");
            System.out.println("4. Thống kê số sản phẩm đã tạo");
            System.out.println("0. Thoát");

            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    Product p = new Product();
                    p.input();
                    list.add(p);
                    break;

                case 2:
                    System.out.println("\nDanh sách sản phẩm:");
                    for (Product pr : list) {
                        pr.print();
                    }
                    break;

                case 3:
                    System.out.print("Nhập giá thấp nhất: ");
                    double min = sc.nextDouble();

                    System.out.print("Nhập giá cao nhất: ");
                    double max = sc.nextDouble();

                    System.out.println("\nSản phẩm trong khoảng giá:");
                    for (Product pr : list) {
                        if (pr.getPrice() >= min && pr.getPrice() <= max) {
                            pr.print();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Tổng sản phẩm đã tạo: " + list.size());
                    break;

                case 0:
                    System.out.println("Thoát chương trình");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 0);

    }

}