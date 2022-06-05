package entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Room {
    private String id;
    private String roomNumber;
    private Integer floor;
    private String roomType;
    private String description;
    private Integer price;
    private List<Booking> bookings;

    public Room(String id, String roomNumber, Integer floor, String roomType, String description, Integer price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.roomType = roomType;
        this.description = description;
        this.price = price;
        this.bookings = Collections.emptyList();
    }

    // метод создания пользователя из резалт сета должен быть в классе репозитория
    public Room(ResultSet resultSet) throws SQLException {

        this.id = resultSet.getString("id_room");
        this.roomNumber = resultSet.getString("room_number");
        this.floor = resultSet.getInt("floor");
        this.roomType = resultSet.getString("room_type");
        this.description = resultSet.getString("description");
        this.price = resultSet.getInt("price");
        this.bookings = Collections.emptyList();
    }
}
