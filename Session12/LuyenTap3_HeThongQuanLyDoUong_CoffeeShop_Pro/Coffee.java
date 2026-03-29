public class Coffee extends Drink {
    public Coffee(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void prepare() {
        System.out.println("Cách pha chế: Pha bằng máy");
    }

    @Override
    public String toString() {
        return "[Coffee] " + super.toString();
    }
}
