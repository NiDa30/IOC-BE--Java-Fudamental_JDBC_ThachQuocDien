public abstract class Drink implements IPromotion {
    private String id;
    private String name;
    private double price;

    public Drink(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract void prepare();

    @Override
    public void applyDiscount(double percentage) {
        this.price = this.price * (1 - percentage / 100);
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Tên: %s, Giá: %.2f", id, name, price);
    }
}
