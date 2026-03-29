import java.math.BigDecimal;

public class Book {
    private int id;
    private String title;
    private String author;
    private int publishedYear;
    private BigDecimal price;

    public Book() {}

    public Book(int id, String title, String author, int publishedYear, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getPublishedYear() { return publishedYear; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("ID: %-4d | Title: %-30s | Author: %-20s | Year: %-4d | Price: $%.2f",
                id, title, author, publishedYear, price);
    }
}
