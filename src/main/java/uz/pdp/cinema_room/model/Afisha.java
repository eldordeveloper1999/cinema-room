package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "afishalar")
@EqualsAndHashCode(callSuper = true)
public class Afisha extends AbsEntity {

    @ManyToOne
    private Movie movie;

    private Boolean is_active;
}
