package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room.model.Attachment;
import uz.pdp.cinema_room.model.AttachmentContent;
import uz.pdp.cinema_room.model.Movie;
import uz.pdp.cinema_room.projections.MovieProjection;
import uz.pdp.cinema_room.repository.AttachmentContentRepository;
import uz.pdp.cinema_room.repository.AttachmentRepository;
import uz.pdp.cinema_room.repository.MovieRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    AttachmentRepository attachmentRepo;

    @Autowired
    AttachmentContentRepository attachmentContentRepo;

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public void saveOrUpdateMovie(UUID movieId, Movie movie, MultipartFile file) throws IOException {
        if (movieId != null) {
            movieRepository.deleteById(movieId);
            Attachment attachment = new Attachment();
            attachment.setFileName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            Attachment savedFile = attachmentRepo.save(attachment);

            movie.setPosterImg(savedFile);
            movieRepository.save(movie);

            AttachmentContent fileAttachment = new AttachmentContent();
            fileAttachment.setAttachment(savedFile);
            fileAttachment.setData(file.getBytes());
            attachmentContentRepo.save(fileAttachment);
        } else {
            Attachment attachment = new Attachment();
            attachment.setFileName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            Attachment savedFile = attachmentRepo.save(attachment);

            movie.setPosterImg(savedFile);
            movieRepository.save(movie);

            AttachmentContent fileAttachment = new AttachmentContent();
            fileAttachment.setAttachment(savedFile);
            fileAttachment.setData(file.getBytes());
            attachmentContentRepo.save(fileAttachment);
        }

    }

    public void deleteMovie(UUID movieId) {

        movieRepository.deleteById(movieId);

    }

    public MovieProjection getMovieInfo(UUID movieId) {

        return movieRepository.findByMovieId(movieId);

    }

    public Page<MovieProjection> movieList(Pageable pageable){

        return movieRepository.findAllByPage(pageable);

    }
}
