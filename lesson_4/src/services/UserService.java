package services;

import Entities.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers(String text);
    public void deleteUsers(List<String> ids);
    public User updateUser(String id, String firstName, String lastName, String middleName, String phone, String email);
    public User createUser(String firstName, String lastName, String middleName, String phone, String email);
}
