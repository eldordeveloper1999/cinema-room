package uz.pdp.cinema_room.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import uz.pdp.cinema_room.repository.HallRepository;

public class DataLoader implements CommandLineRunner {

    @Autowired
    HallRepository hallRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
