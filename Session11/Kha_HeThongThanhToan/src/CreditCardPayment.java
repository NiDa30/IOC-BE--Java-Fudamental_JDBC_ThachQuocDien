public class CreditCardPayment extends Payment implements Refundable {
    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.println("Đang thanh toán bằng thẻ tín dụng...");
    }

    @Override
    public void refund() {
        System.out.println("Hoàn tiền vào thẻ tín dụng thành công.");
    }
}
