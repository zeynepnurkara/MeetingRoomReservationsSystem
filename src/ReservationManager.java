import javax.swing.JTextArea;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager<T extends Room> {
    private List<T> rooms = new ArrayList<>();

    public void addRoom(T room) {
        rooms.add(room);
    }

    // JTextArea kullanarak odaları görüntüleyen metod
    public void displayRooms(JTextArea textArea) {
        rooms.forEach(room -> room.displayInfo(textArea));
    }

    // Rezervasyonları JTextArea içinde görüntüleyen metod
    public void displayReservations(JTextArea textArea) {
        rooms.forEach(room -> {
            textArea.append("Room: " + room.roomId + "\n");
            room.displayReservations(textArea);
        });
    }

    public void makeReservation(String roomId, String user, LocalDateTime time) {
        rooms.stream()
                .filter(room -> room.roomId.equals(roomId))
                .findFirst()
                .ifPresentOrElse(
                        room -> room.reserve(user, time),
                        () -> System.out.println("Room not found: " + roomId)
                );
    }

    public void cancelReservation(String roomId, String user, LocalDateTime time) {
        rooms.stream()
                .filter(room -> room.roomId.equals(roomId))
                .findFirst()
                .ifPresentOrElse(
                        room -> room.cancelReservation(user, time),
                        () -> System.out.println("Room not found: " + roomId)
                );
    }
}
