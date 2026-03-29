import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private int id;
    private int customerId;
    private int productId;
    private Date orderDate;
    private int quantity;
    private BigDecimal totalAmount;

    public Order(int id, int customerId, int productId, Date orderDate, int quantity, BigDecimal totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Order() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    @Override
    public String toString() {
        return String.format("OrderID: %-4d | CustID: %-4d | ProdID: %-4d | Ngày: %-12s | SL: %-3d | Tổng: $%.2f",
                id, customerId, productId, orderDate, quantity, totalAmount);
    }
}
