public class Laptop extends Device implements Connectable, Chargeable {

    public Laptop(int id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        System.out.println("Máy tính xách tay " + getName() + " đang bật.");
    }

    @Override
    public void turnOff() {
        System.out.println("Máy tính xách tay " + getName() + " đang tắt.");
    }

    @Override
    public void charge() {
        System.out.println("Máy tính xách tay " + getName() + " đang sạc pin.");
    }

    @Override
    public void connectWifi() {
        System.out.println("Máy tính xách tay " + getName() + " đã kết nối Wifi.");
    }
}
