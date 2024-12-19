import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

public class ConferenceRoom extends Room {
    private boolean projectorAvailable; // Projeksiyon durumu

    public ConferenceRoom(String roomId, int capacity, boolean projectorAvailable) {
        super(roomId, capacity);
        this.projectorAvailable = projectorAvailable;
    }

    @Override
    public void displayInfo(JTextArea textArea) {
        int totalReservations = reservations.values().stream().mapToInt(Set::size).sum();
        int remainingCapacity = capacity - totalReservations;

        textArea.append("Conference Room: " + roomId + " - Capacity: " + capacity
                + " - Remaining Capacity: " + remainingCapacity
                + " - Projector: " + (projectorAvailable ? "Yes" : "No") + "\n");
    }

    // Gerekli ise diğer metodları da düzenleyin
}
