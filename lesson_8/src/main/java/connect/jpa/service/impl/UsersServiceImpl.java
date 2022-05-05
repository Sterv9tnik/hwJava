package connect.jpa.service.impl;


import connect.jpa.entity.User;
import connect.jpa.exception.RequiredFieldMissedException;
import connect.jpa.repository.UsersRepository;
import connect.jpa.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
