import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookManager bm = new BookManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- HỆ THỐNG QUẢN LÝ THƯ VIỆN ---");
            System.out.println("1. Thêm sách");
            System.out.println("2. Cập nhật sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm theo tác giả");
            System.out.println("5. Hiển thị tất cả sách");
            System.out.println("6. Thoát");
            System.out.print("Vui lòng chọn: ");

            try {
                String choiceStr = scanner.nextLine();
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1: handleAddBook(); break;
                    case 2: handleUpdateBook(); break;
                    case 3: handleDeleteBook(); break;
                    case 4: handleFindAuthor(); break;
                    case 5: handleListAll(); break;
                    case 6: System.out.println("Tạm biệt!"); return;
                    default: System.err.println("Lựa chọn không hợp lệ (1-6).");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số.");
            } catch (Exception e) {
                System.err.println("Lỗi hệ thống: " + e.getMessage());
            }
        }
    }

    private static void handleAddBook() {
        try {
            Book book = inputBookDetails();
            bm.addBook(book);
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleUpdateBook() {
        try {
            System.out.print("Nhập ID sách cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Nhập thông tin mới:");
            Book book = inputBookDetails();
            bm.updateBook(id, book);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleDeleteBook() {
        try {
            System.out.print("Nhập ID sách cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            bm.deleteBook(id);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID phải là số.");
        }
    }

    private static void handleFindAuthor() {
        System.out.print("Nhập tên tác giả: ");
        String author = scanner.nextLine().trim();
        List<Book> results = bm.findBooksByAuthor(author);
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy sách của tác giả: " + author);
        } else {
            System.out.println("Kết quả:");
            results.forEach(System.out::println);
        }
    }

    private static void handleListAll() {
        System.out.println("\n--- TOÀN BỘ SÁCH TRONG THƯ VIỆN ---");
        List<Book> books = bm.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("(Trống)");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static Book inputBookDetails() {
        System.out.print("Tên sách: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) throw new IllegalArgumentException("Tên sách không được để trống!");

        System.out.print("Tác giả: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) throw new IllegalArgumentException("Tác giả không được để trống!");

        System.out.print("Năm xuất bản: ");
        int year = Integer.parseInt(scanner.nextLine().trim());
        if (year < 0 || year > 2100) throw new IllegalArgumentException("Năm xuất bản không hợp lệ!");

        System.out.print("Giá bán: ");
        BigDecimal price = new BigDecimal(scanner.nextLine().trim());
        if (price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Giá không được âm!");

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishedYear(year);
        book.setPrice(price);
        return book;
    }
}