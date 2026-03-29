import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Management management = new Management();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ NHÂN VIÊN VÀ DỰ ÁN ---");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Thêm dự án");
            System.out.println("3. Gán nhân viên vào dự án");
            System.out.println("4. Cập nhật lương nhân viên");
            System.out.println("5. Liệt kê nhân viên & dự án");
            System.out.println("6. Thoát");
            System.out.print("Vui lòng chọn: ");

            try {
                String choiceStr = scanner.nextLine();
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1: handleAddEmployee(); break;
                    case 2: handleAddProject(); break;
                    case 3: handleAssign(); break;
                    case 4: handleUpdateSalary(); break;
                    case 5: management.listEmployeesAndProjects(); break;
                    case 6: System.out.println("Tạm biệt!"); return;
                    default: System.err.println("Lựa chọn không hợp lệ (1-6)");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số.");
            } catch (Exception e) {
                System.err.println("Lỗi hệ thống: " + e.getMessage());
            }
        }
    }

    private static void handleAddEmployee() {
        try {
            System.out.print("Tên nhân viên: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Tên không được trống!");

            System.out.print("Phòng ban: ");
            String dept = scanner.nextLine().trim();
            if (dept.isEmpty()) throw new IllegalArgumentException("Phòng ban không được trống!");

            System.out.print("Mức lương: ");
            BigDecimal salary = new BigDecimal(scanner.nextLine().trim());
            if (salary.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Lương không được âm!");

            Employee emp = new Employee();
            emp.setName(name);
            emp.setDepartment(dept);
            emp.setSalary(salary);
            management.addEmployee(emp);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Mức lương phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleAddProject() {
        try {
            System.out.print("Tên dự án: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Tên dự án không được trống!");

            System.out.print("Ngân sách: ");
            BigDecimal budget = new BigDecimal(scanner.nextLine().trim());
            if (budget.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Ngân sách không được âm!");

            Project proj = new Project();
            proj.setName(name);
            proj.setBudget(budget);
            management.addProject(proj);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Ngân sách phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleAssign() {
        try {
            System.out.print("ID Nhân viên: ");
            int empId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("ID Dự án: ");
            int projId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Vai trò: ");
            String role = scanner.nextLine().trim();
            if (role.isEmpty()) throw new IllegalArgumentException("Vai trò không được trống!");

            management.assignEmployeeToProject(empId, projId, role);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void handleUpdateSalary() {
        try {
            System.out.print("ID Nhân viên: ");
            int empId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Mức lương mới: ");
            BigDecimal newSalary = new BigDecimal(scanner.nextLine().trim());
            if (newSalary.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Lương không âm!");

            management.updateEmployeeSalary(empId, newSalary);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: ID và lương phải là số.");
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
}