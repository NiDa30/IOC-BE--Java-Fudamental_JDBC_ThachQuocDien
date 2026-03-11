import java.util.Scanner;

public class Main {
    // --- Trạng thái cho Bài tập 2 (Quản lý điểm) ---
    static int studentCount = 0;
    static double gradeSum = 0;
    static double maxGrade = -1;
    static double minGrade = 11;

    // --- Trạng thái cho Bài tập 3 (Quản lý lương) ---
    static int employeeCount = 0;
    static double totalSalary = 0;
    static double maxSalary = -1;
    static double minSalary = 500000001;
    static double totalBonus = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n========== MENU BÀI TẬP SESSION 03 ==========");
            System.out.println("1. Bài tập 1: Tính hóa đơn thanh toán");
            System.out.println("2. Bài tập 2: Quản lý điểm học viên");
            System.out.println("3. Bài tập 3: Quản lý lương nhân viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn bài tập (0-3): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                break;
            }

            switch (choice) {
                case 1:
                    exercise01(scanner);
                    break;
                case 2:
                    exercise02(scanner);
                    break;
                case 3:
                    exercise03(scanner);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
        scanner.close();
    }

    /**
     * Bài tập 3: Quản lý lương nhân viên
     */
    public static void exercise03(Scanner scanner) {
        int choice;
        while (true) {
            System.out.println("\n**************** MENU NHẬP LƯƠNG ****************");
            System.out.println("1.\tNhập lương nhân viên");
            System.out.println("2.\tHiển thị thống kê");
            System.out.println("3.\tTính tổng số tiền thưởng cho nhân viên");
            System.out.println("4.\tThoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số (1-4).");
                continue;
            }

            switch (choice) {
                case 1:
                    inputSalaries(scanner);
                    break;
                case 2:
                    displaySalaryStatistics();
                    break;
                case 3:
                    displayTotalBonus();
                    break;
                case 4:
                    System.out.println("Kết thúc chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void inputSalaries(Scanner scanner) {
        System.out.println("\n--- Nhập lương nhân viên (nhập -1 để dừng) ---");
        while (true) {
            System.out.print("Nhập lương: ");
            try {
                double salary = Double.parseDouble(scanner.nextLine());

                if (salary == -1) {
                    break;
                }

                if (salary < 0 || salary > 500000000) {
                    System.err.println("Lương không hợp lệ. Nhập lại.");
                    continue;
                }

                // Phân loại và tính thưởng
                String classification;
                double bonusPercent;

                if (salary < 5000000) {
                    classification = "Thu nhập thấp";
                    bonusPercent = 0.05;
                } else if (salary < 15000000) {
                    classification = "Thu nhập trung bình";
                    bonusPercent = 0.10;
                } else if (salary < 50000000) {
                    classification = "Thu nhập khá";
                    bonusPercent = 0.15;
                } else if (salary < 100000000) {
                    classification = "Thu nhập cao";
                    bonusPercent = 0.20;
                } else {
                    classification = "Thu nhập cao";
                    bonusPercent = 0.25;
                }

                // Cập nhật trạng thái
                employeeCount++;
                totalSalary += salary;
                totalBonus += (salary * bonusPercent);
                if (salary > maxSalary) maxSalary = salary;
                if (salary < minSalary) minSalary = salary;

                System.out.println("-> Phân loại: " + classification);

            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số.");
            }
        }
    }

    private static void displaySalaryStatistics() {
        System.out.println("\n--- Thống kê ---");
        if (employeeCount == 0) {
            System.out.println("Chưa có dữ liệu");
        } else {
            System.out.println("Số nhân viên: " + employeeCount);
            System.out.printf("Tổng lương: %,.0f VND%n", totalSalary);
            System.out.printf("Lương trung bình: %,.0f VND%n", (totalSalary / employeeCount));
            System.out.printf("Lương cao nhất: %,.0f VND%n", maxSalary);
            System.out.printf("Lương thấp nhất: %,.0f VND%n", minSalary);
        }
    }

    private static void displayTotalBonus() {
        System.out.println("\n--- Tính tổng số tiền thưởng nhân viên ---");
        if (employeeCount == 0) {
            System.out.println("Chưa có dữ liệu nhân viên.");
        } else {
            System.out.printf("Tổng tiền thưởng nhân viên: %,.0f VND%n", totalBonus);
        }
    }

    /**
     * Bài tập 2: Quản lý điểm học viên
     */
    public static void exercise02(Scanner scanner) {
        int choice;
        while (true) {
            System.out.println("\n**************** MENU NHẬP ĐIỂM ****************");
            System.out.println("1.\tNhập điểm học viên");
            System.out.println("2.\tHiển thị thống kê");
            System.out.println("3.\tThoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số (1-3).");
                continue;
            }

            switch (choice) {
                case 1:
                    inputGrades(scanner);
                    break;
                case 2:
                    displayStatistics();
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void inputGrades(Scanner scanner) {
        System.out.println("\n--- Nhập điểm học viên (nhập -1 để dừng) ---");
        while (true) {
            System.out.print("Nhập điểm: ");
            try {
                double grade = Double.parseDouble(scanner.nextLine());

                if (grade == -1) {
                    break;
                }

                if (grade < 0 || grade > 10) {
                    System.err.println("Điểm không hợp lệ. Nhập lại.");
                    continue;
                }

                // Cập nhật thống kê
                studentCount++;
                gradeSum += grade;
                if (grade > maxGrade) maxGrade = grade;
                if (grade < minGrade) minGrade = grade;

                // Xếp loại
                String rank;
                if (grade < 5) rank = "Yếu";
                else if (grade < 7) rank = "Trung bình";
                else if (grade < 8) rank = "Khá";
                else if (grade < 9) rank = "Giỏi";
                else rank = "Xuất sắc";

                System.out.println("Học lực: " + rank);

            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số thực.");
            }
        }
    }

    private static void displayStatistics() {
        System.out.println("\n--- KẾT QUẢ ---");
        if (studentCount == 0) {
            System.out.println("Chưa có dữ liệu");
        } else {
            System.out.println("Số học viên đã nhập: " + studentCount);
            System.out.printf("Điểm trung bình: %.2f%n", (gradeSum / studentCount));
            System.out.printf("Điểm cao nhất: %.2f%n", maxGrade);
            System.out.printf("Điểm thấp nhất: %.2f%n", minGrade);
        }
    }

    /**
     * Bài tập 1: Khai báo biến, toán tử, nhập xuất dữ liệu hóa đơn
     */
    public static void exercise01(Scanner scanner) {
        System.out.println("\n========= NHẬP THÔNG TIN HÓA ĐƠN =========");

        try {
            System.out.print("Nhập tên khách hàng: ");
            String customerName = scanner.nextLine();

            System.out.print("Nhập tên sản phẩm: ");
            String productName = scanner.nextLine();

            System.out.print("Nhập giá sản phẩm: ");
            double productPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Nhập số lượng mua: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Khách có thẻ thành viên? (true/false): ");
            boolean hasMemberCard = Boolean.parseBoolean(scanner.nextLine());

            // --- TÍNH TOÁN ---
            double totalAmount = productPrice * quantity;
            double discount = hasMemberCard ? totalAmount * 0.1 : 0;
            double vatAmount = (totalAmount - discount) * 0.08; // VAT tính trên số tiền sau giảm giá hoặc theo tổng gốc?
            // Theo mô tả: Tổng tiền = Thành tiền – Giảm giá + VAT.
            // Thông thường VAT tính trên (Thành tiền - Giảm giá).

            double finalPayment = totalAmount - discount + vatAmount;

            // --- XUẤT HÓA ĐƠN ---
            System.out.println("\n================ HÓA ĐƠN ================");
            System.out.printf("Khách hàng  : %s%n", customerName);
            System.out.printf("Sản phẩm    : %s%n", productName);
            System.out.printf("Số lượng    : %d%n", quantity);
            System.out.printf("Đơn giá     : %,.0f VNĐ%n", productPrice);
            System.out.printf("Thành tiền  : %,.0f VNĐ%n", totalAmount);
            System.out.printf("Giảm giá    : %,.0f%n", discount);
            System.out.printf("Tiền VAT    : %,.0f%n", vatAmount);
            System.out.printf("Tổng thanh toán: %,.0f VNĐ%n", finalPayment);
            System.out.println("=========================================");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Giá và số lượng phải là trị số.");
        }
    }
}