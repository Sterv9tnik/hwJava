package connect.jpa.service.impl;


import connect.jpa.entity.User;
import connect.jpa.entity.exception.RequiredFieldMissedException;
import connect.jpa.entity.exception.UserNotFoundException;
import connect.jpa.repository.UsersRepository;
import connect.jpa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public User create(User user) {
        validation(user);
        usersRepository.save(user);
        return user;
    }

    @Override
    public User findById(String id) {
        User user = usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        List<User> users = usersRepository.findByFirstName(firstName);
        return users;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> users = usersRepository.findByLastName(lastName);
        return users;
    }

    @Override
    public User update(User user) {
        findById(user.getId());
        create(user);
        return user;
    }

    public void validation(User user){
        if(user.getId() == null){
            throw new RequiredFieldMissedException("Id missed");
        }
        if(user.getPhone() == null){
            throw new RequiredFieldMissedException("Phone missed");
        }
        if(user.getFirstName() == null){
            throw new RequiredFieldMissedException("FirstName missed");
        }
        if(user.getLastName() == null){
            throw new RequiredFieldMissedException("LastName missed");
        }
        if(user.getMiddleName() == null){
            throw new RequiredFieldMissedException("MiddleName missed");
        }
    }



}
