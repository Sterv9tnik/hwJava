import entity.Booking;
import entity.Room;
import entity.User;

import exceptions.BookingNotFoundException;
import exceptions.FieldNotFilledException;
import exceptions.RoomNotFoundException;
import exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.UserRepository;
import repository.impl.BookingRepositoryImpl;
import repository.impl.RoomRepositoryImpl;
import repository.impl.UserRepositoryImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;

public class UpdatingTests {
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
    public void updateUser() throws SQLException, UserNotFoundException, FieldNotFilledException {
        userRepository.create(TEST_USER);

        User updatedUser = new User(
                TEST_USER.getId(),
                "888",
                "888@888.888",
                "888",
                "888",
                "888");
        userRepository.update(updatedUser);
        User getUser = userRepository.getBy(TEST_USER.getId());
        getUser.setBookings(Collections.emptyList());
        Assertions.assertEquals(updatedUser, getUser);

        userRepository.deleteBy(TEST_USER.getId());
    }

    @Test
    public void updateUserNullFieldException(){
        User user = new User(
                "123",
                null,
                "888@888.888",
                "888",
                "888",
                "888");
        Assertions.assertThrows(FieldNotFilledException.class, () -> userRepository.update(user));
    }

    @Test
    public void updateUserNotFound(){
        User user = new User(
                "999999",
                "888",
                "888@888.888",
                "888",
                "888",
                "888");
        Assertions.assertThrows(UserNotFoundException.class, () -> userRepository.update(user));
    }

    @Test
    public void updateRoom() throws SQLException, RoomNotFoundException, FieldNotFilledException {
        roomRepository.create(TEST_ROOM);

        Room updatedRoom = new Room(
                TEST_ROOM.getId(),
                "8",
                8,
                "8",
                "8",
                888);
        roomRepository.update(updatedRoom);
        Room getRoom = roomRepository.getBy(TEST_ROOM.getId());
        getRoom.setBookings(Collections.emptyList());
        Assertions.assertEquals(updatedRoom, getRoom);

        roomRepository.deleteBy(TEST_ROOM.getId());
    }

    @Test
    public void updateRoomNullFieldException(){
        Room room = new Room(
                "123",
                null,
                7,
                "7",
                "7",
                777);
        Assertions.assertThrows(FieldNotFilledException.class, () -> roomRepository.update(room));
    }

    @Test
    public void updateRoomNotFound(){
        Room room = new Room(
                "99999",
                "7",
                7,
                "7",
                "7",
                777);
        Assertions.assertThrows(RoomNotFoundException.class, () -> roomRepository.update(room));
    }

    @Test
    public void updateBooking() throws SQLException, BookingNotFoundException, FieldNotFilledException {
        roomRepository.create(TEST_ROOM);
        userRepository.create(TEST_USER);
        bookingRepository.create(TEST_BOOKING);

        Booking updatedBooking = new Booking(
                TEST_BOOKING.getId(),
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(10),
                TEST_USER,
                TEST_ROOM);
        bookingRepository.update(updatedBooking);
        Booking getBooking = bookingRepository.getBy(TEST_BOOKING.getId());
        Assertions.assertEquals(updatedBooking, getBooking);

        roomRepository.deleteBy(TEST_ROOM.getId());
        userRepository.deleteBy(TEST_USER.getId());
        bookingRepository.deleteBy(TEST_BOOKING.getId());
    }

    @Test
    public void updateBookingNullFieldException(){
        Booking booking = new Booking(
                "1",
                LocalDate.now(),
                null,
                TEST_USER,
                TEST_ROOM
        );
        Assertions.assertThrows(FieldNotFilledException.class, () -> bookingRepository.update(booking));
    }

    @Test
    public void updateBookingNotFound(){
        Booking booking = new Booking(
                "999999",
                LocalDate.now(),
                LocalDate.now(),
                TEST_USER,
                TEST_ROOM
        );
        Assertions.assertThrows(BookingNotFoundException.class, () -> bookingRepository.update(booking));
    }
}
