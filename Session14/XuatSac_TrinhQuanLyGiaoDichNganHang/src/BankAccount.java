public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount() {}

    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gửi tiền vào tài khoản
     * @param amount Số tiền gửi
     * @throws Exception Các ngoại lệ liên quan đến số tiền không hợp lệ
     */
    public void deposit(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Lỗi: Số tiền gửi không hợp lệ (phải > 0)!");
        }
        this.balance += amount;
        System.out.println("Gửi tiền thành công và cập nhật số dư (+ " + amount + ")");
    }

    /**
     * Rút tiền từ tài khoản
     * @param amount Số tiền rút
     * @throws Exception Các ngoại lệ liên quan đến số tiền không hợp lệ hoặc không đủ số dư
     */
    public void withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Lỗi: Số tiền rút không hợp lệ (phải > 0)!");
        }
        if (amount > this.balance) {
            throw new Exception("Lỗi: Số tiền rút (" + amount + ") vượt quá số dư hiện có (" + this.balance + ")!");
        }
        this.balance -= amount;
        System.out.println("Rút tiền thành công và cập nhật số dư (- " + amount + ")");
    }

    /**
     * Chuyển tiền giữa các tài khoản
     * @param destAccount Tài khoản nhận tiền
     * @param amount Số tiền chuyển
     * @throws Exception Các ngoại lệ liên quan đến tài khoản không tồn tại, số tiền không hợp lệ hoặc không đủ số dư
     */
    public void transfer(BankAccount destAccount, double amount) throws Exception {
        // Kiểm tra tài khoản đích có tồn tại hay không (theo đề bài yeu cầu xử lý trong phương thức này)
        if (destAccount == null) {
            throw new Exception("Lỗi: Tài khoản đích không tồn tại trong danh sách tài khoản!");
        }

        // Kiểm tra số tiền hợp lệ (> 0)
        if (amount <= 0) {
            throw new Exception("Lỗi: Số tiền chuyển không hợp lệ (phải > 0)!");
        }

        // Kiểm tra tài khoản nguồn có đủ số dư hay không
        if (amount > this.balance) {
            throw new Exception("Lỗi: Số dư tài khoản nguồn không đủ để thực hiện chuyển tiền!");
        }

        // Nếu hợp lệ, tiến hành giao dịch
        this.balance -= amount;
        destAccount.balance += amount;
        System.out.println("Chuyển tiền thành công từ [" + this.accountId + "] quy [" + destAccount.getAccountId() + "] số tiền: " + amount);
    }

    @Override
    public String toString() {
        return "Tài khoản [" + accountId + "] - Số dư: " + balance + " VNĐ";
    }
}
