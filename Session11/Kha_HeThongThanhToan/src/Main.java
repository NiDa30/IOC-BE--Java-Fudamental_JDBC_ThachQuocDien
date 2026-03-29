public class Main {
    public static void main(String[] args) {
        // Tạo mảng Payment chứa các loại thanh toán khác nhau
        Payment[] payments = {
            new CashPayment(1500000),      // Thanh toán tiền mặt
            new CreditCardPayment(4500000), // Thanh toán thẻ tín dụng
            new EWalletPayment(2200000)     // Thanh toán ví điện tử
        };

        System.out.println("--- HỆ THỐNG THANH TOÁN ---");
        
        // Duyệt qua mảng và gọi các phương thức tương ứng
        for (Payment payment : payments) {
            payment.printAmount();
            payment.pay();
            
            // Kiểm tra nếu có khả năng hoàn tiền (implement interface Refundable)
            if (payment instanceof Refundable) {
                ((Refundable) payment).refund();
            }
            System.out.println("--------------------------");
        }
    }
}