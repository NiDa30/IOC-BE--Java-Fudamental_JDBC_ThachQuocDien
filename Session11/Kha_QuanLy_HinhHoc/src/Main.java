public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        shapes[0] = new Rectangle("R1", 10.5, 5.0);
        shapes[1] = new Circle("C1", 4.5);

        for (Shape shape : shapes) {
            shape.displayInfo();
            System.out.println("Area: " + shape.getArea());
            System.out.println("Perimeter: " + shape.getPerimeter());

            if (shape instanceof Drawable) {
                ((Drawable) shape).draw();
            }
            System.out.println("-------------------------");
        }
    }
}