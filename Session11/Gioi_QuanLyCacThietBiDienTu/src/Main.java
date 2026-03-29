public class Main {
    public static void main(String[] args) {
        Device[] devices = new Device[3];
        devices[0] = new SmartPhone(1, "iPhone 15");
        devices[1] = new Laptop(2, "MacBook Pro");
        devices[2] = new Television(3, "Samsung Smart TV");

        System.out.println("--- Quản lý các thiết bị điện tử ---");
        for (Device device : devices) {
            System.out.println("\nThiết bị: " + device.getName());
            device.turnOn();
            
            if (device instanceof Connectable) {
                ((Connectable) device).connectWifi();
            }
            
            if (device instanceof Chargeable) {
                ((Chargeable) device).charge();
            }
            
            device.turnOff();
        }
    }
}