package uz.pdp.cinema_room.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "price_categories")
public class PriceCategory extends AbsEntity {

    private String name;

    private Double additional_fee_in_percentage;

    private Integer color;
}
