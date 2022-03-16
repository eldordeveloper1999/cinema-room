package uz.pdp.cinema_room.projections;

import uz.pdp.cinema_room.model.Distributor;
import uz.pdp.cinema_room.model.Genre;

import java.util.List;

public interface MovieProjection {

    Integer getDurationInMin();

    String getTitle();

    String getDescription();

    Double getBudget();

    String getGenre();

    String getDistributor();
}
