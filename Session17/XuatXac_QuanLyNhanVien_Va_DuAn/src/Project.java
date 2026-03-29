import java.math.BigDecimal;

public class Project {
    private int id;
    private String name;
    private BigDecimal budget;

    public Project(int id, String name, BigDecimal budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    public Project() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }

    @Override
    public String toString() {
        return String.format("ProjID: %-3d | Tên DA: %-30s | NS: $%.2f", id, name, budget);
    }
}
