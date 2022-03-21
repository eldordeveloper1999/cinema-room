package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.Seat;
import uz.pdp.cinema_room.model.User;
import uz.pdp.cinema_room.projections.SeatProjection;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {

    @Query(nativeQuery = true, value = "select cast(s.id as varchar) as id, s.number as number  from seats s\n" +
            "join \"rows\" r on r.id = s.row_id\n" +
            "join halls h on h.id = r.hall_id\n" +
            "join reserved_halls rh on h.id = rh.hall_id\n" +
            "join afishalar a on a.id = rh.afisha_id\n" +
            "join session_times st on st.id = rh.end_time_id\n" +
            "where rh.id = :uuid AND not s.id in (select t1.seat_id from tickets t1)")
    List<SeatProjection> findAvailableSeatsByRHId(UUID uuid);


    @Query(nativeQuery = true, value = "select * from seats where id= :id")
    Seat getById(UUID id);
}
