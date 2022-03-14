package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Movie;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
