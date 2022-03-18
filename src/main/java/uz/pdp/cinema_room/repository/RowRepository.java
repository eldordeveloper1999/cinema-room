package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.Row;
import uz.pdp.cinema_room.projections.RowProjection;

import java.util.List;
import java.util.UUID;

public interface RowRepository extends JpaRepository<Row, UUID> {


    @Query(nativeQuery = true, value = "select cast(r.id as varchar) as id, " +
            "r.number as number from \"rows\" r\n" +
            "join seats s on r.id = s.row_id\n" +
            "where s.id = :id")
    List<RowProjection> findBySeatId(UUID id);
}
