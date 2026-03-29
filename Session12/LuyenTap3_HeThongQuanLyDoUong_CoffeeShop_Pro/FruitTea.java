public class FruitTea extends Drink {
    public FruitTea(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void prepare() {
        System.out.println("Cách pha chế: Lắc với đá và trái cây tươi");
    }

    @Override
    public String toString() {
        return "[Trà trái cây] " + super.toString();
    }
}
