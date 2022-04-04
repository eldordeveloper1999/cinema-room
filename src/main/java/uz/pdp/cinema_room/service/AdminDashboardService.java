package uz.pdp.cinema_room.service;

import uz.pdp.cinema_room.projections.IncomeProjection;
import uz.pdp.cinema_room.projections.OutcomeProjection;

import java.time.LocalDate;


public interface AdminDashboardService {

    Integer getDailyTicketSalesCount(LocalDate date);

    Integer getBetweenTwoDateTicketSalesCount(LocalDate from, LocalDate to);

    Integer getYearlyTicketSalesCount();

    Integer getDailyR_HCount(LocalDate date);

    Integer getR_HCountBetweenTwoDate(LocalDate fromDate, LocalDate toDate);

    Double getDailyIncome(LocalDate date);

    Double getBetweenTwoDateIncome(LocalDate fromDate, LocalDate toDate);
}
