package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Genre;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
}
