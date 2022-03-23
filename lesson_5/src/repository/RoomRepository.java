package repository;

import entity.Room;

import java.util.Set;

public interface RoomRepository {
    Room save(Room room);
    void delete(Room room);
    Room getBy(String id);
    Set<Room> getAll();
}
