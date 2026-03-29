import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private boolean existsStudentByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM student WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private boolean existsCourseByTitle(String title) {
        String sql = "SELECT COUNT(*) FROM course WHERE title = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private boolean existsStudentById(int id) {
        String sql = "SELECT COUNT(*) FROM student WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private boolean existsCourseById(int id) {
        String sql = "SELECT COUNT(*) FROM course WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public void addStudent(Student student) {
        if (existsStudentByEmail(student.getEmail())) {
            System.err.println("Lỗi: Email '" + student.getEmail() + "' đã tồn tại!");
            return;
        }
        String sql = "INSERT INTO student (name, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.executeUpdate();
            System.out.println("Thêm sinh viên thành công!");
        } catch (SQLException e) { System.err.println("Lỗi DB: " + e.getMessage()); }
    }

    public void addCourse(Course course) {
        if (existsCourseByTitle(course.getTitle())) {
            System.err.println("Lỗi: Khóa học '" + course.getTitle() + "' đã tồn tại!");
            return;
        }
        String sql = "INSERT INTO course (title, credits) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getTitle());
            pstmt.setInt(2, course.getCredits());
            pstmt.executeUpdate();
            System.out.println("Thêm khóa học thành công!");
        } catch (SQLException e) { System.err.println("Lỗi DB: " + e.getMessage()); }
    }

    public void enrollStudent(int studentId, int courseId) {
        if (!existsStudentById(studentId)) {
            System.err.println("Lỗi: Sinh viên ID " + studentId + " không tồn tại!");
            return;
        }
        if (!existsCourseById(courseId)) {
            System.err.println("Lỗi: Khóa học ID " + courseId + " không tồn tại!");
            return;
        }
        String sql = "INSERT INTO enrollment (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
            System.out.println("Ghi danh thành công!");
        } catch (SQLException e) { System.err.println("Lỗi (Có thể đã ghi danh): " + e.getMessage()); }
    }

    public void listStudentsAndGrades() {
        String sql = "SELECT s.name as student_name, c.title as course_title, e.grade " +
                     "FROM student s " +
                     "LEFT JOIN enrollment e ON s.id = e.student_id " +
                     "LEFT JOIN course c ON e.course_id = c.id " +
                     "ORDER BY s.name";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- DANH SÁCH SINH VIÊN VÀ ĐIỂM SỐ ---");
            String lastStudent = "";
            while (rs.next()) {
                String currentStudent = rs.getString("student_name");
                if (!currentStudent.equals(lastStudent)) {
                    System.out.println("\n🎓 Sinh viên: " + currentStudent);
                    lastStudent = currentStudent;
                }
                String courseTitle = rs.getString("course_title");
                if (courseTitle != null) {
                    Object grade = rs.getObject("grade");
                    String gradeText = (grade == null) ? "Chưa có điểm" : grade.toString();
                    System.out.println("   - [" + courseTitle + "]: " + gradeText);
                } else {
                    System.out.println("   (Chưa đăng ký khóa học nào)");
                }
            }
        } catch (SQLException e) { System.err.println("Lỗi DB: " + e.getMessage()); }
    }

    public void updateStudentGrade(int studentId, int courseId, double grade) {
        String checkSql = "SELECT COUNT(*) FROM enrollment WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
            checkPstmt.setInt(1, studentId);
            checkPstmt.setInt(2, courseId);
            try (ResultSet rs = checkPstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    System.err.println("Lỗi: Sinh viên chưa ghi danh vào khóa học này!");
                    return;
                }
            }

            String sql = "UPDATE enrollment SET grade = ? WHERE student_id = ? AND course_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, grade);
                pstmt.setInt(2, studentId);
                pstmt.setInt(3, courseId);
                pstmt.executeUpdate();
                System.out.println("Cập nhật điểm thành công!");
            }
        } catch (SQLException e) { System.err.println("Lỗi DB: " + e.getMessage()); }
    }
}
