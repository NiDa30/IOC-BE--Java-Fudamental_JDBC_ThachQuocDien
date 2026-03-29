import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long currentBalance = 1000000; // Số dư ban đầu 1,000,000 đồng
        final long MIN_BALANCE = 50000; // Số dư duy trì tối thiểu 50,000 đồng

        System.out.println("=== CHƯƠNG TRÌNH MÔ PHỎNG ATM ===");
        System.out.println("Số dư hiện tại: " + currentBalance + " đồng");
        System.out.println("Số dư tối thiểu cần duy trì: " + MIN_BALANCE + " đồng");

        while (true) {
            System.out.print("\nNhập số tiền muốn rút (hoặc nhập 'exit' để thoát): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                // Xử lý không phải là số (Lỗi: Vui lòng nhập một số hợp lệ!)
                long withdrawAmount = Long.parseLong(input);

                // Kiểm tra số tiền rút hợp lệ
                if (withdrawAmount <= 0) {
                    System.out.println("Lỗi: Số tiền rút phải lớn hơn 0!");
                    continue;
                }

                // Kiểm tra số tiền rút lớn hơn số dư (Lỗi: Số tiền rút vượt quá số dư!)
                if (withdrawAmount > currentBalance) {
                    System.out.println("Lỗi: Số tiền rút vượt quá số dư!");
                    continue;
                }

                // Kiểm tra số dư tối thiểu sau khi rút (Lỗi: Tài khoản tối thiểu 50.000)
                if (currentBalance - withdrawAmount < MIN_BALANCE) {
                    System.out.println("Lỗi: Tài khoản phải duy trì số dư tối thiểu 50.000 đồng!");
                    continue;
                }

                // Nếu tất cả thỏa mãn -> Rút tiền thành công
                currentBalance -= withdrawAmount;
                System.out.println("-------------------------------------------");
                System.out.println("Giao dịch thành công!");
                System.out.println("Số tiền đã rút: " + withdrawAmount + " đồng");
                System.out.println("Số dư thực tế còn lại: " + currentBalance + " đồng");
                System.out.println("-------------------------------------------");

                // Sau mỗi giao dịch thành công, hỏi người dùng có muốn tiếp tục không
                System.out.print("Bạn có muốn thực hiện giao dịch khác không? (y/n): ");
                String next = scanner.nextLine().trim().toLowerCase();
                if (!next.equals("y")) {
                    break;
                }

            } catch (NumberFormatException e) {
                // Nhập không phải là số
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
            }
        }

        System.out.println("Cảm ơn bạn đã sử dụng dịch vụ ATM!");
        scanner.close();
    }
}