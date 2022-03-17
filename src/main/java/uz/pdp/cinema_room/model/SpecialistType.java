package uz.pdp.cinema_room.model;

import java.util.Arrays;
import java.util.Optional;

public enum SpecialistType {

    SPECIALIST_ACTOR("Aktyor"),
    SPECIALIST_DIRECTOR("Rejissyor");

    String displayName;

    SpecialistType(String displayName) {
        this.displayName = displayName;
    }

    public static SpecialistType getSpecialistByDisplayName(String displayNameDto) {

        return Arrays.stream(
                        SpecialistType.values())
                .filter(castType -> castType.displayName.equals(displayNameDto)).findFirst().get();


    }
}
