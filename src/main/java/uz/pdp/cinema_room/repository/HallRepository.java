package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.Hall;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {
    Optional<Hall> findById(Integer id);
}
