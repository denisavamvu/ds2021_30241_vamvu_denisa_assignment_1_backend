package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    /*
    @Query(value = "SELECT  " +
            "FROM Users u " +
            "WHERE u.role = :role" )
    Optional<User> findAllClients(@Param("role") UserRole role);
    */

    List<User> findByRole(UserRole role);

}
