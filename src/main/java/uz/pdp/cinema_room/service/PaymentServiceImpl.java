package uz.pdp.cinema_room.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.dto.StripeResponseDto;
import uz.pdp.cinema_room.dto.TicketDto;
import uz.pdp.cinema_room.model.CashBox;
import uz.pdp.cinema_room.model.PurchaseHistory;
import uz.pdp.cinema_room.model.Ticket;
import uz.pdp.cinema_room.model.TicketStatus;
import uz.pdp.cinema_room.repository.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Value("${STRIPE_SECRET_KEY}")
    String stripeApiKey;

    @Value("${BASE_URL}")
    String baseUrl;

    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    RefundChargeFeeRepository refundChargeFeeRepository;

    @Autowired
    CashBoxRepository cashBoxRepository;

    @Override
    public HttpEntity createStripeSession(List<TicketDto> ticketDtoList) throws StripeException {
        // SUCCESS and FAILURE URLS
        String successURL = baseUrl + "payment/success";
        String failureURL = baseUrl + "payment/failed";

        Stripe.apiKey = stripeApiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        double amount = 0;

        for (TicketDto ticketDto : ticketDtoList) {
            amount += ticketDto.getPrice();
            sessionItemList.add(createSessionLineItem(ticketDto));
        }

        SessionCreateParams.LineItem build = getLineItemForCommissionFee(amount);

        sessionItemList.add(build);

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setClientReferenceId(ticketDtoList.get(0).getUser_id().toString())
                .setCancelUrl(failureURL)
                .setSuccessUrl(successURL)
                .addAllLineItem(sessionItemList)
                .build();

        Session session = Session.create(params);

        StripeResponseDto stripeResponse = new StripeResponseDto(session.getId());
        return ResponseEntity.ok(session.getUrl());
    }

    private SessionCreateParams.LineItem getLineItemForCommissionFee(double amount) {
        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long) ((((amount * 100) + 30) / (0.971)) - amount * 100))
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName("Commission Fee")
                        .build())
                .build();

        SessionCreateParams.LineItem build = SessionCreateParams.LineItem.builder()
                .setPriceData(priceData)
                .setQuantity(1L)
                .build();
        return build;
    }

    private SessionCreateParams.LineItem createSessionLineItem(TicketDto ticketDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(ticketDto))
                .setQuantity(1L)
                .setDescription(ticketDto.getMovieTitle())
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(TicketDto ticketDto) {

        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long) ticketDto.getPrice() * 100)
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(ticketDto.getMovieTitle())
                        .build())
                .build();
    }

    public HttpEntity refundTicket(UUID ticket_id) {


        PurchaseHistory purchaseHistory = purchaseHistoryRepository.findByTicketId(ticket_id);

        String stripePaymentIntent = purchaseHistory.getStripePaymentIntent();

        Ticket ticket = ticketRepository.getTicketBYId(ticket_id);

        LocalDate sessionDate = ticketRepository.getSessionDateForRefundTicket(ticket_id);

        Period period = Period.between(LocalDate.now(), sessionDate);

        List<CashBox> cashBoxList = cashBoxRepository.findAll();

        Double refundSum = refundChargeFeeRepository.getChargeFeeAmount(ticket_id, cashBoxList.get(0).getId());

        RefundCreateParams params = RefundCreateParams
                .builder()
                .setPaymentIntent(stripePaymentIntent)
                .setAmount(refundSum.longValue())
                .build();

        PurchaseHistory purchaseHistory1 = new PurchaseHistory(
                null,
                purchaseHistory.getUser(),
                purchaseHistory.getTicket(),
                purchaseHistory.getPayType(),
                true,
                null
        );

        purchaseHistoryRepository.save(purchaseHistory1);

        ticket.setStatus(TicketStatus.REFUNDED);
        ticketRepository.save(ticket);

        try {
            Refund refund = Refund.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("succeeded");
    }
}
