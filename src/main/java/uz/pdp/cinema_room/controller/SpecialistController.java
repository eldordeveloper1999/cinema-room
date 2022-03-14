package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.model.Attachment;
import uz.pdp.cinema_room.model.Distributor;
import uz.pdp.cinema_room.model.Specialist;
import uz.pdp.cinema_room.service.SpecialistService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/actors")
public class SpecialistController {

        @Autowired
        private SpecialistService specialistService;


        @GetMapping
        public ResponseEntity<ApiResponse> getAllDistributors() {
            return new ResponseEntity<>(new ApiResponse(true, "success", specialistService.getAllActors()), HttpStatus.OK);
        }


        @PostMapping
        public ResponseEntity<ApiResponse> saveDistributor(@RequestPart("attachment") MultipartFile multipartFile,
                                                           @RequestPart("json") Specialist specialist) {
            try {
                specialistService.saveSpecialist(specialist, multipartFile);
                specialist.setPhoto((Attachment) multipartFile);
                return new ResponseEntity<>(new ApiResponse(true, "created", specialist), HttpStatus.OK);
            } catch (IOException ignored) {
            }
            return new ResponseEntity<>(new ApiResponse(false, "error", specialist), HttpStatus.BAD_REQUEST);
        }

        //update
        @PutMapping("/{specialistId}")
        public ResponseEntity<ApiResponse> updateDistributor(@RequestPart("attachment") MultipartFile multipartFile,
                                                             @RequestPart("json") Specialist specialist,
                                                             @PathVariable UUID specialistId) {
            try {
                specialistService.updateSpecialist(specialistId, specialist, multipartFile);
                return new ResponseEntity<>(new ApiResponse(true, "updated", specialist), HttpStatus.OK);
            } catch (IOException ignored) {
            }

            return new ResponseEntity<>(new ApiResponse(false, "error", specialist), HttpStatus.BAD_REQUEST);
        }

        //delete
        @DeleteMapping("/{specialistId}")
        public ResponseEntity<ApiResponse> deleteDistributor(@PathVariable UUID specialistId) {
            try {
                specialistService.deleteSpecialist(specialistId);
                return new ResponseEntity<>(new ApiResponse(true, "deleted", specialistId), HttpStatus.OK);
            } catch (Exception ex) {
                ex.getMessage();
            }

            return new ResponseEntity<>(new ApiResponse(false, "error", specialistId), HttpStatus.BAD_REQUEST);
        }

    }
