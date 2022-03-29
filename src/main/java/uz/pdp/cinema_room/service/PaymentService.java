package uz.pdp.cinema_room.service;

import com.stripe.exception.StripeException;
import org.springframework.http.HttpEntity;
import uz.pdp.cinema_room.dto.TicketDto;

import java.util.List;

public interface PaymentService {
    HttpEntity createStripeSession(TicketDto ticketDto) throws StripeException;
}
