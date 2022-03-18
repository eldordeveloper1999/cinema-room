package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.Hall;
import uz.pdp.cinema_room.projections.HallProjection;
import uz.pdp.cinema_room.projections.Projection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HallRepository extends JpaRepository<Hall, UUID> {
    Optional<Hall> findById(UUID id);

    @Query(nativeQuery = true, value = "select cast(h.id as varchar) as id, h.name as name, " +
            "h.vip_additional_fee_in_percent as vip_additional_fee_in_percent from halls h\n" +
            "join reserved_halls rh on h.id = rh.hall_id\n" +
            "join afishalar a on a.id = rh.afisha_id\n" +
            "where a.id = :afishaId")
    List<HallProjection>findByAfishaId(UUID afishaId);

    @Query(nativeQuery = true, value = "select * from halls where id = :id")
    Hall findByID(UUID id);

    @Query(nativeQuery = true, value = "select cast(h.id as varchar) as id, h.name, h.vip_additional_fee_in_percent\n" +
            "from halls h\n" +
            "         join \"rows\" r on h.id = r.hall_id\n" +
            "join seats s on r.id = s.row_id\n" +
            "where s.id = :id")
    List<HallProjection> findBySeatId(UUID id);

}
