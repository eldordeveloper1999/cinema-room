package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.model.Hall;
import uz.pdp.cinema_room.repository.HallRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepo;

    public List<Hall> getAllHall() {
        return hallRepo.findAll();
    }

    public void saveHall(Hall hall) {
        hallRepo.save(hall);
    }

    public void updateHall(UUID hallId, Hall hallData) {

        Optional<Hall> hallOptional = hallRepo.findById(hallId);
        if (hallOptional.isPresent()) {
            Hall hall = hallOptional.get();
            hallData.setId(hall.getId());
            hallRepo.save(hallData);

        }
    }

    public void deleteHall(UUID hallId) {
        hallRepo.deleteById(hallId);
    }

}
