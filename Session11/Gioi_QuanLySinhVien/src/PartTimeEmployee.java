public class PartTimeEmployee extends Employee {
    private double workingHour;

    public PartTimeEmployee(int id, String name, double workingHour) {
        super(id, name);
        this.workingHour = workingHour;
    }

    public double getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(double workingHour) {
        this.workingHour = workingHour;
    }

    @Override
    public double calculateSalary() {
        // Assume hourly rate is 50,000 VND
        return workingHour * 50000;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Loại: Nhân viên Bán thời gian, Số giờ làm việc: " + workingHour);
    }
}
