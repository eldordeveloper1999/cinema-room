package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
