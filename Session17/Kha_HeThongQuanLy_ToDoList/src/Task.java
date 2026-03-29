public class Task {
    private int id;
    private String taskName;
    private String status;

    public Task(int id, String taskName, String status) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTaskName() { return taskName; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Tên: %-30s | Trạng thái: %-20s", id, taskName, status);
    }
}
