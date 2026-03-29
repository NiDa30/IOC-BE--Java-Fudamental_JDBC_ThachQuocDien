import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Phương thức phụ hỗ trợ tìm kiếm tài khoản trong danh sách
     */
    private static BankAccount findAccountById(List<BankAccount> accountList, String accountId) {
        for (BankAccount account : accountList) {
            if (account.getAccountId().equalsIgnoreCase(accountId)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<BankAccount> accountList = new ArrayList<>();

        // Khởi tạo các tài khoản ngân hàng mẫu (List<BankAccount>)
        accountList.add(new BankAccount("ACC001", 1000000));
        accountList.add(new BankAccount("ACC002", 500000));

        System.out.println("=== HỆ THỐNG QUẢN LÝ GIAO DỊCH NGÂN HÀNG ===");
        System.out.println("Danh sách tài khoản hiện tại:");
        for (BankAccount a : accountList) {
            System.out.println(a.toString());
        }

        System.out.println("\n--- BẮT ĐẦU THỰC HIỆN GIAO DỊCH ---");

        // Gửi tiền (Deposit)
        System.out.println("\n[1] Gửi tiền vào ACC001:");
        try {
            BankAccount acc = findAccountById(accountList, "ACC001");
            if (acc != null) {
                System.out.print("Nhập số tiền muốn gửi: ");
                double amount = Double.parseDouble(scanner.nextLine());
                acc.deposit(amount);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Số tiền không phải là số hợp lệ!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Rút tiền (Withdraw)
        System.out.println("\n[2] Rút tiền từ ACC002:");
        try {
            BankAccount acc = findAccountById(accountList, "ACC002");
            if (acc != null) {
                System.out.print("Nhập số tiền muốn rút: ");
                double amount = Double.parseDouble(scanner.nextLine());
                acc.withdraw(amount);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Số tiền không phải là số hợp lệ!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Chuyển tiền (Transfer)
        System.out.println("\n[3] Chuyển tiền từ ACC001 sang ACC002:");
        try {
            BankAccount source = findAccountById(accountList, "ACC001");
            System.out.print("Nhập ID tài khoản ĐÍCH: ");
            String destId = scanner.nextLine();
            BankAccount dest = findAccountById(accountList, destId);

            // Xử lý ném ngoại lệ nếu tài khoản đích không tồn tại (yêu cầu xử lý trong các phương thức hoặc kết hợp main)
            // Lưu ý: Đề bài yêu cầu xử lý trong phương thức transfer(), nên chúng ta pass dest vào transfer.
            System.out.print("Nhập số tiền muốn chuyển: ");
            double amount = Double.parseDouble(scanner.nextLine());

            // Chuyển tiền
            source.transfer(dest, amount);

        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Số tiền không phải là số hợp lệ!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // In kết quả ra màn hình cuối cùng để đối soát dữ liệu
        System.out.println("\n--- KẾT QUẢ CUỐI CÙNG ---");
        for (BankAccount a : accountList) {
            System.out.println(String.format("Tài khoản [%s] | Số dư cuối: %.2f VNĐ", a.getAccountId(), a.getBalance()));
        }

        scanner.close();
    }
}