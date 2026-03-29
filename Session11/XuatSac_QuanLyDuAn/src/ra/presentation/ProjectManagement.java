package ra.presentation;

import ra.entity.Employee;
import ra.entity.Project;
import ra.entity.ProjectStatus;
import ra.entity.Role;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class ProjectManagement {
    public static Employee[] employees = new Employee[100];
    public static int empCount = 0;
    public static Project[] projects = new Project[100];
    public static int projCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("=================== QUẢN LÝ DỰ ÁN ===================");
            System.out.println("1. Quản lý nhân viên");
            System.out.println("2. Quản lý dự án");
            System.out.println("3. Thoát");
            System.out.println("=====================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    employeeManager(scanner);
                    break;
                case "2":
                    projectManager(scanner);
                    break;
                case "3":
                    System.out.println("Thoát chương trình!");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1 đến 3!");
            }
        }
    }

    public static void employeeManager(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("=================== QUẢN LÝ NHÂN VIÊN ===================");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Hiển thị danh sách nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Tìm kiếm nhân viên theo tên");
            System.out.println("6. Sắp xếp nhân viên theo lương giảm dần");
            System.out.println("7. Thoát");
            System.out.println("=========================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": // Thêm nhân viên
                    System.out.print("Nhập số lượng nhân viên muốn thêm: ");
                    try {
                        int n = Integer.parseInt(scanner.nextLine().trim());
                        for (int i = 0; i < n; i++) {
                            System.out.println("Nhập thông tin nhân viên thứ " + (i + 1) + ":");
                            Employee emp = new Employee();
                            emp.inputData(scanner, employees, empCount);
                            employees[empCount++] = emp;
                            System.out.println("Thêm thành công!");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Số lượng không hợp lệ!");
                    }
                    break;
                case "2": // Hiển thị danh sách nhân viên
                    if (empCount == 0) {
                        System.out.println("Danh sách nhân viên trống!");
                    } else {
                        System.out.println("--- Danh sách nhân viên ---");
                        for (int i = 0; i < empCount; i++) {
                            employees[i].displayData();
                        }
                    }
                    break;
                case "3": // Cập nhật thông tin nhân viên
                    if (empCount == 0) {
                        System.out.println("Danh sách nhân viên trống!");
                        break;
                    }
                    System.out.print("Nhập mã nhân viên cần cập nhật: ");
                    String empUpdateId = scanner.nextLine().trim();
                    Employee empUpdate = null;
                    for (int i = 0; i < empCount; i++) {
                        if (employees[i].getEmployeeId().equals(empUpdateId)) {
                            empUpdate = employees[i];
                            break;
                        }
                    }
                    if (empUpdate != null) {
                        // Cập nhật tên
                        while (true) {
                            System.out.print("Nhập tên mới (hoặc Enter để bỏ qua): ");
                            String name = scanner.nextLine().trim();
                            if (name.isEmpty()) break;
                            if (name.length() >= 6 && name.length() <= 30) {
                                empUpdate.setEmployeeName(name);
                                break;
                            } else {
                                System.err.println("Tên mới phải từ 6 đến 30 ký tự!");
                            }
                        }
                        // Cập nhật role
                        while (true) {
                            System.out.print("Nhập vai trò mới (1: DEV, 2: TESTER, 3: PM, 4: BA) hoặc Enter để bỏ qua: ");
                            String r = scanner.nextLine().trim();
                            if (r.isEmpty()) break;
                            try {
                                int c = Integer.parseInt(r);
                                if (c == 1) empUpdate.setRole(Role.DEV);
                                else if (c == 2) empUpdate.setRole(Role.TESTER);
                                else if (c == 3) empUpdate.setRole(Role.PM);
                                else if (c == 4) empUpdate.setRole(Role.BA);
                                else {
                                    System.err.println("Lựa chọn không hợp lệ!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("Vui lòng nhập số!");
                            }
                        }
                        // Cập nhật lương
                        while (true) {
                            System.out.print("Nhập lương cơ bản mới (hoặc Enter để bỏ qua): ");
                            String s = scanner.nextLine().trim();
                            if (s.isEmpty()) break;
                            try {
                                double sal = Double.parseDouble(s);
                                if (sal > 0) {
                                    empUpdate.setSalary(sal);
                                    break;
                                } else {
                                    System.err.println("Lương phải lớn hơn 0!");
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Lương không hợp lệ!");
                            }
                        }
                        System.out.println("Cập nhật thông tin nhân viên thành công!");
                    } else {
                        System.err.println("Không tìm thấy nhân viên với mã: " + empUpdateId);
                    }
                    break;
                case "4": // Xóa nhân viên
                    System.out.print("Nhập mã nhân viên cần xóa: ");
                    String empDeleteId = scanner.nextLine().trim();
                    int delIndex = -1;
                    for (int i = 0; i < empCount; i++) {
                        if (employees[i].getEmployeeId().equals(empDeleteId)) {
                            delIndex = i;
                            break;
                        }
                    }
                    if (delIndex != -1) {
                        // Cần check nhân viên có đang trong dự án nào không (có thể yêu cầu thêm, nhưng tạm thời xóa thẳng hoặc tùy logic)
                        for (int i = delIndex; i < empCount - 1; i++) {
                            employees[i] = employees[i + 1];
                        }
                        employees[empCount - 1] = null;
                        empCount--;
                        System.out.println("Xóa nhân viên thành công!");
                    } else {
                        System.err.println("Không tìm thấy nhân viên với mã: " + empDeleteId);
                    }
                    break;
                case "5": // Tìm kiếm nhân viên theo tên
                    System.out.print("Nhập tên nhân viên cần tìm: ");
                    String keyword = scanner.nextLine().trim().toLowerCase();
                    boolean found = false;
                    for (int i = 0; i < empCount; i++) {
                        if (employees[i].getEmployeeName().toLowerCase().contains(keyword)) {
                            employees[i].displayData();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Không tìm thấy nhân viên nào!");
                    }
                    break;
                case "6": // Sắp xếp nhân viên theo lương giảm dần
                    for (int i = 0; i < empCount - 1; i++) {
                        for (int j = i + 1; j < empCount; j++) {
                            if (employees[i].getSalary() < employees[j].getSalary()) {
                                Employee temp = employees[i];
                                employees[i] = employees[j];
                                employees[j] = temp;
                            }
                        }
                    }
                    System.out.println("Đã sắp xếp! Hãy chọn mục 2 để xem kết quả.");
                    break;
                case "7": // Thoát
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public static void projectManager(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("=================== QUẢN LÝ DỰ ÁN ===================");
            System.out.println("1. Thêm dự án");
            System.out.println("2. Hiển thị danh sách dự án");
            System.out.println("3. Cập nhật thông tin dự án");
            System.out.println("4. Xóa dự án (chỉ khi chưa có nhân viên tham gia)");
            System.out.println("5. Thêm nhân viên vào dự án");
            System.out.println("6. Tìm dự án theo tên");
            System.out.println("7. Thống kê số lượng nhân viên theo vai trò trong từng dự án");
            System.out.println("8. Tìm dự án đang chạy và gần kết thúc nhất");
            System.out.println("9. Thoát");
            System.out.println("=====================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": // Thêm dự án
                    System.out.print("Nhập số lượng dự án muốn thêm: ");
                    try {
                        int n = Integer.parseInt(scanner.nextLine().trim());
                        for (int i = 0; i < n; i++) {
                            System.out.println("Nhập thông tin dự án thứ " + (i + 1) + ":");
                            Project proj = new Project();
                            proj.inputData(scanner, projects, projCount, employees, empCount);
                            projects[projCount++] = proj;
                            System.out.println("Thêm dự án thành công!");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Số lượng không hợp lệ!");
                    }
                    break;
                case "2": // Hiển thị danh sách dự án
                    if (projCount == 0) {
                        System.out.println("Danh sách dự án trống!");
                    } else {
                        System.out.println("--- Danh sách dự án ---");
                        for (int i = 0; i < projCount; i++) {
                            projects[i].displayData();
                        }
                    }
                    break;
                case "3": // Cập nhật thông tin dự án
                    if (projCount == 0) {
                        System.out.println("Danh sách dự án trống!");
                        break;
                    }
                    System.out.print("Nhập mã dự án cần cập nhật: ");
                    String prUpId = scanner.nextLine().trim();
                    Project prUpdate = null;
                    for (int i = 0; i < projCount; i++) {
                        if (projects[i].getProjectId().equals(prUpId)) {
                            prUpdate = projects[i];
                            break;
                        }
                    }
                    if (prUpdate != null) {
                        // Cập nhật projectName
                        while (true) {
                            System.out.print("Nhập tên dự án mới (hoặc Enter để bỏ qua): ");
                            String name = scanner.nextLine().trim();
                            if (name.isEmpty()) break;
                            if (name.length() >= 10 && name.length() <= 50) {
                                boolean isExist = false;
                                for (int i = 0; i < projCount; i++) {
                                    if (projects[i] != prUpdate && projects[i].getProjectName().equals(name)) {
                                        isExist = true;
                                        break;
                                    }
                                }
                                if (isExist) {
                                    System.err.println("Tên dự án đã tồn tại!");
                                } else {
                                    prUpdate.setProjectName(name);
                                    break;
                                }
                            } else {
                                System.err.println("Tên mới phải từ 10 đến 50 ký tự!");
                            }
                        }
                        
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        // Cập nhật startDate
                        while (true) {
                            System.out.print("Nhập ngày bắt đầu mới (dd/MM/yyyy) hoặc Enter để bỏ qua: ");
                            String s = scanner.nextLine().trim();
                            if (s.isEmpty()) break;
                            try {
                                LocalDate date = LocalDate.parse(s, dtf);
                                prUpdate.setStartDate(date);
                                // Kiểm tra và sửa lại nếu endDate < startDate mới
                                if (prUpdate.getEndDate() != null && prUpdate.getEndDate().isBefore(date)) {
                                    System.out.println("Lưu ý: Ngày bắt đầu mới lớn hơn ngày kết thúc hiện tại. Hãy cập nhật ngày kết thúc!");
                                }
                                break;
                            } catch (DateTimeParseException e) {
                                System.err.println("Định dạng ngày không hợp lệ!");
                            }
                        }
                        
                        // Cập nhật endDate
                        while (true) {
                            System.out.print("Nhập ngày kết thúc mới (dd/MM/yyyy) hoặc Enter để bỏ qua: ");
                            String e = scanner.nextLine().trim();
                            if (e.isEmpty()) break;
                            try {
                                LocalDate date = LocalDate.parse(e, dtf);
                                if (prUpdate.getStartDate() != null && date.isBefore(prUpdate.getStartDate())) {
                                    System.err.println("Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu!");
                                } else {
                                    prUpdate.setEndDate(date);
                                    break;
                                }
                            } catch (DateTimeParseException e) {
                                System.err.println("Định dạng ngày không hợp lệ!");
                            }
                        }
                        
                        // Cập nhật trạng thái
                        while (true) {
                            System.out.print("Nhập trạng thái mới (1: PLANNING, 2: RUNNING, 3: FINISHED) hoặc Enter để bỏ qua: ");
                            String s = scanner.nextLine().trim();
                            if(s.isEmpty()) break;
                            try {
                                int s_choice = Integer.parseInt(s);
                                if (s_choice == 1) prUpdate.setStatus(ProjectStatus.PLANNING);
                                else if(s_choice == 2) prUpdate.setStatus(ProjectStatus.RUNNING);
                                else if(s_choice == 3) prUpdate.setStatus(ProjectStatus.FINISHED);
                                else {
                                    System.err.println("Lựa chọn không hợp lệ!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("Vui lòng nhập số!");
                            }
                        }
                        System.out.println("Cập nhật thông tin dự án thành công!");
                    } else {
                        System.err.println("Không tìm thấy dự án với mã: " + prUpId);
                    }
                    break;
                case "4": // Xóa dự án (chỉ khi chưa có nhân viên tham gia)
                    System.out.print("Nhập mã dự án cần xóa: ");
                    String prDelId = scanner.nextLine().trim();
                    int pDelIndex = -1;
                    for (int i = 0; i < projCount; i++) {
                        if (projects[i].getProjectId().equals(prDelId)) {
                            pDelIndex = i;
                            break;
                        }
                    }
                    if (pDelIndex != -1) {
                        if (projects[pDelIndex].getEmployeeCount() > 0) {
                            System.err.println("Không thể xóa dự án vì đã có nhân viên tham gia!");
                        } else {
                            for (int i = pDelIndex; i < projCount - 1; i++) {
                                projects[i] = projects[i + 1];
                            }
                            projects[projCount - 1] = null;
                            projCount--;
                            System.out.println("Xóa dự án thành công!");
                        }
                    } else {
                        System.err.println("Không tìm thấy dự án với mã: " + prDelId);
                    }
                    break;
                case "5": // Thêm nhân viên vào dự án
                    System.out.print("Nhập mã dự án: ");
                    String prId = scanner.nextLine().trim();
                    Project prAddEmp = null;
                    for (int i = 0; i < projCount; i++) {
                        if (projects[i].getProjectId().equals(prId)) {
                            prAddEmp = projects[i];
                            break;
                        }
                    }
                    if (prAddEmp != null) {
                        if (empCount == 0) {
                            System.err.println("Danh sách nhân viên trống, không có NV để thêm!");
                            break;
                        }
                        System.out.println("Danh sách nhân viên:");
                        for (int i = 0; i < empCount; i++) {
                            System.out.printf("%d. %s - %s\n", i + 1, employees[i].getEmployeeId(), employees[i].getEmployeeName());
                        }
                        System.out.print("Nhập mã nhân viên muốn thêm vào dự án: ");
                        String eId = scanner.nextLine().trim();
                        Employee empAdd = null;
                        for (int i = 0; i < empCount; i++) {
                            if (employees[i].getEmployeeId().equals(eId)) {
                                empAdd = employees[i];
                                break;
                            }
                        }
                        if (empAdd != null) {
                            boolean isAssigned = false;
                            for (int i = 0; i < prAddEmp.getEmployeeCount(); i++) {
                                if (prAddEmp.getEmployees()[i].getEmployeeId().equals(empAdd.getEmployeeId())) {
                                    isAssigned = true;
                                    break;
                                }
                            }
                            if (isAssigned) {
                                System.err.println("Nhân viên đã tham gia dự án này!");
                            } else {
                                Employee[] eArr = prAddEmp.getEmployees();
                                int eCount = prAddEmp.getEmployeeCount();
                                eArr[eCount] = empAdd;
                                prAddEmp.setEmployeeCount(eCount + 1);
                                System.out.println("Thêm nhân viên vào dự án thành công!");
                            }
                        } else {
                            System.err.println("Không tìm thấy nhân viên với mã: " + eId);
                        }
                    } else {
                        System.err.println("Không tìm thấy dự án với mã: " + prId);
                    }
                    break;
                case "6": // Tìm dự án theo tên
                    System.out.print("Nhập tên dự án cần tìm: ");
                    String pKeyword = scanner.nextLine().trim().toLowerCase();
                    boolean pFound = false;
                    for (int i = 0; i < projCount; i++) {
                        if (projects[i].getProjectName().toLowerCase().contains(pKeyword)) {
                            projects[i].displayData();
                            pFound = true;
                        }
                    }
                    if (!pFound) {
                        System.out.println("Không tìm thấy dự án nào!");
                    }
                    break;
                case "7": // Thống kê số lượng nhân viên theo vai trò trong từng dự án
                    for (int i = 0; i < projCount; i++) {
                        Project p = projects[i];
                        int cDev = 0, cTest = 0, cPm = 0, cBa = 0;
                        Employee[] arrE = p.getEmployees();
                        for (int j = 0; j < p.getEmployeeCount(); j++) {
                            Role r = arrE[j].getRole();
                            if (r == Role.DEV) cDev++;
                            else if (r == Role.TESTER) cTest++;
                            else if (r == Role.PM) cPm++;
                            else if (r == Role.BA) cBa++;
                        }
                        System.out.printf("Dự án: %s - DEV: %d, TESTER: %d, PM: %d, BA: %d\n", 
                                p.getProjectName(), cDev, cTest, cPm, cBa);
                    }
                    break;
                case "8": // Tìm dự án đang chạy và gần kết thúc nhất
                    Project targetP = null;
                    for(int i=0; i<projCount; i++) {
                        Project p = projects[i];
                        if (p.getStatus() == ProjectStatus.RUNNING) {
                            if (targetP == null) {
                                targetP = p;
                            } else {
                                if (p.getEndDate().isBefore(targetP.getEndDate())) {
                                    targetP = p;
                                }
                            }
                        }
                    }
                    if (targetP != null) {
                        System.out.println("Dự án đang chạy và gần kết thúc nhất:");
                        targetP.displayData();
                    } else {
                        System.out.println("Không có dự án nào đang chạy!");
                    }
                    break;
                case "9": // Thoát
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
