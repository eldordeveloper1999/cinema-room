package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {

    AttachmentContent findByAttachmentId(UUID id);

    @Query(nativeQuery = true, value = "select * from attachment_contents \n"+
            "where attachment_id = :id")
    AttachmentContent getByAttachmentId(UUID id);
}
