import java.util.Scanner;

public class WashingMachine extends Machine {
    private double capacity;
    private boolean hasDryer;

    public WashingMachine() {
        super();
        this.capacity = 0.0;
        this.hasDryer = false;
    }

    public WashingMachine(String model, String manufacturer, int yearOfManufacture, double power, double capacity, boolean hasDryer) {
        super(model, manufacturer, yearOfManufacture, power);
        this.capacity = capacity;
        this.hasDryer = hasDryer;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public boolean isHasDryer() {
        return hasDryer;
    }

    public void setHasDryer(boolean hasDryer) {
        this.hasDryer = hasDryer;
    }

    @Override
    public void input() {
        System.out.println("--- Nhập thông tin Máy Giặt ---");
        super.input();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập dung tích (kg): ");
        this.capacity = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Có tính năng sấy không (true/false): ");
        this.hasDryer = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void display() {
        System.out.println("--- Thông tin Máy Giặt ---");
        super.display();
        System.out.println("Dung tích: " + capacity + " kg");
        System.out.println("Tính năng sấy: " + (hasDryer ? "Có" : "Không"));
    }
}
