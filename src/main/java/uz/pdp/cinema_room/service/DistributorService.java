package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room.model.Attachment;
import uz.pdp.cinema_room.model.AttachmentContent;
import uz.pdp.cinema_room.model.Distributor;
import uz.pdp.cinema_room.repository.AttachmentContentRepository;
import uz.pdp.cinema_room.repository.AttachmentRepository;
import uz.pdp.cinema_room.repository.DistributorRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class DistributorService {

    @Autowired
    DistributorRepository distributorRepo;

    @Autowired
    AttachmentRepository attachmentRepo;

    @Autowired
    AttachmentContentRepository attachmentContentRepo;

    public List<Distributor> getAllDistributors() {
        return distributorRepo.findAll();
    }


    public void saveOrUpdateDistributor(UUID distributorId, Distributor distributor, MultipartFile file) throws IOException {
        if (distributorId != null) {

        } else {
            Attachment attachment = new Attachment();
            attachment.setFileName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            Attachment savedFile = attachmentRepo.save(attachment);

            distributor.setAttachment(savedFile);
            distributorRepo.save(distributor);

            AttachmentContent fileAttachment = new AttachmentContent();
            fileAttachment.setAttachment(savedFile);
            fileAttachment.setData(file.getBytes());
            attachmentContentRepo.save(fileAttachment);
        }

    }

    public void deleteDistributor(UUID distributorId) {

        distributorRepo.deleteById(distributorId);

    }


    public Optional<Distributor> getAllCastsByMovieId(@PathVariable(required = true) UUID id) {
        return distributorRepo.findById(id);
    }


}
