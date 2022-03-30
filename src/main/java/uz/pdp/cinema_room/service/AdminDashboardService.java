package uz.pdp.cinema_room.service;

import uz.pdp.cinema_room.projections.IncomeProjection;
import uz.pdp.cinema_room.projections.OutcomeProjection;

import java.time.LocalDate;


public interface AdminDashboardService {

    Double getOutcomes();

    Double getIncomes();

    Integer getDailyTicketSalesCount(LocalDate date);

    Integer getMonthlyTicketSalesCount();

    Integer getYearlyTicketSalesCount();

    Integer getDailyR_HCount();

    Integer getMonthlyR_HCount();
}
