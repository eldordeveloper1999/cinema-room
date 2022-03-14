package uz.pdp.cinema_room.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "price_categories")
public class PriceCategory extends AbsEntity {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Double additional_fee_in_percentage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceCategory")
    private List<Seat> seats;

    private String color;

    public PriceCategory(String name, Double additional_fee_in_percentage, String color) {
        this.name = name;
        this.additional_fee_in_percentage = additional_fee_in_percentage;
        this.color = color;
    }

}
