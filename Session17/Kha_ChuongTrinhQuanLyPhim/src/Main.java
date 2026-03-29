import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MovieManagement mm = new MovieManagement();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- CHƯƠNG TRÌNH QUẢN LÝ PHIM ---");
            System.out.println("1. Thêm phim");
            System.out.println("2. Liệt kê phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xóa phim");
            System.out.println("5. Thoát");
            System.out.print("Vui lòng chọn: ");

            try {
                String choiceStr = scanner.nextLine();
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1:
                        handleAddMovie();
                        break;
                    case 2:
                        handleListMovies();
                        break;
                    case 3:
                        handleUpdateMovie();
                        break;
                    case 4:
                        handleDeleteMovie();
                        break;
                    case 5:
                        System.out.println("Tạm biệt!");
                        return;
                    default:
                        System.err.println("Lựa chọn không hợp lệ (1-5)");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số.");
            } catch (Exception e) {
                System.err.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }

    private static void handleAddMovie() {
        try {
            System.out.print("Nhập tiêu đề: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) throw new IllegalArgumentException("Tiêu đề không được để trống!");

            System.out.print("Nhập đạo diễn: ");
            String director = scanner.nextLine().trim();
            if (director.isEmpty()) throw new IllegalArgumentException("Đạo diễn không được để trống!");

            System.out.print("Nhập năm phát hành: ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            if (year < 1888) throw new IllegalArgumentException("Năm không hợp lệ!");

            mm.addMovie(title, director, year);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Năm phát hành phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleListMovies() {
        System.out.println("\n--- DANH SÁCH PHIM ---");
        List<Movie> movies = mm.listMovies();
        if (movies.isEmpty()) {
            System.out.println("(Trống)");
        } else {
            movies.forEach(System.out::println);
        }
    }

    private static void handleUpdateMovie() {
        try {
            System.out.print("Nhập ID phim cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nhập tiêu đề mới: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) throw new IllegalArgumentException("Tiêu đề không được để trống!");

            System.out.print("Nhập đạo diễn mới: ");
            String director = scanner.nextLine().trim();
            if (director.isEmpty()) throw new IllegalArgumentException("Đạo diễn không được để trống!");

            System.out.print("Nhập năm phát hành mới: ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            if (year < 1888) throw new IllegalArgumentException("Năm không hợp lệ!");

            mm.updateMovie(id, title, director, year);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID và Năm phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleDeleteMovie() {
        try {
            System.out.print("Nhập ID phim cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            mm.deleteMovie(id);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID phải là số.");
        }
    }
}