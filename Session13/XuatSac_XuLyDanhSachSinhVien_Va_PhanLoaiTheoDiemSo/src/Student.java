public class Student {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getRank() {
        if (gpa >= 8.5) return "Xuất sắc";
        if (gpa >= 7.0) return "Giỏi";
        if (gpa >= 5.5) return "Khá";
        return "Trung bình / Yếu";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tên: " + name + ", GPA: " + gpa + ", Xếp loại: " + getRank();
    }
}
