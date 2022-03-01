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

    @Override
    public User save(User user) {
        for (User list_user: users){
            if (list_user.getId().equals(user.getId()))
            {
                users.set(users.indexOf(list_user),user);
                return user;
            }
        }
        users.add(user);
        return user;

    }

    @Override
    public List<User> saveAll(List<User> users) {
        for (User user: users){
            for (User list_user: this.users){
                if (user.getId().equals(list_user.getId())){
                    this.users.set(users.indexOf(list_user),user);
                }
            }
            if (this.users.contains(user) == false){
                this.users.add(user);
            }
        }
        return users;
    }
}
