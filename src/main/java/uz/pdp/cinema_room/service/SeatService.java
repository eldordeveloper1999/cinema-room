package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.model.Seat;
import uz.pdp.cinema_room.model.User;
import uz.pdp.cinema_room.repository.SeatRepository;

import java.util.UUID;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public Seat getSeatById(UUID uuid) {
        return seatRepository.getById(uuid);
    }
}
