package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.ReservedHall;
import uz.pdp.cinema_room.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(nativeQuery = true, value = "select * from users where id= :id")
    User getById(UUID id);

    Optional<User> findByUsername(String username);
}
