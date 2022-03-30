package uz.pdp.cinema_room.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinema_room.dto.TicketDto;
import uz.pdp.cinema_room.model.*;
import uz.pdp.cinema_room.repository.PayTypeRepository;
import uz.pdp.cinema_room.repository.PurchaseHistoryRepository;
import uz.pdp.cinema_room.repository.TicketRepository;
import uz.pdp.cinema_room.repository.UserRepository;
import uz.pdp.cinema_room.service.PaymentServiceImpl;
import uz.pdp.cinema_room.service.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;

    @Autowired
    PayTypeRepository payTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${STRIPE_SECRET_KEY}")
    String stripeApiKey;

    private final String endpointSecret = "whsec_7ff3b46b29bf0ec53804d240f2dd205956f1647a5efbfd8053b106972359a248";

    @PostMapping("/create-checkout-session")
    public HttpEntity getCreateCheckoutSession() {

        List<Ticket> allByUserId = ticketRepository.findAllByUserId(UUID.fromString("629a3524-7018-4f58-ad8e-0a1027de5f2c"));
        
        List<TicketDto> ticketDtoList = getTicketDtos(allByUserId);

        try {
            return paymentService.createStripeSession(ticketDtoList);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.EMPTY;
    }

    private List<TicketDto> getTicketDtos(List<Ticket> ticketBYId) {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        for (Ticket ticket : ticketBYId) {
            TicketDto ticketDto = new TicketDto();
            ticketDto.setPrice(ticket.getPrice());
            ticketDto.setReservedHall_id(ticket.getReservedHall().getId());
            ticketDto.setSeat_id(ticket.getSeat().getId());
            ticketDto.setMovieTitle(ticketRepository.getMovieTitle(ticket.getId()));
            ticketDto.setUser_id(ticketRepository.getUserId(ticket.getId()));
            ticketDtoList.add(ticketDto);
        }
        return ticketDtoList;
    }

    @RequestMapping(value = "/payment/success")
    public HttpEntity getSuccessMessage() {
        return ResponseEntity.ok("payment successfully");
    }

    @RequestMapping(value = "/payment/cancel")
    public HttpEntity getCancelMessage() {
        return ResponseEntity.ok("payment successfully");
    }

        @RequestMapping(value = "stripe-webhook", method = RequestMethod.POST)
    public Object handle(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Stripe.apiKey = stripeApiKey;

        System.out.println("Got payload: " + payload);

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Handle the checkout.session.completed event
        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();

            // Fulfill the purchase...
            fulfillOrder(session);
        }
        return "";
    }

    public void fulfillOrder(Session session) {

        User user = userRepository.findById(UUID.fromString(session.getClientReferenceId())).get();
        PayType payType = payTypeRepository.findByName("Stripe");
        List<Ticket> allByUserId = ticketRepository.findAllByUserId(UUID.fromString(session.getClientReferenceId()));
        for (Ticket ticket : allByUserId) {
            TicketStatus status = TicketStatus.getStatusByDisplayStatus("purchased");
            ticket.setStatus(status);
            ticketRepository.save(ticket);
            PurchaseHistory purchaseHistory = new PurchaseHistory(
                    null,
                    user,
                    ticket,
                    payType
            );
            purchaseHistoryRepository.save(purchaseHistory);
        }
        // TODO: 3/29/2022 TICKET STATUSLARINI PURCHASEDGA
        //  UZGARTIRISH VA PURCHASED BULGAN TICKETLARNI
        //  PURCHASE HISTORYGA YOZISH

        System.out.println("Current User ID: " + session.getClientReferenceId());
    }

}