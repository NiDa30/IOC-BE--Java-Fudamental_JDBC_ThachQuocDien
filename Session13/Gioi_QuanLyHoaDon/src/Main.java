import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InvoiceManager manager = new InvoiceManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("****************** MENU QUẢN LÝ HÓA ĐƠN ******************");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Sửa hóa đơn");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Hiển thị danh sách hóa đơn");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn:\n");
            
            String choiceStr = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                continue;
            }

            switch (choice) {
                case 1:
                    addInvoice();
                    break;
                case 2:
                    editInvoice();
                    break;
                case 3:
                    deleteInvoice();
                    break;
                case 4:
                    manager.display();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addInvoice() {
        System.out.println("Nhập mã hóa đơn:");
        String code = scanner.nextLine();
        double amount = inputAmount("Nhập số tiền:");
        manager.add(new Invoice(0, code, amount)); // ID assigned by manager
        System.out.println("Hóa đơn đã được thêm thành công.");
    }

    private static void editInvoice() {
        manager.display();
        System.out.println("Nhập id hóa đơn cần sửa:");
        String idStr = scanner.nextLine();
        int idTry;
        try {
            idTry = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ.");
            return;
        }

        int index = manager.findIndexById(idTry);
        if (index != -1) {
            String newCode = inputNotEmpty("Nhập mã hóa đơn mới:");
            double newAmount = inputAmount("Nhập số tiền mới:");
            manager.update(index, new Invoice(idTry, newCode, newAmount));
            System.out.println("Hóa đơn đã được sửa thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn nào có id = " + idTry);
        }
    }

    private static void deleteInvoice() {
        manager.display();
        System.out.println("Nhập id hóa đơn cần xóa:");
        String idStr = scanner.nextLine();
        int idTry;
        try {
            idTry = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ.");
            return;
        }

        int index = manager.findIndexById(idTry);
        if (index != -1) {
            manager.delete(index);
            System.out.println("Hóa đơn đã được xoa thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn nào có id = " + idTry);
        }
    }

    private static double inputAmount(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                double val = Double.parseDouble(input);
                if (val < 0) {
                    System.out.println("Vui lòng nhập số thực >= 0 !");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực !");
            }
        }
    }

    private static String inputNotEmpty(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("\nVui lòng ko để trống !");
            } else {
                return input;
            }
        }
    }
}