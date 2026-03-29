import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private boolean existsByTitleAndAuthor(String title, String author) {
        String sql = "SELECT COUNT(*) FROM books WHERE title = ? AND author = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kiểm tra trùng lặp: " + e.getMessage());
        }
        return false;
    }

    private boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM books WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kiểm tra tồn tại: " + e.getMessage());
        }
        return false;
    }

    public void addBook(Book book) {
        if (existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
            System.err.println("Lỗi: Sách '" + book.getTitle() + "' của tác giả '" + book.getAuthor() + "' đã tồn tại!");
            return;
        }

        String sql = "INSERT INTO books (title, author, published_year, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPublishedYear());
            pstmt.setBigDecimal(4, book.getPrice());
            pstmt.executeUpdate();
            System.out.println("Thêm sách thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi thêm sách: " + e.getMessage());
        }
    }

    public void updateBook(int id, Book book) {
        if (!existsById(id)) {
            System.err.println("Lỗi: Sách với ID " + id + " không tồn tại!");
            return;
        }

        String sql = "UPDATE books SET title = ?, author = ?, published_year = ?, price = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPublishedYear());
            pstmt.setBigDecimal(4, book.getPrice());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Cập nhật sách thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật: " + e.getMessage());
        }
    }

    public void deleteBook(int id) {
        if (!existsById(id)) {
            System.err.println("Lỗi: Sách với ID " + id + " không tồn tại!");
            return;
        }

        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Xóa sách thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi xóa sách: " + e.getMessage());
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author LIKE ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + author + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    books.add(extractFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi tìm kiếm: " + e.getMessage());
        }
        return books;
    }

    public List<Book> listAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi liệt kê: " + e.getMessage());
        }
        return books;
    }

    private Book extractFromResultSet(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("published_year"),
                rs.getBigDecimal("price")
        );
    }
}
