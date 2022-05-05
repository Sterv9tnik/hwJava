package connect.jpa;

import connect.jpa.entity.User;
import connect.jpa.exception.RequiredFieldMissedException;
import connect.jpa.service.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;
@SpringBootTest
@EnableJpaRepositories("connect.jpa.repository")
public class CreateUserTest {
    private static final User TEST_USER = new User(
            UUID.randomUUID().toString(),
            "Biba",
            "Bubov",
            "Pupov",
            "8800553535"
    );
    @Autowired
    UsersService usersService;

    @Test
    public void createUser(){
        usersService.create(TEST_USER);
    }

    @Test
    public void NullPhoneCreate(){
        User user = TEST_USER;
        user.setPhone(null);
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> usersService.create(user));
    }
}
