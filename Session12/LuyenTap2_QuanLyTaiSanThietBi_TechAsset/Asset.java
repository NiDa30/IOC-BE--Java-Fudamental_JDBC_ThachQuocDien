

public abstract class Asset {
    private String assetCode;
    private String name;
    private double purchasePrice;
    private int yearsUsed;

    public Asset(String assetCode, String name, double purchasePrice, int yearsUsed) {
        this.assetCode = assetCode;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.yearsUsed = yearsUsed;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getYearsUsed() {
        return yearsUsed;
    }

    public void setYearsUsed(int yearsUsed) {
        this.yearsUsed = yearsUsed;
    }

    public abstract double getMarketValue();

    @Override
    public String toString() {
        return String.format("Mã: %s, Tên: %s, Giá mua: %.2f, Năm SD: %d", assetCode, name, purchasePrice, yearsUsed);
    }
}
