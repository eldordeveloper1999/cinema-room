package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "seats")
public class Seat extends AbsEntity {

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    private Row row;

    @ManyToOne
    private PriceCategory priceCategory;

    public Seat(Integer number, PriceCategory priceCategory) {
        this.number = number;
        this.priceCategory = priceCategory;
    }

}
