package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.SessionTime;

import java.util.UUID;

public interface SessionTimeRepository extends JpaRepository<SessionTime, UUID> {
    @Query(nativeQuery = true, value = "select * from session_times where id = :id")
    SessionTime findByID(UUID id);
}
