import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n========== MENU BÀI TẬP SESSION 01 ==========");
            System.out.println("1. Bài tập 1: Tính diện tích hình tròn");
            System.out.println("2. Bài tập 2: Các phép toán số học cơ bản");
            System.out.println("3. Bài tập 3: Tính tổng hai phân số");
            System.out.println("4. Bài tập 4: Tính diện tích và chu vi hình chữ nhật");
            System.out.println("5. Bài tập 5: Tính toán chỉ số BMI");
            System.out.println("6. Bài tập 6: Tính quãng đường đi được");
            System.out.println("0. Thoát");
            System.out.print("Chọn bài tập (0-6): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số từ 0 - 6.");
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
                case 4:
                    exercise04(scanner);
                    break;
                case 5:
                    exercise05(scanner);
                    break;
                case 6:
                    exercise06(scanner);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
        scanner.close();
    }

    /**
     * Bài 1Công thức: A = π * r^2
     */
    public static void exercise01(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 1:[ Khá ] Tính diện tích hình tròn ---");
        System.out.print("Nhập bán kính của hình tròn (radius): ");
        
        try {
            double radius = Double.parseDouble(scanner.nextLine());
            double area = Math.PI * radius * radius;

            // In kết quả ra màn hình với 2 chữ số thập phân
            System.out.printf("Diện tích : %.2f\n", area);
            
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Bán kính phải là một số.");
        }
    }

    /**
     * Bài 2
     */
    public static void exercise02(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 2:[ Khá ] Toán tử số học ---");
        
        try {
            System.out.print("Nhập số thứ nhất (firstNumber): ");
            int firstNumber = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Nhập số thứ hai (secondNumber): ");
            int secondNumber = Integer.parseInt(scanner.nextLine());

            // Thực hiện các phép toán
            int sum = firstNumber + secondNumber;
            int diff = firstNumber - secondNumber;
            int product = firstNumber * secondNumber;
            
            System.out.println("\n--- Kết quả ---");
            System.out.println("firstNumber = " + firstNumber);
            System.out.println("secondNumber = " + secondNumber);
            System.out.println("Tổng = " + sum);
            System.out.println("Hiệu = " + diff);
            System.out.println("Tích = " + product);

            // Xử lý chia cho 0 để tránh lỗi runtime
            if (secondNumber != 0) {
                int quotient = firstNumber / secondNumber;
                int remainder = firstNumber % secondNumber;
                System.out.println("Thương = " + quotient);
                System.out.println("Phần dư = " + remainder);
            } else {
                System.out.println("Thương = (Không thể chia cho 0)");
                System.out.println("Phần dư = (Không thể chia cho 0)");
            }

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ.");
        }
    }

    /**
     * Bài 3
     */
    public static void exercise03(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 3: [ Giỏi ] Tính tổng hai phân số ---");

        try {
            System.out.print("Nhập tử số phân số 1 (a): ");
            int a = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập mẫu số phân số 1 (b): ");
            int b = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập tử số phân số 2 (c): ");
            int c = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập mẫu số phân số 2 (d): ");
            int d = Integer.parseInt(scanner.nextLine());

            if (b == 0 || d == 0) {
                System.out.println("Lỗi: Mẫu số phải khác 0.");
                return;
            }

            // Tính toán theo công thức: (ad + bc) / bd
            int numerator = a * d + b * c;
            int denominator = b * d;

            System.out.println("\n--- Kết quả ---");
            System.out.println("Tổng hai phân số: " + numerator + "/" + denominator);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Tử số và mẫu số phải là số nguyên.");
        }
    }

    /**
     * Bài 4: Tính diện tích và chu vi hình chữ nhật
     * Diện tích = width * height
     * Chu vi = 2 * (width + height)
     */
    public static void exercise04(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 4: [ Khá ] DIỆN TÍCH & CHU VI HÌNH CHỮ NHẬT ---");

        try {
            System.out.print("Nhập chiều rộng (width): ");
            float width = Float.parseFloat(scanner.nextLine());
            
            System.out.print("Nhập chiều cao (height): ");
            float height = Float.parseFloat(scanner.nextLine());

            // Thực hiện tính toán
            float area = width * height;
            float perimeter = 2 * (width + height);

            System.out.println("\n--- Kết quả ---");
            System.out.printf("Diện tích : %.2f\n", area);
            System.out.printf("Chu vi : %.2f\n", perimeter);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Chiều rộng và chiều cao phải là một số.");
        }
    }

    /**
     * Bài 5: Tính toán chỉ số BMI
     * Công thức: BMI = Cân nặng (kg) / (Chiều cao (m))^2
     */
    public static void exercise05(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 5: [ Khá ] TÍNH TOÁN CHỈ SỐ BMI ---");

        try {
            System.out.print("Nhập cân nặng (kg): ");
            double weight = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Nhập chiều cao (m): ");
            double height = Double.parseDouble(scanner.nextLine());

            if (height <= 0) {
                System.out.println("Lỗi: Chiều cao phải lớn hơn 0.");
                return;
            }

            // Tính BMI
            double bmi = weight / (height * height);

            System.out.println("\n--- Kết quả ---");
            System.out.printf("Chỉ số BMI = %.2f\n", bmi);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Cân nặng và chiều cao phải là một số.");
        }
    }

    /**
     * Bài 6: Tính quãng đường đi được
     * Công thức: Quãng đường = Vận tốc * Thời gian
     */
    public static void exercise06(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 6: [ Khá ] TÍNH QUÃNG ĐƯỜNG ĐI ĐƯỢC ---");

        try {
            System.out.print("Nhập vận tốc (km/h): ");
            double velocity = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Nhập thời gian đi (giờ): ");
            double time = Double.parseDouble(scanner.nextLine());

            // Kiểm tra điều kiện >= 0
            if (velocity < 0 || time < 0) {
                System.out.println("Lỗi: Vận tốc và thời gian không được nhỏ hơn 0.");
                return;
            }

            // Tính quãng đường
            double distance = velocity * time;

            System.out.println("\n--- Kết quả ---");
            System.out.printf("Quãng đường = %.1f (km)\n", distance);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vận tốc và thời gian phải là một số.");
        }
    }
}