package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Country;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
}
