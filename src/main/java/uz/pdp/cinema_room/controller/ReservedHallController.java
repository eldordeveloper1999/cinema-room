package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.dto.ReservedHallDto;
import uz.pdp.cinema_room.model.ReservedHall;
import uz.pdp.cinema_room.service.ReservedHallService;
import uz.pdp.cinema_room.service.ReservedHallService;

import javax.imageio.spi.RegisterableService;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservedHall")
public class ReservedHallController {

    @Autowired
    ReservedHallService reservedHallService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveReservedHall(@RequestBody ReservedHallDto reservedHall) {
        try {
            reservedHallService.saveReservedHall(reservedHall);
            return new ResponseEntity<>(new ApiResponse(true, "created", reservedHall), HttpStatus.OK);
        } catch (IOException ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", reservedHall), HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllReservedHalls() {
        return new ResponseEntity<>(new ApiResponse(true, "success", reservedHallService.getAllReservedHalls()), HttpStatus.OK);
    }

//    @GetMapping
//    public HttpEntity getAllReservedHall(
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "size", defaultValue = "2") int size
//    ) {
//        Pageable paging =  PageRequest.of(page, size);
//        Page<ReservedHallProjection> movies = reservedHallService.reservedHallList(paging);
//
//        ApiResponse res = new ApiResponse(true, "success", movies);
//
//        return ResponseEntity.ok(res);
//    }

    @PutMapping("/{reservedHallId}")
    public ResponseEntity<ApiResponse> updateReservedHall(@RequestBody ReservedHallDto reservedHall,
                                                    @PathVariable UUID reservedHallId) {
        try {
            reservedHallService.updateReservedHall(reservedHall, reservedHallId);
            return new ResponseEntity<>(new ApiResponse(true, "updated", reservedHall), HttpStatus.OK);
        } catch (IOException ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", reservedHall), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reservedHallId}")
    public ResponseEntity<ApiResponse> deleteReservedHall(@PathVariable UUID reservedHallId) {
        try {
            reservedHallService.deleteReservedHall(reservedHallId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", reservedHallId), HttpStatus.OK);
        } catch (Exception ignored) {
            ignored.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", reservedHallId), HttpStatus.BAD_REQUEST);
    }

}
