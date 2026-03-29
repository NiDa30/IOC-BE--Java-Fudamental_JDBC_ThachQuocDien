import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("****************** MENU QUẢN LÝ ĐIỂM DANH ******************");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị danh sách sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choiceStr = scanner.nextLine();
            
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Nhập id sinh viên :");
                    String id = scanner.nextLine();
                    System.out.println("Nhập tên sinh viên:");
                    String name = scanner.nextLine();
                    manager.add(new Student(id, name));
                    System.out.println("Sinh viên đã được thêm thành công.");
                    break;
                case 2:
                    manager.display();
                    System.out.println("Nhập id sinh viên cần sửa:");
                    String editId = scanner.nextLine();
                    int editIndex = manager.findIndexById(editId);
                    if (editIndex != -1) {
                        System.out.println("Nhập tên mới sinh viên:");
                        String newName = scanner.nextLine();
                        manager.update(editIndex, new Student(editId, newName));
                        System.out.println("Sinh viên đã được sửa thành công.");
                    } else {
                        System.out.println("Không tìm thấy sinh viên !");
                    }
                    break;
                case 3:
                    manager.display();
                    System.out.println("Nhập id sinh viên cần xóa:");
                    String deleteId = scanner.nextLine();
                    int deleteIndex = manager.findIndexById(deleteId);
                    if (deleteIndex != -1) {
                        manager.delete(deleteIndex);
                        System.out.println("Đã xóa thành công sinh viên !");
                    } else {
                        System.out.println("Không tìm thấy sinh viên !");
                    }
                    break;
                case 4:
                    manager.display();
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}