package repository.impl;

import config.DBConnect;
import entity.Booking;
import entity.Room;
import entity.User;
import exceptions.BookingNotFoundException;
import exceptions.FieldNotFilledException;
import exceptions.RoomNotFoundException;
import exceptions.UserNotFoundException;
import repository.BookingRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingRepositoryImpl implements BookingRepository {
    @Override
    public Booking create(Booking booking) throws FieldNotFilledException {
        try (Connection connection = DBConnect.getDBConnection()){
            validation(booking);
            String query = "INSERT INTO bookings values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, booking.getId());
            statement.setDate(2, Date.valueOf(booking.getCheckIn()));
            statement.setDate(3,Date.valueOf(booking.getCheckOut()));
            statement.setString(4,booking.getUser().getId());
            statement.setString(5,booking.getRoom().getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public Booking update(Booking booking) throws BookingNotFoundException, FieldNotFilledException {
        try (Connection connection = DBConnect.getDBConnection()){
            validation(booking);
            getBy(booking.getId());
            String query = "UPDATE bookings set check_in = ?, check_out = ?, user_id  = ?, room_id  = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(booking.getCheckIn()));
            statement.setDate(2,Date.valueOf(booking.getCheckOut()));
            statement.setString(3,booking.getUser().getId());
            statement.setString(4,booking.getRoom().getId());
            statement.setString(5,booking.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DBConnect.getDBConnection()){
            String query = "DELETE FROM bookings where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Booking getBy(String id) throws BookingNotFoundException {
        try (Connection connection = DBConnect.getDBConnection()){
            Booking booking = new Booking();
            String query = "Select u.id as id_user, b.id as booking_id, r.id as id_room, * from bookings as b " +
                    "left join users as u on b.user_id  = u.id " +
                    "left join rooms as r on b.room_id  = r.id where b.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                //user
                User user = new User(resultSet);
                //room
                Room room = new Room(resultSet);
                //booking
                booking = new Booking(resultSet);
                booking.setRoom(room);
                booking.setUser(user);
            }
            checkBookingId(booking);
            return booking;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Booking> getBookings(LocalDate checkInFrom, LocalDate checkInTo, LocalDate checkOutFrom, LocalDate checkOutTo){
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = DBConnect.getDBConnection()) {
            PreparedStatement statement = null;
            if (checkInFrom != null && checkInTo != null) {
                String query = "Select u.id as id_user, b.id as booking_id, r.id as id_room, * from bookings as b" +
                        " left join users as u on b.user_id  = u.id" +
                        " left join rooms as r on b.room_id  = r.id" +
                        " where check_in >= ? and check_in <= ?";
                statement = connection.prepareStatement(query);
                statement.setDate(1, Date.valueOf(checkInFrom));
                statement.setDate(2, Date.valueOf(checkInTo));
            } else if (checkOutFrom != null && checkOutTo != null) {
                String query = "Select u.id as id_user, b.id as booking_id, r.id as id_room, * from bookings as b" +
                        " left join users as u on b.user_id  = u.id" +
                        " left join rooms as r on b.room_id  = r.id" +
                        " where check_out >= ? and check_out <= ?";
                statement = connection.prepareStatement(query);
                statement.setDate(1, Date.valueOf(checkOutFrom));
                statement.setDate(2, Date.valueOf(checkOutTo));
            } else {
                return null;
            }
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                //user
                User user = new User(resultSet);
                //room
                Room room = new Room(resultSet);
                //booking
                Booking booking = new Booking(resultSet);
                booking.setRoom(room);
                booking.setUser(user);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public void deleteAll(){
        try (Connection connection = DBConnect.getDBConnection()){
            String query = "DELETE FROM bookings";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void validation(Booking booking) throws FieldNotFilledException {
        if(booking.getCheckIn() == null){
            throw new FieldNotFilledException("CheckIn missed");
        }
        if(booking.getCheckOut() == null){
            throw new FieldNotFilledException("CheckOut missed");
        }
        if (booking.getUser() == null){
            throw new FieldNotFilledException("User missed");
        }
        if (booking.getRoom() == null){
            throw new FieldNotFilledException("Room missed");
        }
    }

    public void checkBookingId(Booking booking) throws BookingNotFoundException {
        if (booking.getId() == null){
            throw new BookingNotFoundException();
        }
    }
}
