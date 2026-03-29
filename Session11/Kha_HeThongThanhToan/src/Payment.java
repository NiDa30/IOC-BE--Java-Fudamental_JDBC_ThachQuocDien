public abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void pay();

    public void printAmount() {
        System.out.println("Số tiền: " + amount);
    }
}
