import entity.Booking;
import entity.Room;
import entity.User;
import exceptions.BookingNotFoundException;
import exceptions.RoomNotFoundException;
import exceptions.UserNotFoundException;
import org.junit.jupiter.api.*;
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

public class Main extends BeforeAndAfter{
    RoomRepository roomRepository = new RoomRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    BookingRepository bookingRepository = new BookingRepositoryImpl();

    private  List <User> users = createSomeUsers();
    private  List <Room> rooms = createSomeRooms();
    private  List<Booking> bookings = createSomeBookings();

    public  List<User> createSomeUsers(){
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
        return Arrays.asList(inUser1, inUser2, inUser3);
    }
    public  List<Room> createSomeRooms(){
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

        return Arrays.asList(inRoom1, inRoom2, inRoom3);
    }
    public  List<Booking> createSomeBookings(){
        Booking booking1 = new Booking("1", LocalDate.now(), LocalDate.now().plusDays(1), users.get(0), rooms.get(0));
        Booking booking2 = new Booking("2", LocalDate.now().plusDays(2), LocalDate.now().plusDays(4), users.get(1), rooms.get(0));
        Booking booking3 = new Booking("3", LocalDate.now(), LocalDate.now().plusDays(1), users.get(2), rooms.get(1));
        Booking booking4 = new Booking("4", LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), users.get(1), rooms.get(1));
        return Arrays.asList(booking1, booking2, booking3, booking4);
    }


    @Test
    @Tag("getUsers")
    public void getUsersOnFirstName() throws SQLException, UserNotFoundException {
        List <User> user1 = Arrays.asList(userRepository.getUser(users.get(0).getId()), userRepository.getUser(users.get(2).getId()));
        List <User> user2 = userRepository.getUsers("Biba", null, null);
        Assertions.assertTrue(user1.size()==user2.size() && user1.containsAll(user2) && user2.containsAll(user1));
    }

    @Test
    @Tag("getUsers")
    public void getUsersOnLastName() throws SQLException, UserNotFoundException {
        List <User> user1 = Arrays.asList(userRepository.getUser(users.get(0).getId()), userRepository.getUser(users.get(1).getId()));
        List <User> user2 = userRepository.getUsers(null, "Bibov", null);
        Assertions.assertTrue(user1.size()==user2.size() && user1.containsAll(user2) && user2.containsAll(user1));
    }

    @Test
    @Tag("getUsers")
    public void getUsersOnMiddleName() throws SQLException, UserNotFoundException {
        List <User> user1 = Arrays.asList(userRepository.getUser(users.get(1).getId()), userRepository.getUser(users.get(2).getId()));
        List <User> user2 = userRepository.getUsers(null, null, "Lupovich");
        Assertions.assertTrue(user1.size()==user2.size() && user1.containsAll(user2) && user2.containsAll(user1));
    }

    @Test
    @Tag("getUsers")
    public void getUsersOnFirstNameAndMiddleName() throws SQLException, UserNotFoundException {
        List <User> user1 = Arrays.asList(userRepository.getUser(users.get(2).getId()));
        List <User> user2 = userRepository.getUsers("Biba", null, "Lupovich");
        Assertions.assertTrue(user1.size()==user2.size() && user1.containsAll(user2) && user2.containsAll(user1));
    }

    @Test
    @Tag("getUsers")
    public void getUsersOnAll() throws SQLException, UserNotFoundException {
        List <User> user1 = Arrays.asList(userRepository.getUser(users.get(2).getId()));
        List <User> user2 = userRepository.getUsers("Biba", "Lupov", "Lupovich");
        Assertions.assertTrue(user1.size()==user2.size() && user1.containsAll(user2) && user2.containsAll(user1));
    }

    @Test
    @Tag("getRooms")
    public void getRoomsOnRoomNumber() throws SQLException, RoomNotFoundException {
        List <Room> rooms1 = Arrays.asList(roomRepository.getBy(rooms.get(0).getId()), roomRepository.getBy(rooms.get(2).getId()));
        List <Room> rooms2 = roomRepository.getRooms("1", null, null, null);
        Assertions.assertTrue(rooms1.size()==rooms2.size() && rooms1.containsAll(rooms2) && rooms2.containsAll(rooms1));
    }

    @Test
    @Tag("getRooms")
    public void getRoomsOnFloor() throws SQLException, RoomNotFoundException {
        List <Room> rooms1 = Arrays.asList(roomRepository.getBy(rooms.get(0).getId()), roomRepository.getBy(rooms.get(1).getId()));
        List <Room> rooms2 = roomRepository.getRooms(null, 1, null, null);
        Assertions.assertTrue(rooms1.size()==rooms2.size() && rooms1.containsAll(rooms2) && rooms2.containsAll(rooms1));
    }

    @Test
    @Tag("getRooms")
    public void getRoomsOnRoomType() throws SQLException, RoomNotFoundException {
        List <Room> rooms1 = Arrays.asList(roomRepository.getBy(rooms.get(1).getId()), roomRepository.getBy(rooms.get(2).getId()));
        List <Room> rooms2 = roomRepository.getRooms(null, null, "Cheap", null);
        Assertions.assertTrue(rooms1.size()==rooms2.size() && rooms1.containsAll(rooms2) && rooms2.containsAll(rooms1));
    }

    @Test
    @Tag("getRooms")
    public void getRoomsOnPrice() throws SQLException, RoomNotFoundException {
        List <Room> rooms1 = Arrays.asList(roomRepository.getBy(rooms.get(1).getId()), roomRepository.getBy(rooms.get(2).getId()));
        List <Room> rooms2 = roomRepository.getRooms(null, null, null, 100);
        Assertions.assertTrue(rooms1.size()==rooms2.size() && rooms1.containsAll(rooms2) && rooms2.containsAll(rooms1));
    }

    @Test
    @Tag("getRooms")
    public void getRoomsOnRoomNumberAndRoomType() throws SQLException, RoomNotFoundException {
        List <Room> rooms1 = Arrays.asList(roomRepository.getBy(rooms.get(0).getId()));
        List <Room> rooms2 = roomRepository.getRooms("1", null, "ULTRA", null);
        Assertions.assertTrue(rooms1.size()==rooms2.size() && rooms1.containsAll(rooms2) && rooms2.containsAll(rooms1));
    }

    @Test
    @Tag("getRooms")
    public void getRoomsOnFloorAndRoomType() throws SQLException, RoomNotFoundException {
        List <Room> rooms1 = Arrays.asList(roomRepository.getBy(rooms.get(1).getId()));
        List <Room> rooms2 = roomRepository.getRooms(null, 1, "Cheap", null);
        Assertions.assertTrue(rooms1.size()==rooms2.size() && rooms1.containsAll(rooms2) && rooms2.containsAll(rooms1));
    }
    @Test
    @Tag("getRooms")
    public void getRoomsOnFloorAndRoomTypeAndPrice() throws SQLException, RoomNotFoundException {
        List <Room> rooms1 = Arrays.asList(roomRepository.getBy(rooms.get(1).getId()));
        List <Room> rooms2 = roomRepository.getRooms(null, 1, "Cheap", 100);
        Assertions.assertTrue(rooms1.size()==rooms2.size() && rooms1.containsAll(rooms2) && rooms2.containsAll(rooms1));
    }

    @Test
    @Tag("getRooms")
    public void getRoomsOnAll() throws SQLException, RoomNotFoundException {
        List <Room> rooms1 = Arrays.asList(roomRepository.getBy(rooms.get(0).getId()));
        List <Room> rooms2 = roomRepository.getRooms("1", 1, "ULTRA", 500);
        Assertions.assertTrue(rooms1.size()==rooms2.size() && rooms1.containsAll(rooms2) && rooms2.containsAll(rooms1));
    }

    @Test
    @Tag("getBookings")
    public void getBookingsOnCheckIn() throws SQLException, BookingNotFoundException {
        List <Booking> bookings1 = Arrays.asList(bookingRepository.getBy(bookings.get(0).getId()),bookingRepository.getBy(bookings.get(2).getId()));
        List <Booking> bookings2 = bookingRepository.getBookings(LocalDate.now(), LocalDate.now().plusDays(1), null, null);
        Assertions.assertTrue(bookings1.size()==bookings2.size() && bookings1.containsAll(bookings2) && bookings2.containsAll(bookings1));
    }

    @Test
    @Tag("getBookings")
    public void getBookingsOnCheckOut() throws SQLException, BookingNotFoundException {
        List <Booking> bookings1 = Arrays.asList(bookingRepository.getBy(bookings.get(1).getId()),bookingRepository.getBy(bookings.get(3).getId()));
        List <Booking> bookings2 = bookingRepository.getBookings(null, null, LocalDate.now().plusDays(3), LocalDate.now().plusDays(5));
        Assertions.assertTrue(bookings1.size()==bookings2.size() && bookings1.containsAll(bookings2) && bookings2.containsAll(bookings1));
    }
}
