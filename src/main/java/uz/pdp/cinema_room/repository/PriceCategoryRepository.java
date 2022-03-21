package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinema_room.model.PriceCategory;

import java.util.UUID;

@Repository
public interface PriceCategoryRepository extends JpaRepository<PriceCategory, UUID> {

    @Query(nativeQuery = true, value = "select pg.additional_fee_in_percentage from price_categories pg\n" +
            "join seats s on pg.id = s.price_category_id\n" +
            "where s.id = :id")
    Double getPrice(UUID id);

}
