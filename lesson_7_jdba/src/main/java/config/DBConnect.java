package config;


import entity.Booking;
import entity.Room;
import entity.User;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;
import repository.impl.BookingRepositoryImpl;
import repository.impl.RoomRepositoryImpl;
import repository.impl.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DBConnect {
    public static Connection getDBConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5556/RenRoom";
        String user = "postgresadmin";
        String passwd = "postgres";
        return DriverManager.getConnection(url, user, passwd);
    }
}

