package Gioi_XayDungChatDonGian;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChatApplication {
    static List<Message> messageList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Nhập tên người gửi (hoặc 'exit' để thoát):");
            String sender = scanner.nextLine().trim();

            if (sender.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Nhập nội dung tin nhắn:");
            String content = scanner.nextLine().trim();

            messageList.add(new Message(sender, content));

            System.out.println("Nhập 'history' để xem lịch sử, hoặc 'filter' để lọc tin nhắn theo người gửi, hoặc 'date' để lọc theo ngày:");
            String response = scanner.nextLine().trim().toLowerCase();

            switch (response) {
                case "history":
                    displayHistory();
                    break;
                case "filter":
                    filterBySender();
                    break;
                case "date":
                    filterByDate();
                    break;
                default:
                    // If something else or empty, just proceed to next message
                    break;
            }
        }
    }

    private static void displayHistory() {
        System.out.println("Lịch sử chat:");
        if (messageList.isEmpty()) {
            System.out.println("No messages yet.");
        } else {
            messageList.forEach(System.out::println);
        }
    }

    private static void filterBySender() {
        System.out.println("Nhập tên người gửi để lọc:");
        String senderToFilter = scanner.nextLine().trim();
        System.out.println("Tin nhắn từ " + senderToFilter + ":");
        List<Message> filtered = messageList.stream()
                .filter(m -> m.getSender().equalsIgnoreCase(senderToFilter))
                .collect(Collectors.toList());
        
        if (filtered.isEmpty()) {
            System.out.println("Không tìm thấy tin nhắn nào.");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    private static void filterByDate() {
        System.out.println("Nhập ngày (dd-MM-yyyy):");
        String dateStr = scanner.nextLine().trim();
        try {
            LocalDate targetDate = LocalDate.parse(dateStr, dateFormatter);
            System.out.println("Tin nhắn trong ngày " + targetDate + ":");
            List<Message> filtered = messageList.stream()
                    .filter(m -> m.getTimestamp().toLocalDate().isEqual(targetDate))
                    .collect(Collectors.toList());
            
            if (filtered.isEmpty()) {
                System.out.println("Không có tin nhắn nào trong ngày này.");
            } else {
                filtered.forEach(System.out::println);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Định dạng ngày không hợp lệ. Hãy sử dụng dd-MM-yyyy.");
        }
    }
}
