

public class Computer extends Asset {
    private String ram;
    private String cpu;

    public Computer(String assetCode, String name, double purchasePrice, int yearsUsed, String ram, String cpu) {
        super(assetCode, name, purchasePrice, yearsUsed);
        this.ram = ram;
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public double getMarketValue() {
        // Depreciation 20% per year: price * (1 - 0.2 * yearsUsed) or price * (0.8 ^ yearsUsed)
        // I'll use linear depreciation for simplicity, but capped at 0.
        double value = getPurchasePrice() * (1 - 0.2 * getYearsUsed());
        return Math.max(0, value);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", RAM: %s, CPU: %s, Giá trị hiện tại: %.2f", ram, cpu, getMarketValue());
    }
}
