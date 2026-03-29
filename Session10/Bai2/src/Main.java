public class Main {
    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("    NHẬP THÔNG TIN MÁY MÓC      ");
        System.out.println("================================\n");

        WashingMachine washingMachine = new WashingMachine();
        washingMachine.input();

        System.out.println(); // Dòng trống ngăn cách
        
        AirConditioner airConditioner = new AirConditioner();
        airConditioner.input();

        System.out.println("\n================================");
        System.out.println("    HIỂN THỊ THÔNG TIN MÁY MÓC  ");
        System.out.println("================================\n");

        washingMachine.display();
        System.out.println(); // Dòng trống ngăn cách
        airConditioner.display();
    }
}
