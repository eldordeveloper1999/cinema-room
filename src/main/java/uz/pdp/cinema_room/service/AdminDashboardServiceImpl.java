package uz.pdp.cinema_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinema_room.projections.IncomeProjection;
import uz.pdp.cinema_room.projections.OutcomeProjection;
import uz.pdp.cinema_room.repository.TicketRepository;

import java.time.LocalDate;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Integer getDailyTicketSalesCount(LocalDate date) {
        return ticketRepository.getTicketsByDate(date);
    }

    @Override
    public Integer getBetweenTwoDateTicketSalesCount(LocalDate from, LocalDate to) {
        return ticketRepository.getTicketsBetweenTwoDate(from, to);

    }

    @Override
    public Integer getYearlyTicketSalesCount() {
        return null;
    }

    @Override
    public Integer getDailyR_HCount(LocalDate date) {
        return ticketRepository.getDailyR_HCount(date);
    }

    @Override
    public Integer getR_HCountBetweenTwoDate(LocalDate fromDate, LocalDate toDate) {
        return
                ticketRepository.getR_HCountBetweenTwoDate(fromDate, toDate);
    }

    @Override
    public Double getDailyIncome(LocalDate date) {
        return ticketRepository.getDailyIncome(date);
    }

    @Override
    public Double getBetweenTwoDateIncome(LocalDate fromDate, LocalDate toDate) {
        return ticketRepository.getBetweenTwoDateIncome(fromDate, toDate);
    }
}
