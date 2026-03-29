public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * Tính thành tiền cho mục này
     */
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s | SL: %d | Thành tiền: %.2f VNĐ", product.toString(), quantity, getTotalPrice());
    }
}
