package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.ReservedHall;

import java.util.UUID;

@Repository
public interface ReservedHallRepository extends JpaRepository<ReservedHall, UUID> {
}
