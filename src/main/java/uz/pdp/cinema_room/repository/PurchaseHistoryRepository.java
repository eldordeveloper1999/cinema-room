package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.PurchaseHistory;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, UUID> {

    @Query(nativeQuery = true, value = "select *\n" +
            "from purchase_histories\n" +
            "where ticket_id = :ticket_id")
    PurchaseHistory findByTicketId(UUID ticket_id);
}
