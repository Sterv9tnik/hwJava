package entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class User {
    private String id;
    private String phone;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private List<Booking> bookings;

    public User(String id, String phone, String email, String firstName, String lastName, String middleName) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.bookings = Collections.emptyList();
    }

    public User(){
    }

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("id_user");
        this.phone = resultSet.getString("phone");
        this.email = resultSet.getString("email");
        this.firstName = resultSet.getString("first_name");
        this.lastName = resultSet.getString("last_name");
        this.middleName = resultSet.getString("middle_name");
        this.bookings = Collections.emptyList();
    }
}
