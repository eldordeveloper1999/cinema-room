package uz.pdp.cinema_room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinema_room.api_response.ApiResponse;
import uz.pdp.cinema_room.model.Cart;
import uz.pdp.cinema_room.model.SpecialistType;
import uz.pdp.cinema_room.model.Ticket;
import uz.pdp.cinema_room.model.TicketStatus;
import uz.pdp.cinema_room.repository.*;
import uz.pdp.cinema_room.service.ReservedHallService;
import uz.pdp.cinema_room.service.SeatService;
import uz.pdp.cinema_room.service.TicketService;
import uz.pdp.cinema_room.service.UserService;

import javax.xml.ws.Action;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {


    @Autowired
    TicketService ticketService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Autowired
    ReservedHallService reservedHallService;

    @Autowired
    SeatService seatService;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    PriceCategoryRepository priceCategoryRepository;

    @GetMapping("/{rh_id}/{seat_id}/{user_id}")
    public HttpEntity getTicket(@PathVariable UUID rh_id,
                                @PathVariable UUID seat_id,
                                @PathVariable UUID user_id){
        Cart cart = new Cart(null, userService.getUserById(user_id));
        cartRepository.save(cart);

        TicketStatus status = TicketStatus.getStatusByDisplayStatus("new");

        Double initPrice = movieRepository.getPriceByRHId(rh_id);

        Double priceSeat = priceCategoryRepository.getPrice(seat_id);

        Double hallFee;

        if(hallRepository.getFee(seat_id)!= null) {
            hallFee = hallRepository.getFee(seat_id);
        } else {
            hallFee = 1.0;
        }

        Double price = hallFee * initPrice + initPrice * priceSeat;

        Ticket ticket = new Ticket();
        ticket.setReservedHall(reservedHallService.getReservedHallById(rh_id));
        ticket.setCart(cart);
        ticket.setSeat(seatService.getSeatById(seat_id));
        ticket.setStatus(status);
        ticket.setPrice(price);
        ticketService.saveTicket(ticket);

        return ResponseEntity.ok("success");
    }
}
