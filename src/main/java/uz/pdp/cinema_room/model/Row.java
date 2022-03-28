package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rows")
public class Row extends AbsEntity {

    @Column(nullable = false)
    private Integer number;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @ManyToOne
    private Hall hall;

    public Row(Integer number, Hall hall) {
        this.number = number;
        this.hall = hall;
    }

    @Override
    public String toString() {
        return "Row{" +
                "number=" + number +
                ", hall=" + hall +
                '}';
    }
}
