public class Television extends Device implements Connectable {

    public Television(int id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        System.out.println("Tivi " + getName() + " đang bật.");
    }

    @Override
    public void turnOff() {
        System.out.println("Tivi " + getName() + " đang tắt.");
    }

    @Override
    public void connectWifi() {
        System.out.println("Tivi " + getName() + " đã kết nối Wifi.");
    }
}
