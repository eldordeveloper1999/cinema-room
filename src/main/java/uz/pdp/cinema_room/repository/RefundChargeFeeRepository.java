package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.RefundChargeFee;

import java.util.UUID;

@Repository
public interface RefundChargeFeeRepository extends JpaRepository<RefundChargeFee, UUID> {

    @Query(nativeQuery = true, value = "select refund_checked_balance_calculate(:ticket_id, :cash_box_id)")
    Double getChargeFeeAmount(UUID ticket_id, UUID cash_box_id);
}
