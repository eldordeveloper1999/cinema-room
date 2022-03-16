package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.Genre;
import uz.pdp.cinema_room.projections.GenreProjection;

import java.util.List;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {

    @Query(nativeQuery = true, value = "select cast(g.id as varchar) as id , g.name as name from genres g " +
            "join movies_genres mg on g.id = mg.genres_id\n" +
            "where mg.movies_id = :movieId")
    List<GenreProjection> findAllByMovieId(UUID movieId);

}
