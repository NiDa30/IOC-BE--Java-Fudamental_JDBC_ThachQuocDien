import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create 3 Book objects
        Book book1 = new Book("Java", "James", 100.0);
        Book book2 = new Book("Python", "Guido", 120.0);
        Book book3 = new Book("C++", "Bjarne", 150.0);

        // Create a list to store books
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        // Print header
        System.out.println("------ LIST OF BOOKS ------");

        // Iterate through the list and print info
        for (Book book : bookList) {
            book.printInfo();
        }
    }
}