import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/movie_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "yourpassword"; // USER CAN UPDATE THIS

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found. Add it to your project.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
