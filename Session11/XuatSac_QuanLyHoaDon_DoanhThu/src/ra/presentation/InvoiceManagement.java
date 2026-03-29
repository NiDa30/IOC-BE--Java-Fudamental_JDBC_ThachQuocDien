package ra.presentation;

import ra.entity.Invoice;
import ra.entity.InvoiceDetail;
import ra.entity.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InvoiceManagement {
    private static Product[] arrProd = new Product[100];
    private static int prodIndex = 0;
    private static Invoice[] arrInv = new Invoice[100];
    private static int invIndex = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        while (true) {
            System.out.println("============================== QUẢN LÝ HÓA ĐƠN ==============================");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý hóa đơn");
            System.out.println("3. Báo cáo doanh thu");
            System.out.println("4. Thoát");
            System.out.println("============================================================================");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productMenu();
                        break;
                    case 2:
                        invoiceMenu();
                        break;
                    case 3:
                        revenueMenu();
                        break;
                    case 4:
                        System.out.println("Thoát chương trình.");
                        return;
                    default:
                        System.err.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập một số nguyên.");
            }
        }
    }

    private static void productMenu() {
        while (true) {
            System.out.println("============================== QUẢN LÝ SẢN PHẨM ==============================");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm (nếu chưa có trong hóa đơn nào)");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Thoát");
            System.out.println("==============================================================================");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        displayProducts();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        findProductByName();
                        break;
                    case 6:
                        return;
                    default:
                        System.err.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập một số nguyên.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Nhập số lượng sản phẩm cần thêm: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            arrProd[prodIndex] = new Product();
            arrProd[prodIndex].inputData(scanner, arrProd, prodIndex);
            prodIndex++;
        }
    }

    private static void displayProducts() {
        if (prodIndex == 0) {
            System.out.println("Danh sách sản phẩm trống.");
            return;
        }
        for (int i = 0; i < prodIndex; i++) {
            if (arrProd[i] != null) {
                arrProd[i].displayData();
            }
        }
    }

    private static void updateProduct() {
        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        String id = scanner.nextLine();
        for (int i = 0; i < prodIndex; i++) {
            if (arrProd[i] != null && arrProd[i].getProductId().equalsIgnoreCase(id)) {
                System.out.println("Sản phẩm đã tìm thấy. Cập nhật các thông tin:");
                arrProd[i].inputData(scanner, arrProd, i);
                System.out.println("Cập nhật thành công.");
                return;
            }
        }
        System.err.println("Mã sản phẩm không tồn tại.");
    }

    private static void deleteProduct() {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String id = scanner.nextLine();
        for (int i = 0; i < prodIndex; i++) {
            if (arrProd[i] != null && arrProd[i].getProductId().equalsIgnoreCase(id)) {
                // Kiểm tra xem sản phẩm có trong hóa đơn nào không
                boolean isUsed = false;
                for (int j = 0; j < invIndex; j++) {
                    if (arrInv[j] != null) {
                        for (InvoiceDetail detail : arrInv[j].getInvoiceDetails()) {
                            if (detail.getProduct().getProductId().equalsIgnoreCase(id)) {
                                isUsed = true;
                                break;
                            }
                        }
                    }
                    if (isUsed) break;
                }
                if (isUsed) {
                    System.err.println("Không thể xóa sản phẩm vì nó đã có trong hóa đơn.");
                } else {
                    for (int j = i; j < prodIndex - 1; j++) {
                        arrProd[j] = arrProd[j + 1];
                    }
                    arrProd[prodIndex - 1] = null;
                    prodIndex--;
                    System.out.println("Xóa thành công.");
                }
                return;
            }
        }
        System.err.println("Mã sản phẩm không tồn tại.");
    }

    private static void findProductByName() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < prodIndex; i++) {
            if (arrProd[i] != null && arrProd[i].getProductName().toLowerCase().contains(name.toLowerCase())) {
                arrProd[i].displayData();
                found = true;
            }
        }
        if (!found) {
            System.err.println("Không tìm thấy sản phẩm nào có tên khớp.");
        }
    }

    private static void invoiceMenu() {
        while (true) {
            System.out.println("============================== QUẢN LÝ HÓA ĐƠN ==============================");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Hiển thị danh sách hóa đơn");
            System.out.println("3. Cập nhật thông tin hóa đơn");
            System.out.println("4. Xóa hóa đơn");
            System.out.println("5. Tìm hóa đơn theo mã");
            System.out.println("6. Tìm hóa đơn theo tên khách hàng");
            System.out.println("7. Thoát");
            System.out.println("==============================================================================");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addInvoice();
                        break;
                    case 2:
                        displayInvoices();
                        break;
                    case 3:
                        updateInvoice();
                        break;
                    case 4:
                        deleteInvoice();
                        break;
                    case 5:
                        findInvoiceById();
                        break;
                    case 6:
                        findInvoiceByCustomerName();
                        break;
                    case 7:
                        return;
                    default:
                        System.err.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập một số nguyên.");
            }
        }
    }

    private static void addInvoice() {
        if (prodIndex == 0) {
            System.err.println("Chưa có sản phẩm nào. Cần thêm sản phẩm trước.");
            return;
        }
        System.out.print("Nhập số lượng hóa đơn cần thêm: ");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            arrInv[invIndex] = new Invoice();
            arrInv[invIndex].inputData(scanner, arrProd, prodIndex, arrInv, invIndex);
            invIndex++;
        }
    }

    private static void displayInvoices() {
        if (invIndex == 0) {
            System.out.println("Danh sách hóa đơn trống.");
            return;
        }
        for (int i = 0; i < invIndex; i++) {
            if (arrInv[i] != null) {
                arrInv[i].displayData();
            }
        }
    }

    private static void updateInvoice() {
        System.out.print("Nhập mã hóa đơn cần cập nhật: ");
        String id = scanner.nextLine();
        for (int i = 0; i < invIndex; i++) {
            if (arrInv[i] != null && arrInv[i].getInvoiceId().equalsIgnoreCase(id)) {
                System.out.println("Hóa đơn đã tìm thấy. CẬP NHẬT DỮ LIỆU:");
                arrInv[i].inputData(scanner, arrProd, prodIndex, arrInv, i);
                System.out.println("Cập nhật thành công.");
                return;
            }
        }
        System.err.println("Mã hóa đơn không tồn tại.");
    }

    private static void deleteInvoice() {
        System.out.print("Nhập mã hóa đơn cần xóa: ");
        String id = scanner.nextLine();
        for (int i = 0; i < invIndex; i++) {
            if (arrInv[i] != null && arrInv[i].getInvoiceId().equalsIgnoreCase(id)) {
                for (int j = i; j < invIndex - 1; j++) {
                    arrInv[j] = arrInv[j + 1];
                }
                arrInv[invIndex - 1] = null;
                invIndex--;
                System.out.println("Xóa thành công.");
                return;
            }
        }
        System.err.println("Mã hóa đơn không tồn tại.");
    }

    private static void findInvoiceById() {
        System.out.print("Nhập mã hóa đơn cần tìm: ");
        String id = scanner.nextLine();
        for (int i = 0; i < invIndex; i++) {
            if (arrInv[i] != null && arrInv[i].getInvoiceId().equalsIgnoreCase(id)) {
                arrInv[i].displayData();
                return;
            }
        }
        System.err.println("Mã hóa đơn không tồn tại.");
    }

    private static void findInvoiceByCustomerName() {
        System.out.print("Nhập tên khách hàng cần tìm: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < invIndex; i++) {
            if (arrInv[i] != null && arrInv[i].getCustomerName().toLowerCase().contains(name.toLowerCase())) {
                arrInv[i].displayData();
                found = true;
            }
        }
        if (!found) {
            System.err.println("Không tìm thấy khách hàng.");
        }
    }

    private static void revenueMenu() {
        while (true) {
            System.out.println("============================== QUẢN LÝ DOANH THU ==============================");
            System.out.println("1. Tính tổng doanh thu tất cả hóa đơn");
            System.out.println("2. Tìm hóa đơn có giá trị lớn nhất");
            System.out.println("3. Thống kê số hóa đơn theo khoảng ngày (nhập từ - đến)");
            System.out.println("4. Thống kê tổng doanh thu theo khoảng ngày");
            System.out.println("5. Thoát");
            System.out.println("==============================================================================");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        totalRevenueAll();
                        break;
                    case 2:
                        getHighestTotalInvoice();
                        break;
                    case 3:
                        countInvoicesInRange();
                        break;
                    case 4:
                        revenueInRange();
                        break;
                    case 5:
                        return;
                    default:
                        System.err.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập một số nguyên.");
            }
        }
    }

    private static void totalRevenueAll() {
        double total = 0;
        for (int i = 0; i < invIndex; i++) {
            if (arrInv[i] != null) {
                total += arrInv[i].getTotalAmount();
            }
        }
        System.out.printf("Tổng doanh thu của tất cả hóa đơn là: %.2f\n", total);
    }

    private static void getHighestTotalInvoice() {
        if (invIndex == 0) {
            System.out.println("Chưa có hóa đơn nào.");
            return;
        }
        Invoice maxInvoice = arrInv[0];
        for (int i = 1; i < invIndex; i++) {
            if (arrInv[i] != null && arrInv[i].getTotalAmount() > maxInvoice.getTotalAmount()) {
                maxInvoice = arrInv[i];
            }
        }
        System.out.println("Hóa đơn có giá trị lớn nhất:");
        maxInvoice.displayData();
    }

    private static void countInvoicesInRange() {
        try {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            Date fromDate = sdf.parse(scanner.nextLine());
            System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
            Date toDate = sdf.parse(scanner.nextLine());

            int count = 0;
            for (int i = 0; i < invIndex; i++) {
                if (arrInv[i] != null) {
                    Date date = arrInv[i].getInvoiceDate();
                    if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                        count++;
                    }
                }
            }
            System.out.println("Số lượng hóa đơn trong khoảng ngày từ " + sdf.format(fromDate) + " đến " + sdf.format(toDate) + " là: " + count);
        } catch (ParseException e) {
            System.err.println("Định dạng ngày không hợp lệ.");
        }
    }

    private static void revenueInRange() {
        try {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            Date fromDate = sdf.parse(scanner.nextLine());
            System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
            Date toDate = sdf.parse(scanner.nextLine());

            double totalRevenue = 0;
            for (int i = 0; i < invIndex; i++) {
                if (arrInv[i] != null) {
                    Date date = arrInv[i].getInvoiceDate();
                    if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                        totalRevenue += arrInv[i].getTotalAmount();
                    }
                }
            }
            System.out.printf("Tổng doanh thu trong khoảng ngày từ " + sdf.format(fromDate) + " đến " + sdf.format(toDate) + " là: %.2f\n", totalRevenue);
        } catch (ParseException e) {
            System.err.println("Định dạng ngày không hợp lệ.");
        }
    }
}
