import entity.Booking;
import entity.Room;
import entity.User;
import exceptions.FieldNotFilledException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;
import repository.impl.BookingRepositoryImpl;
import repository.impl.RoomRepositoryImpl;
import repository.impl.UserRepositoryImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BeforeAndAfter {
    static RoomRepository roomRepository = new RoomRepositoryImpl();
    static UserRepository userRepository = new UserRepositoryImpl();
    static BookingRepository bookingRepository = new BookingRepositoryImpl();

    @BeforeAll
    public static void create(){
        Room inRoom1 = new Room(
                "1",
                "1",
                1,
                "ULTRA",
                "LUXE",
                500);
        Room inRoom2 = new Room(
                "2",
                "2",
                1,
                "Cheap",
                "LUXE",
                100);
        Room inRoom3 = new Room(
                "3",
                "1",
                2,
                "Cheap",
                "LUXE",
                100);
        User inUser1 = new User(
                "1",
                "1234",
                "1234@1234.1234",
                "Biba",
                "Bibov",
                "Bibavich");
        User inUser2 = new User(
                "2",
                "5678",
                "5678@5678.5678",
                "Lupa",
                "Bibov",
                "Lupovich");
        User inUser3 = new User(
                "3",
                "9009",
                "9009@9009.9009",
                "Biba",
                "Lupov",
                "Lupovich");
        List<Room> rooms = Arrays.asList(inRoom1, inRoom2, inRoom3);
        List<User> users = Arrays.asList(inUser1, inUser2, inUser3);
        Booking booking1 = new Booking("1", LocalDate.now(), LocalDate.now().plusDays(1), users.get(0), rooms.get(0));
        Booking booking2 = new Booking("2", LocalDate.now().plusDays(2), LocalDate.now().plusDays(4), users.get(1), rooms.get(0));
        Booking booking3 = new Booking("3", LocalDate.now(), LocalDate.now().plusDays(1), users.get(2), rooms.get(1));
        Booking booking4 = new Booking("4", LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), users.get(1), rooms.get(1));
        List<Booking> bookings = Arrays.asList(booking1, booking2, booking3, booking4);
        users.stream().forEach(user -> {
            try {
                userRepository.create(user);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FieldNotFilledException e) {
                e.printStackTrace();
            }
        });
        rooms.stream().forEach(room -> {
            try {
                roomRepository.create(room);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FieldNotFilledException e) {
                e.printStackTrace();
            }
        });
        bookings.stream().forEach(booking -> {
            try {
                bookingRepository.create(booking);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FieldNotFilledException e) {
                e.printStackTrace();
            }
        });
    }
    @AfterAll
    public static void deleteAll(){
        userRepository.deleteAll();
        bookingRepository.deleteAll();
        roomRepository.deleteAll();
    }
}
