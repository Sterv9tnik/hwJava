package repository.impl;

import config.DBConnect;
import entity.Booking;

import entity.Room;
import entity.User;
import exceptions.FieldNotFilledException;
import exceptions.UserNotFoundException;
import repository.UserRepository;

import java.sql.*;
import java.util.*;


public class UserRepositoryImpl implements UserRepository {
    @Override
    public User create(User user) throws FieldNotFilledException {
        try (Connection connection = DBConnect.getDBConnection()){
            validation(user);
            String query = "INSERT INTO users values(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getId());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getMiddleName());
            statement.execute();
            return user;
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User user) throws FieldNotFilledException, UserNotFoundException {
        try (Connection connection = DBConnect.getDBConnection()){
            validation(user);
            getUser(user.getId());
            String query = "UPDATE users SET phone = ?, email = ?, first_name = ?, last_name = ?, middle_name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getPhone());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getMiddleName());
            statement.setString(6, user.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DBConnect.getDBConnection()){
            String query = "DELETE FROM users where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String id) throws UserNotFoundException {
        try (Connection connection = DBConnect.getDBConnection()){
            User user = new User();
            String query = "Select u.id as id_user, b.id as booking_id, r.id as id_room, * from users as u" +
                    " left join bookings as b on b.user_id  = u.id"+
                    " left join rooms as r on b.room_id  = r.id where u.id = ?";
            List <Booking> bookings = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                Room room = new Room(resultSet);
                user = new User(resultSet);
                Booking booking = new Booking(resultSet);
                if (booking.getId() != null) {
                    booking.setRoom(room);
                    bookings.add(booking);
                }
            }
            checkUserId(user);
            user.setBookings(bookings);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUsers(String firstName, String lastName, String middleName) {
        try (Connection connection = DBConnect.getDBConnection()){
            List <User> users = new ArrayList<>();
            //Query
            StringBuilder query = new StringBuilder("Select u.id as id_user, b.id as booking_id, r.id as id_room, * from users as u left join bookings as b on b.user_id  = u.id left join rooms as r on b.room_id  = r.id where ");
            Map<Integer, String> names = new HashMap<>();
            if (firstName != null){
                query.append("first_name = ?");
                names.put(1,firstName);
            }
            if (lastName != null){
                if (names.isEmpty()){
                    query.append("last_name = ?");
                    names.put(1,lastName);
                }
                else{
                    query.append(" and last_name = ?");
                    names.put(names.size() + 1,lastName);
                }
            }
            if (middleName != null){
                if (names.isEmpty()){
                    query.append("middle_name = ?");
                    names.put(1,middleName);
                }
                else{
                    query.append(" and middle_name = ?");
                    names.put(names.size() + 1,middleName);
                }
            }
            query.append("ORDER BY u.id ASC");
            PreparedStatement statement = connection.prepareStatement(query.toString());
            for (Map.Entry<Integer, String> name : names.entrySet()){
                statement.setString(name.getKey(),name.getValue());
            }
            statement.execute();
            Map <User, ArrayList<Booking>> userBookings = new HashMap<>();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User(resultSet);
                Room room = new Room(resultSet);
                Booking booking = new Booking(resultSet);
                if (userBookings.containsKey(user)){
                    if (booking.getId() != null){
                        booking.setRoom(room);
                        userBookings.get(user).add(booking);
                    }
                }
                else{
                    if (booking.getId() != null){
                        booking.setRoom(room);
                        userBookings.put(user, new ArrayList<>(List.of(booking)));
                    }
                    else{
                        userBookings.put(user, new ArrayList<>());
                    }

                }
            }
            for (Map.Entry<User, ArrayList<Booking>> roomBooking : userBookings.entrySet()){
                User user = roomBooking.getKey();
                user.setBookings(roomBooking.getValue());
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void deleteAll(){
        try (Connection connection = DBConnect.getDBConnection()){
            String query = "DELETE FROM users";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validation(User user) throws FieldNotFilledException {
        if(user.getEmail() == null){
            throw new FieldNotFilledException("Email missed");
        }
        if(user.getPhone() == null){
            throw new FieldNotFilledException("Phone missed");
        }
        if(user.getFirstName() == null){
            throw new FieldNotFilledException("FirstName missed");
        }
        if(user.getLastName() == null){
            throw new FieldNotFilledException("LastName missed");
        }
        if(user.getMiddleName() == null){
            throw new FieldNotFilledException("MiddleName missed");
        }
    }

    public void checkUserId(User user) throws UserNotFoundException {
        if (user.getId() == null){
            throw new UserNotFoundException();
        }
    }

}

