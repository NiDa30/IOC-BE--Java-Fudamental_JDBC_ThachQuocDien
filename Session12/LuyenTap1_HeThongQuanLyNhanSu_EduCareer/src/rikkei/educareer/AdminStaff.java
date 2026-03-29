package rikkei.educareer;

import java.util.Scanner;

public class AdminStaff extends Staff {
    private double bonus;

    public AdminStaff() {
    }

    public AdminStaff(String id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double calculateTotalSalary() {
        return getBaseSalary() + bonus;
    }

    @Override
    public void checkPerformance() {
        System.out.println("Hiệu suất nhân viên hành chính được đánh giá qua sự chuyên nghiệp và tỉ lệ hoàn thành nhiệm vụ.");
    }

    @Override
    public void inputData(Scanner scanner) {
        super.inputData(scanner);
        System.out.print("Nhập tiền thưởng: ");
        while (true) {
            try {
                this.bonus = Double.parseDouble(scanner.nextLine());
                if (this.bonus < 0) {
                    System.out.print("Tiền thưởng không được âm, nhập lại: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Lương phải là số, nhập lại: ");
            }
        }
    }

    @Override
    public void displayData() {
        super.displayData();
        System.out.printf(" | Thưởng: %,.0f VNĐ | Tổng lương: %,.0f VNĐ%n", bonus, calculateTotalSalary());
    }
}
