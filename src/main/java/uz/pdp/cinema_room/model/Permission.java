package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "permissions")
public class Permission extends AbsEntity {

    private String string;
}
