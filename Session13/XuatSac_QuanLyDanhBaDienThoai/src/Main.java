import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Contact> contactList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("================ MENU ================");
            System.out.println("1. Thêm liên lạc");
            System.out.println("2. Xóa liên lạc theo số điện thoại");
            System.out.println("3. Tìm kiếm liên lạc");
            System.out.println("4. Hiển thị danh bạ");
            System.out.println("0. Thoát");
            System.out.println("======================================");
            System.out.print("Lựa chọn của bạn: ");
            
            String choiceStr = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    removeContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    displayContacts();
                    break;
                case 0:
                    System.out.println("Đã thoát.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addContact() {
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();

        Contact newContact = new Contact(name, phoneNumber);
        if (contactList.contains(newContact)) {
            System.out.println("Số điện thoại đã tồn tại!");
        } else {
            contactList.add(newContact);
            System.out.println("Thêm thành công.");
        }
    }

    private static void removeContact() {
        System.out.print("Nhập số điện thoại cần xóa: ");
        String phoneNumber = scanner.nextLine();
        // Since equals() is overridden based on phoneNumber, this will work.
        Contact lookup = new Contact("", phoneNumber);
        if (contactList.remove(lookup)) {
            System.out.println("Xóa thành công.");
        } else {
            System.out.println("Không tìm thấy liên lạc với số điện thoại này.");
        }
    }

    private static void searchContact() {
        System.out.print("Nhập số điện thoại cần tìm: ");
        String phoneNumber = scanner.nextLine();
        Contact lookup = new Contact("", phoneNumber);
        if (contactList.contains(lookup)) {
            // Find the actual contact in list for detailed info.
            for (Contact c : contactList) {
                if (c.equals(lookup)) {
                    System.out.println("Thông tin: " + c);
                    break;
                }
            }
        } else {
            System.out.println("Số điện thoại không tồn tại trong danh bạ.");
        }
    }

    private static void displayContacts() {
        if (contactList.isEmpty()) {
            System.out.println("Danh bạ trống!");
        } else {
            System.out.println("DANH SÁCH LIÊN LẠC:");
            for (Contact c : contactList) {
                System.out.println(c);
            }
        }
    }
}