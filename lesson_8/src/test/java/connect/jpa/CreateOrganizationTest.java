package connect.jpa;

import connect.jpa.entity.Organization;
import connect.jpa.entity.User;
import connect.jpa.exception.RequiredFieldMissedException;
import connect.jpa.service.OrganizationsService;
import connect.jpa.service.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Timestamp;
import java.util.UUID;

@SpringBootTest
@EnableJpaRepositories("connect.jpa.repository")
public class CreateOrganizationTest {
    private static final User TEST_USER = new User(
            UUID.randomUUID().toString(),
            "Biba",
            "Bubov",
            "Pupov",
            "8800553535"
    );

    public static final Organization TEST_ORGANIZATION = new Organization(
            "1",
            "OOO Pupa and Lupa",
            "Beep",
            TEST_USER.getId(),
            "Beep",
            "Beep",
            "Beep",
            "Beep",
            "Beep",
            "Beep",
            "Beep",
            "Beep",
            "Beep",
            "Beep",
            666,
            "Beep",
            Timestamp.valueOf("1000-01-01 00:00:00"),
            Timestamp.valueOf("2000-02-02 00:00:00"),
            "Beep");

    @Autowired
    OrganizationsService organizationsService;
    @Autowired
    UsersService usersService;

    @Test
    public void createOrganization(){
        usersService.create(TEST_USER);
        organizationsService.create(TEST_ORGANIZATION);
    }

    @Test
    public void NullInnCreate(){
        Organization organization = TEST_ORGANIZATION;
        organization.setInn(null);
        Assertions.assertThrows(RequiredFieldMissedException.class, () -> organizationsService.create(organization));

    }
}
