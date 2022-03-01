package repository;

import Entities.User;
import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User getBy(String id);
    User save(User user);
    List<User> saveAll(List<User> users);
}
