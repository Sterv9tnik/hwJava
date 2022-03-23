package service;

import entity.Room;
import entity.RoomType;

import java.util.List;
import java.util.Map;

public interface RoomService {
    Room getBy(String id);
    Room createRoom(Room room);
    Room updateRoom(String id, Room room);
    void deleteRoom(Room room);
    Map<RoomType, List<Room>> getRoomsGroupByType();
}
