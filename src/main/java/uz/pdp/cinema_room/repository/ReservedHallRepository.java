package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.ReservedHall;
import uz.pdp.cinema_room.projections.Reserved_HallProjection;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservedHallRepository extends JpaRepository<ReservedHall, UUID> {

    @Query(nativeQuery = true, value = "select * from reserved_halls where id= :id")
    ReservedHall getById(UUID id);

    @Query(nativeQuery = true, value = "select cast(rh.id as varchar) as id,\n" +
            "   m.title as movieName,\n" +
            "       sd.date as sessionDate,\n" +
            "       st.time as sessionTime,\n" +
            "       cast(h.id as varchar) as hallId from reserved_halls rh\n" +
            "join afishalar a on a.id = rh.afisha_id\n" +
            "join session_dates sd on sd.id = rh.session_date_id\n" +
            "join session_times st on st.id = rh.end_time_id\n" +
            "join movies m on m.id = a.movie_id\n" +
            "join halls h on h.id = rh.hall_id")
    List<Reserved_HallProjection> getAllReservedHalls();
}
