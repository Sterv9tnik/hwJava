package repository.impl;

import config.DBConnect;
import entity.Booking;
import entity.Room;
import entity.User;
import exceptions.FieldNotFilledException;
import exceptions.RoomNotFoundException;
import repository.RoomRepository;

import java.sql.*;
import java.util.*;

public class RoomRepositoryImpl implements RoomRepository {

    private static boolean isDigit(String s) {
        if (s.contains("int")) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Room create(Room room) throws FieldNotFilledException {
        try (Connection connection = DBConnect.getDBConnection()){
            validation(room);
            String query = "INSERT INTO rooms values(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, room.getId());
            statement.setString(2, room.getRoomNumber());
            statement.setInt(3, room.getFloor());
            statement.setString(4, room.getRoomType());
            statement.setString(5, room.getDescription());
            statement.setInt(6, room.getPrice());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public Room update(Room room) throws FieldNotFilledException, RoomNotFoundException {
        try (Connection connection = DBConnect.getDBConnection()){
            validation(room);
            getBy(room.getId());
            String query = "UPDATE rooms SET room_number = ?, floor = ?, room_type = ?, description = ?, price = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, room.getRoomNumber());
            statement.setInt(2, room.getFloor());
            statement.setString(3, room.getRoomType());
            statement.setString(4, room.getDescription());
            statement.setInt(5, room.getPrice());
            statement.setString(6, room.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void deleteBy(String id) {
        try (Connection connection = DBConnect.getDBConnection()){
            String query = "DELETE FROM rooms where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getRoomsBy(String roomNumber, Integer floor, String roomType, Integer price) {
        List<Room> listRooms = new ArrayList<>();
        try (Connection connection = DBConnect.getDBConnection()){
            StringBuilder query = new StringBuilder("Select u.id as id_user, b.id as booking_id, r.id as id_room, * from rooms as r left join bookings as b on b.room_id  = r.id left join users as u on b.user_id  = u.id where ");
            Map<Integer, String> rooms = new HashMap<>();
            if (roomNumber != null){
                query.append("room_number = ?");
                rooms.put(1, "str"+roomNumber);
            }
            if (floor != null){
                if (rooms.isEmpty()){
                    query.append("floor = ?");
                    rooms.put(1, "int" + floor);
                }
                else{
                    query.append(" and floor = ?");
                    rooms.put(rooms.size() + 1, "int" + floor);
                }
            }
            if (roomType != null){
                if (rooms.isEmpty()){
                    query.append("room_type = ?");
                    rooms.put(1, "str"+roomType);
                }
                else{
                    query.append(" and room_type = ?");
                    rooms.put(rooms.size() + 1, "str"+roomType );
                }
            }
            if (price != null){
                if (rooms.isEmpty()){
                    query.append("price = ?");
                    rooms.put(1, "int" + price);
                }
                else{
                    query.append(" and price = ?");
                    rooms.put(rooms.size() + 1, "int" + price);
                }
            }
            query.append("ORDER BY r.id ASC");
            PreparedStatement statement = connection.prepareStatement(query.toString());
            for (Map.Entry<Integer, String> name : rooms.entrySet()){
                if (isDigit(name.getValue())){
                    statement.setInt(name.getKey(), Integer.parseInt(name.getValue().substring(3)));
                }else{
                    statement.setString(name.getKey(), name.getValue().substring(3));
                }

            }
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            Map <Room, ArrayList<Booking>> roomBookings = new HashMap<>();
            while (resultSet.next()) {
                //room
                Room room = new Room(resultSet);
                //user
                User user = new User(resultSet);
                //booking
                Booking booking = new Booking(resultSet);

                if (roomBookings.containsKey(room)){
                    if (booking.getId() != null){
                        booking.setUser(user);
                        roomBookings.get(room).add(booking);
                    }
                }else{
                    if (booking.getId() != null) {
                        booking.setUser(user);
                        roomBookings.put(room, new ArrayList<Booking>(Arrays.asList(booking)));
                    }else{
                        roomBookings.put(room, new ArrayList<Booking>());
                    }
                }
            }

            for (Map.Entry<Room, ArrayList<Booking>> roomBooking : roomBookings.entrySet()){
                Room room = roomBooking.getKey();
                room.setBookings(roomBooking.getValue());
                listRooms.add(room);
            }
            return listRooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room getBy(String id) throws RoomNotFoundException{
        try (Connection connection = DBConnect.getDBConnection()){
            List<Booking> bookings = new ArrayList<>();
            Room room = new Room();
            String query = "Select u.id as id_user, b.id as booking_id, r.id as id_room, * from rooms as r "+
                    "left join bookings as b on b.room_id  = r.id "+
                    "left join users as u on b.user_id  = u.id where r.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                room = new Room(resultSet);
                //user
                User user = new User(resultSet);
                //booking
                Booking booking = new Booking(resultSet);
                if (booking.getId() != null){
                    booking.setUser(user);
                    bookings.add(booking);
                }
            }
            checkRoomId(room);
            room.setBookings(bookings);
            return room;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll(){
        try (Connection connection = DBConnect.getDBConnection()){
            String query = "DELETE FROM rooms";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validation(Room room) throws FieldNotFilledException {
        if (room.getFloor() == null){
            throw new FieldNotFilledException("Floor missed");
        }
        if (room.getRoomNumber() == null){
            throw new FieldNotFilledException("RoomType missed");
        }
        if (room.getRoomType() == null){
            throw new FieldNotFilledException("RoomType missed");
        }
        if (room.getDescription() == null){
            throw new FieldNotFilledException("Description missed");
        }
        if (room.getRoomType() == null){
            throw new FieldNotFilledException("RoomType missed");
        }
    }

    public void checkRoomId(Room room) throws RoomNotFoundException {
        if (room.getId() == null){
            throw new RoomNotFoundException();
        }
    }

}
