package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbsEntity {

    private String fullName;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;
}
