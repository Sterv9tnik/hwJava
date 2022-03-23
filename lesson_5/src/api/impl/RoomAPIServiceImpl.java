package api.impl;

import api.RoomAPIService;
import entity.Room;
import entity.RoomType;
import service.exceptions.RoomNotFoundException;
import service.impl.RoomServiceImpl;

import java.util.List;
import java.util.Map;

public class RoomAPIServiceImpl implements RoomAPIService {
    private final RoomServiceImpl roomServiceImpl;

    public RoomAPIServiceImpl(RoomServiceImpl roomServiceImpl) {
        this.roomServiceImpl = roomServiceImpl;
    }

    @Override
    public Room createRoom(Room room) {
        try {
            roomServiceImpl.createRoom(room);
        }
        catch (RoomNotFoundException e){
            System.out.println(e);
        }
        return room;
    }

    @Override
    public Room updateRoom(String id, Room room) {
        try {
            roomServiceImpl.updateRoom(id, room);
        }
        catch (RoomNotFoundException e){
            System.out.println(e);
        }
        return room;
    }

    @Override
    public Room getRoom(String id) {
        try {
            roomServiceImpl.getBy(id);
        }
        catch (RoomNotFoundException e){
            System.out.println(e);
        }
        return roomServiceImpl.getBy(id);
    }

    @Override
    public Map<RoomType, List<Room>> getRoomsGroupedByType() {
        return roomServiceImpl.getRoomsGroupByType();
    }

    @Override
    public void deleteRoom(String id) {
        roomServiceImpl.deleteRoom(getRoom(id));
    }
}
