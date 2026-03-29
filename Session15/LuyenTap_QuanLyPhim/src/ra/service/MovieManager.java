package ra.service;

import java.util.ArrayList;
import java.util.List;

public class MovieManager<T> {
    private List<T> list;

    public MovieManager() {
        this.list = new ArrayList<>();
    }

    public void add(T item) {
        list.add(item);
    }

    public boolean remove(T item) {
        return list.remove(item);
    }

    public List<T> getAll() {
        return list;
    }

    public int size() {
        return list.size();
    }

    public T get(int index) {
        return list.get(index);
    }
}
