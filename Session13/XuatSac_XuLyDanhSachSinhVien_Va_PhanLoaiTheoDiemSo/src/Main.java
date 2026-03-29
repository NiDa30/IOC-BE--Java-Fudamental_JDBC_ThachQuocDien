import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Student> studentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("================ MENU ================");
            System.out.println("1. Nhập danh sách sinh viên");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo tên");
            System.out.println("4. Phân loại sinh viên theo GPA");
            System.out.println("0. Thoát chương trình");
            System.out.println("======================================");
            System.out.print("Lựa chọn của bạn: ");
            
            String choiceStr = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    inputStudents();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchByName();
                    break;
                case 4:
                    classifyStudents();
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void inputStudents() {
        System.out.print("Nhập số lượng sinh viên muốn thêm: ");
        int count;
        try {
            count = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Số lượng không hợp lệ!");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("--- Nhập thông tin sinh viên " + (i + 1) + " ---");
            System.out.print("Nhập tên: ");
            String name = scanner.nextLine();
            System.out.print("Nhập GPA: ");
            double gpa;
            try {
                gpa = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("GPA không hợp lệ, vui lòng nhập lại cho sinh viên này!");
                i--;
                continue;
            }
            studentList.add(new Student(nextId++, name, gpa));
        }
        System.out.println("Đã thêm sinh viên thành công!");
    }

    private static void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("Danh sách trống!");
        } else {
            System.out.println("\nDANH SÁCH SINH VIÊN:");
            for (Student s : studentList) {
                System.out.println(s);
            }
        }
    }

    private static void searchByName() {
        if (studentList.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.print("Nhập tên sinh viên cần tìm: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(searchName.toLowerCase())) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sinh viên có tên chứa: " + searchName);
        }
    }

    private static void classifyStudents() {
        if (studentList.isEmpty()) {
            System.out.println("Danh sách trống!");
        } else {
            System.out.println("\nPHÂN LOẠI SINH VIÊN:");
            for (Student s : studentList) {
                System.out.println("Tên: " + s.getName() + " | GPA: " + s.getGpa() + " | Xếp loại: " + s.getRank());
            }
        }
    }
}