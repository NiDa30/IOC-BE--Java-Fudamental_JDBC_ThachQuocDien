public class CashPayment extends Payment {
    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.println("Đang thanh toán bằng tiền mặt...");
    }
}
