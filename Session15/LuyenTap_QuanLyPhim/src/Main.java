import ra.entity.Movie;
import ra.service.MovieManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static MovieManager<Movie> movieManager = new MovieManager<>();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Thêm phim");
            System.out.println("2. Xóa phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Hiển thị phim");
            System.out.println("5. Tìm kiếm phim theo tên");
            System.out.println("6. Lọc phim theo rating");
            System.out.println("7. Thoát");

            int choose = 0;
            try {
                choose = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số!");
                continue;
            }

            switch (choose) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    deleteMovie();
                    break;
                case 3:
                    editMovie();
                    break;
                case 4:
                    displayMovies();
                    break;
                case 5:
                    searchMovie();
                    break;
                case 6:
                    filterByRating();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Hãy chọn từ 1 - 7!");
            }
        }
    }

    private static void addMovie() {
        try {
            System.out.println("Nhập ID phim:");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập tiêu đề phim:");
            String title = scanner.nextLine();
            System.out.println("Nhập đạo diễn:");
            String director = scanner.nextLine();
            System.out.println("Nhập ngày phát hành (dd-MM-yyyy):");
            LocalDate releaseDate = LocalDate.parse(scanner.nextLine(), formatter);
            System.out.println("Nhập rating:");
            double rating = Double.parseDouble(scanner.nextLine());

            Movie movie = new Movie(id, title, director, releaseDate, rating);
            movieManager.add(movie);
            System.out.println("Phim đã được thêm thành công.");
        } catch (NumberFormatException e) {
            System.err.println("Sai định dạng số!");
        } catch (DateTimeParseException e) {
            System.err.println("Sai định dạng ngày!");
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi: " + e.getLocalizedMessage());
        }
    }

    private static void deleteMovie() {
        System.out.println("Nhập ID phim cần xóa:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Movie movieToDelete = findById(id);
            if (movieToDelete != null) {
                movieManager.remove(movieToDelete);
                System.out.println("Phim đã được xóa thành công.");
            } else {
                System.out.println("Không tìm thấy phim muốn xóa !");
            }
        } catch (NumberFormatException e) {
            System.err.println("ID không hợp lệ!");
        }
    }

    private static void editMovie() {
        System.out.println("Mời nhập id phim muốn sửa :");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Movie movieToEdit = findById(id);
            if (movieToEdit != null) {
                System.out.println("Nhập tiêu đề phim:");
                movieToEdit.setTitle(scanner.nextLine());
                System.out.println("Nhập đạo diễn:");
                movieToEdit.setDirector(scanner.nextLine());
                System.out.println("Nhập ngày phát hành (dd-MM-yyyy):");
                movieToEdit.setReleaseDate(LocalDate.parse(scanner.nextLine(), formatter));
                System.out.println("Nhập rating:");
                movieToEdit.setRating(Double.parseDouble(scanner.nextLine()));
                System.out.println("Cập nhật phim thành công !");
            } else {
                System.out.println("Không tìm thấy phim với id = " + id);
            }
        } catch (DateTimeParseException e) {
            System.err.println("Sai định dạng ngày!");
        } catch (NumberFormatException e) {
            System.err.println("Số liệu không hợp lệ!");
        }
    }

    private static void displayMovies() {
        List<Movie> list = movieManager.getAll();
        if (list.isEmpty()) {
            System.out.println("Danh sách phim trống!");
        } else {
            System.out.println("Danh sách phim:");
            list.forEach(System.out::println);
        }
    }

    private static void searchMovie() {
        System.out.print("Nhập tiêu đề phim để tìm kiếm:\n");
        String titleInput = scanner.nextLine().toLowerCase().trim();
        List<Movie> results = movieManager.getAll().stream()
                .filter(m -> m.getTitle().toLowerCase().contains(titleInput))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy phim!");
        } else {
            System.out.print("Phim tìm thấy: ");
            results.forEach(m -> System.out.println(m));
        }
    }

    private static void filterByRating() {
        System.out.print("Nhập rating tối thiểu để lọc:\n");
        try {
            double minRating = Double.parseDouble(scanner.nextLine());
            List<Movie> filtered = movieManager.getAll().stream()
                    .filter(m -> m.getRating() > minRating)
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                System.out.println("Không có phim nào đạt yêu cầu!");
            } else {
                System.out.println("Phim có rating lớn hơn " + minRating + ":");
                filtered.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.err.println("Dữ liệu nhập vào sai định dạng số!");
        }
    }

    private static Movie findById(int id) {
        return movieManager.getAll().stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }
}