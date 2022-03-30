package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room.service.AdminDashboardService;
import uz.pdp.cinema_room.service.AdminDashboardServiceImpl;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    AdminDashboardServiceImpl adminDashboardService;

    @GetMapping("/tickets/{dateS}")
    public HttpEntity getTickets(@PathVariable String dateS) {
        LocalDate date = LocalDate.parse(dateS);
        return ResponseEntity.ok(adminDashboardService.getDailyTicketSalesCount(date));
    }
}
