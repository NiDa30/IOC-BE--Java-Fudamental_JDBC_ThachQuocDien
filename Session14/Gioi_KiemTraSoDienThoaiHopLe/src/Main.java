import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneNumberValidator validator = new PhoneNumberValidator();

        System.out.println("=== CHƯƠNG TRÌNH KIỂM TRA SỐ ĐIỆN THOẠI HỢP LỆ ===");
        System.out.println("Nhập các số điện thoại cách nhau bởi dấu phẩy:");
        System.out.println("(Ví dụ: 0987654321, 0123456789, 12345abcde, 0909 888 777)");

        String input = scanner.nextLine();

        // Ủy quyền logic cho đối tượng PhoneNumberValidator
        validator.validateList(input);

        scanner.close();
    }
}