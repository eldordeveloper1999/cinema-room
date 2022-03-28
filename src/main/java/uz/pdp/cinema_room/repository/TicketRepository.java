package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.Ticket;
import uz.pdp.cinema_room.model.TicketStatus;
import uz.pdp.cinema_room.projections.TicketProjection;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {


    @Query(nativeQuery = true, value = "select cast(id as varchar) as id, price, " +
            "cast(seat_id as varchar) as seatId from tickets\n" +
            "where id = :id")
    TicketProjection getTicketById(UUID id);


    @Query(nativeQuery = true, value = "select * from tickets where id = :id")
    Ticket getTicketBYId(UUID id);

}
