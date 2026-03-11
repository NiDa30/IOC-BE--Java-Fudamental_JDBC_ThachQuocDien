import Class.Student;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(1, "Nguyen Van A", 20);

        System.out.println("ID: " + s1.id + ", Name: " + s1.name + ", Age: " + s1.age);
    }
}