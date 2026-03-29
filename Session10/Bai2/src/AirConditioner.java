import java.util.Scanner;

public class AirConditioner extends Machine {
    private double coolingCapacity;
    private String energyEfficiency;

    public AirConditioner() {
        super();
        this.coolingCapacity = 0.0;
        this.energyEfficiency = "";
    }

    public AirConditioner(String model, String manufacturer, int yearOfManufacture, double power, double coolingCapacity, String energyEfficiency) {
        super(model, manufacturer, yearOfManufacture, power);
        this.coolingCapacity = coolingCapacity;
        this.energyEfficiency = energyEfficiency;
    }

    public double getCoolingCapacity() {
        return coolingCapacity;
    }

    public void setCoolingCapacity(double coolingCapacity) {
        this.coolingCapacity = coolingCapacity;
    }

    public String getEnergyEfficiency() {
        return energyEfficiency;
    }

    public void setEnergyEfficiency(String energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
    }

    @Override
    public void input() {
        System.out.println("--- Nhập thông tin Điều Hòa ---");
        super.input();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập công suất làm lạnh (BTU): ");
        this.coolingCapacity = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Nhập hiệu suất năng lượng (VD: A+, B, C): ");
        this.energyEfficiency = scanner.nextLine();
    }

    @Override
    public void display() {
        System.out.println("--- Thông tin Điều Hòa ---");
        super.display();
        System.out.println("Công suất làm lạnh: " + coolingCapacity + " BTU");
        System.out.println("Hiệu suất năng lượng: " + energyEfficiency);
    }
}
