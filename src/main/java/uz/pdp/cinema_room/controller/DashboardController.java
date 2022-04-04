package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema_room.service.AdminDashboardService;
import uz.pdp.cinema_room.service.AdminDashboardServiceImpl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    AdminDashboardServiceImpl adminDashboardServiceImpl;

    @GetMapping("/tickets")
    public HttpEntity getTicketsDaily(@RequestParam("day") String dateS) {
        LocalDate date = LocalDate.parse(dateS);
        return ResponseEntity.ok(adminDashboardServiceImpl.getDailyTicketSalesCount(date));
    }
    
    @GetMapping("/tickets/between")
    public HttpEntity getTicketsBetweenTwoDate(@RequestParam("from") String from,
                                        @RequestParam("to") String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return ResponseEntity.ok(adminDashboardServiceImpl.getBetweenTwoDateTicketSalesCount(fromDate, toDate));
    }

    @GetMapping("/income")
    public HttpEntity getIncomeDaily(@RequestParam("day") String dateS) {
        LocalDate date = LocalDate.parse(dateS);
        return ResponseEntity.ok(adminDashboardServiceImpl.getDailyIncome(date));
    }

    @GetMapping("/income/between")
    public HttpEntity getIncomeBetweenTwoDate(@RequestParam("from") String from,
                                        @RequestParam("to") String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return ResponseEntity.ok(adminDashboardServiceImpl.getBetweenTwoDateIncome(fromDate, toDate));
    }

    @GetMapping("/movie_sessions")
    public HttpEntity getSessionsDaily(@RequestParam("day") String dateS) {
        LocalDate date = LocalDate.parse(dateS);
        return ResponseEntity.ok(adminDashboardServiceImpl.getDailyR_HCount(date));
    }

    @GetMapping("/movie_sessions/between")
    public HttpEntity getSessionsBetweenTwoDate(@RequestParam("from") String from, 
                                        @RequestParam("to") String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return ResponseEntity.ok(adminDashboardServiceImpl.getR_HCountBetweenTwoDate(fromDate, toDate));
    }


}
