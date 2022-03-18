package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.SessionDate;
import uz.pdp.cinema_room.projections.SessionDateProjection;

import java.util.UUID;

public interface SessionDateRepository extends JpaRepository<SessionDate, UUID> {
    @Query(nativeQuery = true, value = "select * from session_dates where id = :id")
    SessionDate findByID(UUID id);

    @Query(nativeQuery = true, value = "select cast(sd.id as varchar) as id, sd.date from session_dates sd\n" +
            "join reserved_halls rh on sd.id = rh.session_date_id\n" +
            "join afishalar a on a.id = rh.afisha_id\n" +
            "where a.id = :uuid")
    SessionDateProjection findByAfishaId(UUID uuid);

}
