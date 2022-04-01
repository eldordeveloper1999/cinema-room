package uz.pdp.cinema_room.projections;

import java.time.LocalTime;

public interface PdfWriterProjection {

    String getMovieName();

    String getHall();

    Integer getRow();

    Integer getSeat();

    LocalTime getTime();
}
