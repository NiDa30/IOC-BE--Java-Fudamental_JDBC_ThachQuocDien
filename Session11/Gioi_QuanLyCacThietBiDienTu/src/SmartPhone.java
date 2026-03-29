public class SmartPhone extends Device implements Connectable, Chargeable {

    public SmartPhone(int id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        System.out.println("Điện thoại thông minh " + getName() + " đang bật.");
    }

    @Override
    public void turnOff() {
        System.out.println("Điện thoại thông minh " + getName() + " đang tắt.");
    }

    @Override
    public void charge() {
        System.out.println("Điện thoại thông minh " + getName() + " đang sạc pin.");
    }

    @Override
    public void connectWifi() {
        System.out.println("Điện thoại thông minh " + getName() + " đã kết nối Wifi.");
    }
}
