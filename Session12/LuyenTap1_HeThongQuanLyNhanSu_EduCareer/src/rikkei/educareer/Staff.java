package rikkei.educareer;

import java.util.Scanner;

public abstract class Staff implements ICapability {
    private String id;
    private String name;
    private double baseSalary;

    public Staff() {
    }

    public Staff(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double calculateTotalSalary();

    public void inputData(Scanner scanner) {
        System.out.print("Nhập mã nhân viên: ");
        this.id = scanner.nextLine();
        System.out.print("Nhập tên nhân viên: ");
        this.name = scanner.nextLine();
        System.out.print("Nhập lương cơ bản: ");
        while (true) {
            try {
                this.baseSalary = Double.parseDouble(scanner.nextLine());
                if (this.baseSalary < 0) {
                    System.out.print("Lương không được âm, nhập lại: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Lương phải là số, nhập lại: ");
            }
        }
    }

    public void displayData() {
        System.out.printf("ID: %s | Tên: %s | Lương cơ bản: %,.0f VNĐ", id, name, baseSalary);
    }
}
