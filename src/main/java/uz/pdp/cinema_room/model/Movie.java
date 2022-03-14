package uz.pdp.cinema_room.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie extends AbsEntity {

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private int durationInMin;

    private double ticketInitPrice;

    @Column(nullable = false)
    private String trailerVideoUrl; // ex. youtube link

    @OneToOne(optional = false)
    private Attachment posterImg;

    @Column(nullable = false)
    private Date releaseDate;

    private Double budget;

    @ManyToOne
    private Distributor distributor;

    @Column(nullable = false)
    private Double distributorShareInPercentage;


    @ManyToMany
    private List<Specialist> specialistList;

    @ManyToMany
    private List<Genre> genres;

}
