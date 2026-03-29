public abstract class Shape {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public void displayInfo() {
        System.out.println("Shape: " + name);
    }
}
