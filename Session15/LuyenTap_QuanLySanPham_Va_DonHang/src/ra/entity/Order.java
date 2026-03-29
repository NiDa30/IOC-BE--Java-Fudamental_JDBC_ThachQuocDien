package ra.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public double calculateTotalAmount() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalProducts=" + products.size() +
                ", totalAmount=" + calculateTotalAmount() +
                ", products=" + products +
                '}';
    }
}
