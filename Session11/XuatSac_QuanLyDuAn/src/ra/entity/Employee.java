package ra.entity;

import java.util.Scanner;

public class Employee {
    private String employeeId;
    private String employeeName;
    private Role role;
    private double salary;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, Role role, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputData(Scanner scanner, Employee[] arrEmp, int index) {
        // Nhập employeeId
        while (true) {
            System.out.print("Nhập mã nhân viên (5 ký tự, bắt đầu bằng E): ");
            String id = scanner.nextLine().trim();
            if (id.length() == 5 && id.startsWith("E")) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrEmp[i].getEmployeeId().equals(id)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Mã nhân viên đã tồn tại, vui lòng nhập lại!");
                } else {
                    this.employeeId = id;
                    break;
                }
            } else {
                System.err.println("Mã nhân viên phải gồm 5 ký tự và bắt đầu bằng E!");
            }
        }

        // Nhập employeeName
        while (true) {
            System.out.print("Nhập tên nhân viên (6 - 30 ký tự): ");
            String name = scanner.nextLine().trim();
            if (name.length() >= 6 && name.length() <= 30) {
                this.employeeName = name;
                break;
            } else {
                System.err.println("Tên nhân viên phải từ 6 đến 30 ký tự!");
            }
        }

        // Nhập role
        while (true) {
            System.out.print("Nhập vai trò của nhân viên (1: DEV, 2: TESTER, 3: PM, 4: BA): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        this.role = Role.DEV;
                        break;
                    case 2:
                        this.role = Role.TESTER;
                        break;
                    case 3:
                        this.role = Role.PM;
                        break;
                    case 4:
                        this.role = Role.BA;
                        break;
                    default:
                        System.err.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 4!");
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số!");
            }
        }

        // Nhập salary
        while (true) {
            System.out.print("Nhập lương cơ bản (> 0): ");
            try {
                double sal = Double.parseDouble(scanner.nextLine().trim());
                if (sal > 0) {
                    this.salary = sal;
                    break;
                } else {
                    System.err.println("Lương phải lớn hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    public void displayData() {
        System.out.printf("Mã NV: %s | Tên NV: %s | Vai trò: %s | Lương: %.2f\n", 
            employeeId, employeeName, role, salary);
    }
}
