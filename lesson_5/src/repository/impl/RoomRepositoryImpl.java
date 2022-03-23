package repository.impl;

import entity.Room;
import repository.RoomRepository;

import java.util.HashSet;
import java.util.Set;

public class RoomRepositoryImpl implements RoomRepository {
    private Set<Room> rooms;

    public RoomRepositoryImpl(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room save(Room room) {
        if (getBy(room.getId()) != null){
            delete(getBy(room.getId()));
        }
        rooms.add(room);
        return room;
    }

    @Override
    public void delete(Room room) {
        rooms.remove(room);
    }

    @Override
    public Room getBy(String id) {
        for (Room room: rooms){
            if (room.getId().equals(id))
            {
                return room;
            }
        }
        return null;
    }

    @Override
    public Set<Room> getAll() {
        return rooms;
    }
}
