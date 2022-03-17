package uz.pdp.cinema_room.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.Afisha;
import uz.pdp.cinema_room.projections.AfishaProjection;
import uz.pdp.cinema_room.projections.MovieProjection;

import java.util.UUID;

@Repository
public interface AfishaRepository extends JpaRepository<Afisha, UUID> {

//    @Query(nativeQuery = true, value = "select cast(a.id as varchar) as id, m.title as movieName, " +
//            "a.is_active as isActive from afishalar a  " +
//            "join movies m on m.id = a.movie_id")
    @Query(nativeQuery = true, value = "select cast(a.id as varchar) as id, m.title as movieName, cast(m.id as varchar) as movieId,\n" +
            "       sd.date as date, cast(sd.id as varchar) as dateId, st.time as start_time,\n" +
            "       st.time as end_time, h.name as hallName, cast(h.id as varchar) as hallId from afishalar a\n" +
            "join movies m on m.id = a.movie_id\n" +
            "join reserved_halls rh on a.id = rh.afisha_id\n" +
            "join halls h on h.id = rh.hall_id\n" +
            "join session_dates sd on sd.id = rh.session_date_id\n" +
            "join session_times st on st.id = rh.end_time_id")
    Page<AfishaProjection> findAllByPage(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from afishalar where id = :id")
    Afisha findByID(UUID id);
}
