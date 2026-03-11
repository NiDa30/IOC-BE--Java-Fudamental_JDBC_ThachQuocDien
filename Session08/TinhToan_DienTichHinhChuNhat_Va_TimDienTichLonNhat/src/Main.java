//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(3, 4);
        Rectangle r2 = new Rectangle(5, 2);
        Rectangle r3 = new Rectangle(4.5, 3.5);

        System.out.println("Rectangle 1: " + r1);
        System.out.println("Rectangle 2: " + r2);
        System.out.println("Rectangle 3: " + r3);

        // tìm diện tích lớn nhất
        Rectangle largest = r1;

        if (r2.getArea() > largest.getArea()) {
            largest = r2;
        }

        if (r3.getArea() > largest.getArea()) {
            largest = r3;
        }

        System.out.println("\nLargest area = " + largest.getArea() + " (" + largest + ")");
    }

}