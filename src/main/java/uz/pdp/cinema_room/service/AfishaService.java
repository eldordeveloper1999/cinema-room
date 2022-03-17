package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room.model.Afisha;
import uz.pdp.cinema_room.model.Attachment;
import uz.pdp.cinema_room.model.AttachmentContent;
import uz.pdp.cinema_room.model.Afisha;
import uz.pdp.cinema_room.projections.AfishaProjection;
import uz.pdp.cinema_room.projections.MovieProjection;
import uz.pdp.cinema_room.repository.AfishaRepository;
import uz.pdp.cinema_room.repository.AttachmentContentRepository;
import uz.pdp.cinema_room.repository.AttachmentRepository;
import uz.pdp.cinema_room.repository.AfishaRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AfishaService {

    @Autowired
    AfishaRepository afishaRepository;

    public List<Afisha> getAllAfishas() {
        return afishaRepository.findAll();
    }


    public Page<AfishaProjection> movieList(Pageable pageable){

        return afishaRepository.findAllByPage(pageable);

    }


    public void saveOrUpdateAfisha(UUID afishaId, Afisha afisha) throws IOException {
        if (afishaId != null) {
            afishaRepository.deleteById(afishaId);
            afishaRepository.save(afisha);

        } else {

            afishaRepository.save(afisha);

        }

    }

    public Page<AfishaProjection> afishaList(Pageable pageable){

        return afishaRepository.findAllByPage(pageable);

    }

    public void deleteAfisha(UUID afishaId) {

        afishaRepository.deleteById(afishaId);

    }


    public Optional<Afisha> getAllAfishasByMovieId(@PathVariable(required = true) UUID id) {
        return afishaRepository.findById(id);
    }
}
