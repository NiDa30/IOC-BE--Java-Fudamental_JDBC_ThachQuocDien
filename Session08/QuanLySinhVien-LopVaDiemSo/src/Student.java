import java.util.Scanner;

public class Student {

    private int id;
    private String name;
    private double gpa;

    // static đếm số sinh viên
    private static int countStudent = 0;

    // hằng số
    final double SCORE_FACTOR = 0.25;

    // constructor không tham số
    public Student() {
        countStudent++;
    }

    // constructor có tham số
    public Student(int id, String name, double gpa) {
        this(); // gọi constructor không tham số
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // nhập thông tin
    public void input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập ID: ");
        id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhập tên: ");
        name = sc.nextLine();

        System.out.print("Nhập GPA: ");
        gpa = sc.nextDouble();
    }

    // in thông tin
    public void print() {
        System.out.println("ID: " + id +
                ", Name: " + name +
                ", GPA: " + gpa);
    }

    // getter GPA
    public double getGpa() {
        return gpa;
    }

    // tổng số sinh viên
    public static int getTotalStudent() {
        return countStudent;
    }
}