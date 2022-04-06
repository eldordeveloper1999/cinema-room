package uz.pdp.cinema_room.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.cinema_room.model.*;
import uz.pdp.cinema_room.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static java.time.LocalTime.now;


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

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    PurchaseWaitingTimeRepository purchaseWaitingTimeRepository;

    @Autowired
    DistributorRepository distributorRepository;

    @Autowired
    SpecialistRepository specialistRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    SessionTimeRepository sessionTimeRepository;

    @Autowired
    SessionDateRepository sessionDateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PayTypeRepository payTypeRepository;

    @Autowired
    RefundChargeFeeRepository refundChargeFeeRepository;

    @Autowired
    CashBoxRepository cashBoxRepository;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
//        List<Hall> hallList = new ArrayList<>(Arrays.asList(
//                new Hall("Zal 1", null),
//                new Hall("Zal 2", null),
//                new Hall("Zal 3", null),
//                new Hall("VIP", 10.0)
//        ));
//
//        hallRepository.saveAll(hallList);
//
//        List<PriceCategory> priceCategories = new ArrayList<>(Arrays.asList(
//                new PriceCategory("Category 1", 10.0, ""),
//                new PriceCategory("Category 2", 20.0, "")
//        ));
//
//
//        priceCategoryRepository.saveAll(priceCategories);
//        List<Genre> genres = new ArrayList<>(Arrays.asList(
//                new Genre("War"),
//                new Genre("Fantastic")
//        ));
//
//        genreRepository.saveAll(genres);
//
//        List<Row> rows = new ArrayList<>(Arrays.asList(
//                new Row(1, hallList.get(1)),
//                new Row(2, hallList.get(1)),
//                new Row(3, hallList.get(1)),
//                new Row(4, hallList.get(1)),
//                new Row(5, hallList.get(1)),
//                new Row(6, hallList.get(1)),
//                new Row(7, hallList.get(1)),
//                new Row(8, hallList.get(1))
//        ));
//
//        rowRepository.saveAll(rows);
//
//        List<Seat> seats = new ArrayList<Seat>(Arrays.asList(
//                new Seat(1, rows.get(1), priceCategories.get(1)),
//                new Seat(2, rows.get(1), priceCategories.get(1)),
//                new Seat(3, rows.get(1), priceCategories.get(1)),
//                new Seat(4, rows.get(1), priceCategories.get(1)),
//                new Seat(1, rows.get(2), priceCategories.get(1)),
//                new Seat(2, rows.get(2), priceCategories.get(1)),
//                new Seat(3, rows.get(2), priceCategories.get(1)),
//                new Seat(4, rows.get(2), priceCategories.get(1)),
//                new Seat(1, rows.get(3), priceCategories.get(0)),
//                new Seat(2, rows.get(3), priceCategories.get(0)),
//                new Seat(3, rows.get(3), priceCategories.get(0)),
//                new Seat(4, rows.get(3), priceCategories.get(0)),
//                new Seat(1, rows.get(4), priceCategories.get(0)),
//                new Seat(2, rows.get(4), priceCategories.get(0)),
//                new Seat(3, rows.get(4), priceCategories.get(0)),
//                new Seat(4, rows.get(4), priceCategories.get(0))
//        ));
//
//        seatRepository.saveAll(seats);

//        List<RefundChargeFee> refundChargeFeeList = new ArrayList<>(Arrays.asList(
//           new RefundChargeFee(10000, 30.0),
//           new RefundChargeFee(30000, 20.0),
//           new RefundChargeFee(50000, 10.0)
//        ));
//
//        refundChargeFeeRepository.saveAll(refundChargeFeeList);

//           cashBoxRepository.save(new CashBox("Primary", 0.0));

        List<Attachment> all = attachmentRepository.findAll();

        List<Distributor> distributors = distributorRepository.findAll();

        List<Specialist> specialistList = specialistRepository.findAll();

        List<Genre> genreList = genreRepository.findAll();

//
//        Role role_admin = roleRepo.save(new Role("ROLE_ADMIN"));
//        Role role_user = roleRepo.save(new Role("ROLE_USER"));
//
//        Permission permission_1 = permissionRepository.save(new Permission("can_add_movie"));
//        Permission permission_2 = permissionRepository.save(new Permission("can_delete_movie"));
//
//
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(role_admin);
//        roles.add(role_user);
//
//        Set<Role> roles_user = new HashSet<>();
//        roles_user.add(role_user);
//
//        Set<Permission> user_permission = new HashSet<>();
//        user_permission.add(permission_1);
//
//        Set<Permission> admin_permission = new HashSet<>();
//        admin_permission.add(permission_2);
//        admin_permission.add(permission_1);
//
//        userRepository.save(
//                new User("Eldor Choriyev",
//                        "eldor",
//                        passwordEncoder.encode("1"),
//                        "ch.eldor1999@gmail.com",
//                        "+998946250119",
//                        roles,
//                        user_permission
//                )
//        );
//
//        userRepository.save(
//                new User("Max Alberto",
//                        "max",
//                        passwordEncoder.encode("1"),
//                        "max@gmail.com",
//                        "+99856787654",
//                        roles_user,
//                        user_permission
//                        )
//        );
//


//        List<SessionTime> sessionTimes = new ArrayList<>(Arrays.asList(
//                new SessionTime(LocalTime.of(22, 00, 00), 10.0)
//        ));
//
//        sessionTimeRepository.saveAll(sessionTimes);

//        List<SessionDate> sessionDates = new ArrayList<>(Arrays.asList(
//                new SessionDate(LocalDate.of(2022, 03, 21))
//        ));
//
//        sessionDateRepository.saveAll(sessionDates);

//        User user = new User("Mark Twin", "mark", "1", "mark1@gmail.com", "+62266824");
//
//        userRepository.save(user);

//        PurchaseWaitingTime purchaseWaitingTime = new PurchaseWaitingTime(30);
//
//        purchaseWaitingTimeRepository.save(purchaseWaitingTime);

//        PayType payType = new PayType(null, "Stripe", null);
//
//        payTypeRepository.save(payType);
    }


}