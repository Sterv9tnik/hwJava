package repository.impl;

import Entities.User;
import repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private List<User> users;

    public UserRepositoryImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getBy(String id) {
        for (User user: users){
            if (user.getId().equals(id))
            {
                return user;
            }
        }

        return null;
    }

    // тут можно вот так, чтобы без цикла явного
    @Override
    public User save(User user) {
        int indexOfUser = this.users.indexOf(user);
        if (indexOfUser != -1) {
            this.users.set(indexOfUser, user);
            return user;
        }
        this.users.add(user);
        return user;

    }

    @Override
    public List<User> saveAll(List<User> users) {
        for (User user: users) {
            save(user);
        }
        return this.users; // яб тут на пофиг в цикле просто сделал вызов save'a
    }
}
