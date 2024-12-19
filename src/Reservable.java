import java.time.LocalDateTime;

public interface Reservable {
    void reserve(String user, LocalDateTime time);
    void cancelReservation(String user, LocalDateTime time);
}
