package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Distributor;

import java.util.UUID;

public interface DistributorRepository extends JpaRepository<Distributor, UUID> {
}
