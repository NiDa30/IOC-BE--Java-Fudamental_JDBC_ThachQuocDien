import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static final LinkedList<Person> people = new LinkedList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("****************** MENU QUẢN LÝ NGƯỜI DÙNG ******************");
            System.out.println("1. Thêm người dùng");
            System.out.println("2. Xóa người dùng");
            System.out.println("3. Hiển thị danh sách người dùng");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choiceStr = scanner.nextLine();
            
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                continue;
            }

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    deletePersonByEmail();
                    break;
                case 3:
                    displayPeople();
                    break;
                case 4:
                    System.out.println("Kết thúc chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addPerson() {
        String name = inputWithNotEmptyValidation("Nhập tên người dùng:");
        String email = inputWithNotEmptyValidation("Nhập email người dùng:");
        String phone = inputWithNotEmptyValidation("Nhập số điện thoại người dùng:");
        people.add(new Person(name, email, phone));
        System.out.println("Người dùng đã được thêm thành công.");
    }

    private static void deletePersonByEmail() {
        System.out.println("Nhập email người dùng để xóa:");
        String email = scanner.nextLine();
        boolean removed = false;
        
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getEmail().equalsIgnoreCase(email)) {
                people.remove(i);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Người dùng đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy người dùng với email: " + email);
        }
    }

    private static void displayPeople() {
        System.out.println("\nDanh sách người dùng:");
        if (people.isEmpty()) {
            System.out.println("(Danh sách trống)");
        } else {
            for (Person p : people) {
                System.out.println(p);
            }
        }
        System.out.println();
    }

    private static String inputWithNotEmptyValidation(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("\nVui lòng ko để trống !");
            } else {
                return input;
            }
        }
    }
}