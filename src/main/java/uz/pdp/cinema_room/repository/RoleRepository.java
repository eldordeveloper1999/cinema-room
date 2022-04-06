package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
