package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Seat;

import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {
}
