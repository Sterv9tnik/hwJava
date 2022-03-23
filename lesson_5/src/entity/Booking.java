package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private String id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Guest guest;
    private Room room;

    public Booking(String id, LocalDate checkIn, LocalDate checkOut, Guest guest, Room room) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guest = guest;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(checkIn, booking.checkIn) && Objects.equals(checkOut, booking.checkOut) && Objects.equals(guest, booking.guest) && Objects.equals(room, booking.room);
    }

  //  @Override
   // public int hashCode() {
       // return Objects.hash(id, checkIn, checkOut, guest, room);
    //}

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", guest=" + guest +
                ", room=" + room.getId() +
                '}';
    }
}
