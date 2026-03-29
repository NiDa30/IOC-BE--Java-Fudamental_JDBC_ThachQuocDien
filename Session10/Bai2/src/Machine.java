import java.util.Scanner;

public class Machine {
    private String model;
    private String manufacturer;
    private int yearOfManufacture;
    private double power;

    public Machine() {
        this.model = "";
        this.manufacturer = "";
        this.yearOfManufacture = 0;
        this.power = 0.0;
    }

    public Machine(String model, String manufacturer, int yearOfManufacture, double power) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mẫu máy (model): ");
        this.model = scanner.nextLine();
        
        System.out.print("Nhập nhà sản xuất: ");
        this.manufacturer = scanner.nextLine();
        
        System.out.print("Nhập năm sản xuất: ");
        this.yearOfManufacture = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Nhập công suất (kW): ");
        this.power = Double.parseDouble(scanner.nextLine());
    }

    public void display() {
        System.out.println("Mẫu máy: " + model);
        System.out.println("Nhà sản xuất: " + manufacturer);
        System.out.println("Năm sản xuất: " + yearOfManufacture);
        System.out.println("Công suất: " + power + " kW");
    }
}
