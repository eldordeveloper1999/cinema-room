package uz.pdp.cinema_room.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "specialists")
@EqualsAndHashCode(callSuper = true)
public class Specialist extends AbsEntity {

    private String fullName;

    @OneToOne
    private Attachment photo;

    @Enumerated(EnumType.STRING)
    private SpecialistType castType;

}
