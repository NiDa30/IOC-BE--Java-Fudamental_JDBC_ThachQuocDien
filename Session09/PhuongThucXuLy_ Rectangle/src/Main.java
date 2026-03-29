import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Phương thức xử lý (Rectangle) ===");

        // Get width from user
        System.out.print("Nhập chiều rộng: ");
        double width = getValidDouble(scanner);

        // Get height from user
        System.out.print("Nhập chiều cao: ");
        double height = getValidDouble(scanner);

        // Create Rectangle object
        Rectangle myRectangle = new Rectangle(width, height);

        // Display results
        System.out.println("\nKết quả:");
        System.out.printf("Diện tích hình chữ nhật:      %.2f\n", myRectangle.getArea());
        System.out.printf("Chu vi hình chữ nhật: %.2f\n", myRectangle.getPerimeter());

        // Call optional printInfo method
        System.out.println();
        myRectangle.printInfo();

        scanner.close();
    }

    /**
     * Utility method to ensure valid double input
     */
    private static double getValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Vui lòng nhập vào một số.");        
            scanner.next(); 
            System.out.print("Thử lại: ");
        }
        double value = scanner.nextDouble();
        if (value <= 0) {
            System.out.print("Giá trị phải lớn hơn 0. Thử lại: ");
            return getValidDouble(scanner);
        }
        return value;
    }
}