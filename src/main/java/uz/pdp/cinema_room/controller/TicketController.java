package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.model.*;
import uz.pdp.cinema_room.projections.TicketProjection;
import uz.pdp.cinema_room.repository.TicketRepository;
import uz.pdp.cinema_room.service.TicketService;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {


    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/{rh_id}/{seat_id}")
    public ResponseEntity<ApiResponse> getTicket(@PathVariable UUID rh_id,
                                                 @PathVariable UUID seat_id) {
            UUID user_id = UUID.fromString("629a3524-7018-4f58-ad8e-0a1027de5f2c");
        UUID ticket_id = ticketService.saveTicket(user_id, rh_id, seat_id);
        return ResponseEntity.ok(new ApiResponse(true, "success", ticketService.getTicket(ticket_id)));
    }

    @GetMapping("/buy/{ticket_id}")
    public HttpEntity getPdfTicket(@PathVariable UUID ticket_id) {
        Ticket ticket = ticketService.getTicketById(ticket_id);
        String pdfTicketUrl = null;
        try {
            pdfTicketUrl = ticketService.generatePdfTicket(ticket_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TicketStatus status = TicketStatus.getStatusByDisplayStatus("purchased");
        ticket.setStatus(status);
        ticketRepository.save(ticket);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/refund/{ticket_id}")
    public HttpEntity refundTicket(@PathVariable UUID ticket_id) {
        Ticket ticket = ticketService.getTicketById(ticket_id);
        TicketStatus status = TicketStatus.getStatusByDisplayStatus("refunded");
        ticket.setStatus(status);
        ticketRepository.save(ticket);
        return ResponseEntity.ok("success");
    }

}