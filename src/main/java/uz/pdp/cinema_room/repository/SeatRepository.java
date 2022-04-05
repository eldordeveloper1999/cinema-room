package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.Seat;
import uz.pdp.cinema_room.model.User;
import uz.pdp.cinema_room.projections.SeatProjection;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {

    @Query(nativeQuery = true, value = "select cast(s.id as varchar) as id, s.number as number\n" +
            "from seats s\n" +
            "         join \"rows\" r on r.id = s.row_id\n" +
            "        join reserved_halls rh on r.hall_id = rh.hall_id\n" +
            "         join session_times st on st.id = rh.start_time_id\n" +
            "         join session_dates sd on sd.id = rh.session_date_id\n" +
            "where rh.id = :uuid\n" +
            "  and s.id not in (select t1.seat_id\n" +
            "                   from tickets t1\n" +
            "                   where t1.status = 'NEW'\n" +
            "                      or t1.status = 'PURCHASED' and t1.reserved_hall_id=:uuid)")
    List<SeatProjection> findAvailableSeatsByRHId(UUID uuid);


    @Query(nativeQuery = true, value = "select * from seats where id= :id")
    Seat getById(UUID id);
}
