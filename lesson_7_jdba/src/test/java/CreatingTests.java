import entity.Booking;
import entity.Room;
import entity.User;
import exceptions.BookingNotFoundException;
import exceptions.FieldNotFilledException;
import exceptions.RoomNotFoundException;
import exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;
import repository.impl.BookingRepositoryImpl;
import repository.impl.RoomRepositoryImpl;
import repository.impl.UserRepositoryImpl;

import java.sql.SQLException;
import java.time.LocalDate;

public class CreatingTests {
    RoomRepository roomRepository = new RoomRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    BookingRepository bookingRepository = new BookingRepositoryImpl();

    private static final User TEST_USER = new User(
            "123",
            "777",
            "777@777.777",
            "777",
            "777",
            "777");

    private static final Room TEST_ROOM = new Room(
            "123",
            "7",
            7,
            "7",
            "7",
            777);
    private static final Booking TEST_BOOKING = new Booking(
            "123",
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            TEST_USER,
            TEST_ROOM);

    @Test
    public void createUser() throws SQLException, UserNotFoundException, FieldNotFilledException {
        userRepository.create(TEST_USER);
        User getUser = userRepository.getUser(TEST_USER.getId());
        Assertions.assertEquals(getUser, TEST_USER);
        userRepository.delete(TEST_USER.getId());
    }

    @Test
    public void createUserNullFieldException(){
         User user = new User(
                "123",
                null,
                "888@888.888",
                "888",
                "888",
                "888");
        Assertions.assertThrows(FieldNotFilledException.class, () -> userRepository.create(user));
    }

    @Test
    public void createRoom() throws SQLException, RoomNotFoundException, FieldNotFilledException{
        roomRepository.create(TEST_ROOM);
        Room getRoom = roomRepository.getBy(TEST_ROOM.getId());
        Assertions.assertEquals(getRoom, TEST_ROOM);

        roomRepository.delete(TEST_ROOM.getId());
    }

    @Test
    public void createBooking() throws SQLException, BookingNotFoundException, FieldNotFilledException {
        roomRepository.create(TEST_ROOM);
        userRepository.create(TEST_USER);
        bookingRepository.create(TEST_BOOKING);

        Booking getBooking = bookingRepository.getBy(TEST_BOOKING.getId());
        Assertions.assertEquals(TEST_BOOKING, getBooking);

        roomRepository.delete(TEST_ROOM.getId());
        userRepository.delete(TEST_USER.getId());
        bookingRepository.delete(TEST_BOOKING.getId());
    }

    @Test
    public void createBookingNullFieldException(){
        Booking booking = new Booking(
                "1",
                LocalDate.now(),
                null,
                TEST_USER,
                TEST_ROOM
        );
        Assertions.assertThrows(FieldNotFilledException.class, () -> bookingRepository.create(booking));

    }

    @Test
    public void deleteUser() throws SQLException, FieldNotFilledException {
        userRepository.create(TEST_USER);
        userRepository.delete(TEST_USER.getId());
        Assertions.assertThrows(UserNotFoundException.class, () -> userRepository.getUser(TEST_USER.getId()));

    }
    @Test
    public void deleteRoom() throws SQLException, FieldNotFilledException {
        roomRepository.create(TEST_ROOM);
        roomRepository.delete(TEST_ROOM.getId());
        Assertions.assertThrows(RoomNotFoundException.class, () -> roomRepository.getBy(TEST_ROOM.getId()));

    }
    @Test
    public void deleteBooking() throws SQLException, FieldNotFilledException {
        bookingRepository.create(TEST_BOOKING);
        bookingRepository.delete(TEST_BOOKING.getId());
        Assertions.assertThrows(BookingNotFoundException.class, () -> bookingRepository.getBy(TEST_BOOKING.getId()));
    }
}
