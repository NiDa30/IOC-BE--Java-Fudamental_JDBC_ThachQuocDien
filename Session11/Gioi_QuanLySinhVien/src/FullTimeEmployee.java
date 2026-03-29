public class FullTimeEmployee extends Employee implements BonusEligible {
    private double basicSalary;

    public FullTimeEmployee(int id, String name, double basicSalary) {
        super(id, name);
        this.basicSalary = basicSalary;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    @Override
    public double calculateSalary() {
        return basicSalary;
    }

    @Override
    public double calculateBonus() {
        // Assume bonus is 10% of basic salary
        return basicSalary * 0.10;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Loại: Nhân viên Toàn thời gian, Lương CB: " + String.format("%,.0f", basicSalary) + " VND");
    }
}
