package rikkei.educareer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Staff> staffList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("================ EDUCAREER MANAGEMENT MENU =================");
            System.out.println("1. Thêm mới nhân viên");
            System.out.println("2. Hiển thị danh sách nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên theo ID");
            System.out.println("4. Xóa nhân viên theo ID");
            System.out.println("5. Thoát");
            System.out.print("Vui lòng chọn chức năng (1-5): ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addNewStaff();
                    break;
                case "2":
                    displayAllStaff();
                    break;
                case "3":
                    updateStaff();
                    break;
                case "4":
                    deleteStaff();
                    break;
                case "5":
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống! Hẹn gặp lại.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số từ 1 đến 5.");
            }
        }
    }

    private static void addNewStaff() {
        System.out.println("Nhóm nhân viên bạn muốn thêm:");
        System.out.println("1. Giảng viên (Lecturer)");
        System.out.println("2. Nhân viên hành chính (Admin Staff)");
        System.out.print("Lựa chọn: ");
        String subChoice = scanner.nextLine();

        Staff newStaff = null;
        if (subChoice.equals("1")) {
            newStaff = new Lecturer();
        } else if (subChoice.equals("2")) {
            newStaff = new AdminStaff();
        } else {
            System.out.println("Lựa chọn không đúng!");
            return;
        }

        newStaff.inputData(scanner);
        staffList.add(newStaff);
        System.out.println("Thêm mới thành công!");
    }

    private static void displayAllStaff() {
        if (staffList.isEmpty()) {
            System.out.println("Danh sách nhân viên hiện đang trống.");
            return;
        }

        System.out.println("---------------- DANH SÁCH NHÂN VIÊN ----------------");
        for (Staff staff : staffList) {
            staff.displayData();
            staff.checkPerformance();
            System.out.println("-----------------------------------------------------");
        }
    }

    private static void updateStaff() {
        if (staffList.isEmpty()) {
            System.out.println("Danh sách trống, không thể cập nhật.");
            return;
        }

        System.out.print("Nhập ID nhân viên cần sửa: ");
        String targetId = scanner.nextLine();
        Staff foundStaff = null;
        for (Staff s : staffList) {
            if (s.getId().equalsIgnoreCase(targetId)) {
                foundStaff = s;
                break;
            }
        }

        if (foundStaff == null) {
            System.out.println("Không tìm thấy nhân viên có ID: " + targetId);
        } else {
            System.out.println("Đã tìm thấy: " + foundStaff.getName());
            System.out.println("Mời bạn nhập lại thông tin mới:");
            foundStaff.inputData(scanner);
            System.out.println("Cập nhật thành công!");
        }
    }

    private static void deleteStaff() {
        if (staffList.isEmpty()) {
            System.out.println("Danh sách trống, không thể xóa.");
            return;
        }

        System.out.print("Nhập ID nhân viên cần xóa: ");
        String targetId = scanner.nextLine();
        Staff foundStaff = null;
        for (Staff s : staffList) {
            if (s.getId().equalsIgnoreCase(targetId)) {
                foundStaff = s;
                break;
            }
        }

        if (foundStaff == null) {
            System.out.println("Không tìm thấy nhân viên có ID: " + targetId);
        } else {
            staffList.remove(foundStaff);
            System.out.println("Đã xóa nhân viên: " + foundStaff.getName());
        }
    }
}
