public class Student {
    private int id;
    private String fullName;
    private int age;
    private double gpa;

    private static int count = 0;

    public final double MIN_GPA = 0.0;
    public final double MAX_GPA = 4.0;

    public Student(int id, String fullName, int age, double gpa) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gpa = gpa;
        count++;
    }

    public void printInfo() {
        System.out.println("------------------------------");
        System.out.println("Mã sinh viên: " + id);
        System.out.println("Họ và tên: " + fullName);
        System.out.println("Tuổi: " + age);
        System.out.println("Điểm trung bình: " + gpa);
    }

    public static int getCount() {
        return count;
    }
}
