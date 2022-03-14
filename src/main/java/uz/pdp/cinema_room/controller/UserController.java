package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uz.pdp.cinema_room.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepo;

    public void saveUser() {
    }
}
