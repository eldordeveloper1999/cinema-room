package uz.pdp.cinema_room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {

    private UUID reservedHall_id;

    private UUID seat_id;

    private UUID user_id;

    private double price;

    private String movieTitle;


}
