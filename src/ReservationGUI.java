import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationGUI {
    private JFrame frame;
    private ReservationManager<Room> manager;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ReservationGUI() {
        manager = new ReservationManager<>();
        initializeRooms();
        initializeUI();
    }

    private void initializeRooms() {
        // Odaları ekliyoruz
        manager.addRoom(new Room("Basic Room", 10));
        manager.addRoom(new StudyRoom("Study Room", 3, true));
        manager.addRoom(new ConferenceRoom("Conference Room", 2, true));
    }

    private void initializeUI() {
        frame = new JFrame("Meeting Room Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 1));

        JButton displayRoomsButton = new JButton("Display Rooms");
        JButton makeReservationButton = new JButton("Make Reservation");
        JButton cancelReservationButton = new JButton("Cancel Reservation");
        JButton displayReservationsButton = new JButton("Display Reservations");
        JButton exitButton = new JButton("Exit");

        panel.add(displayRoomsButton);
        panel.add(makeReservationButton);
        panel.add(cancelReservationButton);
        panel.add(displayReservationsButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);

        displayRoomsButton.addActionListener(e -> displayRooms());
        makeReservationButton.addActionListener(e -> makeReservation());
        cancelReservationButton.addActionListener(e -> cancelReservation());
        displayReservationsButton.addActionListener(e -> displayReservations());
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private void displayRooms() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        manager.displayRooms(textArea);
        JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "Rooms", JOptionPane.INFORMATION_MESSAGE);
    }

    private void makeReservation() {
        JTextField roomIdField = new JTextField();
        JTextField userField = new JTextField();
        JTextField timeField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Room ID:"));
        panel.add(roomIdField);
        panel.add(new JLabel("Your Name:"));
        panel.add(userField);
        panel.add(new JLabel("Reservation Time (yyyy-MM-dd HH:mm):"));
        panel.add(timeField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Make Reservation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String roomId = roomIdField.getText();
            String user = userField.getText();
            String timeInput = timeField.getText();

            try {
                LocalDateTime time = LocalDateTime.parse(timeInput, formatter);
                manager.makeReservation(roomId, user, time);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cancelReservation() {
        JTextField roomIdField = new JTextField();
        JTextField userField = new JTextField();
        JTextField timeField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Room ID:"));
        panel.add(roomIdField);
        panel.add(new JLabel("Your Name:"));
        panel.add(userField);
        panel.add(new JLabel("Reservation Time (yyyy-MM-dd HH:mm):"));
        panel.add(timeField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Cancel Reservation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String roomId = roomIdField.getText();
            String user = userField.getText();
            String timeInput = timeField.getText();

            try {
                LocalDateTime time = LocalDateTime.parse(timeInput, formatter);
                manager.cancelReservation(roomId, user, time);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayReservations() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        manager.displayReservations(textArea);
        JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "Reservations", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReservationGUI::new);
    }
}
