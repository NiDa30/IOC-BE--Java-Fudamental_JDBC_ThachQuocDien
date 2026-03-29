import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringToIntConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> validNumbersList = new ArrayList<>();
        int invalidStringCounter = 0;

        System.out.println("=== CHƯƠNG TRÌNH CHUYỂN ĐỔI CHUỖI SANG SỐ NGUYÊN ===");
        System.out.println("Vui lòng nhập các chuỗi. Nhập 'exit' để kết thúc và xem kết quả.");

        while (true) {
            System.out.print("Nhập một chuỗi: ");
            String userInput = scanner.nextLine().trim();

            // Kiểm tra ký tự thoát
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            // Thử chuyển đổi chuỗi sang số nguyên
            try {
                int convertedNumber = Integer.parseInt(userInput);
                // Nếu thành công, lưu vào danh sách hợp lệ
                validNumbersList.add(convertedNumber);
                System.out.println("-> [Đã chuyển đổi thành công: " + convertedNumber + "]");
            } catch (NumberFormatException e) {
                // Nếu thất bại (ngoại lệ NumberFormatException), tăng biến đếm
                invalidStringCounter++;
                System.out.println("-> [Lỗi: Chuỗi '" + userInput + "' không phải là số nguyên hợp lệ]");
            }
        }

        // Thống kê và hiển thị kết quả
        System.out.println("\n-------------------------------------------");
        System.out.println("BÁO CÁO THỐNG KÊ:");
        System.out.println("Số chuỗi hợp lệ: " + validNumbersList.size());
        System.out.println("Số chuỗi không hợp lệ: " + invalidStringCounter);
        System.out.println("Danh sách số nguyên hợp lệ: " + validNumbersList);
        System.out.println("-------------------------------------------");

        scanner.close();
    }
}
