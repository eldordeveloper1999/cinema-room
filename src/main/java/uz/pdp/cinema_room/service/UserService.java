package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.model.ReservedHall;
import uz.pdp.cinema_room.model.User;
import uz.pdp.cinema_room.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public User getUserById(UUID uuid) {
        return userRepository.getById(uuid);
    }
}
