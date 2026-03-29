import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManagement tm = new TaskManagement();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- HỆ THỐNG QUẢN LÝ TO-DO LIST ---");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Liệt kê công việc");
            System.out.println("3. Cập nhật trạng thái");
            System.out.println("4. Xóa công việc");
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("6. Thống kê");
            System.out.println("7. Thoát");
            System.out.print("Vui lòng chọn: ");

            try {
                String choiceStr = scanner.nextLine();
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1: handleAddTask(); break;
                    case 2: handleListTasks(); break;
                    case 3: handleUpdateStatus(); break;
                    case 4: handleDeleteTask(); break;
                    case 5: handleSearchTask(); break;
                    case 6: tm.taskStatistics(); break;
                    case 7: System.out.println("Chào tạm biệt!"); return;
                    default: System.err.println("Lựa chọn không hợp lệ (1-7)");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số.");
            } catch (Exception e) {
                System.err.println("Lỗi hệ thống: " + e.getMessage());
            }
        }
    }

    private static void handleAddTask() {
        try {
            System.out.print("Nhập tên công việc: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Tên công việc không được để trống!");

            System.out.println("Trạng thái: ");
            System.out.println("1. Chưa hoàn thành");
            System.out.println("2. Đã hoàn thành");
            System.out.print("Vui lòng chọn: ");
            int stCode = Integer.parseInt(scanner.nextLine().trim());
            String status = (stCode == 2) ? "đã hoàn thành" : "chưa hoàn thành";

            tm.addTask(name, status);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Trạng thái phải là số (1 hoặc 2).");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleListTasks() {
        System.out.println("\n--- DANH SÁCH CÔNG VIỆC ---");
        List<Task> tasks = tm.listTasks();
        if (tasks.isEmpty()) {
            System.out.println("(Trống)");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    private static void handleUpdateStatus() {
        try {
            System.out.print("Nhập ID công việc cần cập nhật: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Cập nhật trạng thái thành: ");
            System.out.println("1. Chưa hoàn thành");
            System.out.println("2. Đã hoàn thành");
            System.out.print("Vui lòng chọn: ");
            int stCode = Integer.parseInt(scanner.nextLine().trim());
            String status = (stCode == 2) ? "đã hoàn thành" : "chưa hoàn thành";

            tm.updateTaskStatus(id, status);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID và trạng thái phải là số.");
        }
    }

    private static void handleDeleteTask() {
        try {
            System.out.print("Nhập ID công việc cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            tm.deleteTask(id);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID phải là số.");
        }
    }

    private static void handleSearchTask() {
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyword = scanner.nextLine().trim();
        List<Task> result = tm.searchTaskByName(keyword);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy công việc nào khớp với: " + keyword);
        } else {
            System.out.println("Kết quả tìm kiếm:");
            result.forEach(System.out::println);
        }
    }
}