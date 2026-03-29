import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Management {

    private boolean existsEmployeeByName(String name) {
        String sql = "SELECT COUNT(*) FROM employee WHERE name = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private boolean existsProjectByName(String name) {
        String sql = "SELECT COUNT(*) FROM project WHERE name = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private boolean existsEmployeeById(int id) {
        String sql = "SELECT COUNT(*) FROM employee WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private boolean existsProjectById(int id) {
        String sql = "SELECT COUNT(*) FROM project WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public void addEmployee(Employee emp) {
        if (existsEmployeeByName(emp.getName())) {
            System.err.println("Lỗi: Nhân viên '" + emp.getName() + "' đã tồn tại!");
            return;
        }
        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emp.getName());
            pstmt.setString(2, emp.getDepartment());
            pstmt.setBigDecimal(3, emp.getSalary());
            pstmt.executeUpdate();
            System.out.println("Thêm nhân viên thành công!");
        } catch (SQLException e) { System.err.println("Lỗi DB: " + e.getMessage()); }
    }

    public void addProject(Project proj) {
        if (existsProjectByName(proj.getName())) {
            System.err.println("Lỗi: Dự án '" + proj.getName() + "' đã tồn tại!");
            return;
        }
        String sql = "INSERT INTO project (name, budget) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proj.getName());
            pstmt.setBigDecimal(2, proj.getBudget());
            pstmt.executeUpdate();
            System.out.println("Thêm dự án thành công!");
        } catch (SQLException e) { System.err.println("Lỗi DB: " + e.getMessage()); }
    }

    public void assignEmployeeToProject(int empId, int projId, String role) {
        if (!existsEmployeeById(empId)) {
            System.err.println("Lỗi: Nhân viên ID " + empId + " không tồn tại!");
            return;
        }
        if (!existsProjectById(projId)) {
            System.err.println("Lỗi: Dự án ID " + projId + " không tồn tại!");
            return;
        }

        String sql = "INSERT INTO assignment (employee_id, project_id, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empId);
            pstmt.setInt(2, projId);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
            System.out.println("Gán nhân viên vào dự án thành công!");
        } catch (SQLException e) { System.err.println("Lỗi (Có thể đã gán rồi): " + e.getMessage()); }
    }

    public void listEmployeesAndProjects() {
        String sql = "SELECT e.name as emp_name, p.name as proj_name, a.role " +
                     "FROM employee e " +
                     "LEFT JOIN assignment a ON e.id = a.employee_id " +
                     "LEFT JOIN project p ON a.project_id = p.id " +
                     "ORDER BY e.name";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- DANH SÁCH NHÂN VIÊN VÀ DỰ ÁN ---");
            String lastEmp = "";
            while (rs.next()) {
                String currentEmp = rs.getString("emp_name");
                if (!currentEmp.equals(lastEmp)) {
                    System.out.println("\n🏠 Nhân viên: " + currentEmp);
                    lastEmp = currentEmp;
                }
                String projName = rs.getString("proj_name");
                if (projName != null) {
                    System.out.println("   - Dự án: " + projName + " [Vai trò: " + rs.getString("role") + "]");
                } else {
                    System.out.println("   (Chưa tham gia dự án)");
                }
            }
        } catch (SQLException e) { System.err.println("Lỗi DB: " + e.getMessage()); }
    }

    public void updateEmployeeSalary(int empId, BigDecimal newSalary) {
        if (!existsEmployeeById(empId)) {
            System.err.println("Lỗi: Nhân viên ID " + empId + " không tồn tại!");
            return;
        }
        String sql = "UPDATE employee SET salary = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBigDecimal(1, newSalary);
            pstmt.setInt(2, empId);
            pstmt.executeUpdate();
            System.out.println("Cập nhật lương thành công!");
        } catch (SQLException e) { System.err.println("Lỗi DB: " + e.getMessage()); }
    }
}
