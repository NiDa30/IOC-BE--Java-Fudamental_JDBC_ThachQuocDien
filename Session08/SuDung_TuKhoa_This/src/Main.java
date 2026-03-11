//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("Java Programming", "Nguyen Van A", 120.5);

        System.out.println(
                "Tựa sách: " + b1.title +
                        ", Tác giả: " + b1.author +
                        ", Giá: " + b1.price
        );
    }
}