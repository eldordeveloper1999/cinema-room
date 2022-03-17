package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "session_times")
@EqualsAndHashCode(callSuper = true)
public class SessionTime extends AbsEntity {

    private LocalTime time;

    private Double session_additional_fee_in_percent;
}
