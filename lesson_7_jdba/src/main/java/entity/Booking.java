package entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Booking {
    private String id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private User user;
    private Room room;
    public Booking(){
    }

    public Booking(ResultSet resultSet) throws SQLException {

        this.id = resultSet.getString("booking_id");
        if (resultSet.getDate("check_in") == null){
            this.checkIn = null;
        }
        else {
            this.checkIn = resultSet.getDate("check_in").toLocalDate();
        }
        if (resultSet.getDate("check_out") == null){
            this.checkOut = null;
        }
        else {
            this.checkOut = resultSet.getDate("check_out").toLocalDate();
        }
        this.user = null;
        this.room = null;
    }
}
