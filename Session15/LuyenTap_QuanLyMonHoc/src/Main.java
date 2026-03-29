import ra.entity.Subject;
import ra.service.SubjectManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static SubjectManager<Subject> subjectManager = new SubjectManager<>();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ MÔN HỌC ---");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm môn học");
            System.out.println("3. Xóa môn học theo code");
            System.out.println("4. Tìm kiếm môn học theo tên");
            System.out.println("5. Lọc môn học theo tín chỉ (> 3)");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    subjectManager.display();
                    break;
                case 2:
                    addSubject();
                    break;
                case 3:
                    deleteSubject();
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    filterByCredits();
                    break;
                case 6:
                    System.out.println("Đã thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }

    private static void addSubject() {
        try {
            System.out.print("Nhập mã môn học: ");
            String code = scanner.nextLine();
            System.out.print("Nhập tên môn học: ");
            String name = scanner.nextLine();
            System.out.print("Nhập số tín chỉ (0-10): ");
            int credits = Integer.parseInt(scanner.nextLine());

            // Xử lý ngoại lệ theo yêu cầu
            if (credits < 0 || credits > 10) {
                throw new IllegalArgumentException("Số tín chỉ không hợp lệ! Chỉ được từ 0 đến 10.");
            }

            System.out.print("Nhập ngày bắt đầu (dd-MM-yyyy): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine(), formatter);

            Subject subject = new Subject(code, name, credits, startDate);
            subjectManager.add(subject);
            System.out.println("Thêm môn học thành công!");

        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Tín chỉ phải là số nguyên!");
        } catch (DateTimeParseException e) {
            System.err.println("Lỗi: Sai định dạng ngày! Vui lòng nhập dd-MM-yyyy.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi hệ thống: " + e.getLocalizedMessage());
        }
    }

    private static void deleteSubject() {
        System.out.print("Nhập mã môn học cần xóa: ");
        String code = scanner.nextLine();
        boolean removed = subjectManager.remove(s -> s.getCode().equalsIgnoreCase(code));
        if (removed) {
            System.out.println("Xóa môn học thành công.");
        } else {
            System.out.println("Lỗi: Không tìm thấy môn học có mã " + code);
        }
    }

    private static void searchByName() {
        System.out.print("Nhập tên môn học cần tìm: ");
        String name = scanner.nextLine().toLowerCase();
        
        // Thực hành Optional để xử lý tìm kiếm
        Optional<Subject> result = subjectManager.findFirst(s -> s.getName().toLowerCase().contains(name));
        
        if (result.isPresent()) {
            System.out.println("Kết quả tìm thấy: " + result.get());
        } else {
            System.out.println("Không có môn học phù hợp.");
        }
    }

    private static void filterByCredits() {
        System.out.println("Các môn học có số tín chỉ trên 3:");
        List<Subject> filteredList = subjectManager.filter(s -> s.getCredits() > 3);
        
        if (filteredList.isEmpty()) {
            System.out.println("Không có môn học nào có tín chỉ > 3.");
        } else {
            filteredList.forEach(System.out::println);
        }
    }
}