import Class.Book;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book book = new Book();  // Chỉ để test
        book.title = "Java Programming";
        book.author = "John Doe";
        book.price = 29.99;
        book.printInfo();
    }
}