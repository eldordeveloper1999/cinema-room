package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.model.MovieSession;
import uz.pdp.cinema_room.repository.MovieSessionRepository;

import java.util.List;

@Service
public class MovieSessionService {

    @Autowired
    MovieSessionRepository movieSessionRepository;

    public List<MovieSession> getSessions() {
        return movieSessionRepository.findAll();
    }
}
