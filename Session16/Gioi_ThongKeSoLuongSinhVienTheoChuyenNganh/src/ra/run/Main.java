package ra.run;

import ra.entity.Student;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize student list
        List<Student> students = Arrays.asList(
            new Student("Nguyen Van A", "IT", 8.5),
            new Student("Le Van B", "Kinh te", 7.0),
            new Student("Tran Thi C", "IT", 9.0),
            new Student("Pham Van D", "Marketing", 6.5),
            new Student("Hoang Van E", "IT", 8.0),
            new Student("Nguyen Thi F", "Kinh te", 8.8),
            new Student("Do Van G", "Marketing", 7.5),
            new Student("Bui Van H", "IT", 9.5),
            new Student("Vu Thi I", "Ngon ngu Anh", 8.2),
            new Student("Phan Van K", "IT", 7.8),
            new Student("Dinh Van L", "Kinh te", 6.0)
        );

        // 2. Statistics by major
        Map<String, Long> majorCount = students.stream()
                .collect(Collectors.groupingBy(Student::getMajor, Collectors.counting()));

        // 3. Sort and print entries
        System.out.println("--- THỐNG KÊ SINH VIÊN THEO CHUYÊN NGÀNH ---");
        majorCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> 
                    System.out.println("Chuyên ngành: " + entry.getKey() + " - Số lượng: " + entry.getValue())
                );
    }
}
