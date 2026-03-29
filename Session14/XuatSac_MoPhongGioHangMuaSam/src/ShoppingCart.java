import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /**
     * Thêm sản phẩm vào giỏ hàng
     */
    public void addToCart(Product product, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Lỗi: Số lượng không hợp lệ!");
        }

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("-> Đã cập nhật số lượng cho sản phẩm: " + product.getName());
                return;
            }
        }

        // Nếu chưa có, thêm mới
        items.add(new CartItem(product, quantity));
        System.out.println("-> Đã thêm sản phẩm " + product.getName() + " vào giỏ hàng.");
    }

    /**
     * Xóa sản phẩm khỏi giỏ hàng
     */
    public void removeFromCart(String productId) throws Exception {
        CartItem toRemove = null;
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                toRemove = item;
                break;
            }
        }

        if (toRemove == null) {
            throw new Exception("Lỗi: Không tìm thấy sản phẩm trong giỏ hàng!");
        }

        items.remove(toRemove);
        System.out.println("-> Đã xóa sản phẩm [" + productId + "] khỏi giỏ hàng.");
    }

    /**
     * Hiển thị nội dung giỏ hàng
     */
    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
            return;
        }
        System.out.println("\n--- CHI TIẾT GIỎ HÀNG ---");
        for (CartItem item : items) {
            System.out.println(item);
        }
    }

    /**
     * Tính tiền thanh toán
     */
    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Không có gì để thanh toán.");
            return;
        }
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        System.out.println("\n-------------------------------------------");
        System.out.println(String.format("TỔNG TIỀN CẦN THANH TOÁN: %.2f VNĐ", total));
        System.out.println("-------------------------------------------");
        System.out.println("Cảm ơn bạn đã mua sắm!");
        
        // Sau khi thanh toán thì xóa giỏ hàng
        items.clear();
    }
}
