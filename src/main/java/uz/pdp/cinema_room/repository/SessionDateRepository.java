package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.SessionDate;

import java.util.UUID;

public interface SessionDateRepository extends JpaRepository<SessionDate, UUID> {
    @Query(nativeQuery = true, value = "select * from session_dates where id = :id")
    SessionDate findByID(UUID id);
}
