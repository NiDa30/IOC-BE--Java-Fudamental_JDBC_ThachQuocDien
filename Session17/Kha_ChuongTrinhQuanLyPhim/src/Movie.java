public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;

    public Movie(int id, String title, String director, int year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return String.format("ID: %d | Tiêu đề: %-25s | Đạo diễn: %-15s | Năm: %d", id, title, director, year);
    }
}
