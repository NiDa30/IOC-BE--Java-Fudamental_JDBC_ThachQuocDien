
public class Rectangle {
    private double width;
    private double height;

    public Rectangle() {
    }

    //constructor
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Getter and Setter for width
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    // Getter and Setter for height
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Calculates the area of the rectangle.
     * @return width * height
     */
    public double getArea() {
        return width * height;
    }

    /**
     * Calculates the perimeter of the rectangle.
     * @return (width + height) * 2
     */
    public double getPerimeter() {
        return (width + height) * 2;
    }

    public void printInfo() {
        System.out.println("--- Thông tin hình chữ nhật ---");
        System.out.printf("Chiều rộng:  %.2f\n", width);
        System.out.printf("Chiều cao: %.2f\n", height);
        System.out.printf("Diện tích:   %.2f\n", getArea());
        System.out.printf("Chu vi: %.2f\n", getPerimeter());
        System.out.println("-----------------------------");
    }
}
