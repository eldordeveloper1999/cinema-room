package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
}
