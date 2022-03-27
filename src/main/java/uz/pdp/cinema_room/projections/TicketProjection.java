package uz.pdp.cinema_room.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema_room.model.Ticket;

@Projection(types = Ticket.class)
public interface TicketProjection {

    String getId();

    Double getPrice();

    @Value("#{@seatRepository.getById(target.seatId)}")
    SeatProjection getSeat();
}
