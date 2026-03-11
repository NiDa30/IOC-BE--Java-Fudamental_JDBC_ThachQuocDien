import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n========== MENU BÀI TẬP SESSION 03 ==========");
            System.out.println("1. Bài tập 1: Quản lý điểm SV");
            System.out.println("2. Bài tập 2: Quản lý người dùng");
            System.out.println("3. Bài tập 3: Quản lý biển số xe");
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

    // ================= BÀI 1: QUẢN LÝ ĐIỂM SV =================
    private static void exercise01(Scanner scanner) {
        List<Double> diemSV = new ArrayList<>();
        int choice;

        while (true) {
            System.out.println("\n*** *************QUẢN LÝ ĐIỂM SV****************");
            System.out.println("1. Nhập danh sách điểm sinh viên");
            System.out.println("2. In danh sách điểm");
            System.out.println("3. Tính điểm trung bình của các sinh viên");
            System.out.println("4. Tìm điểm cao nhất và thấp nhất");
            System.out.println("5. Đếm số lượng sinh viên đạt và trượt");
            System.out.println("6. Sắp xếp điểm tăng dần");
            System.out.println("7. Thống kê số lượng sinh viên giỏi và xuất sắc");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên.");
                continue;
            }

            switch (choice) {
                case 1:
                    nhapDiem(scanner, diemSV);
                    break;
                case 2:
                    inDanhSachDiem(diemSV);
                    break;
                case 3:
                    tinhTB(diemSV);
                    break;
                case 4:
                    timMaxMin(diemSV);
                    break;
                case 5:
                    demDatTruot(diemSV);
                    break;
                case 6:
                    sapXepTangDiem(diemSV);
                    break;
                case 7:
                    thongKeGioiXuatSac(diemSV);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void nhapDiem(Scanner scanner, List<Double> diemSV) {
        System.out.print("Nhập số lượng sinh viên: ");
        int n;
        try {
            n = Integer.parseInt(scanner.nextLine());
            if (n <= 0) {
                System.out.println("Số lượng phải > 0!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Nhập số nguyên!");
            return;
        }
        diemSV.clear();
        for (int i = 0; i < n; i++) {
            System.out.print("Điểm SV " + (i + 1) + " (0-10): ");
            double diem;
            try {
                diem = Double.parseDouble(scanner.nextLine());
                if (diem < 0 || diem > 10) {
                    System.out.println("Điểm phải từ 0-10!");
                    i--;
                    continue;
                }
                diemSV.add(diem);
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Nhập số thực!");
                i--;
            }
        }
        System.out.println("Nhập thành công " + n + " điểm!");
    }

    private static void inDanhSachDiem(List<Double> diemSV) {
        if (diemSV.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        System.out.println("Danh sách điểm:");
        for (int i = 0; i < diemSV.size(); i++) {
            System.out.println((i + 1) + ". " + diemSV.get(i));
        }
    }

    private static void tinhTB(List<Double> diemSV) {
        if (diemSV.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        double tb = diemSV.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        System.out.printf("Điểm trung bình: %.2f\n", tb);
    }

    private static void timMaxMin(List<Double> diemSV) {
        if (diemSV.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        double max = Collections.max(diemSV);
        double min = Collections.min(diemSV);
        System.out.printf("Điểm cao nhất: %.2f\n", max);
        System.out.printf("Điểm thấp nhất: %.2f\n", min);
    }

    private static void demDatTruot(List<Double> diemSV) {
        if (diemSV.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        long dat = diemSV.stream().mapToDouble(d -> d).filter(d -> d >= 5).count();
        long truot = diemSV.size() - dat;
        System.out.println("Số SV đạt: " + dat);
        System.out.println("Số SV trượt: " + truot);
    }

    private static void sapXepTangDiem(List<Double> diemSV) {
        if (diemSV.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        Collections.sort(diemSV);
        System.out.println("Đã sắp xếp tăng dần!");
        inDanhSachDiem(diemSV);
    }

    private static void thongKeGioiXuatSac(List<Double> diemSV) {
        if (diemSV.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        long gioiXuatSac = diemSV.stream().mapToDouble(d -> d).filter(d -> d >= 8).count();
        System.out.println("Số SV giỏi/xuất sắc (>=8): " + gioiXuatSac);
    }

    // ================= BÀI 2: QUẢN LÝ NGƯỜI DÙNG =================
    private static void exercise02(Scanner scanner) {
        while (true) {
            System.out.println("\n*** QUẢN LÝ NGƯỜI DÙNG **********");
            System.out.println("1. Nhập thông tin người dùng");
            System.out.println("2. Chuẩn hóa họ tên");
            System.out.println("3. Kiểm tra email hợp lệ");
            System.out.println("4. Kiểm tra số điện thoại hợp lệ");
            System.out.println("5. Kiểm tra mật khẩu hợp lệ");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        nhapThongTin(scanner);
                        break;
                    case 2:
                        System.out.print("Nhập họ tên cần chuẩn hóa: ");
                        System.out.println("Họ tên chuẩn hóa: " + chuanHoaHoTen(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.print("Nhập email cần kiểm tra: ");
                        System.out.println("Email " + (kiemTraEmail(scanner.nextLine()) ? "HỢP LỆ" : "KHÔNG HỢP LỆ"));
                        break;
                    case 4:
                        System.out.print("Nhập số điện thoại cần kiểm tra: ");
                        System.out.println("SĐT " + (kiemTraSDT(scanner.nextLine()) ? "HỢP LỆ" : "KHÔNG HỢP LỆ"));
                        break;
                    case 5:
                        System.out.print("Nhập mật khẩu cần kiểm tra: ");
                        kiemTraMatKhau(scanner.nextLine(), scanner);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }

    private static void nhapThongTin(Scanner scanner) {
        System.out.println("\n--- NHẬP THÔNG TIN NGƯỜI DÙNG ---");
        System.out.print("Họ và tên: ");
        String hoTen = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Số điện thoại: ");
        String sdt = scanner.nextLine();
        System.out.print("Mật khẩu: ");
        String matKhau = scanner.nextLine();

        System.out.println("\n--- KẾT QUẢ KIỂM TRA ---");
        System.out.println("Họ tên chuẩn hóa: " + chuanHoaHoTen(hoTen));
        System.out.println("Email " + email + ": " + (kiemTraEmail(email) ? "HỢP LỆ" : "KHÔNG HỢP LỆ"));
        System.out.println("SĐT " + sdt + ": " + (kiemTraSDT(sdt) ? "HỢP LỆ" : "KHÔNG HỢP LỆ"));
        kiemTraMatKhau(matKhau, scanner);
    }

    private static String chuanHoaHoTen(String hoTen) {
        StringBuilder sb = new StringBuilder(hoTen.trim());
        while (sb.indexOf("  ") != -1) {
            sb = new StringBuilder(sb.toString().replace("  ", " "));
        }
        String[] words = sb.toString().split(" ");
        sb.setLength(0);
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return sb.toString().trim();
    }

    private static boolean kiemTraEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean kiemTraSDT(String sdt) {
        String sdtRegex = "^0[35789]\\d{8}$";
        Pattern pattern = Pattern.compile(sdtRegex);
        Matcher matcher = pattern.matcher(sdt);
        return matcher.matches();
    }

    private static void kiemTraMatKhau(String matKhau, Scanner scanner) {
        StringBuilder feedback = new StringBuilder();
        if (matKhau.length() < 8) feedback.append("❌ Độ dài < 8\n");
        if (!matKhau.matches(".*[a-z].*")) feedback.append("❌ Thiếu chữ thường\n");
        if (!matKhau.matches(".*[A-Z].*")) feedback.append("❌ Thiếu chữ hoa\n");
        if (!matKhau.matches(".*\\d.*")) feedback.append("❌ Thiếu số\n");
        if (!matKhau.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) feedback.append("❌ Thiếu ký tự đặc biệt\n");

        if (feedback.length() == 0) {
            System.out.println("✅ MẬT KHẨU HỢP LỆ!");
        } else {
            System.out.println("❌ MẬT KHẨU KHÔNG HỢP LỆ:\n" + feedback);
        }
    }

    // ================= BÀI 3: QUẢN LÝ BIỂN SỐ XE =================
    private static void exercise03(Scanner scanner) {
        List<String> danhSachBienSo = new ArrayList<>();

        while (true) {
            System.out.println("\n** QUẢN LÝ BIỂN SỐ XE ***");
            System.out.println("1. Thêm các biển số xe");
            System.out.println("2. Hiển thị danh sách biển số xe");
            System.out.println("3. Tìm kiếm biển số xe");
            System.out.println("4. Tìm biển số xe theo mã tỉnh");
            System.out.println("5. Sắp xếp biển số xe tăng dần");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        themBienSo(scanner, danhSachBienSo);
                        break;
                    case 2:
                        hienThiDanhSachBienSo(danhSachBienSo);
                        break;
                    case 3:
                        timKiemChinhXac(scanner, danhSachBienSo);
                        break;
                    case 4:
                        timTheoMaTinh(scanner, danhSachBienSo);
                        break;
                    case 5:
                        sapXepTangBienSo(danhSachBienSo);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }

    private static void themBienSo(Scanner scanner, List<String> danhSach) {
        System.out.print("Nhập số lượng biển số xe: ");
        int n;
        try {
            n = Integer.parseInt(scanner.nextLine());
            if (n <= 0) {
                System.out.println("Số lượng phải > 0!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Nhập số nguyên!");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Biển số xe " + (i + 1) + " (30F-123.45): ");
            String bienSo = scanner.nextLine();
            if (kiemTraDinhDangBienSo(bienSo)) {
                danhSach.add(bienSo);
            } else {
                System.out.println("❌ Sai định dạng! Ví dụ: 30F-123.45");
                i--;
            }
        }
        System.out.println("✅ Thêm thành công " + n + " biển số!");
    }

    private static boolean kiemTraDinhDangBienSo(String bienSo) {
        String regex = "^\\d{2}[A-Z]-\\d{3}\\.\\d{2}$";
        return Pattern.matches(regex, bienSo);
    }

    private static void hienThiDanhSachBienSo(List<String> danhSach) {
        if (danhSach.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        System.out.println("\nDanh sách biển số xe:");
        for (int i = 0; i < danhSach.size(); i++) {
            System.out.println((i + 1) + ". " + danhSach.get(i));
        }
    }

    private static void timKiemChinhXac(Scanner scanner, List<String> danhSach) {
        if (danhSach.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        System.out.print("Nhập biển số cần tìm: ");
        String timKiem = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).equalsIgnoreCase(timKiem)) {
                System.out.println("✅ Tìm thấy: " + danhSach.get(i) + " (vị trí " + (i + 1) + ")");
                found = true;
                break;
            }
        }
        if (!found) System.out.println("❌ Không tìm thấy!");
    }

    private static void timTheoMaTinh(Scanner scanner, List<String> danhSach) {
        if (danhSach.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        System.out.println("Mã tỉnh: 29/30=HN, 51/52=HCM, 16/17=TB");
        System.out.print("Nhập mã tỉnh (2 số): ");
        String maTinh = scanner.nextLine();

        List<String> ketQua = new ArrayList<>();
        for (String bienSo : danhSach) {
            if (bienSo.startsWith(maTinh)) ketQua.add(bienSo);
        }

        if (ketQua.isEmpty()) {
            System.out.println("❌ Không có biển " + maTinh);
        } else {
            System.out.println("✅ " + ketQua.size() + " biển " + maTinh + ":");
            for (int i = 0; i < ketQua.size(); i++) {
                System.out.println((i + 1) + ". " + ketQua.get(i));
            }
        }
    }

    private static void sapXepTangBienSo(List<String> danhSach) {
        if (danhSach.isEmpty()) {
            System.out.println("Chưa có dữ liệu!");
            return;
        }
        Collections.sort(danhSach, (bs1, bs2) -> {
            String tinh1 = bs1.substring(0, 2);
            String tinh2 = bs2.substring(0, 2);
            int cmp = tinh1.compareTo(tinh2);
            if (cmp != 0) return cmp;
            char kh1 = bs1.charAt(2);
            char kh2 = bs2.charAt(2);
            if (kh1 != kh2) return Character.compare(kh1, kh2);
            return bs1.substring(bs1.indexOf('-') + 1).compareTo(bs2.substring(bs2.indexOf('-') + 1));
        });
        System.out.println("✅ Đã sắp xếp tăng dần!");
        hienThiDanhSachBienSo(danhSach);
    }
}
