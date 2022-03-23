package entity;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Room {

    private String id;
    private String roomNumber;
    private Integer floor;
    private RoomType type;
    private String description;
    private Integer price;
    private Set<Booking> bookings;

    public Room(String id, String roomNumber, Integer floor, RoomType type, String description, Integer price, Set<Booking> bookings) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.type = type;
        this.description = description;
        this.price = price;
        this.bookings = bookings.stream().filter(book -> book.getRoom().getId() == id).collect(Collectors.toSet());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) && Objects.equals(roomNumber, room.roomNumber) && Objects.equals(floor, room.floor) && type == room.type && Objects.equals(description, room.description) && Objects.equals(price, room.price) && Objects.equals(bookings, room.bookings);
    }

   // @Override
   // public int hashCode() {
        //return Objects.hash(id, roomNumber, floor, type, description, price, bookings);
   // }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", floor=" + floor +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", bookings=" + bookings.stream().map(String::valueOf).collect(Collectors.toList()) +
                '}';
    }
}
