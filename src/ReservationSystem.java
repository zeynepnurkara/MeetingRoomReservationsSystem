import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationManager<Room> manager = new ReservationManager<>();

        // Odaları ekliyoruz
        manager.addRoom(new Room("Basic Room", 10)); // Basic Room
        manager.addRoom(new StudyRoom("Study Room", 3, true)); // StudyRoom
        manager.addRoom(new ConferenceRoom("Conference Room", 2, true)); // ConferenceRoom

        boolean running = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (running) {
            System.out.println("\n1. Display Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Display Reservations");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Display Rooms - JTextArea kullanarak
                    JTextArea roomsTextArea = new JTextArea();
                    roomsTextArea.setEditable(false);
                    manager.displayRooms(roomsTextArea);
                    JOptionPane.showMessageDialog(null, new JScrollPane(roomsTextArea),
                            "Rooms", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2:
                    // Make Reservation
                    System.out.print("Enter Room ID: ");
                    String roomId = scanner.nextLine();
                    System.out.print("Enter Your Name: ");
                    String user = scanner.nextLine();
                    System.out.print("Enter Reservation Date and Time (yyyy-MM-dd HH:mm): ");
                    String dateTimeInput = scanner.nextLine();
                    try {
                        LocalDateTime time = LocalDateTime.parse(dateTimeInput, formatter);
                        manager.makeReservation(roomId, user, time);
                    } catch (Exception e) {
                        System.out.println("Invalid date format! Please try again.");
                    }
                    break;
                case 3:
                    // Cancel Reservation
                    System.out.print("Enter Room ID: ");
                    String cancelRoomId = scanner.nextLine();
                    System.out.print("Enter Your Name: ");
                    String cancelUser = scanner.nextLine();
                    System.out.print("Enter Reservation Date and Time (yyyy-MM-dd HH:mm): ");
                    String cancelDateTimeInput = scanner.nextLine();
                    try {
                        LocalDateTime cancelTime = LocalDateTime.parse(cancelDateTimeInput, formatter);
                        manager.cancelReservation(cancelRoomId, cancelUser, cancelTime);
                    } catch (Exception e) {
                        System.out.println("Invalid date format! Please try again.");
                    }
                    break;
                case 4:
                    // Display Reservations - JTextArea kullanarak
                    JTextArea reservationsTextArea = new JTextArea();
                    reservationsTextArea.setEditable(false);
                    manager.displayReservations(reservationsTextArea);
                    JOptionPane.showMessageDialog(null, new JScrollPane(reservationsTextArea),
                            "Reservations", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
