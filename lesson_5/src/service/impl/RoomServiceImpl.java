package service.impl;

import entity.Room;
import entity.RoomType;
import repository.RoomRepository;
import service.RoomService;
import service.exceptions.RoomNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room getBy(String id) {
        if (roomRepository.getBy(id) == null){
            throw new RoomNotFoundException();
        }
        return roomRepository.getBy(id);
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(String id, Room room) {
        getBy(id);
        return createRoom(room);
    }

    @Override
    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public Map<RoomType, List<Room>> getRoomsGroupByType() {
        Map<RoomType, List<Room>> rooms = new HashMap<>();
        for (Room room: roomRepository.getAll()){
            if (rooms.get(room.getType()) == null){
                rooms.put(room.getType(),new ArrayList<Room>(List.of(room)));
            }
            else{
                rooms.get(room.getType()).add(room);
            }
        }
        return rooms;
    }
}
