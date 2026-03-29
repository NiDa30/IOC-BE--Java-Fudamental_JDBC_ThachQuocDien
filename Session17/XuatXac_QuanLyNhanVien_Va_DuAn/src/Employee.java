import java.math.BigDecimal;

public class Employee {
    private int id;
    private String name;
    private String department;
    private BigDecimal salary;

    public Employee(int id, String name, String department, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Employee() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Tên: %-25s | Phòng: %-15s | Lương: $%.2f", id, name, department, salary);
    }
}
