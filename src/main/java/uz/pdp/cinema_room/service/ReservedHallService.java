package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.dto.ReservedHallDto;
import uz.pdp.cinema_room.model.*;
import uz.pdp.cinema_room.projections.Reserved_HallProjection;
import uz.pdp.cinema_room.repository.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ReservedHallService {

    @Autowired
    ReservedHallRepository reservedHallRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SessionDateRepository sessionDateRepository;

    @Autowired
    SessionTimeRepository sessionTimeRepository;

    @Autowired
    AfishaRepository afishaRepository;

    public List<Reserved_HallProjection> getAllReservedHalls() {
        return reservedHallRepository.getAllReservedHalls();
    }

    public ReservedHall getReservedHallById(UUID uuid) {
        return reservedHallRepository.getById(uuid);
    }

//    public List<ReservedHallProjection> getReservedHallsByMovieId(UUID movieId) {
//        return reservedHallRepository.findAllByMovieId(movieId);
//    }

    public void saveReservedHall(ReservedHallDto reservedHallDto) throws IOException {
        Hall hallRepositoryById = hallRepository.findByID(reservedHallDto.getHall_id());
        Afisha afishaRepositoryById = afishaRepository.findByID(reservedHallDto.getAfisha_id());
        SessionDate sessionDateRepositoryById = sessionDateRepository.findByID(reservedHallDto.getSessionDate_id());
        SessionTime start_time = sessionTimeRepository.findByID(reservedHallDto.getStart_time_id());
        SessionTime end_time = sessionTimeRepository.findByID(reservedHallDto.getStart_time_id());

        ReservedHall reservedHall = new ReservedHall(hallRepositoryById,
                sessionDateRepositoryById,
                start_time,
                end_time,
                afishaRepositoryById);
        reservedHallRepository.save(reservedHall);

    }

        public void updateReservedHall (ReservedHallDto reservedHallDto, UUID id) throws IOException {


                deleteReservedHall(id);
            Hall hallRepositoryById = hallRepository.findByID(reservedHallDto.getHall_id());
            Afisha afishaRepositoryById = afishaRepository.findByID(reservedHallDto.getAfisha_id());
            SessionDate sessionDateRepositoryById = sessionDateRepository.findByID(reservedHallDto.getSessionDate_id());
            SessionTime start_time = sessionTimeRepository.findByID(reservedHallDto.getStart_time_id());
            SessionTime end_time = sessionTimeRepository.findByID(reservedHallDto.getStart_time_id());

            ReservedHall reservedHall = new ReservedHall(hallRepositoryById,
                    sessionDateRepositoryById,
                    start_time,
                    end_time,
                    afishaRepositoryById);
            reservedHallRepository.save(reservedHall);

        }

        public void deleteReservedHall (UUID reservedHallId){

            reservedHallRepository.deleteById(reservedHallId);

        }
    }
