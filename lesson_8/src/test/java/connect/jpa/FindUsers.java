package connect.jpa;

import connect.jpa.entity.User;
import connect.jpa.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@EnableJpaRepositories("connect.jpa.repository")
class FindUsers { // это в класс UserTests
    @Autowired
    UsersService usersService;

    @Test
    void findByFirstName(){
        for (User user: usersService.findByFirstName("Biba")){
            System.out.println(user);
        }
    }

    @Test
    void findByLastName(){
        for (User user: usersService.findByLastName("Bubov")){
            System.out.println(user);
        }
    }
}
