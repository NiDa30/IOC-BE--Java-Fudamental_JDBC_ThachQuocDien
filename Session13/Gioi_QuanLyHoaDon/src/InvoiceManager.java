import java.util.ArrayList;
import java.util.List;

public class InvoiceManager implements Manage<Invoice> {
    private List<Invoice> invoices = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void add(Invoice item) {
        item.setId(nextId++);
        invoices.add(item);
    }

    @Override
    public void update(int index, Invoice item) {
        if (index >= 0 && index < invoices.size()) {
            // Retain original ID
            int originalId = invoices.get(index).getId();
            item.setId(originalId);
            invoices.set(index, item);
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < invoices.size()) {
            invoices.remove(index);
        }
    }

    @Override
    public void display() {
        if (invoices.isEmpty()) {
            System.out.println("(Danh sách hóa đơn trống)");
        } else {
            for (int i = 0; i < invoices.size(); i++) {
                System.out.println((i + 1) + ". " + invoices.get(i).toString());
            }
        }
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    // Helper to find index by numeric ID
    public int findIndexById(int id) {
        for (int i = 0; i < invoices.size(); i++) {
            if (invoices.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
