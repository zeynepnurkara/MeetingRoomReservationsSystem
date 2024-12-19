import javax.swing.JTextArea;
import java.util.Set;

public class StudyRoom extends Room {
    private boolean hasWhiteboard;

    public StudyRoom(String roomId, int capacity, boolean hasWhiteboard) {
        super(roomId, capacity);
        this.hasWhiteboard = hasWhiteboard;
    }

    @Override
    public void displayInfo(JTextArea textArea) {
        int totalReservations = reservations.values().stream().mapToInt(Set::size).sum();
        int remainingCapacity = capacity - totalReservations;

        textArea.append("Study Room: " + roomId + " - Capacity: " + capacity
                + " - Remaining Capacity: " + remainingCapacity
                + " - Whiteboard Available: " + (hasWhiteboard ? "Yes" : "No") + "\n");
    }
}
