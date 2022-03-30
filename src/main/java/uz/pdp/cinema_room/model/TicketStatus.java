package uz.pdp.cinema_room.model;

import java.util.Arrays;

public enum TicketStatus {

    NEW("new"),
    PURCHASED("purchased"),
    REFUNDED("refunded");

    String displayStatus;

    TicketStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public static TicketStatus getStatusByDisplayStatus(String displayStatusDto) {

        return Arrays.stream(
                        TicketStatus.values())
                .filter(castType -> castType.displayStatus.equals(displayStatusDto)).findFirst().get();

    }
}
