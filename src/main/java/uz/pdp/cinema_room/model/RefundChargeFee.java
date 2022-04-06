package uz.pdp.cinema_room.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "refund_charge_fees")
public class RefundChargeFee {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Integer interval_in_minutes;

    private Double charge_fee_in_percentage;

    public RefundChargeFee(Integer interval_in_minutes, Double charge_fee_in_percentage) {
        this.interval_in_minutes = interval_in_minutes;
        this.charge_fee_in_percentage = charge_fee_in_percentage;
    }
}
