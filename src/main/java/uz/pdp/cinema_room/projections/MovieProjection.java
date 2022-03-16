package uz.pdp.cinema_room.projections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinema_room.model.Distributor;
import uz.pdp.cinema_room.model.Genre;
import uz.pdp.cinema_room.model.Movie;
import uz.pdp.cinema_room.repository.GenreRepository;

import java.util.List;

@Projection(types = { Movie.class })
public interface MovieProjection {

    String getId();

    Integer getDurationInMin();

    String getTitle();

    String getDescription();

    Double getBudget();

    @Value("#{@genreRepository.findAllByMovieId(target.id)}")
    List<GenreProjection> getGenres();

    String getDistributorId();

    String getDistributor();
}
