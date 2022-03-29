package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.model.Afisha;
import uz.pdp.cinema_room.projections.AfishaProjection;
import uz.pdp.cinema_room.projections.MovieProjection;
import uz.pdp.cinema_room.service.AfishaService;
import uz.pdp.cinema_room.service.AfishaService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/afisha")
public class AfishaController {

    @Autowired
    AfishaService afishaService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveAfisha(@RequestPart("data") Afisha afisha) {
        try {
            afishaService.saveOrUpdateAfisha(null, afisha);
            return new ResponseEntity<>(new ApiResponse(true, "created", afisha), HttpStatus.OK);
        } catch (IOException ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", afisha), HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public HttpEntity getAllAfisha(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Page<AfishaProjection> movies = afishaService.afishaList(paging);

        ApiResponse res = new ApiResponse(true, "success", movies);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/{afishaId}")
    public ResponseEntity<ApiResponse> updateAfisha(@RequestPart("data") Afisha afisha,
                                                         @PathVariable UUID afishaId) {
        try {
            afishaService.saveOrUpdateAfisha(afishaId, afisha);
            return new ResponseEntity<>(new ApiResponse(true, "updated", afisha), HttpStatus.OK);
        } catch (IOException ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", afisha), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{afishaId}")
    public ResponseEntity<ApiResponse> deleteAfisha(@PathVariable UUID afishaId) {
        try {
            afishaService.deleteAfisha(afishaId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", afishaId), HttpStatus.OK);
        } catch (Exception ignored) {
            ignored.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", afishaId), HttpStatus.BAD_REQUEST);
    }

}
