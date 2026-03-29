import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private boolean existsProductByName(String name) {
        String sql = "SELECT COUNT(*) FROM products WHERE name = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean existsCustomerById(int id) {
        String sql = "SELECT COUNT(*) FROM customers WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private BigDecimal getProductPrice(int productId) throws SQLException {
        String sql = "SELECT price FROM products WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getBigDecimal("price");
            }
        }
        throw new SQLException("Product ID not found: " + productId);
    }

    public void addProduct(Product product) {
        if (existsProductByName(product.getName())) {
            System.err.println("Lỗi: Sản phẩm '" + product.getName() + "' đã tồn tại!");
            return;
        }
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setBigDecimal(2, product.getPrice());
            pstmt.executeUpdate();
            System.out.println("Thêm sản phẩm thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi DB: " + e.getMessage());
        }
    }

    public void updateCustomer(int customerId, Customer customer) {
        if (!existsCustomerById(customerId)) {
            System.err.println("Lỗi: Khách hàng ID " + customerId + " không tồn tại!");
            return;
        }
        String sql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setInt(3, customerId);
            pstmt.executeUpdate();
            System.out.println("Cập nhật khách hàng thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi DB (Có thể trùng Email): " + e.getMessage());
        }
    }

    public void createOrder(int customerId, int productId, int quantity) {
        try {
            if (!existsCustomerById(customerId)) {
                System.err.println("Lỗi: Khách hàng không tồn tại!");
                return;
            }
            BigDecimal price = getProductPrice(productId);
            BigDecimal total = price.multiply(new BigDecimal(quantity));

            String sql = "INSERT INTO orders (customer_id, product_id, order_date, quantity, total_amount) VALUES (?, ?, CURRENT_DATE, ?, ?)";
            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, customerId);
                pstmt.setInt(2, productId);
                pstmt.setInt(3, quantity);
                pstmt.setBigDecimal(4, total);
                pstmt.executeUpdate();
                System.out.printf("Tạo đơn hàng thành công! Tổng tiền: $%.2f\n", total);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tạo đơn hàng: " + e.getMessage());
        }
    }

    public void listAllOrders() {
        String sql = "SELECT o.id, c.name as customer_name, o.order_date, o.total_amount " +
                     "FROM orders o JOIN customers c ON o.customer_id = c.id";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- DANH SÁCH ĐƠN HÀNG ---");
            while (rs.next()) {
                System.out.printf("ID: %-4d | KH: %-20s | Ngày: %-12s | Tổng: $%.2f\n",
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getDate("order_date"),
                        rs.getBigDecimal("total_amount"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi liệt kê: " + e.getMessage());
        }
    }

    public void getOrdersByCustomer(int customerId) {
        String sql = "SELECT o.id, o.order_date, o.total_amount " +
                     "FROM orders o WHERE o.customer_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n--- ĐƠN HÀNG CỦA KHÁCH HÀNG " + customerId + " ---");
                while (rs.next()) {
                    System.out.printf("ID: %-4d | Ngày: %-12s | Tổng: $%.2f\n",
                            rs.getInt("id"),
                            rs.getDate("order_date"),
                            rs.getBigDecimal("total_amount"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy đơn hàng của KH: " + e.getMessage());
        }
    }

    // Additional helper for adding customer (to test update/order)
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.executeUpdate();
            System.out.println("Thêm khách hàng thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi DB: " + e.getMessage());
        }
    }
}
