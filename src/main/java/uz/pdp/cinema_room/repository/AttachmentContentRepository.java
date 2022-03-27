package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {

    AttachmentContent findByAttachmentId(UUID id);

    @Query(nativeQuery = true, value = "select * from attachment_contents \n" +
            "    join attachments a on a.id = attachment_contents.attachment_id\n" +
            "join tickets t on a.id = t.qr_code_id\n" +
            "where t.id = :ticket_id")
    AttachmentContent getByTicketId(UUID ticket_id);
}
