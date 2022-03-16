package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserved_halls")
@EqualsAndHashCode(callSuper = true)
public class ReservedHall extends AbsEntity {

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private SessionDate sessionDate;

    @ManyToOne
    private SessionTime start_time;

    @ManyToOne
    private SessionTime end_time;

    @ManyToOne
    private Afisha afisha;
}
