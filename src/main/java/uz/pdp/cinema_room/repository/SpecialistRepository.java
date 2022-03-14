package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Specialist;

import java.util.UUID;

public interface SpecialistRepository extends JpaRepository<Specialist, UUID> {
}
