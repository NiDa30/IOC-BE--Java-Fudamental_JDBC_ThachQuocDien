import java.util.ArrayList;
import java.util.List;

public class AttendanceManager implements Manage<Student> {
    private List<Student> students = new ArrayList<>();

    @Override
    public void add(Student item) {
        students.add(item);
    }

    @Override
    public void update(int index, Student item) {
        if (index >= 0 && index < students.size()) {
            students.set(index, item);
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
        }
    }

    @Override
    public void display() {
        if (students.isEmpty()) {
            System.out.println("(Danh sách điểm danh trống)");
        } else {
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i).toString());
            }
        }
    }

    // Helper method to find index by ID
    public int findIndexById(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}
