package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Row;

import java.util.UUID;

public interface RowRepository extends JpaRepository<Row, UUID> {
}
