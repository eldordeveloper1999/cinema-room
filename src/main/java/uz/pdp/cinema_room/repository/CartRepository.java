package uz.pdp.cinema_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinema_room.model.Cart;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

}
