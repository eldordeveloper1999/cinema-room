package uz.pdp.cinema_room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservedHallDto {

    private UUID hall_id;
    private UUID afisha_id;
    private UUID sessionDate_id;
    private UUID start_time_id;
    private UUID end_time_id;
}
