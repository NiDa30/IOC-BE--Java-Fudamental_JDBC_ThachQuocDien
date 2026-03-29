import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieManagement {

    public void addMovie(String title, String director, int year) {
        String sql = "{CALL add_movie(?, ?, ?)}";
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, director);
            stmt.setInt(3, year);
            stmt.execute();
            System.out.println("Thêm phim thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm phim: " + e.getMessage());
        }
    }

    public List<Movie> listMovies() {
        List<Movie> movies = new ArrayList<>();
        // Note: For PostgreSQL functions returning tables, select them. 
        // We call it via CallableStatement and get a ResultSet.
        String sql = "SELECT * FROM list_movies()";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách phim: " + e.getMessage());
        }
        return movies;
    }

    public void updateMovie(int id, String title, String director, int year) {
        String sql = "{CALL update_movie(?, ?, ?, ?)}";
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, director);
            stmt.setInt(4, year);
            stmt.execute();
            System.out.println("Cập nhật phim thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật phim: " + e.getMessage());
        }
    }

    public void deleteMovie(int id) {
        String sql = "{CALL delete_movie(?)}";
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Xóa phim thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa phim: " + e.getMessage());
        }
    }
}
