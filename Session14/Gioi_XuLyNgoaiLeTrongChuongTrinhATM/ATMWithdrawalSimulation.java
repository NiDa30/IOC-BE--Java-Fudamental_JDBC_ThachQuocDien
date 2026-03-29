import java.util.Scanner;

public class ATMWithdrawalSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long currentBalance = 1000000; // 1,000,000 đồng
        final long MIN_BALANCE = 50000; // 50,000 đồng

        System.out.println("=== CHƯƠNG TRÌNH MÔ PHỎNG ATM ===");
        System.out.println("Số dư hiện tại: " + currentBalance + " đồng");
        System.out.println("Số dư tối thiểu cần duy trì: " + MIN_BALANCE + " đồng");

        while (true) {
            System.out.print("\nNhập số tiền muốn rút: ");
            String input = scanner.nextLine().trim();

            try {
                // Xử lý không phải là số (Lỗi: Vui lòng nhập một số hợp lệ!)
                long withdrawAmount = Long.parseLong(input);

                // Kiểm tra số tiền rút phải lớn hơn 0
                if (withdrawAmount <= 0) {
                    System.err.println("Lỗi: Số tiền rút phải lớn hơn 0!");
                    continue;
                }

                // Số tiền rút lớn hơn số dư hiện có (Lỗi: Số tiền rút vượt quá số dư!)
                if (withdrawAmount > currentBalance) {
                    System.err.println("Lỗi: Số tiền rút vượt quá số dư!");
                    continue;
                }

                // Số dư còn lại sau khi rút nhỏ hơn 50.000 đồng (Lỗi: Tài khoản phải duy trì số dư tối thiểu 50.000 đồng!)
                if (currentBalance - withdrawAmount < MIN_BALANCE) {
                    System.err.println("Lỗi: Tài khoản phải duy trì số dư tối thiểu 50.000 đồng!");
                    continue;
                }

                // Rút tiền thành công
                currentBalance -= withdrawAmount;
                System.out.println("-------------------------------------------");
                System.out.println("Giao dịch thành công!");
                System.out.println("Số tiền đã rút: " + withdrawAmount + " đồng");
                System.out.println("Số dư còn lại: " + currentBalance + " đồng");
                System.out.println("-------------------------------------------");
                
                // Sau khi rút thành công thì có thể thoát hoặc tiếp tục. Ở đây tôi sẽ cho phép rút tiếp.
                System.out.print("Bạn có muốn thực hiện giao dịch khác? (y/n): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("y")) {
                    break;
                }

            } catch (NumberFormatException e) {
                // Nhập không phải là số (chữ, ký tự đặc biệt)
                System.err.println("Lỗi: Vui lòng nhập một số hợp lệ!");
            }
        }

        System.out.println("Cảm ơn bạn đã sử dụng dịch vụ ATM!");
        scanner.close();
    }
}
