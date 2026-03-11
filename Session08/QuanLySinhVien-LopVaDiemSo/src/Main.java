import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();

        int choice;

        do {

            System.out.println("\n===== MENU SINH VIÊN =====");
            System.out.println("1. Nhập danh sách sinh viên");
            System.out.println("2. In danh sách sinh viên");
            System.out.println("3. Tìm sinh viên GPA cao nhất");
            System.out.println("4. In tổng số sinh viên đã tạo");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Nhập số lượng sinh viên: ");
                    int n = sc.nextInt();

                    for (int i = 0; i < n; i++) {
                        System.out.println("\nSinh viên " + (i + 1));
                        Student s = new Student();
                        s.input();
                        list.add(s);
                    }
                    break;

                case 2:
                    System.out.println("\nDanh sách sinh viên:");
                    for (Student s : list) {
                        s.print();
                    }
                    break;

                case 3:
                    if (list.isEmpty()) {
                        System.out.println("Danh sách rỗng");
                        break;
                    }

                    Student max = list.get(0);

                    for (Student s : list) {
                        if (s.getGpa() > max.getGpa()) {
                            max = s;
                        }
                    }

                    System.out.println("\nSinh viên GPA cao nhất:");
                    max.print();
                    break;

                case 4:
                    System.out.println("Tổng số sinh viên đã tạo: "
                            + Student.getTotalStudent());
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