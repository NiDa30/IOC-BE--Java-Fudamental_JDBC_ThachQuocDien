package Class;

public class Book {
    public String title;
    public String author;
    public double price;

    // Phương thức public
    public void printInfo() {
        System.out.println("Book: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
    }
}
