package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.model.Attachment;
import uz.pdp.cinema_room.model.Specialist;
import uz.pdp.cinema_room.model.SpecialistType;
import uz.pdp.cinema_room.service.SpecialistService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/specialist")
public class SpecialistController {

        @Autowired
        private SpecialistService specialistService;


        @GetMapping
        public ResponseEntity<ApiResponse> getAllSpecialists() {
            return new ResponseEntity<>(new ApiResponse(true, "success", specialistService.getAllSpecialists()), HttpStatus.OK);
        }

        @GetMapping("/{specialistId}")
        public ResponseEntity<ApiResponse> getSpecialistById(@PathVariable UUID specialistId) {
            return new ResponseEntity<>(new ApiResponse(true, "success", specialistService.getSpecialistById(specialistId)), HttpStatus.OK);
        }

        @GetMapping("/m/{movieId}")
        public ResponseEntity<ApiResponse> getSpecialistsByMovieId(@PathVariable UUID movieId) {
            return new ResponseEntity<>(new ApiResponse(true, "success", specialistService.getSpecialistsByMovieId(movieId)), HttpStatus.OK);
        }


        @PostMapping
        public ResponseEntity<ApiResponse> saveSpecialist(@RequestPart("attachment") MultipartFile multipartFile,
                                                           @RequestPart("data") Specialist specialist,
                                                          @RequestPart("displayName") String displayName) {
            try {
                SpecialistType specialistType1 = SpecialistType.getSpecialistByDisplayName(displayName);

                specialist.setCastType(specialistType1);

                specialistService.saveSpecialist(specialist, multipartFile);
                return new ResponseEntity<>(new ApiResponse(true, "created", specialist), HttpStatus.OK);
            } catch (IOException ignored) {
            }
            return new ResponseEntity<>(new ApiResponse(false, "error", specialist), HttpStatus.BAD_REQUEST);
        }

        //update
        @PutMapping("/{specialistId}")
        public ResponseEntity<ApiResponse> updateSpecialist(@RequestPart("attachment") MultipartFile multipartFile,
                                                             @RequestPart("data") Specialist specialist,
                                                            @RequestPart("displayName") String displayName,
                                                             @PathVariable UUID specialistId) {
            try {
                SpecialistType specialistType1 = SpecialistType.getSpecialistByDisplayName(displayName);

                specialist.setCastType(specialistType1);
                specialistService.updateSpecialist(specialistId, specialist, multipartFile);
                return new ResponseEntity<>(new ApiResponse(true, "updated", specialist), HttpStatus.OK);
            } catch (IOException ignored) {
            }

            return new ResponseEntity<>(new ApiResponse(false, "error", specialist), HttpStatus.BAD_REQUEST);
        }

        //delete
        @DeleteMapping("/{specialistId}")
        public ResponseEntity<ApiResponse> deleteSpecialist(@PathVariable UUID specialistId) {
            try {
                specialistService.deleteSpecialist(specialistId);
                return new ResponseEntity<>(new ApiResponse(true, "deleted", specialistId), HttpStatus.OK);
            } catch (Exception ex) {
                ex.getMessage();
            }

            return new ResponseEntity<>(new ApiResponse(false, "error", specialistId), HttpStatus.BAD_REQUEST);
        }

    }
