package connect.jpa;

import connect.jpa.entity.User;
import connect.jpa.entity.exception.RequiredFieldMissedException;
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
    private static final User TEST_UPDATE_USER = new User(
            "40220802-ebf5-4efb-b60c-2500a0fcb608",
            "Andrey",
            "Andreevich",
            "Andreev",
            "999                                                                                                                                           "
    );

    @Autowired
    UsersService usersService;

    @Test
    public void setTestUpdateUser(){
    usersService.update(TEST_UPDATE_USER);
    }

    @Test
    public void createUser(){
        usersService.create(TEST_USER);
        Assertions.assertEquals(TEST_USER, usersService.findById(TEST_USER.getId()));
    }

    @Test
    public void NullPhoneCreate(){
        User user = TEST_USER;
        user.setPhone(null);
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> usersService.create(user));
    }
}
