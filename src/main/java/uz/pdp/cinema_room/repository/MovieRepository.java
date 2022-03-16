package uz.pdp.cinema_room.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.Movie;
import uz.pdp.cinema_room.projections.MovieProjection;
import uz.pdp.cinema_room.projections.Projection;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    @Query(nativeQuery = true, value = "select m.duration_in_min as durationInMin, m.title as title,\n" +
            "       m.description, m.budget, g.name as genre, d.name as distributor from movies m\n" +
            "           join distributors d on d.id = m.distributor_id\n" +
            "join movies_genres mg on m.id = mg.movies_id\n" +
            "           join genres g on mg.genres_id = g.id\n" +
            "\n" +
            "where m.id = :movieId")
    MovieProjection findByMovieId(UUID movieId);

    @Query(nativeQuery = true, value = "select m.duration_in_min as durationInMin, m.title as title,\n" +
            "       m.description, m.budget, g.name as genre, d.name as distributor from movies m\n" +
            "           join distributors d on d.id = m.distributor_id\n" +
            "join movies_genres mg on m.id = mg.movies_id\n" +
            "           join genres g on mg.genres_id = g.id")
    Page<MovieProjection> findAllByPage(Pageable pageable);
}
