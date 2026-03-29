public class InvalidPhoneNumberLengthException extends Exception {
    public InvalidPhoneNumberLengthException(String message) {
        super(message);
    }

    /**
     * Phương thức kiểm tra tính hợp lệ của số điện thoại.
     * Tự định nghĩa trong lớp Exception theo yêu cầu đề bài.
     */
    public static void validatePhoneNumber(String phone) throws InvalidPhoneNumberLengthException {
        // Kiểm tra chứa khoảng trắng
        if (phone.contains(" ")) {
            throw new InvalidPhoneNumberLengthException("Không được chứa khoảng trắng");
        }

        // Kiểm tra độ dài đúng 10 chữ số
        if (phone.length() != 10) {
            throw new InvalidPhoneNumberLengthException("Sai độ dài (phải đúng 10 chữ số)");
        }

        // Kiểm tra chứa ký tự không hợp lệ (không phải 0-9)
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new InvalidPhoneNumberLengthException("Chứa ký tự không hợp lệ (chỉ được phép chứa 0-9)");
            }
        }
    }
}
