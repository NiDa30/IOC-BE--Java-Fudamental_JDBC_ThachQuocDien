import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize data with 5 orders
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "Nguyen Van A", LocalDate.of(2025, 3, 10), LocalDate.of(2025, 3, 15)));
        orders.add(new Order(2, "Le Van B",    LocalDate.of(2025, 3, 12), null)); // Not delivered
        orders.add(new Order(3, "Tran Thi C", LocalDate.of(2025, 3, 15), LocalDate.of(2025, 3, 20)));
        orders.add(new Order(4, "Pham Van D", LocalDate.of(2025, 3, 18), LocalDate.of(2025, 3, 22)));
        orders.add(new Order(5, "Hoang Van E", LocalDate.of(2025, 3, 20), null)); // Not delivered
        
        // 2. List delivered orders
        System.out.println("--- Đơn hàng đã giao ---");
        List<Order> deliveredOrders = orders.stream()
                .filter(o -> o.getDeliveryDate().isPresent())
                .collect(Collectors.toList());
        printOrders(deliveredOrders);
        
        // 3. List undelivered orders
        System.out.println("\n--- Đơn hàng chưa giao ---");
        List<Order> undeliveredOrders = orders.stream()
                .filter(o -> o.getDeliveryDate().isEmpty())
                .collect(Collectors.toList());
        printOrders(undeliveredOrders);
        
        // 4. Count delivered orders within [2025-03-17, 2025-03-23]
        LocalDate startRange = LocalDate.of(2025, 3, 17);
        LocalDate endRange   = LocalDate.of(2025, 3, 23);
        
        long countDeliveredInRange = orders.stream()
                .filter(o -> o.getDeliveryDate()
                        .map(d -> !d.isBefore(startRange) && !d.isAfter(endRange))
                        .orElse(false))
                .count();

        System.out.println("\n--- Thống kê số lượng ---");
        System.out.println("Số đơn đã giao từ " + startRange + " đến " + endRange + ": " + countDeliveredInRange);
        
        // 5. In thông tin từng đơn ID | Tên KH | Ngày đặt | Ngày giao
        System.out.println("\n--- Thông tin chi tiết toàn bộ đơn hàng ---");
        orders.forEach(o -> System.out.println(o.toDisplayString()));
    }
    
    /**
     * Helper method to print order lists in a standard format
     */
    public static void printOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders to display.");
        } else {
            orders.forEach(o -> System.out.println(o.toDisplayString()));
        }
    }
}