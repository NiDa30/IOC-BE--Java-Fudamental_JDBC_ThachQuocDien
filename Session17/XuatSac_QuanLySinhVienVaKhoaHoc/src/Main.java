import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager sm = new StudentManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN VÀ KHÓA HỌC ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Thêm khóa học");
            System.out.println("3. Ghi danh sinh viên");
            System.out.println("4. Cập nhật điểm số");
            System.out.println("5. Liệt kê sinh viên & điểm");
            System.out.println("6. Thoát");
            System.out.print("Vui lòng chọn: ");

            try {
                String choiceStr = scanner.nextLine();
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1: handleAddStudent(); break;
                    case 2: handleAddCourse(); break;
                    case 3: handleEnroll(); break;
                    case 4: handleUpdateGrade(); break;
                    case 5: sm.listStudentsAndGrades(); break;
                    case 6: System.out.println("Tạm biệt!"); return;
                    default: System.err.println("Lựa chọn không hợp lệ (1-6).");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số.");
            } catch (Exception e) {
                System.err.println("Lỗi hệ thống: " + e.getMessage());
            }
        }
    }

    private static void handleAddStudent() {
        try {
            System.out.print("Tên sinh viên: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Tên không được trống!");

            System.out.print("Email sinh viên: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) throw new IllegalArgumentException("Email không được trống!");

            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            sm.addStudent(student);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleAddCourse() {
        try {
            System.out.print("Tên khóa học: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) throw new IllegalArgumentException("Tên không được trống!");

            System.out.print("Số tín chỉ: ");
            int credits = Integer.parseInt(scanner.nextLine().trim());
            if (credits <= 0) throw new IllegalArgumentException("Tín chỉ phải > 0!");

            Course course = new Course();
            course.setTitle(title);
            course.setCredits(credits);
            sm.addCourse(course);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Tín chỉ phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleEnroll() {
        try {
            System.out.print("ID Sinh viên: ");
            int sid = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("ID Khóa học: ");
            int cid = Integer.parseInt(scanner.nextLine().trim());
            sm.enrollStudent(sid, cid);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID phải là số.");
        }
    }

    private static void handleUpdateGrade() {
        try {
            System.out.print("ID Sinh viên: ");
            int sid = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("ID Khóa học: ");
            int cid = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Nhập điểm mới: ");
            double grade = Double.parseDouble(scanner.nextLine().trim());
            if (grade < 0 || grade > 10) throw new IllegalArgumentException("Điểm phải từ 0 đến 10!");

            sm.updateStudentGrade(sid, cid, grade);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID và điểm phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
}