package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.dto.TicketDto;
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


    @Query(nativeQuery = true, value = "select  " +
            "m.title as movieTitle from tickets\n" +
            "join reserved_halls rh on rh.id = tickets.reserved_hall_id\n" +
            "join seats s on s.id = tickets.seat_id\n" +
            "join carts c on tickets.cart_id = c.id\n" +
            "join users u on c.user_id = u.id\n" +
            "join afishalar a on a.id = rh.afisha_id\n" +
            "join movies m on m.id = a.movie_id" +
            " where tickets.id = :id")
    String getMovieTitle(UUID id);

    @Query(nativeQuery = true, value = "select " +
            "cast(u.id as varchar) as user_id from tickets\n" +
            "join reserved_halls rh on rh.id = tickets.reserved_hall_id\n" +
            "join seats s on s.id = tickets.seat_id\n" +
            "join carts c on tickets.cart_id = c.id\n" +
            "join users u on c.user_id = u.id\n" +
            "join afishalar a on a.id = rh.afisha_id\n" +
            "join movies m on m.id = a.movie_id" +
            " where tickets.id = :id")
    UUID getUserId(UUID id);
}
