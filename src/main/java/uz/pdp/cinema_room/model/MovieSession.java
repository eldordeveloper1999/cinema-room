package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movie_sessions")
@EqualsAndHashCode(callSuper = true)
public class MovieSession extends AbsEntity {

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Hall hall;
}
