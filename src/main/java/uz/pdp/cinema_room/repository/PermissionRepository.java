package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Permission;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}
