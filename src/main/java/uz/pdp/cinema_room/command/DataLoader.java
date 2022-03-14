package uz.pdp.cinema_room.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room.model.Hall;
import uz.pdp.cinema_room.model.PriceCategory;
import uz.pdp.cinema_room.model.Row;
import uz.pdp.cinema_room.model.Seat;
import uz.pdp.cinema_room.repository.HallRepository;
import uz.pdp.cinema_room.repository.PriceCategoryRepository;
import uz.pdp.cinema_room.repository.RowRepository;
import uz.pdp.cinema_room.repository.SeatRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    HallRepository hallRepository;

    @Autowired
    PriceCategoryRepository priceCategoryRepository;

    @Autowired
    RowRepository rowRepository;

    @Autowired
    SeatRepository seatRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Hall> hallList = new ArrayList<>(Arrays.asList(
           new Hall("Zal 1", null),
           new Hall("Zal 2", null) ,
           new Hall("Zal 3", null),
           new Hall("VIP", 10.0)
        ));

        hallRepository.saveAll(hallList);

        List<PriceCategory> priceCategories = new ArrayList<>(Arrays.asList(
           new PriceCategory("Category 1", 10.0, 1),
           new PriceCategory("Category 2", 20.0, 2)
        ));

        priceCategoryRepository.saveAll(priceCategories);

        List<Row> rows = new ArrayList<>(Arrays.asList(
                new Row(1, hallList.get(1)),
                new Row(2, hallList.get(1)),
                new Row(3, hallList.get(1)),
                new Row(4, hallList.get(1)),
                new Row(5, hallList.get(1)),
                new Row(6, hallList.get(1)),
                new Row(7, hallList.get(1)),
                new Row(8, hallList.get(1))
        ));

        rowRepository.saveAll(rows);

        List<Seat> seats = new ArrayList<Seat>(Arrays.asList(
           new Seat(1, rows.get(1), priceCategories.get(1)),
           new Seat(2, rows.get(1), priceCategories.get(1)),
           new Seat(3, rows.get(1), priceCategories.get(1)),
           new Seat(4, rows.get(1), priceCategories.get(1)),
           new Seat(1, rows.get(2), priceCategories.get(1)),
           new Seat(2, rows.get(2), priceCategories.get(1)),
           new Seat(3, rows.get(2), priceCategories.get(1)),
           new Seat(4, rows.get(2), priceCategories.get(1)),
           new Seat(1, rows.get(3), priceCategories.get(0)),
           new Seat(2, rows.get(3), priceCategories.get(0)),
           new Seat(3, rows.get(3), priceCategories.get(0)),
           new Seat(4, rows.get(3), priceCategories.get(0)),
           new Seat(1, rows.get(4), priceCategories.get(0)),
           new Seat(2, rows.get(4), priceCategories.get(0)),
           new Seat(3, rows.get(4), priceCategories.get(0)),
           new Seat(4, rows.get(4), priceCategories.get(0))
        ));

       seatRepository.saveAll(seats);
    }
}
