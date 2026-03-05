import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n========== MENU BÀI TẬP SESSION 02 ==========");
            System.out.println("1. Bài tập 1: Tính tổng các số từ 1 đến N");
            System.out.println("2. Bài tập 2: Tìm số ngày trong tháng");
            System.out.println("3. Bài tập 3: Tính tổng các chữ số của một số nguyên");
            System.out.println("4. Bài tập 4: Kiểm tra tính hợp lệ của tam giác");
            System.out.println("5. Bài tập 5: Đọc số thành chữ (100-999)");
            System.out.println("6. Bài tập 6: Xác định số Armstrong từ 0 đến N");
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
     * Bài 1 [ Khá ] Tính tổng các số từ 1 đến N
     */
    public static void exercise01(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 1: Tính tổng các số từ 1 đến N ---");
        System.out.print("Nhập vào một số nguyên dương N: ");
        
        try {
            int n = Integer.parseInt(scanner.nextLine());
            
            if (n <= 0) {
                System.out.println("Số nhập vào không hợp lệ.");
                return;
            }
            
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
            
            System.out.println("Tổng các số từ 1 đến " + n + " là: " + sum);
            
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số nguyên.");
        }
    }

    /**
     * Bài 2 [ Khá ] Tìm số ngày trong tháng
     * Yêu cầu: Nhập vào tháng (1-12), hiển thị số ngày tương ứng bằng switch-case.
     */
    public static void exercise02(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 2: Tìm số ngày trong tháng ---");
        System.out.print("Nhập vào số tháng (1-12): ");
        
        try {
            int month = Integer.parseInt(scanner.nextLine());
            
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    System.out.println("Tháng " + month + " có 31 ngày.");
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println("Tháng " + month + " có 30 ngày.");
                    break;
                case 2:
                    System.out.println("Tháng 2 có 28 hoặc 29 ngày.");
                    break;
                default:
                    System.out.println("Tháng không hợp lệ.");
                    break;
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số nguyên.");
        }
    }

    /**
     * Bài 3 [ Giỏi ] Tính tổng các chữ số của một số nguyên
     * Yêu cầu: Nhập vào một số nguyên N, tính tổng các chữ số của nó.
     */
    public static void exercise03(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 3: Tính tổng các chữ số ---");
        System.out.print("Nhập vào một số nguyên: ");
        
        try {
            long n = Long.parseLong(scanner.nextLine());
            
            // Xử lý nếu số là âm, chuyển thành số dương
            long tempN = Math.abs(n);
            int sum = 0;
            
            // Nếu n = 0 thì tổng là 0 (vòng lặp while sẽ không chạy nếu tempN = 0)
            if (tempN == 0) {
                sum = 0;
            } else {
                // Sử dụng vòng lặp để tách từng chữ số
                while (tempN > 0) {
                    sum += tempN % 10; // Lấy chữ số cuối
                    tempN /= 10;       // Loại bỏ chữ số cuối
                }
            }
            
            System.out.println("Tổng các chữ số là: " + sum);
            
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ.");
        }
    }

    /**
     * Bài 4 [ Giỏi ] Kiểm tra tính hợp lệ của tam giác
     * Yêu cầu: Nhập 3 cạnh, kiểm tra tam giác và phân loại.
     */
    public static void exercise04(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 4: Kiểm tra tính hợp lệ của tam giác ---");
        
        try {
            System.out.print("Nhập cạnh thứ nhất: ");
            int a = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập cạnh thứ hai: ");
            int b = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập cạnh thứ ba: ");
            int c = Integer.parseInt(scanner.nextLine());
            
            if (a > 0 && b > 0 && c > 0 && (a + b > c) && (a + c > b) && (b + c > a)) {
                if (a == b && b == c) {
                    System.out.println("Đây là tam giác đều.");
                } else if (a == b || b == c || a == c) {
                    System.out.println("Đây là tam giác cân.");
                } else if (a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a) {
                    System.out.println("Đây là tam giác vuông.");
                } else {
                    System.out.println("Đây là tam giác thường.");
                }
                
            } else {
                System.out.println("Ba cạnh không tạo thành tam giác.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ.");
        }
    }

    /**
     * Bài 5 [ Xuất sắc ] Đọc số thành chữ (3 chữ số)
     * Yêu cầu: Nhập vào số (100-999), đọc thành chữ tiếng Việt.
     */
    public static void exercise05(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 5: Đọc số thành chữ (100-999) ---");
        System.out.print("Nhập vào một số nguyên (100-999): ");
        
        try {
            int number = Integer.parseInt(scanner.nextLine());
            
            if (number < 100 || number > 999) {
                System.out.println("Số nhập vào không hợp lệ");
                return;
            }
            
            int hundreds = number / 100;
            int tens = (number % 100) / 10;
            int units = number % 10;
            
            String result = "";
            
            switch (hundreds) {
                case 1: result = "Một trăm"; break;
                case 2: result = "Hai trăm"; break;
                case 3: result = "Ba trăm"; break;
                case 4: result = "Bốn trăm"; break;
                case 5: result = "Năm trăm"; break;
                case 6: result = "Sáu trăm"; break;
                case 7: result = "Bảy trăm"; break;
                case 8: result = "Tám trăm"; break;
                case 9: result = "Chín trăm"; break;
            }
            
            // 2. Đọc hàng chục
            if (tens == 0) {
                if (units != 0) {
                    result += " lẻ";
                }
            } else if (tens == 1) {
                result += " mười";
            } else {
                switch (tens) {
                    case 2: result += " hai mươi"; break;
                    case 3: result += " ba mươi"; break;
                    case 4: result += " bốn mươi"; break;
                    case 5: result += " năm mươi"; break;
                    case 6: result += " sáu mươi"; break;
                    case 7: result += " bảy mươi"; break;
                    case 8: result += " tám mươi"; break;
                    case 9: result += " chín mươi"; break;
                }
            }
            
            // 3. Đọc hàng đơn vị
            if (units != 0) {
                if (units == 1 && tens > 1) {
                    result += " mốt";
                } else if (units == 5 && tens > 0) {
                    result += " lăm";
                } else {
                    switch (units) {
                        case 1: result += " một"; break;
                        case 2: result += " hai"; break;
                        case 3: result += " ba"; break;
                        case 4: result += " bốn"; break;
                        case 5: result += " năm"; break;
                        case 6: result += " sáu"; break;
                        case 7: result += " bảy"; break;
                        case 8: result += " tám"; break;
                        case 9: result += " chín"; break;
                    }
                }
            }
            
            System.out.println(result);
            
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Số nhập vào không hợp lệ");
        }
    }

    /**
     * Bài 6 [ Xuất sắc ] Xác định số Armstrong trong khoảng từ 0 đến N
     * Yêu cầu: Nhập N, in các số Armstrong từ 0 đến N.
     */
    public static void exercise06(Scanner scanner) {
        System.out.println("\n--- BÀI TẬP 6: Xác định số Armstrong từ 0 đến N ---");
        int n;
        
        // Yêu cầu nhập đến khi hợp lệ (số nguyên dương >= 0)
        while (true) {
            System.out.print("Nhập vào số nguyên dương N: ");
            try {
                n = Integer.parseInt(scanner.nextLine());
                if (n >= 0) {
                    break;
                } else {
                    System.out.println("Số nhập vào không hợp lệ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Số nhập vào không hợp lệ");
            }
        }
        
        System.out.print("Các số Armstrong từ 0 đến " + n + " là: ");
        
        for (int i = 0; i <= n; i++) {
            if (isArmstrong(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    /**
     * Hàm kiểm tra một số có phải là số Armstrong hay không
     */
    private static boolean isArmstrong(int num) {
        if (num < 0) return false;
        if (num == 0) return true;
        
        int k = String.valueOf(num).length();
        
        int sum = 0;
        int temp = num;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, k);
            temp /= 10;
        }
        
        return sum == num;
    }
}