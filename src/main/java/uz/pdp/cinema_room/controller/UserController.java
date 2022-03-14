package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room.model.Hall;
import uz.pdp.cinema_room.model.Row;
import uz.pdp.cinema_room.model.Seat;
import uz.pdp.cinema_room.repository.HallRepository;
import uz.pdp.cinema_room.repository.RowRepository;
import uz.pdp.cinema_room.repository.SeatRepository;
import uz.pdp.cinema_room.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    RowRepository rowRepository;

    @Autowired
    HallRepository hallRepository;

    @GetMapping("/seats")
    public ResponseEntity<List<Seat>> getSeats() {
        return ResponseEntity.ok().body(seatRepository.findAll());
    }

    @GetMapping("/rows")
    public ResponseEntity<List<Row>> getRows() {
        return ResponseEntity.ok().body(rowRepository.findAll());
    }

    @GetMapping("/halls")
    public ResponseEntity<List<Hall>> getHalls() {
        return ResponseEntity.ok().body(hallRepository.findAll());
    }

}
