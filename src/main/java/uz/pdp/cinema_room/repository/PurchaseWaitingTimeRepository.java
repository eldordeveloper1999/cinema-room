package uz.pdp.cinema_room.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinema_room.model.PurchaseWaitingTime;

import java.util.UUID;

public interface PurchaseWaitingTimeRepository extends JpaRepository<PurchaseWaitingTime, UUID> {
    @Query(nativeQuery = true, value = "select time_in_min from purchase_waiting_times")
    Integer getWaitingMinute();
}
