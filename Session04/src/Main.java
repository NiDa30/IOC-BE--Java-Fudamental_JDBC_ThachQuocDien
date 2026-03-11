import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========== MENU BÀI TẬP SESSION 04 ==========");
            System.out.println("1. [Khá] Sắp xếp mảng giảm dần (Bubble Sort)");
            System.out.println("2. [Khá] Tính tổng các số chẵn và lẻ trong mảng 2 chiều");
            System.out.println("3. [Giỏi] Sắp xếp chọn giảm dần và tìm kiếm (Tuyến tính & Nhị phân)");
            System.out.println("4. [Bài tập] Chẵn trước lẻ sau (Giữ nguyên thứ tự)");
            System.out.println("5. [Xuất sắc] Quản lý điểm số sinh viên");
            System.out.println("6. [Xuất sắc] Quản lý lương nhân viên");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số nguyên!");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    exercise1(scanner);
                    break;
                case 2:
                    exercise2(scanner);
                    break;
                case 3:
                    exercise3(scanner);
                    break;
                case 4:
                    exercise4(scanner);
                    break;
                case 5:
                    exercise5(scanner);
                    break;
                case 6:
                    exercise6(scanner);
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại (0-6).");
            }
        } while (choice != 0);

        scanner.close();
    }

    /**
     * Bài 1: Sắp xếp mảng giảm dần bằng thuật toán Bubble Sort
     */
    public static void exercise1(Scanner scanner) {
        System.out.print("Nhập số lượng phần tử của mảng: ");
        int n = scanner.nextInt();
        
        if (n <= 0) {
            System.out.println("Số lượng phần tử phải lớn hơn 0!");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + " : ");
            arr[i] = scanner.nextInt();
        }

        // Thuật toán Bubble Sort sắp xếp giảm dần
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    // Hoán đổi
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("Mảng sau khi sắp xếp giảm dần:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();
    }

    /**
     * Bài 2: Tính tổng các số chẵn và lẻ trong mảng 2 chiều
     */
    public static void exercise2(Scanner scanner) {
        System.out.print("Nhập số hàng của mảng: ");
        int rows = scanner.nextInt();
        System.out.print("Nhập số cột của mảng: ");
        int cols = scanner.nextInt();

        if (rows <= 0 || cols <= 0) {
            System.out.println("Số hàng và số cột phải lớn hơn 0!");
            return;
        }

        int[][] matrix = new int[rows][cols];
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Phần tử [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        long sumEven = 0;
        long sumOdd = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] % 2 == 0) {
                    sumEven += matrix[i][j];
                } else {
                    sumOdd += matrix[i][j];
                }
            }
        }

        System.out.println("Tổng các số chẵn: " + sumEven);
        System.out.println("Tổng các số lẻ: " + sumOdd);
    }

    /**
     * Bài 3: Sắp xếp chọn giảm dần và tìm kiếm
     */
    public static void exercise3(Scanner scanner) {
        System.out.print("Nhập số lượng phần tử của mảng: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Số lượng phần tử phải lớn hơn 0!");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + " : ");
            arr[i] = scanner.nextInt();
        }

        // 1. Thuật toán Sắp xếp chọn (Selection Sort) giảm dần
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            // Hoán đổi
            int temp = arr[maxIdx];
            arr[maxIdx] = arr[i];
            arr[i] = temp;
        }

        System.out.println("Mảng sau khi sắp xếp giảm dần:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        // 2. Tìm kiếm
        System.out.print("Nhập số cần tìm: ");
        int target = scanner.nextInt();

        // Tìm kiếm tuyến tính
        int linearResult = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == target) {
                linearResult = i;
                break;
            }
        }
        if (linearResult != -1) {
            System.out.println("Tìm kiếm tuyến tính: Số " + target + " có tại vị trí " + linearResult);
        } else {
            System.out.println("Tìm kiếm tuyến tính: Không tìm thấy số " + target);
        }

        // Tìm kiếm nhị phân (Mảng đang giảm dần)
        int binaryResult = -1;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                binaryResult = mid;
                break;
            }
            if (arr[mid] < target) {
                high = mid - 1; // Vì mảng giảm dần, số lớn hơn nằm ở bên trái
            } else {
                low = mid + 1;  // Số nhỏ hơn nằm ở bên phải
            }
        }
        if (binaryResult != -1) {
            System.out.println("Tìm kiếm nhị phân: Số " + target + " có tại vị trí " + binaryResult);
        } else {
            System.out.println("Tìm kiếm nhị phân: Không tìm thấy số " + target);
        }
    }

    /**
     * Bài 4: Chẵn đứng trước, lẻ đứng sau, giữ nguyên thứ tự trong từng nhóm
     */
    public static void exercise4(Scanner scanner) {
        System.out.print("Nhập số lượng phần tử của mảng: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Mảng không có phần tử");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + " : ");
            arr[i] = scanner.nextInt();
        }

        // Tạo mảng kết quả để giữ nguyên thứ tự
        int[] result = new int[n];
        int index = 0;

        // Đưa các số chẵn vào trước
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) {
                result[index++] = arr[i];
            }
        }

        // Đưa các số lẻ vào sau
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 != 0) {
                result[index++] = arr[i];
            }
        }

        System.out.println("Mảng sau khi sắp xếp (Chẵn trước, Lẻ sau):");
        for (int i = 0; i < n; i++) {
            System.out.print(result[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();
    }

    /**
     * Bài 5: Quản lý điểm số sinh viên
     */
    public static void exercise5(Scanner scanner) {
        System.out.print("Nhập số lượng sinh viên: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Vui lòng nhập một số nguyên!");
            scanner.next();
            return;
        }
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Số lượng sinh viên phải lớn hơn 0!");
            return;
        }

        double[] scores = new double[n];
        System.out.println("Nhập thông tin lớp học và điểm sinh viên:");
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập điểm sinh viên thứ " + (i + 1) + ": ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Vui lòng nhập điểm hợp lệ!");
                scanner.next();
            }
            scores[i] = scanner.nextDouble();
        }

        int choice;
        boolean isSortedAsc = false;
        boolean isSortedDesc = false;

        do {
            System.out.println("\n--- QUẢN LÝ ĐIỂM SINH VIÊN ---");
            System.out.println("1. Xem tất cả điểm");
            System.out.println("2. Sắp xếp điểm");
            System.out.println("3. Tìm kiếm điểm");
            System.out.println("4. Thống kê điểm");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số nguyên!");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Danh sách điểm:");
                    for (int i = 0; i < n; i++) {
                        System.out.printf("Sinh viên %d: %.1f\n", (i + 1), scores[i]);
                    }
                    break;
                case 2:
                    System.out.println("Chọn cách sắp xếp:");
                    System.out.println("1. Tăng dần");
                    System.out.println("2. Giảm dần");
                    int sortType = scanner.nextInt();
                    if (sortType == 1) {
                        // Bubble Sort Tăng dần
                        for (int i = 0; i < n - 1; i++) {
                            for (int j = 0; j < n - i - 1; j++) {
                                if (scores[j] > scores[j + 1]) {
                                    double temp = scores[j];
                                    scores[j] = scores[j + 1];
                                    scores[j + 1] = temp;
                                }
                            }
                        }
                        isSortedAsc = true;
                        isSortedDesc = false;
                        System.out.println("Đã sắp xếp tăng dần.");
                    } else if (sortType == 2) {
                        // Bubble Sort Giảm dần
                        for (int i = 0; i < n - 1; i++) {
                            for (int j = 0; j < n - i - 1; j++) {
                                if (scores[j] < scores[j + 1]) {
                                    double temp = scores[j];
                                    scores[j] = scores[j + 1];
                                    scores[j + 1] = temp;
                                }
                            }
                        }
                        isSortedAsc = false;
                        isSortedDesc = true;
                        System.out.println("Đã sắp xếp giảm dần.");
                    } else {
                        System.out.println("Lựa chọn không hợp lệ.");
                    }
                    break;
                case 3:
                    System.out.print("Nhập điểm cần tìm: ");
                    double target = scanner.nextDouble();
                    
                    // Tìm kiếm tuyến tính
                    int linearIdx = -1;
                    for (int i = 0; i < n; i++) {
                        if (scores[i] == target) {
                            linearIdx = i;
                            break;
                        }
                    }
                    if (linearIdx != -1) {
                        System.out.println("Tìm kiếm tuyến tính: Tìm thấy tại vị trí " + linearIdx);
                    } else {
                        System.out.println("Tìm kiếm tuyến tính: Không tìm thấy");
                    }

                    // Tìm kiếm nhị phân
                    if (isSortedAsc) {
                        int low = 0, high = n - 1;
                        int binaryIdx = -1;
                        while (low <= high) {
                            int mid = low + (high - low) / 2;
                            if (scores[mid] == target) {
                                binaryIdx = mid;
                                break;
                            }
                            if (scores[mid] < target) low = mid + 1;
                            else high = mid - 1;
                        }
                        if (binaryIdx != -1) System.out.println("Tìm kiếm nhị phân (mảng tăng dần): Tìm thấy tại vị trí " + binaryIdx);
                        else System.out.println("Tìm kiếm nhị phân (mảng tăng dần): Không tìm thấy");
                    } else if (isSortedDesc) {
                        int low = 0, high = n - 1;
                        int binaryIdx = -1;
                        while (low <= high) {
                            int mid = low + (high - low) / 2;
                            if (scores[mid] == target) {
                                binaryIdx = mid;
                                break;
                            }
                            if (scores[mid] < target) high = mid - 1;
                            else low = mid + 1;
                        }
                        if (binaryIdx != -1) System.out.println("Tìm kiếm nhị phân (mảng giảm dần): Tìm thấy tại vị trí " + binaryIdx);
                        else System.out.println("Tìm kiếm nhị phân (mảng giảm dần): Không tìm thấy");
                    } else {
                        System.out.println("Lưu ý: Bạn chưa sắp xếp mảng, nên không thực hiện tìm kiếm nhị phân.");
                    }
                    break;
                case 4:
                    double sum = 0;
                    double max = scores[0];
                    double min = scores[0];
                    for (double s : scores) {
                        sum += s;
                        if (s > max) max = s;
                        if (s < min) min = s;
                    }
                    double avg = sum / n;
                    int countAbove = 0;
                    for (double s : scores) {
                        if (s >= avg) countAbove++;
                    }
                    System.out.printf("Điểm trung bình: %.2f\n", avg);
                    System.out.printf("Điểm cao nhất: %.1f\n", max);
                    System.out.printf("Điểm thấp nhất: %.1f\n", min);
                    System.out.println("Số sinh viên đạt điểm >= trung bình: " + countAbove);
                    break;
                case 5:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 5);
    }

    /**
     * Bài 6: Quản lý lương nhân viên
     */
    public static void exercise6(Scanner scanner) {
        System.out.print("Nhập số lượng nhân viên: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Vui lòng nhập một số nguyên!");
            scanner.next();
        }
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Số lượng nhân viên phải lớn hơn 0!");
            return;
        }

        double[] salaries = new double[n];
        System.out.println("Nhập số lượng nhân viên và lương nhân viên:");
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập lương nhân viên " + (i + 1) + ": ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Vui lòng nhập lương hợp lệ!");
                scanner.next();
            }
            salaries[i] = scanner.nextDouble();
        }

        int choice;
        boolean isSortedAsc = false;

        do {
            System.out.println("\n--- QUẢN LÝ LƯƠNG NHÂN VIÊN ---");
            System.out.println("1. Xem danh sách lương");
            System.out.println("2. Sắp xếp lương");
            System.out.println("3. Tìm kiếm lương cụ thể");
            System.out.println("4. Thống kê lương");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số nguyên!");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Danh sách lương nhân viên:");
                    for (int i = 0; i < n; i++) {
                        System.out.printf("Nhân viên %d: %.1f\n", (i + 1), salaries[i]);
                    }
                    break;
                case 2:
                    System.out.println("Chọn cách sắp xếp:");
                    System.out.println("1. Tăng dần");
                    System.out.println("2. Giảm dần");
                    int sortType = scanner.nextInt();
                    if (sortType == 1) {
                        // Selection Sort Tăng dần
                        for (int i = 0; i < n - 1; i++) {
                            int minIdx = i;
                            for (int j = i + 1; j < n; j++) {
                                if (salaries[j] < salaries[minIdx]) minIdx = j;
                            }
                            double temp = salaries[minIdx];
                            salaries[minIdx] = salaries[i];
                            salaries[i] = temp;
                        }
                        isSortedAsc = true;
                        System.out.println("Đã sắp xếp lương tăng dần.");
                    } else if (sortType == 2) {
                        // Selection Sort Giảm dần
                        for (int i = 0; i < n - 1; i++) {
                            int maxIdx = i;
                            for (int j = i + 1; j < n; j++) {
                                if (salaries[j] > salaries[maxIdx]) maxIdx = j;
                            }
                            double temp = salaries[maxIdx];
                            salaries[maxIdx] = salaries[i];
                            salaries[i] = temp;
                        }
                        isSortedAsc = false;
                        System.out.println("Đã sắp xếp lương giảm dần.");
                    } else {
                        System.out.println("Lựa chọn không hợp lệ.");
                    }
                    break;
                case 3:
                    System.out.print("Nhập lương cần tìm: ");
                    double target = scanner.nextDouble();
                    
                    // Linear Search
                    int linearIdx = -1;
                    for (int i = 0; i < n; i++) {
                        if (salaries[i] == target) {
                            linearIdx = i;
                            break;
                        }
                    }
                    if (linearIdx != -1) {
                        System.out.println("Linear Search: Tìm thấy tại vị trí " + linearIdx);
                    } else {
                        System.out.println("Linear Search: Không tìm thấy");
                    }

                    // Binary Search (Chỉ khi mảng tăng dần theo yêu cầu đề bài)
                    if (isSortedAsc) {
                        int low = 0, high = n - 1;
                        int binaryIdx = -1;
                        while (low <= high) {
                            int mid = low + (high - low) / 2;
                            if (salaries[mid] == target) {
                                binaryIdx = mid;
                                break;
                            }
                            if (salaries[mid] < target) low = mid + 1;
                            else high = mid - 1;
                        }
                        if (binaryIdx != -1) System.out.println("Binary Search (mảng tăng dần): Tìm thấy tại vị trí " + binaryIdx);
                        else System.out.println("Binary Search (mảng tăng dần): Không tìm thấy");
                    } else {
                        System.out.println("Binary Search (mảng tăng dần): Chỉ thực hiện được khi mảng đã sắp xếp tăng dần.");
                    }
                    break;
                case 4:
                    double total = 0;
                    double maxS = salaries[0];
                    double minS = salaries[0];
                    for (double s : salaries) {
                        total += s;
                        if (s > maxS) maxS = s;
                        if (s < minS) minS = s;
                    }
                    double avgS = total / n;
                    int countAboveAvg = 0;
                    for (double s : salaries) {
                        if (s > avgS) countAboveAvg++;
                    }
                    System.out.println("Tổng lương: " + total);
                    System.out.println("Lương trung bình: " + avgS);
                    System.out.println("Lương cao nhất: " + maxS);
                    System.out.println("Lương thấp nhất: " + minS);
                    System.out.println("Số nhân viên có lương trên trung bình: " + countAboveAvg);
                    break;
                case 5:
                    System.out.println("Thoát chương trình quản lý lương.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 5);
    }
}
