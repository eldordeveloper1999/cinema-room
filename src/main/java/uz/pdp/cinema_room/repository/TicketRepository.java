package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.dto.TicketDto;
import uz.pdp.cinema_room.model.Ticket;
import uz.pdp.cinema_room.model.TicketStatus;
import uz.pdp.cinema_room.projections.PdfWriterProjection;
import uz.pdp.cinema_room.projections.TicketProjection;

import java.time.LocalDate;
import java.util.List;
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

    @Query(nativeQuery = true, value = "select * from tickets\n" +
            "    join carts c on c.id = tickets.cart_id\n" +
            "join users u on u.id = c.user_id\n" +
            "where u.id = :Id and status = 'NEW'")
    List<Ticket> findAllByUserId(UUID Id);


    @Query(nativeQuery = true, value = "select count(*) from tickets\n" +
            "join reserved_halls rh on rh.id = tickets.reserved_hall_id\n" +
            "join session_dates sd on sd.id = rh.session_date_id\n" +
            "where status = 'PURCHASED' and sd.date = :date")
    Integer getTicketsByDate(LocalDate date);

    @Query(nativeQuery = true, value = "select s.number as seat, " +
            "r.number as row, " +
            "h.name as hall, " +
            "st.time as time, " +
            "m.title as movieName from tickets t\n" +
            "join seats s on t.seat_id = s.id\n" +
            "join \"rows\" r on r.id = s.row_id\n" +
            "join reserved_halls rh on rh.id = t.reserved_hall_id\n" +
            "join halls h on h.id = rh.hall_id\n" +
            "join session_times st on st.id = rh.end_time_id\n" +
            "join afishalar a on a.id = rh.afisha_id\n" +
            "join movies m on m.id = a.movie_id\n" +
            "where t.id = :ticket_id")
    PdfWriterProjection getTicketPdfProjection(UUID ticket_id);
}
