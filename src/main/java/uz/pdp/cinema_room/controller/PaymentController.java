package uz.pdp.cinema_room.controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room.dto.TicketDto;
import uz.pdp.cinema_room.model.Ticket;
import uz.pdp.cinema_room.repository.TicketRepository;
import uz.pdp.cinema_room.service.PaymentServiceImpl;
import uz.pdp.cinema_room.service.TicketService;

import java.util.UUID;

@RestController
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @PostMapping("/create-checkout-session/{ticket_id}")
    public HttpEntity getCreateCheckoutSession(@PathVariable UUID ticket_id) {
        Ticket ticketBYId = ticketRepository.getTicketBYId(ticket_id);

        TicketDto ticketDto = getTicketDto(ticket_id, ticketBYId);

        try {
            return paymentService.createStripeSession(ticketDto);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.EMPTY;
    }

    private TicketDto getTicketDto(UUID ticket_id, Ticket ticketBYId) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setPrice(ticketBYId.getPrice());
        ticketDto.setReservedHall_id(ticketBYId.getReservedHall().getId());
        ticketDto.setSeat_id(ticketBYId.getSeat().getId());
        ticketDto.setMovieTitle(ticketRepository.getMovieTitle(ticket_id));
        ticketDto.setUser_id(ticketRepository.getUserId(ticket_id));
        return ticketDto;
    }

    @RequestMapping(value = "/payment/success")
    public HttpEntity getSuccessMessage() {
        return ResponseEntity.ok("payment successfully");
    }

    @RequestMapping(value = "/payment/cancel")
    public HttpEntity getCancelMessage() {
        return ResponseEntity.ok("payment successfully");
    }

}
