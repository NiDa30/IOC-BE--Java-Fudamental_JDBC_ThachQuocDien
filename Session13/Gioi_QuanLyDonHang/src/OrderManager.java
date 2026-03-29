import java.util.ArrayList;
import java.util.List;

public class OrderManager implements Manage<Order> {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void add(Order item) {
        orders.add(item);
    }

    @Override
    public void update(int index, Order item) {
        if (index >= 0 && index < orders.size()) {
            orders.set(index, item);
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < orders.size()) {
            orders.remove(index);
        }
    }

    @Override
    public void display() {
        if (orders.isEmpty()) {
            System.out.println("(Danh sách đơn hàng trống)");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ". " + orders.get(i).toString());
            }
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    // Helper method to find index by order code
    public int findIndexByCode(String code) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderCode().equalsIgnoreCase(code)) {
                return i;
            }
        }
        return -1;
    }
}
