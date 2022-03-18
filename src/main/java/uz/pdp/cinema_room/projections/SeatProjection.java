package uz.pdp.cinema_room.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema_room.model.Seat;

@Projection(types = Seat.class)
public interface SeatProjection {

    String getId();

    Integer getNumber();

    @Value("#{@rowRepository.findBySeatId(target.id)}")
    RowProjection getRow();

    @Value("#{@hallRepository.findBySeatId(target.id)}")
    HallProjection getHall();

}
