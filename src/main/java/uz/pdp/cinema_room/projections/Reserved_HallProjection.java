package uz.pdp.cinema_room.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema_room.model.Afisha;

import java.util.List;
import java.util.UUID;

@Projection(types = Reserved_HallProjection.class)
public interface Reserved_HallProjection {

    String getId();

    String getMovieName();

    String getSessionDate();

    String getSessionTime();

    String getHallId();
}
