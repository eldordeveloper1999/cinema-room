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
    public Double getOutcomes() {
        return null;
    }

    @Override
    public Double getIncomes() {
        return null;
    }

    @Override
    public Integer getDailyTicketSalesCount(LocalDate date) {
        return ticketRepository.getTicketsByDate(date);
    }

    @Override
    public Integer getMonthlyTicketSalesCount() {
        return null;
    }

    @Override
    public Integer getYearlyTicketSalesCount() {
        return null;
    }

    @Override
    public Integer getDailyR_HCount() {
        return null;
    }

    @Override
    public Integer getMonthlyR_HCount() {
        return null;
    }
}
