public class Book {
    private String title;
    private String author;
    private double price;

    // Constructor to initialize Book object
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Method to print book information
    public void printInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
        System.out.println(); // Add a blank line for better readability
    }
}
