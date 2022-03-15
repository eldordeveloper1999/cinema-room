package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.MovieSession;

import java.util.UUID;

public interface MovieSessionRepository extends JpaRepository<MovieSession, UUID> {

}
