public class CurrencyConverter {

    private static double rate;

    public static void setRate(double r) {
        if (r > 0) {
            rate = r;
        } else {
            System.out.println("Tỉ giá không hợp lệ");
        }
    }

    public static double getRate() {
        return rate;
    }

    public static double toUSD(int vnd) {
        return vnd / rate;
    }

    public static String formatUSD(double usd) {
        return String.format("%.2f USD", usd);
    }

}