import java.util.Scanner;

public class PrimeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== CHƯƠNG TRÌNH KIỂM TRA SỐ NGUYÊN TỐ ===");

        try {
            System.out.print("Vui lòng nhập một số nguyên: ");
            String input = scanner.nextLine();
            
            // Xử lý không phải là số
            int number = Integer.parseInt(input);

            // Kiểm tra số hợp lệ (> 0)
            if (number <= 0) {
                // Chúng ta có thể tự throw Exception để nó bắt trong catch
                throw new Exception("số nhập vào không hợp lệ (phải > 0) để kiểm tra số nguyên tố.");
            }

            // Kiểm tra số nguyên tố
            if (isPrime(number)) {
                System.out.println("Kết quả: " + number + " là số nguyên tố.");
            } else {
                System.out.println("Kết quả: " + number + " KHÔNG PHẢI là số nguyên tố.");
            }

        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Người dùng nhập không phải là số (chữ, ký tự đặc biệt, hoặc số thực).");
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Phương thức kiểm tra số nguyên tố
     * @param n số cần kiểm tra
     * @return true nếu là số nguyên tố, ngược lại false
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

