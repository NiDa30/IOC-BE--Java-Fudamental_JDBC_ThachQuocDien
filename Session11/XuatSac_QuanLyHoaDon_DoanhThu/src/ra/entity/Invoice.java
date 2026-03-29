package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Invoice {
    private String invoiceId;
    private String customerName;
    private Date invoiceDate;
    private InvoiceDetail[] invoiceDetails;
    private double totalAmount;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Invoice() {}

    public Invoice(String invoiceId, String customerName, Date invoiceDate, InvoiceDetail[] invoiceDetails) {
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        this.invoiceDetails = invoiceDetails;
        calculateTotalAmount();
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public InvoiceDetail[] getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(InvoiceDetail[] invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void inputData(Scanner scanner, Product[] arrProd, int prodIndex, Invoice[] arrInv, int invIndex) {
        this.invoiceId = inputInvoiceId(scanner, arrInv, invIndex);
        this.customerName = inputCustomerName(scanner);
        this.invoiceDate = inputInvoiceDate(scanner);
        this.invoiceDetails = inputInvoiceDetails(scanner, arrProd, prodIndex);
        calculateTotalAmount();
    }

    private String inputInvoiceId(Scanner scanner, Invoice[] arrInv, int invIndex) {
        while (true) {
            System.out.print("Nhập mã hóa đơn (HDxxxx, 6 ký tự): ");
            String id = scanner.nextLine();
            if (id.matches("^HD\\w{4}$")) {
                boolean isExist = false;
                for (int i = 0; i < invIndex; i++) {
                    if (arrInv[i] != null && arrInv[i].getInvoiceId().equalsIgnoreCase(id)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return id;
                } else {
                    System.err.println("Mã hóa đơn đã tồn tại. Vui lòng nhập lại.");
                }
            } else {
                System.err.println("Mã hóa đơn không đúng định dạng. Vui lòng nhập lại.");
            }
        }
    }

    private String inputCustomerName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên khách hàng: ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                return name;
            } else {
                System.err.println("Tên khách hàng không được để trống. Vui lòng nhập lại.");
            }
        }
    }

    private Date inputInvoiceDate(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ngày lập hóa đơn (dd/MM/yyyy): ");
            String dateInput = scanner.nextLine();
            try {
                return sdf.parse(dateInput);
            } catch (ParseException e) {
                System.err.println("Định dạng ngày không hợp lệ. Vui lòng nhập đúng định dạng (dd/MM/yyyy).");
            }
        }
    }

    private InvoiceDetail[] inputInvoiceDetails(Scanner scanner, Product[] arrProd, int prodIndex) {
        if (prodIndex == 0) {
            System.err.println("Chưa có sản phẩm nào. Không thể lập hóa đơn.");
            return new InvoiceDetail[0];
        }
        System.out.print("Nhập số lượng sản phẩm trong hóa đơn: ");
        int n;
        while (true) {
            try {
                n = Integer.parseInt(scanner.nextLine());
                if (n > 0) break;
                else System.err.println("Số lượng sản phẩm phải lớn hơn 0.");
            } catch (NumberFormatException e) {
                System.err.println("Số lượng phải là số nguyên.");
            }
        }
        InvoiceDetail[] details = new InvoiceDetail[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập chi tiết sản phẩm thứ " + (i + 1) + ":");
            details[i] = new InvoiceDetail();
            details[i].inputData(scanner, arrProd, prodIndex);
        }
        return details;
    }

    public void calculateTotalAmount() {
        this.totalAmount = 0;
        if (invoiceDetails != null) {
            for (InvoiceDetail detail : invoiceDetails) {
                if (detail != null) {
                    this.totalAmount += detail.getSubTotal();
                }
            }
        }
    }

    public void displayData() {
        System.out.println("============================== HÓA ĐƠN ==============================");
        System.out.printf("Mã hóa đơn: %-15s | Tên khách hàng: %-20s\n", invoiceId, customerName);
        System.out.printf("Ngày lập: %-19s | Tổng tiền: %-10.2f\n", sdf.format(invoiceDate), totalAmount);
        System.out.println("Chi tiết hóa đơn:");
        if (invoiceDetails != null) {
            for (int i = 0; i < invoiceDetails.length; i++) {
                if (invoiceDetails[i] != null) {
                    System.out.print((i + 1) + ". ");
                    invoiceDetails[i].displayData();
                }
            }
        }
    }
}
