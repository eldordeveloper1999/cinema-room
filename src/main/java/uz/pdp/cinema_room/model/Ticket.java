package uz.pdp.cinema_room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import uz.pdp.cinema_room.entity.AbsEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tickets")
public class Ticket {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @ManyToOne
    private ReservedHall reservedHall;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Attachment qr_code;

    private Double price;

    @ManyToOne
    private Cart cart;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

}
