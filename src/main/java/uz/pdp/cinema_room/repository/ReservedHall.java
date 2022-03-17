package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservedHall extends JpaRepository<ReservedHall, UUID> {
}
