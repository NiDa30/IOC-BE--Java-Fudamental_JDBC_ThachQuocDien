package ra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SubjectManager<T> {
    private List<T> list;

    public SubjectManager() {
        this.list = new ArrayList<>();
    }

    public void add(T item) {
        list.add(item);
    }

    public boolean remove(Predicate<T> condition) {
        return list.removeIf(condition);
    }

    public List<T> getAll() {
        return list;
    }

    public Optional<T> findFirst(Predicate<T> condition) {
        return list.stream().filter(condition).findFirst();
    }

    public List<T> filter(Predicate<T> condition) {
        return list.stream().filter(condition).collect(Collectors.toList());
    }

    public void display() {
        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
        } else {
            list.forEach(System.out::println);
        }
    }
}
