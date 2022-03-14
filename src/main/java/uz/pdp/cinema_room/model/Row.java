package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rows")
public class Row {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer number;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    private List<Seat> seatList;

    @ManyToOne
    private Hall hall;
}
