package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.PayType;

import java.util.UUID;

@Repository
public interface PayTypeRepository extends JpaRepository<PayType, UUID> {
    @Query(nativeQuery = true, value = "select * from pay_types where name = :stripe")
    PayType findByName(String stripe);
}
