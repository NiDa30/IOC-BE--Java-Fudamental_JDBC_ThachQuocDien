import java.util.ArrayList;
import java.util.List;

public class PhoneNumberValidator {

    /**
     * Phương thức chính thực hiện logic kiểm tra danh sách số điện thoại.
     * Tách logic nghiệp vụ ra khỏi hàm Main giúp mã nguồn dễ quản lý hơn.
     */
    public void validateList(String input) {
        List<String> validPhoneNumbers = new ArrayList<>();
        List<String> invalidPhoneNumbers = new ArrayList<>();

        // Tách chuỗi theo dấu phẩy
        String[] rawNumbers = input.split(",");

        for (String raw : rawNumbers) {
            String phone = raw.trim();
            if (phone.isEmpty()) continue;

            try {
                // Gọi kiểm tra tính hợp lệ
                InvalidPhoneNumberLengthException.validatePhoneNumber(phone);
                validPhoneNumbers.add(phone);
            } catch (InvalidPhoneNumberLengthException e) {
                // Lưu lý do không hợp lệ
                invalidPhoneNumbers.add(phone + " : " + e.getMessage());
            }
        }

        // Hiển thị báo cáo kết quả
        printReport(validPhoneNumbers, invalidPhoneNumbers);
    }

    private void printReport(List<String> valid, List<String> invalid) {
        System.out.println("\n-------------------------------------------");
        System.out.println("SỐ ĐIỆN THOẠI HỢP LỆ:");
        if (valid.isEmpty()) System.out.println("(Trống)");
        else valid.forEach(v -> System.out.println("- " + v));

        System.out.println("\nSỐ ĐIỆN THOẠI KHÔNG HỢP LỆ:");
        if (invalid.isEmpty()) System.out.println("(Trống)");
        else invalid.forEach(inv -> System.out.println("- " + inv));
        System.out.println("-------------------------------------------");
    }
}
