package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.service.MovieSessionService;

@Controller
@RequestMapping("/api/sessions")
public class MovieSessionController {

    @Autowired
    MovieSessionService movieSessionService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDistributors() {
        return new ResponseEntity<>(new ApiResponse(true, "success", movieSessionService.getSessions()), HttpStatus.OK);
    }
}
