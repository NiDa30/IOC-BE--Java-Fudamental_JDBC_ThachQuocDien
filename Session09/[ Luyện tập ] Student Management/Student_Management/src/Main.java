
public class Main {
    public static void main(String[] args) {
        // Create an array to manage students
        Student[] students = new Student[3];
        students[0] = new Student(1, "Tran Van A", 20, 3.5);
        students[1] = new Student(2, "Le Thi B", 21, 3.8);
        students[2] = new Student(3, "Nguyen Van C", 19, 3.2);

        // Display info using a loop
        System.out.println("Thông tin sinh viên: ");
        for (Student s : students) {
            s.printInfo();
        }

        // Print total number of students
        System.out.println("------------------------------");
        System.out.println("Tổng số sinh viên: " + Student.getCount());
    }
}