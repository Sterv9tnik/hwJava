package repository;

import entity.User;
import exceptions.FieldNotFilledException;
import exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User create(User user) throws SQLException, FieldNotFilledException;
    User update(User user) throws SQLException, FieldNotFilledException, UserNotFoundException;
    void deleteBy(String id) throws SQLException;
    User getBy(String id) throws SQLException, UserNotFoundException;
    List<User> getUsersBy(String firstName, String lastName, String middleName);
    public void deleteAll();
}
