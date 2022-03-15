package uz.pdp.cinema_room.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room.model.*;
import uz.pdp.cinema_room.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.time.LocalTime.now;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    PriceCategoryRepository priceCategoryRepository;

    @Autowired
    RowRepository rowRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    DistributorRepository distributorRepository;

    @Autowired
    SpecialistRepository specialistRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieRepository movieRepository;

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
           new PriceCategory("Category 1", 10.0, ""),
           new PriceCategory("Category 2", 20.0, "")
        ));


        priceCategoryRepository.saveAll(priceCategories);
        List<Genre> genres = new ArrayList<>(Arrays.asList(
                new Genre("War"),
                new Genre("Fantastic")
        ));

        genreRepository.saveAll(genres);

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

        List<Attachment> all = attachmentRepository.findAll();

        List<Distributor> distributors = distributorRepository.findAll();

        List<Specialist> specialistList = specialistRepository.findAll();

        List<Genre> genreList = genreRepository.findAll();


        List<Movie> movies = new ArrayList<>(Arrays.asList(
            new Movie("New Title", "Some Description", 120, 50.0, "", all.get(0), new Date(), 500.0, distributors.get(0), 60.0, specialistList, genreList)
       ));

        movieRepository.saveAll(movies);

        List<MovieSession> movieSessionList = new ArrayList<>(Arrays.asList(
                new MovieSession(movies.get(0), hallList.get(1))
        ));

        movieSessionRepository.saveAll(movieSessionList);
    }
}
