

public class NetworkDevice extends Asset {
    private int numberOfPorts;

    public NetworkDevice(String assetCode, String name, double purchasePrice, int yearsUsed, int numberOfPorts) {
        super(assetCode, name, purchasePrice, yearsUsed);
        this.numberOfPorts = numberOfPorts;
    }

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    @Override
    public double getMarketValue() {
        // Depreciation 10% per year: price * (1 - 0.1 * yearsUsed)
        double value = getPurchasePrice() * (1 - 0.1 * getYearsUsed());
        return Math.max(0, value);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Số cổng: %d, Giá trị hiện tại: %.2f", numberOfPorts, getMarketValue());
    }
}
