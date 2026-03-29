package ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Project {
    private String projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee[] employees = new Employee[100];
    private int employeeCount = 0;
    private ProjectStatus status;

    public Project() {
    }

    public Project(String projectId, String projectName, LocalDate startDate, LocalDate endDate, ProjectStatus status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Project[] arrProject, int index, Employee[] arrEmp, int empIndex) {
        // Nhập projectId
        while (true) {
            System.out.print("Nhập mã dự án (5 ký tự, bắt đầu bằng P): ");
            String id = scanner.nextLine().trim();
            if (id.length() == 5 && id.startsWith("P")) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrProject[i].getProjectId().equals(id)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Mã dự án đã tồn tại, vui lòng nhập lại!");
                } else {
                    this.projectId = id;
                    break;
                }
            } else {
                System.err.println("Mã dự án phải gồm 5 ký tự và bắt đầu bằng P!");
            }
        }

        // Nhập projectName
        while (true) {
            System.out.print("Nhập tên dự án (10 - 50 ký tự, duy nhất): ");
            String name = scanner.nextLine().trim();
            if (name.length() >= 10 && name.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrProject[i].getProjectName().equals(name)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên dự án đã tồn tại, vui lòng nhập lại!");
                } else {
                    this.projectName = name;
                    break;
                }
            } else {
                System.err.println("Tên dự án phải từ 10 đến 50 ký tự!");
            }
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Nhập startDate
        while (true) {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            String start = scanner.nextLine().trim();
            try {
                this.startDate = LocalDate.parse(start, dtf);
                break;
            } catch (DateTimeParseException e) {
                System.err.println("Định dạng ngày không hợp lệ, vui lòng nhập lại!");
            }
        }

        // Nhập endDate
        while (true) {
            System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
            String end = scanner.nextLine().trim();
            try {
                LocalDate tempEnd = LocalDate.parse(end, dtf);
                if (tempEnd.isBefore(this.startDate)) {
                    System.err.println("Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu!");
                } else {
                    this.endDate = tempEnd;
                    break;
                }
            } catch (DateTimeParseException e) {
                System.err.println("Định dạng ngày không hợp lệ, vui lòng nhập lại!");
            }
        }

        // Nhập status
        while (true) {
            System.out.print("Nhập trạng thái dự án (1: PLANNING, 2: RUNNING, 3: FINISHED): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        this.status = ProjectStatus.PLANNING;
                        break;
                    case 2:
                        this.status = ProjectStatus.RUNNING;
                        break;
                    case 3:
                        this.status = ProjectStatus.FINISHED;
                        break;
                    default:
                        System.err.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 3!");
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số!");
            }
        }
        
        // Gán nhân viên tham gia
        System.out.print("Bạn có muốn thêm nhân viên vào dự án ngay bây giờ không? (Y/N): ");
        String choice = scanner.nextLine().trim();
        if(choice.equalsIgnoreCase("Y")) {
             while(true) {
                 if (empIndex == 0) {
                     System.out.println("Chưa có nhân viên nào trong hệ thống, không thể thêm nhân viên.");
                     break;
                 }
                 System.out.println("Danh sách nhân viên:");
                 for (int i = 0; i < empIndex; i++) {
                     System.out.printf("%d. %s - %s\n", i + 1, arrEmp[i].getEmployeeId(), arrEmp[i].getEmployeeName());
                 }
                 System.out.print("Nhập mã nhân viên muốn thêm: ");
                 String empId = scanner.nextLine().trim();
                 boolean isFound = false;
                 for (int i = 0; i < empIndex; i++) {
                     if (arrEmp[i].getEmployeeId().equals(empId)) {
                         isFound = true;
                         // Kiểm tra trùng
                         boolean isAssigned = false;
                         for(int j = 0; j < employeeCount; j++) {
                             if(this.employees[j].getEmployeeId().equals(empId)) {
                                 isAssigned = true;
                                 break;
                             }
                         }
                         if(isAssigned) {
                             System.err.println("Nhân viên " + arrEmp[i].getEmployeeName() + " đã có trong dự án!");
                         } else {
                             this.employees[employeeCount++] = arrEmp[i];
                             System.out.println("Thêm nhân viên " + arrEmp[i].getEmployeeName() + " vào dự án thành công!");
                         }
                         break;
                     }
                 }
                 if(!isFound) {
                     System.err.println("Không tìm thấy nhân viên với mã: " + empId);
                 }
                 
                 System.out.print("Bạn có muốn thêm tiếp không? (Y/N): ");
                 String c = scanner.nextLine().trim();
                 if(!c.equalsIgnoreCase("Y")) {
                     break;
                 }
             }
        }
    }

    public void displayData() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("Mã DA: %s | Tên DA: %s | Ngày bắt đầu: %s | Ngày kết thúc: %s | Trạng thái: %s | Số NV thi công: %d\n",
            projectId, projectName, startDate.format(dtf), endDate.format(dtf), status, employeeCount);
        if (employeeCount > 0) {
            System.out.println("Danh sách nhân viên tham gia:");
            for (int i = 0; i < employeeCount; i++) {
                System.out.println("\t- " + employees[i].getEmployeeName() + " (" + employees[i].getRole() + ")");
            }
        }
    }
}
