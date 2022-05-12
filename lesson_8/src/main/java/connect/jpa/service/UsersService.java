package connect.jpa.service;

import connect.jpa.entity.User;

import java.util.List;

public interface UsersService {
    User create(User user);
    User findById(String id);
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    User update(User user);
}
