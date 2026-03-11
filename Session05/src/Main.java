import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Session 05 - Java Fundamental
 * Exercise Management System
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Vui lòng chọn bài tập (0-6): ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Lỗi: Vui lòng nhập một số từ 0 đến 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    executeExercise01(scanner);
                    break;
                case 2:
                    executeExercise02();
                    break;
                case 3:
                    executeExercise03(scanner);
                    break;
                case 4:
                    executeExercise04(scanner);
                    break;
                case 5:
                    executeExercise05(scanner);
                    break;
                case 6:
                    executeExercise06(scanner);
                    break;
                case 0:
                    System.out.println("\n✨ Cảm ơn bạn đã sử dụng chương trình. Hẹn gặp lại!");
                    running = false;
                    break;
                default:
                    System.out.println("\n⚠️ Lựa chọn không hợp lệ. Vui lòng chọn từ 0 đến 6.");
            }

            if (running && choice != 0 && choice != 5 && choice != 6) {
                System.out.println("\nNhấn Enter để quay lại menu chính...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         HỆ THỐNG QUẢN LÝ BÀI TẬP - SESSION 05");
        System.out.println("=".repeat(50));
        System.out.println("1. [ Khá ] Kiểm tra định dạng email");
        System.out.println("2. [ Khá ] So sánh hiệu suất String, StringBuilder, StringBuffer");
        System.out.println("3. [ Giỏi ] Kiểm tra mật khẩu hợp lệ");
        System.out.println("4. [ Giỏi ] Tạo mã giả ngẫu nhiên");
        System.out.println("5. [ Xuất sắc ] Quản lý chuỗi ký tự");
        System.out.println("6. [ Xuất sắc ] Quản lý tên sinh viên");
        System.out.println("0. Thoát chương trình");
        System.out.println("-".repeat(50));
    }

    /**
     * Bài 1: Kiểm tra định dạng email
     */
    private static void executeExercise01(Scanner scanner) {
        System.out.println("\n--- [ BÀI 1: KIỂM TRA ĐỊNH DẠNG EMAIL ] ---");
        System.out.print("Nhập địa chỉ email cần kiểm tra: ");
        String email = scanner.nextLine().trim();
        String emailRegex = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,6}$";
        if (Pattern.matches(emailRegex, email)) {
            System.out.println("✅ Kết quả: \"Email hợp lệ\"");
        } else {
            System.out.println("❌ Kết quả: \"Email không hợp lệ\"");
        }
    }

    /**
     * Bài 2: So sánh hiệu suất String, StringBuilder, StringBuffer
     */
    private static void executeExercise02() {
        System.out.println("\n--- [ BÀI 2: SO SÁNH HIỆU SUẤT XỬ LÝ CHUỖI ] ---");
        int iterations = 40000; 
        System.out.println("Đang thực hiện nối chuỗi " + iterations + " lần...");

        long start = System.currentTimeMillis();
        String str = "Hello";
        for (int i = 0; i < iterations; i++) str += " World";
        long end = System.currentTimeMillis();
        System.out.println("Thời gian với String: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("Hello");
        for (int i = 0; i < iterations; i++) sb.append(" World");
        end = System.currentTimeMillis();
        System.out.println("Thời gian với StringBuilder: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer("Hello");
        for (int i = 0; i < iterations; i++) sbf.append(" World");
        end = System.currentTimeMillis();
        System.out.println("Thời gian với StringBuffer: " + (end - start) + " ms");
    }

    /**
     * Bài 3: Kiểm tra mật khẩu hợp lệ
     */
    private static void executeExercise03(Scanner scanner) {
        System.out.println("\n--- [ BÀI 3: KIỂM TRA MẬT KHẨU HỢP LỆ ] ---");
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%!]).{8,}$";
        if (Pattern.matches(passwordRegex, password)) System.out.println("✅ Mật khẩu hợp lệ");
        else System.out.println("❌ Mật khẩu không hợp lệ");
    }

    /**
     * Bài 4: Tạo mã giả ngẫu nhiên
     */
    private static void executeExercise04(Scanner scanner) {
        System.out.println("\n--- [ BÀI 4: TẠO MÃ GIẢ NGẪU NHIÊN ] ---");
        System.out.print("Nhập độ dài n: ");
        try {
            int n = Integer.parseInt(scanner.nextLine().trim());
            if (n < 1 || n > 1000) return;
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder res = new StringBuilder();
            Random rand = new Random();
            for (int i = 0; i < n; i++) res.append(chars.charAt(rand.nextInt(chars.length())));
            System.out.println("Mã ngẫu nhiên: " + res.toString());
        } catch (Exception e) {}
    }

    /**
     * Bài 5: Quản lý chuỗi ký tự
     */
    private static void executeExercise05(Scanner scanner) {
        String currentString = "";
        boolean inExercise = true;
        while (inExercise) {
            System.out.println("\n********************** MENU **********************");
            System.out.println("1. Nhập chuỗi");
            System.out.println("2. Đếm số ký tự thường, hoa, số, đặc biệt");
            System.out.println("3. Đảo ngược chuỗi");
            System.out.println("4. Kiểm tra Palindrome");
            System.out.println("5. Chuẩn hóa chuỗi (xóa khoảng trắng dư thừa, viết hoa chữ cái đầu)");
            System.out.println("6. Thoát");
            System.out.println("**************************************************");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1: System.out.print("Nhập chuỗi: "); currentString = scanner.nextLine(); break;
                    case 2:
                        int lower = 0, upper = 0, digit = 0, special = 0;
                        for (char c : currentString.toCharArray()) {
                            if (Character.isLowerCase(c)) lower++;
                            else if (Character.isUpperCase(c)) upper++;
                            else if (Character.isDigit(c)) digit++;
                            else special++;
                        }
                        System.out.println("Số ký tự thường: " + lower + ", hoa: " + upper + ", số: " + digit + ", đặc biệt: " + special);
                        break;
                    case 3: System.out.println("Đảo ngược: " + new StringBuilder(currentString).reverse().toString()); break;
                    case 4:
                        String clean = currentString.replaceAll("\\s+", "").toLowerCase();
                        if (clean.equals(new StringBuilder(clean).reverse().toString())) System.out.println("✅ Palindrome");
                        else System.out.println("❌ Không phải Palindrome");
                        break;
                    case 5:
                        String normalized = currentString.trim().replaceAll("\\s+", " ");
                        if (!normalized.isEmpty()) normalized = normalized.substring(0, 1).toUpperCase() + normalized.substring(1);
                        currentString = normalized; System.out.println("Chuẩn hóa: " + currentString);
                        break;
                    case 6: inExercise = false; break;
                }
            } catch (Exception e) {}
        }
    }

    /**
     * Bài 6: Quản lý tên sinh viên
     */
    private static void executeExercise06(Scanner scanner) {
        ArrayList<String> students = new ArrayList<>();
        boolean inExercise = true;

        while (inExercise) {
            System.out.println("\n********************** MENU **********************");
            System.out.println("1. Thêm tên sinh viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Tìm tên sinh viên chứa từ khóa");
            System.out.println("4. Đếm số sinh viên có tên bắt đầu bằng chữ cái nhập vào");
            System.out.println("5. Sắp xếp danh sách tên theo thứ tự A-Z");
            System.out.println("6. Thoát");
            System.out.println("**************************************************");
            System.out.print("Lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên sinh viên mới: ");
                        String newStudent = scanner.nextLine().trim();
                        if (!newStudent.isEmpty()) {
                            students.add(newStudent);
                            System.out.println("✅ Đã thêm: " + newStudent);
                        }
                        break;
                    case 2:
                        if (students.isEmpty()) System.out.println("Danh sách trống.");
                        else {
                            System.out.println("Danh sách sinh viên:");
                            for (int i = 0; i < students.size(); i++) System.out.println((i + 1) + ". " + students.get(i));
                        }
                        break;
                    case 3:
                        System.out.print("Nhập từ khóa tìm kiếm: ");
                        String keyword = scanner.nextLine().toLowerCase();
                        System.out.println("Kết quả tìm kiếm:");
                        boolean found = false;
                        for (String s : students) {
                            if (s.toLowerCase().contains(keyword)) {
                                System.out.println("- " + s);
                                found = true;
                            }
                        }
                        if (!found) System.out.println("Không tìm thấy kết quả nào.");
                        break;
                    case 4:
                        System.out.print("Nhập chữ cái bắt đầu: ");
                        String letterInput = scanner.nextLine().trim().toLowerCase();
                        if (letterInput.isEmpty()) break;
                        char firstChar = letterInput.charAt(0);
                        long count = students.stream().filter(s -> s.toLowerCase().startsWith(String.valueOf(firstChar))).count();
                        System.out.println("Số sinh viên có tên bắt đầu bằng '" + firstChar + "': " + count);
                        break;
                    case 5:
                        Collections.sort(students);
                        System.out.println("✅ Danh sách đã được sắp xếp A-Z.");
                        break;
                    case 6:
                        inExercise = false;
                        System.out.println("Quay lại menu chính.");
                        break;
                    default:
                        System.out.println("⚠️ Lựa chọn không hợp lệ.");
                }
            } catch (Exception e) {
                System.out.println("❌ Vui lòng nhập số.");
            }
        }
    }
}