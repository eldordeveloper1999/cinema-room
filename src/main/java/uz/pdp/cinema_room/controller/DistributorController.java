package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.model.Distributor;
import uz.pdp.cinema_room.service.DistributorService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/distributors")
public class DistributorController {

    @Autowired
    DistributorService distributorService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveDistributor(@RequestPart("attachment") MultipartFile multipartFile,

                                                       @RequestPart("json") Distributor distributor) {
        try {
            distributorService.saveOrUpdateDistributor(null, distributor, multipartFile);
            return new ResponseEntity<>(new ApiResponse(true, "created", distributor), HttpStatus.OK);
        } catch (IOException ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", distributor), HttpStatus.BAD_REQUEST);
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllDistributors() {
        return new ResponseEntity<>(new ApiResponse(true, "success", distributorService.getAllDistributors()), HttpStatus.OK);
    }

    @PutMapping("/{distributorId}")
    public ResponseEntity<ApiResponse> updateDistributor(@RequestPart("attachment") MultipartFile multipartFile,
                                                         @RequestPart("json") Distributor distributor,
                                                         @PathVariable UUID distributorId) {
        try {
            distributorService.saveOrUpdateDistributor(distributorId, distributor, multipartFile);
            return new ResponseEntity<>(new ApiResponse(true, "updated", distributor), HttpStatus.OK);
        } catch (IOException ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", distributor), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{distributorId}")
    public ResponseEntity<ApiResponse> deleteDistributor(@PathVariable UUID distributorId) {
        try {
            distributorService.deleteDistributor(distributorId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", distributorId), HttpStatus.OK);
        } catch (Exception ignored) {
            ignored.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", distributorId), HttpStatus.BAD_REQUEST);
    }


}
