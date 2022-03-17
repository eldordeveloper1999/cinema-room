package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.repository.SeatRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/{rh_id}")
    public ResponseEntity<ApiResponse> getAvailableSeats(@PathVariable UUID rh_id) {
        return new ResponseEntity<>(new ApiResponse(true, "success", seatRepository.findAvailableSeatsByRHId(rh_id)), HttpStatus.OK);
    }
}
