package connect.jpa.repository;

import connect.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsersRepository extends JpaRepository<User, String> {
    @Query(value ="Select * from users where first_name = ?1",
            nativeQuery = true)
    public List<User> findByFirstName(String firstName);


    @Query(value = "Select * from users where last_name = :lastName",
            nativeQuery = true)
    public List<User> findByLastName(@Param("lastName")String lastName);



}
