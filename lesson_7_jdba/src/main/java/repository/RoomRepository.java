package repository;

import entity.Booking;
import entity.Room;
import exceptions.FieldNotFilledException;
import exceptions.RoomNotFoundException;
import exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface RoomRepository {
    Room create(Room room) throws SQLException, FieldNotFilledException;
    Room update(Room room) throws SQLException, FieldNotFilledException, RoomNotFoundException;
    void delete(String id) throws SQLException;
    List<Room> getRooms(String roomNumber, Integer floor, String roomType,
                        Integer price) throws SQLException;
    Room getBy(String id) throws SQLException, RoomNotFoundException;
    public void deleteAll();
}
