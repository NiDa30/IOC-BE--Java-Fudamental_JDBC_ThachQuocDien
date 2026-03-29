import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {

    public void addTask(String taskName, String status) {
        String sql = "{CALL add_task(?, ?)}";
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, taskName);
            stmt.setString(2, status);
            stmt.execute();
            System.out.println("Thêm công việc thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm: " + e.getMessage());
        }
    }

    public List<Task> listTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM list_tasks()"; // Func version
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("task_name"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách: " + e.getMessage());
        }
        return tasks;
    }

    public void updateTaskStatus(int taskId, String status) {
        String sql = "{CALL update_task_status(?, ?)}";
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, taskId);
            stmt.setString(2, status);
            stmt.execute();
            System.out.println("Cập nhật trạng thái thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật: " + e.getMessage());
        }
    }

    public void deleteTask(int taskId) {
        String sql = "{CALL delete_task(?)}";
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, taskId);
            stmt.execute();
            System.out.println("Xóa công việc thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa: " + e.getMessage());
        }
    }

    public List<Task> searchTaskByName(String taskName) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM search_task_by_name(?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taskName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tasks.add(new Task(
                            rs.getInt("id"),
                            rs.getString("task_name"),
                            rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return tasks;
    }

    public void taskStatistics() {
        String sql = "{CALL task_statistics(?, ?)}";
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.execute();

            int completed = stmt.getInt(1);
            int notCompleted = stmt.getInt(2);

            System.out.println("\n--- THỐNG KÊ CÔNG VIỆC ---");
            System.out.println("✅ Đã hoàn thành: " + completed);
            System.out.println("❌ Chưa hoàn thành: " + notCompleted);
            System.out.println("📊 Tổng cộng: " + (completed + notCompleted));
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy thống kê: " + e.getMessage());
        }
    }
}
