package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
