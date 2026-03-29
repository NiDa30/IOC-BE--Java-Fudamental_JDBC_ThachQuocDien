package Kha_SuDungDateTimeAPI_De_QuanLySuKien;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventManager {
    static List<Event> eventList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Nhập tên sự kiện (hoặc 'exit' để thoát):");
            String name = scanner.nextLine().trim();

            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            if (name.isEmpty()) {
                System.out.println("Can not enter empty string");
                continue;
            }

            LocalDateTime startDate = readDateTime("Nhập thời gian bắt đầu (dd-MM-yyyy HH:mm):");
            LocalDateTime endDate = readDateTime("Nhập thời gian kết thúc (dd-MM-yyyy HH:mm):");

            if (endDate.isBefore(startDate)) {
                System.out.println("Lỗi: Thời gian kết thúc không thể trước thời gian bắt đầu.");
                continue;
            }

            eventList.add(new Event(name, startDate, endDate));
        }

        if (eventList.isEmpty()) {
            System.out.println("Danh sách sự kiện trống.");
        } else {
            System.out.println("Danh sách sự kiện:");
            for (Event event : eventList) {
                System.out.println(event.toString());
            }
        }
    }

    private static LocalDateTime readDateTime(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Enter not valid date");
            }
        }
    }
}
