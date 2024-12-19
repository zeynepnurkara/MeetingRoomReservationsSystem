import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

public class Room implements Reservable {
    protected String roomId;
    protected int capacity;
    protected boolean isAvailable; // Odanın genel kullanılabilirlik durumu
    protected Map<LocalDateTime, Set<String>> reservations; // Tarih/Saat bazında kullanıcı seti

    public Room(String roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.isAvailable = true; // Başlangıçta oda kullanılabilir
        this.reservations = new HashMap<>();
    }

    // Oda bilgilerini gösteren metod
    public void displayInfo(JTextArea textArea) {
        int totalReservations = reservations.values().stream().mapToInt(Set::size).sum();
        int remainingCapacity = capacity - totalReservations;

        textArea.append("Room: " + roomId + " - Capacity: " + capacity
                + " - Remaining Capacity: " + remainingCapacity + "\n");
    }


    // Rezervasyon ekleyen metod
    @Override
    public void reserve(String user, LocalDateTime time) {
        reservations.putIfAbsent(time, new HashSet<>());
        Set<String> users = reservations.get(time);

        if (users.size() < capacity) {
            if (users.add(user)) {
                System.out.println("Reservation successful for user: " + user + " at " + time);
                updateAvailability(); // Kapasiteyi güncelle
            } else {
                System.out.println("User " + user + " already has a reservation at this time.");
            }
        } else {
            System.out.println("Room " + roomId + " is fully booked at " + time);
        }
    }

    // Rezervasyon iptal eden metod
    @Override
    public void cancelReservation(String user, LocalDateTime time) {
        if (reservations.containsKey(time) && reservations.get(time).remove(user)) {
            System.out.println("Reservation for user: " + user + " at " + time + " has been canceled.");
            if (reservations.get(time).isEmpty()) {
                reservations.remove(time); // Boş zaman dilimini kaldır
            }
            updateAvailability(); // Kapasiteyi güncelle
        } else {
            System.out.println("No reservation found for user: " + user + " at " + time);
        }
    }

    // Odanın kapasitesine göre 'isAvailable' durumunu günceller
    private void updateAvailability() {
        int totalReservations = reservations.values().stream().mapToInt(Set::size).sum();
        isAvailable = totalReservations < capacity; // Eğer kapasite dolmadıysa kullanılabilir
    }

    // Rezervasyonları gösteren metod
    public void displayReservations(JTextArea textArea) {
        reservations.forEach((time, users) -> {
            textArea.append("  Time: " + time + ", Users: " + users + "\n");
        });
    }

}


