public class EWalletPayment extends Payment implements Refundable {
    public EWalletPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.println("Đang thanh toán bằng ví điện tử...");
    }

    @Override
    public void refund() {
        System.out.println("Hoàn tiền vào ví điện tử thành công.");
    }
}
