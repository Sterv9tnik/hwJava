package repository;

import entity.Booking;
import exceptions.BookingNotFoundException;
import exceptions.FieldNotFilledException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {
    Booking create(Booking booking) throws SQLException, FieldNotFilledException;
    Booking update(Booking booking) throws SQLException, BookingNotFoundException, FieldNotFilledException;
    void deleteBy(String id) throws SQLException;
    Booking getBy(String id) throws SQLException, BookingNotFoundException;
    List<Booking> getBookingsBy(LocalDate checkInFrom, LocalDate checkInTo,
                                LocalDate checkOutFrom, LocalDate checkOutTo) throws SQLException;
    void deleteAll();
}
