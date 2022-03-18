package uz.pdp.cinema_room.projections;

import java.time.LocalDate;

public interface SessionDateProjection {

    String getId();

    LocalDate getDate();
}
