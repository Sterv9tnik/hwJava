package services.impl;

import Entities.User;
import Entities.UserStatus;
import services.UserService;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers(String text) {

        List<User> users = new ArrayList<>();
        for (User user : userRepository.getAll()) {
            if (text.matches("^\\d+$")) {
                if (user.getStatus() == UserStatus.ACTIVE && user.getPhone().equals(text)) {
                    users.add(user);
                }
            }
            else if (text.contains("@")) {
                if (user.getStatus() == UserStatus.ACTIVE && user.getEmail().equals(text)) {
                    users.add(user);
                }

            }
            else if (text.trim().split(" ").length == 3){
                if (user.getStatus() == UserStatus.ACTIVE && text.contains(user.getFirstName()) && text.contains(user.getLastName()) && text.contains(user.getFirstName())){
                    users.add(user);
                }

            }
            else{
                System.out.printf("Не верные данные: %s", text);
            }

        }
        return users;
    }

    @Override
    public void deleteUsers(List<String> ids) {
        List <User> users = new ArrayList<>();
        for (String id : ids){
            User user = userRepository.getBy(id);
            if (user != null){
                user.setStatus(UserStatus.DELETED);
            }
        }
        userRepository.saveAll(users);
    }

    @Override
    public User updateUser(String id, String firstName, String lastName, String middleName, String phone, String email) {
        if (userRepository.getBy(id) == null) {
            System.out.println("Пользователя не существует");
        }
        else if (userRepository.getBy(id).getStatus() == UserStatus.DELETED){
            System.out.printf("Can't update user with id %s , because it's status is DELETED\n", id);
        }
        else {
           return userRepository.save(new User(id,firstName,lastName,middleName,phone,email));
        }
        return null;
    }

    public User createUser(String firstName, String lastName, String middleName, String phone, String email){
        return userRepository.save(new User(UUID.randomUUID().toString(),firstName,lastName,middleName,phone,email));
    }
}
