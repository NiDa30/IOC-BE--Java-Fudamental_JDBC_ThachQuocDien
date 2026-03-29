package rikkei.educareer;

import java.util.Scanner;

public class Lecturer extends Staff {
    private int teachingHours;

    public Lecturer() {
    }

    public Lecturer(String id, String name, double baseSalary, int teachingHours) {
        super(id, name, baseSalary);
        this.teachingHours = teachingHours;
    }

    public int getTeachingHours() {
        return teachingHours;
    }

    public void setTeachingHours(int teachingHours) {
        this.teachingHours = teachingHours;
    }

    @Override
    public double calculateTotalSalary() {
        return getBaseSalary() + (teachingHours * 200000.0);
    }

    @Override
    public void checkPerformance() {
        if (teachingHours >= 100) {
            System.out.println("Hiệu suất giảng dạy: Tốt (Trên 100 giờ)");
        } else {
            System.out.println("Hiệu suất giảng dạy: Cần cải thiện (Dưới 100 giờ)");
        }
    }

    @Override
    public void inputData(Scanner scanner) {
        super.inputData(scanner);
        System.out.print("Nhập số giờ giảng dạy: ");
        while (true) {
            try {
                this.teachingHours = Integer.parseInt(scanner.nextLine());
                if (this.teachingHours < 0) {
                    System.out.print("Số giờ giảng không được âm, nhập lại: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Số giờ giảng phải là số nguyên, nhập lại: ");
            }
        }
    }

    @Override
    public void displayData() {
        super.displayData();
        System.out.printf(" | Giờ giảng: %d | Tổng lương: %,.0f VNĐ%n", teachingHours, calculateTotalSalary());
    }
}
